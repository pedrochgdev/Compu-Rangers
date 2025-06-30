using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.Optimization;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;
using Web.Encriptacion;

namespace Web
{
    public partial class SiteMaster : MasterPage
    {
        private readonly AuthWSClient authWS;
        private readonly UsuarioWSClient userWS;
        private readonly ClienteWSClient clientWS;
        private readonly InventoryWSClient invWS;
        private readonly CarritoWSClient carritoWSClient;
        private readonly ItemCarritoWSClient icWS;
        private readonly LogWSClient logWS;
        private carrito shoppingcart;
        private ClientScriptManager clientScriptManager;
        public bool EsAdmin
        {
            get
            {
                return Session["isAdmin"] != null && (bool)Session["isAdmin"];
            }
        }

        public carrito ShoppingCart
        {
            get
            {
                if (shoppingcart == null && Session["user"] != null)
                {
                    int userId = Convert.ToInt32(Session["user"]);
                    shoppingcart = carritoWSClient.getCarritoFromUser(userId);
                }
                return shoppingcart;
            }
            set
            {
                shoppingcart = value;
            }
        }
        public string TotalCarritoFormatted
        {
            get
            {
                if (ShoppingCart == null || ShoppingCart.items == null) return "0.00";
                double total = ShoppingCart.items.Sum(i => i.subtotal);
                return total.ToString("C2");
            }
        }
        public SiteMaster()
        {
            this.authWS = new AuthWSClient();
            this.userWS = new UsuarioWSClient();
            this.clientWS = new ClienteWSClient();
            this.invWS = new InventoryWSClient();
            this.icWS = new ItemCarritoWSClient();
            this.carritoWSClient = new CarritoWSClient();
            this.logWS = new LogWSClient();
        }
        protected void btnActualizarCantidad_Command(object sender, CommandEventArgs e)
        {
            string[] args = e.CommandArgument.ToString().Split(';');
            int itemId = Convert.ToInt32(args[0]);
            int delta = Convert.ToInt32(args[1]);

            itemCarrito item = ShoppingCart.items.FirstOrDefault(i => i.id == itemId);
            if (item != null)
            {
                int nuevaCantidad = item.cantidad + delta;

                // Aquí validamos contra la cantidad disponible en inventario
                int cantidadDisponible = invWS.getCantidadTotalDisponible(item.producto.id); // Suponiendo que tengas este método

                if (nuevaCantidad > cantidadDisponible)
                {
                    string scriptStock = "window.onload = function() { showAlert('Stock insuficiente para este producto', 'warning'); };";
                    clientScriptManager.RegisterStartupScript(GetType(), "", scriptStock, true);
                    return;
                }

                if (nuevaCantidad <= 0)
                {
                    icWS.deleteItem(itemId);
                }
                else
                {
                    item.cantidad = nuevaCantidad;
                    item.subtotal = item.producto.precioVenta * nuevaCantidad;
                    icWS.updateItem(item);
                }
            }

            CargarCarrito();

            string script = "window.onload = function() { showModal('carritoModal'); };";
            clientScriptManager.RegisterStartupScript(GetType(), "", script, true);
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            // Login automático desde cookie
            if (Session["user"] == null && Request.Cookies["RecordarUsuario"] != null)
            {
                try
                {
                    string email = CryptoUtil.Decrypt(Request.Cookies["RecordarUsuario"]["email"]);
                    string password = CryptoUtil.Decrypt(Request.Cookies["RecordarUsuario"]["pass"]);

                    int id = authWS.login(email, password);

                    if (id > 0)
                    {
                        Session["user"] = id;

                        FormsAuthenticationTicket tkt = new FormsAuthenticationTicket(
                            1, email, DateTime.Now,
                            DateTime.Now.AddMinutes(30), true, "datos adicionales");

                        string cookiestr = FormsAuthentication.Encrypt(tkt);
                        HttpCookie ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr)
                        {
                            Expires = tkt.Expiration,
                            Path = FormsAuthentication.FormsCookiePath
                        };
                        Response.Cookies.Add(ck);
                        string strRedirect = userWS.getRole(id) ? "../Admin/Ventas.aspx" : "../Catalogo/Home.aspx";
                        Response.Redirect(strRedirect + "?msg=login-success", true);
                    }
                    else
                    {
                        // Login fallido: eliminar cookie corrupta
                        Response.Cookies["RecordarUsuario"].Expires = DateTime.Now.AddDays(-1);
                    }
                }
                catch
                {
                    // Error al desencriptar o valores corruptos
                    Response.Cookies["RecordarUsuario"].Expires = DateTime.Now.AddDays(-1);
                }
            }

            this.clientScriptManager = Page.ClientScript;
        }


        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                MostrarMensajeDesdeQuery();
                ControlarEstadoSesion();

                // === NUEVO: Cargar cookies si existen ===
                HttpCookie userCookie = Request.Cookies["RecordarUsuario"];
                if (userCookie != null)
                {
                    txtEmail.Text = userCookie["email"];
                    txtPassword.Attributes["value"] = userCookie["pass"];
                }
            }
        }


        private void MostrarMensajeDesdeQuery()
        {
            string mensaje = Request.QueryString["msg"];
            if (string.IsNullOrEmpty(mensaje)) return;

            string tipo = "info"; // valor por defecto
            string texto = "";

            switch (mensaje)
            {
                case "login-success":
                    tipo = "success";
                    texto = "Inicio de sesión exitoso.";
                    break;
                case "logout-true":
                    tipo = "info";
                    texto = "Sesión cerrada correctamente.";
                    break;
                case "reset_success":
                    tipo = "success";
                    texto = "Contraseña restablecida exitosamente.";
                    break;
                case "reset_error":
                    tipo = "danger";
                    texto = "Error al restablecer la contraseña.";
                    break;
                case "expired":
                    tipo = "warning";
                    texto = "El enlace de recuperación ha expirado.";
                    break;
                default:
                    return;
            }

            clientScriptManager.RegisterStartupScript(this.GetType(), "",
                $"window.onload = function() {{ showAlert('{texto}', '{tipo}'); }};", true);
        }

        private void ControlarEstadoSesion()
        {
            if (hfLoginStatus.Value == "failed")
            {
                ScriptManager.RegisterStartupScript(this, GetType(), "ShowLoginModal", "$('#form-modal-login').modal('show');", true);
            }

            bool loggedIn = Session["user"] != null;
            phUserLogged.Visible = loggedIn;
            phUserNotLogged.Visible = !loggedIn;
            phTituloTexto.Visible = false;
            phLinkTitulo.Visible = true;
            if (loggedIn)
            {

                bool isadmin = userWS.getRole(Convert.ToInt32(Session["user"]));
                Session["isAdmin"] = isadmin;
                if (!isadmin)
                {
                    CargarCarrito();
                }
                else
                {
                    adminLogged.Visible = true;
                    adminNotLogged.Visible = false;
                    phTituloTexto.Visible = true;
                    phLinkTitulo.Visible = false;
                }
            }
        }

        protected void btnLogOut(object sender, EventArgs e)
        {
            // Limpiar sesión
            Session.Clear();
            FormsAuthentication.SignOut();

            // Eliminar cookie de "recordarme"
            if (Request.Cookies["RecordarUsuario"] != null)
            {
                HttpCookie cookie = new HttpCookie("RecordarUsuario");
                cookie.Expires = DateTime.Now.AddDays(-1); // Fecha pasada = eliminación
                Response.Cookies.Add(cookie);
            }

            Response.Redirect("~/Catalogo/Home.aspx?msg=logout-true");
        }


        protected void bttnCarrito_Show(object sender, EventArgs e)
        {
            string script;
            if (Session["user"] == null)

                script = "window.onload = function() { showModal('form-modal-login');};";
            else
            {

                script = "window.onload = function() { showModal('carritoModal');};";
            }
            clientScriptManager.RegisterStartupScript(GetType(), "", script, true);
        }
        protected void btnIniciarLogin(object sender, EventArgs e)
        {
            string password = txtPassword.Text;
            string usuario = txtEmail.Text;

            string patronInyeccion = @"['"";#--]";
            if (System.Text.RegularExpressions.Regex.IsMatch(usuario, patronInyeccion) ||
                System.Text.RegularExpressions.Regex.IsMatch(password, patronInyeccion))
            {
                hfLoginStatus.Value = "failed";
                lblLoginFeedback.Text = "Usuario o contraseña incorrectos.";
                return;
            }

            int id = authWS.login(usuario, password);

            if (id > 0)
            {
                Session["user"] = id;

                // === NUEVO: Recordarme ===
                bool recordar = Request.Form["remember"] == "on";

                if (recordar)
                {
                    HttpCookie userCookie = new HttpCookie("RecordarUsuario");
                    userCookie["email"] = CryptoUtil.Encrypt(usuario);
                    userCookie["pass"] = CryptoUtil.Encrypt(password);
                    userCookie.Expires = DateTime.Now.AddDays(15);
                    Response.Cookies.Add(userCookie);
                }

                // Login con autenticación estándar
                FormsAuthenticationTicket tkt = new FormsAuthenticationTicket(
                    1, usuario, DateTime.Now,
                    DateTime.Now.AddMinutes(30), recordar, "datos adicionales");

                string cookiestr = FormsAuthentication.Encrypt(tkt);
                HttpCookie ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr)
                {
                    Expires = tkt.Expiration,
                    Path = FormsAuthentication.FormsCookiePath
                };
                Response.Cookies.Add(ck);

                string strRedirect;
                if (userWS.getRole(id)) {
                    strRedirect="../Admin/Ventas.aspx";
                    log line = new log
                    {
                        usuarioId = id,
                        accion = "Inicio de sesión exitoso",
                    };
                    logWS.addLog(line);
                    bool isadmin = userWS.getRole(Convert.ToInt32(Session["user"]));
                    Session["isAdmin"] = isadmin;
                } else {
                    strRedirect="../Catalogo/Home.aspx";
                }
                    
                Response.Redirect(strRedirect + "?msg=login-success", true);
            }
            else
            {
                Session["user"] = null;
                hfLoginStatus.Value = "failed";
                lblLoginFeedback.Text = "Usuario o contraseña incorrectos.";
            }
        }


        protected void btnEliminar_Click(object sender, EventArgs e)
        {
            LinkButton btn = (LinkButton)sender;
            RepeaterItem item = (RepeaterItem)btn.NamingContainer;
            HiddenField hiddenId = (HiddenField)item.FindControl("hfItemCarritoId");
            int icId = Convert.ToInt32(hiddenId.Value);
            icWS.deleteItem(icId);

            CargarCarrito();

            string script = "window.onload = function() { showModal('carritoModal'); };";
            clientScriptManager.RegisterStartupScript(GetType(), "", script, true);
        }
        public void CargarCarrito()
        {
            if (Session["user"] == null) return;

            int userId = Convert.ToInt32(Session["user"]);
            shoppingcart = carritoWSClient.getCarritoFromUser(userId);

            if (shoppingcart == null)
                shoppingcart = new carrito();

            if (shoppingcart.items == null)
                shoppingcart.items = new List<itemCarrito>().ToArray();


            int cantidadTotalProductos = shoppingcart.items.Sum(i => i.cantidad);
            double totalGeneral = shoppingcart.items.Sum(i => i.subtotal);

            actualizarCarrito(cantidadTotalProductos, totalGeneral);

            rptCarrito.DataSource = shoppingcart.items;
            rptCarrito.DataBind();

            actualizarIcono(cantidadTotalProductos);
        }

        private void actualizarCarrito(int cantidadTotalProductos, double totalGeneral)
        {
            shoppingcart.cantidadProductos = cantidadTotalProductos;
            shoppingcart.total = totalGeneral;
            carritoWSClient.updateCarrito(shoppingcart);

        }
        private void actualizarIcono(int cantidadTotalProductos)
        {
            if (cantidadTotalProductos > 0)
            {
                spanCantidadCarrito.InnerText = cantidadTotalProductos.ToString();
                spanCantidadCarrito.Style["display"] = "inline";
            }
            else
            {
                spanCantidadCarrito.Style["display"] = "none";
            }
        }

    }
}