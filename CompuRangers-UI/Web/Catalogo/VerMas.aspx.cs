using System;
using System.Linq;
using System.Web.UI;
using Web.WebService;

namespace Web
{
    public partial class VerMas : System.Web.UI.Page
    {
        private readonly InventoryWSClient invWS;
        private readonly CarritoWSClient carritoWS;

        public VerMas()
        {
            this.invWS = new InventoryWSClient();
            this.carritoWS = new CarritoWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarDetalleDeProducto();
            }
        }

        // 1. Cargar flujo principal del detalle
        private void CargarDetalleDeProducto()
        {
            int? idProducto = ObtenerIdDesdeQueryString();
            if (!idProducto.HasValue)
            {
                RedirigirAPaginaDeError();
                return;
            }

            productoDTO producto = ObtenerProductoPorId(idProducto.Value);
            if (producto == null)
            {
                RedirigirAPaginaDeError();
                return;
            }

            MostrarProducto(producto);
            CargarSugeridos(producto.producto.categoria.nombre, producto.producto.id);
        }

        // 2. Obtener ID de la URL
        private int? ObtenerIdDesdeQueryString()
        {
            string idStr = Request.QueryString["id"];
            return int.TryParse(idStr, out int id) ? id : (int?)null;
        }

        // 3. Lógica para buscar producto por ID
        private productoDTO ObtenerProductoPorId(int id)
        {
            return invWS.getCatalog().FirstOrDefault(p => p.producto.id == id);
        }

        // 4. Mostrar info principal del producto
        private void MostrarProducto(productoDTO producto)
        {
            imgProducto.ImageUrl = "https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg";
            litNombre.Text = producto.producto.nombre;
            litDescripcion.Text = producto.producto.descripcion;
            litPrecio.Text = producto.producto.precioVenta.ToString("C2");
            hfIdProducto.Value = producto.producto.id.ToString();
            litCategorias.Text = GenerarHtmlCategorias(producto);
        }

        // 5. Sugerencias basadas en categoría
        private void CargarSugeridos(string categoriaNombre, int idActual)
        {
            var sugeridos = invWS.getCatalog()
                .Where(p => p.producto.categoria.nombre == categoriaNombre && p.producto.id != idActual)
                .Take(6)
                .ToList();

            rptSugeridos.DataSource = sugeridos;
            rptSugeridos.DataBind();
        }

        // 6. HTML para las categorías
        private string GenerarHtmlCategorias(productoDTO producto)
        {
            string html = $"<span class='badge-category'>{producto.producto.categoria.nombre}</span>";
            if (!string.IsNullOrEmpty(producto.producto.categoria.categoriaPadre?.nombre))
            {
                html += $" <span class='badge-category'>{producto.producto.categoria.categoriaPadre.nombre}</span>";
            }
            return html;
        }

        // 7. Redirección si hay error
        private void RedirigirAPaginaDeError()
        {
            Response.Redirect("~/Error.aspx");
        }

        protected void btnAgregarCarrito_Click(object sender, EventArgs e)
        {
            if (Session["user"] == null)
            {
                ClientScript.RegisterStartupScript(GetType(), "", "window.onload = function() { showModal('form-modal-login'); };", true);
                return;
            }

            SiteMaster master = (SiteMaster)this.Master;
            carrito shoppingcart = master.ShoppingCart;

            int cantidad = 1;
            if (!int.TryParse(quantity.Text, out cantidad) || cantidad <= 0)
            {
                cantidad = 1;
            }

            int? idProducto = ObtenerIdDesdeQueryString();
            if (!idProducto.HasValue) return;

            productoDTO prodDto = ObtenerProductoPorId(idProducto.Value);
            if (prodDto == null) return;

            producto p = prodDto.producto;
            itemCarrito ic = new itemCarrito
            {
                producto = p,
                cantidad = cantidad,
                subtotal = p.precioVenta * cantidad,
                carritoId = shoppingcart.id
            };

            var existente = shoppingcart.items?.FirstOrDefault(i => i.producto.id == p.id);
            int yaEnCarrito = existente != null ? existente.cantidad : 0;

            if (prodDto.cantidadDisponible - yaEnCarrito < cantidad)
            {
                ClientScript.RegisterStartupScript(GetType(), "", "window.onload = function() { showAlert('Stock insuficiente', 'warning'); };", true);
                return;
            }

            shoppingcart.cantidadProductos += cantidad;
            shoppingcart.total += ic.subtotal;

            if (carritoWS.addItem(ic))
            {
                carritoWS.updateCarrito(shoppingcart);
                master.ShoppingCart = shoppingcart;
                master.CargarCarrito();
            }

            ClientScript.RegisterStartupScript(GetType(), "", "window.onload = function() { showModal('carritoModal'); };", true);
        }

    }
}
