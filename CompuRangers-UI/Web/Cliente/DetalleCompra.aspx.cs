using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web
{
    public partial class DetalleCompra : System.Web.UI.Page
    {
        private readonly ClienteWSClient clientWS;
        private readonly OrdenDeVentaWSClient ordenWS;
        private readonly InventoryWSClient invWS;
        private ClientScriptManager clientScriptManager;
        public DetalleCompra()
        {
            this.clientWS = new ClienteWSClient();
            this.ordenWS = new OrdenDeVentaWSClient();
            this.invWS = new InventoryWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            clientScriptManager = Page.ClientScript;

            if (!IsPostBack)
            {
                var master = (SiteMaster)this.Master;
                var carrito = master.ShoppingCart;

                rptConfirmacionCompra.DataSource = carrito.items;
                rptConfirmacionCompra.DataBind();

                lblTotalPedido.Text = carrito.total.ToString("C2");

                int userId = Convert.ToInt32(Session["user"]);
                cliente usuario = clientWS.searchCliente(userId);

                if (usuario != null)
                {
                    txtDireccion.Text = usuario.direccionPreferida;
                }
            }
        }

        protected void ConfirmarPago_Click(object sender, EventArgs e)
        {
            var master = (SiteMaster)this.Master;
            var carrito = master.ShoppingCart;

            int userId = Convert.ToInt32(Session["user"]);

            cliente cli = new cliente
            {
                direccionPreferida = txtDireccion.Text
            };

            //clientWS.updateCliente(userId, cli);

            ordenDeVenta nuevaOrden = new ordenDeVenta
            {
                clienteId = Convert.ToInt32(Session["user"]),
                fecha = DateTime.Now,
                total = carrito.total,
                direccion = txtDireccion.Text,
                estado = "EN_PROCESO"
            };

            int ordenId = ordenWS.addOrden(nuevaOrden);
            nuevaOrden.id = ordenId;

            List<detalleVenta> detallesList = new List<detalleVenta>();

            foreach (itemCarrito item in carrito.items)
            {
                int cantidadPendiente = item.cantidad;

                var inventarios = invWS.getInvDisponible(item.producto.id);

                int stockTotalDisponible = inventarios.Sum(i => i.cantidadDisponible);

                // ❌ Validar stock actualizado
                if (stockTotalDisponible < cantidadPendiente)
                {
                    string mensaje = $"Stock insuficiente para el producto {item.producto.nombre}. Solo hay {stockTotalDisponible} unidades disponibles.";
                    clientScriptManager.RegisterStartupScript(this.GetType(), "",
                        $"window.onload = function() {{ showAlert('{mensaje}', 'warning'); }};", true);
                    return;
                }

                foreach (var inv in inventarios)
                {
                    if (cantidadPendiente <= 0)
                        break;

                    int cantidadAConsumir = Math.Min(cantidadPendiente, inv.cantidadDisponible);

                    detalleVenta detalle = new detalleVenta
                    {
                        producto = inv,
                        cantidad = cantidadAConsumir,
                        subtotal = cantidadAConsumir * item.producto.precioVenta,
                        devuelto = 0,
                        idOrdenVenta = ordenId
                    };

                    detallesList.Add(detalle);

                    cantidadPendiente -= cantidadAConsumir;
                }

                if (cantidadPendiente > 0)
                {
                    string mensaje = $"No hay suficiente stock para el producto {item.producto.nombre}";
                    clientScriptManager.RegisterStartupScript(this.GetType(), "",
                        $"window.onload = function() {{ showAlert('{mensaje}', 'warning'); }};", true);
                    return;
                }
            }

            nuevaOrden.detalles = detallesList.ToArray();
            invWS.reservarInventario(nuevaOrden);
            string linkPago = clientWS.payment(nuevaOrden);
            Session["linkPago"] = linkPago;
            Response.Redirect("PaymentResponse/WaitingPayment.aspx");
        }
    }
}