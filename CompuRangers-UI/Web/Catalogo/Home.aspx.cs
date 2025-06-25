using System;
using System.CodeDom;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.HtmlControls;
using System.Web.UI.WebControls;
using System.Xml.Linq;
using Web.WebService;

namespace Web
{
    public partial class Home : Page
    {
        private readonly AuthWSClient authWS;
        private readonly UsuarioWSClient userWS;
        private readonly ClienteWSClient clientWS;
        private readonly InventoryWSClient invWS;
        private readonly CarritoWSClient carritoWSClient;
        private readonly ItemCarritoWSClient icWS;
        private readonly CategoriaWSClient categoriaWS;
        private BindingList<productoDTO> catalogo;
        private carrito shoppingcart;

        private int paginaActual
        {
            get { return ViewState["paginaActual"] != null ? (int)ViewState["paginaActual"] : 0; }
            set { ViewState["paginaActual"] = value; }
        }
        private List<productoDTO> Catalogo
        {
            get
            {
                return ViewState["Catalogo"] as List<productoDTO>;
            }
            set
            {
                ViewState["Catalogo"] = value;
            }
        }

        private const int ProductosPorPagina = 9;

        public Home()
        {
            this.authWS = new AuthWSClient();
            this.userWS = new UsuarioWSClient();
            this.clientWS = new ClienteWSClient();
            this.invWS = new InventoryWSClient();
            this.icWS = new ItemCarritoWSClient();
            this.carritoWSClient = new CarritoWSClient();
            this.categoriaWS = new CategoriaWSClient();
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            if (Session["user"] == null)
            {
                //ms¿ostrar btnes login
            }
            else
            {
                //mostar icno user
            }
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                List<productoDTO> productos = invWS.getCatalog().ToList();
                Catalogo = productos;

                paginaActual = 0;
                CargarProductosConPaginacion(Catalogo);

                categoria[] categoriasRaiz = categoriaWS.getAllCategorias();
                List<categoria> hojas = ObtenerCategoriasHoja(categoriasRaiz.ToList());

                ddlCategoria.Items.Clear();
                ddlCategoria.Items.Add(new ListItem("Todas las categorías", ""));

                foreach (categoria hoja in hojas)
                {
                    ddlCategoria.Items.Add(new ListItem(hoja.nombre, hoja.nombre));
                }
            }
            SiteMaster master = (SiteMaster)this.Master;
            if (master != null)
            {
                master.CargarCarrito();
            }
        }

        private List<categoria> ObtenerCategoriasHoja(List<categoria> categorias)
        {
            List<categoria> hojas = new List<categoria>();

            foreach (var cat in categorias)
            {
                if (cat.subcategorias == null || cat.subcategorias.Length == 0)
                {
                    hojas.Add(cat);
                }
                else
                {
                    hojas.AddRange(ObtenerCategoriasHoja(cat.subcategorias.ToList()));
                }
            }

            return hojas;
        }


        protected void ddlCategoria_SelectedIndexChanged(object sender, EventArgs e)
        {
            string categoriaNombre = ddlCategoria.SelectedValue;
            List<productoDTO> productos = invWS.getCatalog().ToList();

            if (!string.IsNullOrEmpty(categoriaNombre))
                productos = productos.Where(p => p.producto.categoria.nombre == categoriaNombre).ToList();

            catalogo = new BindingList<productoDTO>(productos);
            paginaActual = 0;
            CargarProductosConPaginacion(catalogo.ToList());
        }

        protected void btnAddCart(object sender, EventArgs e)
        {
            string script;

            if (Session["user"] == null)
            {
                script = "window.onload = function() { showModal('form-modal-login');};";
            }
            else
            {
                SiteMaster master = (SiteMaster)this.Master;
                shoppingcart = master.ShoppingCart;

                LinkButton btn = (LinkButton)sender;
                RepeaterItem item = (RepeaterItem)btn.NamingContainer;

                HiddenField hiddenId = (HiddenField)item.FindControl("hiddenId");
                HiddenField hiddenCantidad = (HiddenField)item.FindControl("hiddenCantidad"); // tu nuevo campo

                HtmlGenericControl lblNombre = (HtmlGenericControl)item.FindControl("lblNombre");
                HtmlGenericControl lblPrecioVenta = (HtmlGenericControl)item.FindControl("lblPrecioVenta");

                int cantidadDisponible = Convert.ToInt32(hiddenCantidad.Value);

                itemCarrito existente = shoppingcart.items
                    .FirstOrDefault(i => i.producto.id == Convert.ToInt32(hiddenId.Value));

                int cantidadActualEnCarrito = existente != null ? existente.cantidad : 0;

                if (cantidadDisponible - cantidadActualEnCarrito <= 0)
                {
                    script = "window.onload = function() { showAlert('No hay stock','warning');};";
                }
                else
                {
                    producto p = new producto();
                    p.id = Convert.ToInt32(hiddenId.Value);
                    string precioTexto = lblPrecioVenta.InnerText.Replace("$", "").Trim();
                    p.precioVenta = Convert.ToDouble(precioTexto);
                    p.nombre = lblNombre.InnerText;

                    itemCarrito ic = new itemCarrito();
                    ic.carritoId = shoppingcart.id;
                    ic.producto = p;
                    ic.cantidad = 1;
                    ic.subtotal = ic.cantidad * p.precioVenta;

                    shoppingcart.cantidadProductos += ic.cantidad;
                    shoppingcart.total += ic.subtotal;

                    if (carritoWSClient.addItem(ic))
                    {
                        carritoWSClient.updateCarrito(shoppingcart);
                    }

                    if (master != null)
                    {
                        master.ShoppingCart = shoppingcart;
                        master.CargarCarrito();
                    }

                    script = "window.onload = function() { showModal('carritoModal');};";
                }
            }

            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }
        protected void btnFiltrar_Click(object sender, EventArgs e)
        {
            string script;
            //Llamar a una función Javascript
            if (Session["user"] == null)
                script = "window.onload = function() { showModal('form-modal-login');};";
            else
                script = "window.onload = function() { showModal('carritoModal');};";

            ClientScript.RegisterStartupScript(GetType(), "", script, true);
        }
        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            paginaActual--;
            CargarProductosConPaginacion(Catalogo);
        }

        protected void btnSiguiente_Click(object sender, EventArgs e)
        {
            paginaActual++;
            CargarProductosConPaginacion(Catalogo);
        }

        private void CargarProductosConPaginacion(List<productoDTO> productos)
        {
            PagedDataSource pagedData = new PagedDataSource();
            pagedData.DataSource = productos;
            pagedData.AllowPaging = true;
            pagedData.PageSize = ProductosPorPagina;
            pagedData.CurrentPageIndex = paginaActual;

            btnAnterior.Enabled = !pagedData.IsFirstPage;
            btnSiguiente.Enabled = !pagedData.IsLastPage;

            lblPagina.Text = $"Página {paginaActual + 1} de {pagedData.PageCount}";

            rptProductos.DataSource = pagedData;
            rptProductos.DataBind();
        }

        protected void rptProductos_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "Agregar")
            {
                string id = e.CommandArgument.ToString();
                // Lógica para añadir al carrito
            }
        }
    }
}