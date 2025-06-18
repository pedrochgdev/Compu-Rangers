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
        public BindingList<itemCarrito> listItems;
        private carrito shoppingcart;
        private ClientScriptManager clientScriptManager;

        public BindingList<itemCarrito> ListItems
        {
            get
            {
                if (listItems == null && Session["user"] != null)
                {
                    int userId = Convert.ToInt32(Session["user"]);
                    object items = icWS.getAllFromCarrito(userId);
                    if (items != null)
                        listItems = new BindingList<itemCarrito>(((itemCarrito[])items).ToList());
                    else
                        listItems = new BindingList<itemCarrito>();
                }
                return listItems;
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
                if (listItems == null) return "0.00";
                double total = listItems.Sum(i => i.subtotal);
                return total.ToString("N2");
            }
        }

        protected void btnActualizarCantidad_Command(object sender, CommandEventArgs e)
        {
            string[] args = e.CommandArgument.ToString().Split(';');
            int itemId = Convert.ToInt32(args[0]);
            int delta = Convert.ToInt32(args[1]);

            itemCarrito item = listItems.FirstOrDefault(i => i.id == itemId);
            if (item != null)
            {
                int nuevaCantidad = item.cantidad + delta;

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

            string script = "window.onload = function() { updateModal('carritoModal'); };";
            clientScriptManager.RegisterStartupScript(GetType(), "", script, true);
        }

        public SiteMaster()
        {
            this.authWS = new AuthWSClient();
            this.userWS = new UsuarioWSClient();
            this.clientWS = new ClienteWSClient();
            this.invWS = new InventoryWSClient();
            this.icWS = new ItemCarritoWSClient();
            this.carritoWSClient = new CarritoWSClient();
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            if (Session["user"] == null)
            {
                //ms¿ostrar btnes login
            }
            else
            {
            }
            this.clientScriptManager = Page.ClientScript;
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (hfLoginStatus.Value == "failed")
                {
                    ScriptManager.RegisterStartupScript(this, GetType(), "ShowLoginModal", "$('#form-modal-login').modal('show');", true);
                }
                // Mostrar/ocultar bloques dependiendo del login
                bool loggedIn = Session["user"] != null;
                phUserLogged.Visible = loggedIn;
                phUserNotLogged.Visible = !loggedIn;


                if (loggedIn)
                {
                    clientScriptManager.RegisterStartupScript(GetType(), "",
                    "window.onload = function() { showAlert('Acabas de iniciar sesion','success');};", true);
                    bool isadmin = userWS.getRole(Convert.ToInt32(Session["user"]));
                    if (!isadmin)
                        CargarCarrito();
                    else
                    {
                        adminLogged.Visible = true;
                        adminNotLogged.Visible = false;
                    }
                }else {
                    clientScriptManager.RegisterStartupScript(GetType(), "",
                    "window.onload = function() { showAlert('Inicia sessión para comprar ','danger');};", true);
                }
                else
                {
                    clientScriptManager.RegisterStartupScript(GetType(), "",
                    "window.onload = function() { showAlert('Cerraste sesion','danger');};", true);
                }

                
            }
        }

        protected void btnLogOut(object sender, EventArgs e)
        {
            Session.Clear();
            FormsAuthentication.SignOut();
            Response.Redirect("~/Catalogo/Home.aspx");
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
            Console.WriteLine(usuario + "" + password);
            int id = authWS.login(usuario, password);
            
            if (id > 0)
            {
                Session["user"] = id;
              
                
                FormsAuthenticationTicket tkt;
                string cookiestr;
                HttpCookie ck;
                tkt = new FormsAuthenticationTicket(30, usuario, DateTime.Now,
                DateTime.Now.AddMinutes(1), true, "datos adicionales del usuario");
                cookiestr = FormsAuthentication.Encrypt(tkt);
                ck = new HttpCookie(FormsAuthentication.FormsCookieName, cookiestr);
                ck.Expires = tkt.Expiration;
                ck.Path = FormsAuthentication.FormsCookiePath;
                Response.Cookies.Add(ck);
                string strRedirect;
                bool user = userWS.getRole(id);
                if (user)
                {
                    strRedirect = "../Admin/Ventas.aspx";
                }
                else
                    strRedirect = "../Catalogo/Home.aspx";

                Response.Redirect(strRedirect, true);
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
            int icId= Convert.ToInt32(hiddenId.Value);
            icWS.deleteItem(icId);

            CargarCarrito();

            string script = "window.onload = function() { updateModal('carritoModal'); };";
            clientScriptManager.RegisterStartupScript(GetType(), "", script, true);
        }
        public void CargarCarrito()
        {
            if (Session["user"] == null) return;

            int userId = Convert.ToInt32(Session["user"]);
            object items = icWS.getAllFromCarrito(userId);
            if (items != null)
            {
                listItems = new BindingList<itemCarrito>(((itemCarrito[])items).ToList());
                int cantidadTotalProductos = listItems.Sum(i => i.cantidad);
                double totalGeneral = listItems.Sum(i => i.subtotal);
                shoppingcart = carritoWSClient.getCarritoFromUser(userId);
                actualizarCarrito(cantidadTotalProductos, totalGeneral);
                rptCarrito.DataSource = listItems;
                rptCarrito.DataBind();

                actualizarIcono(cantidadTotalProductos);
            }
            else
            {
                listItems = new BindingList<itemCarrito>();
                rptCarrito.DataSource = listItems;
                rptCarrito.DataBind();

                shoppingcart = carritoWSClient.getCarritoFromUser(userId);
                if (shoppingcart == null) shoppingcart = new carrito();

                actualizarCarrito(0, 0);
            }
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