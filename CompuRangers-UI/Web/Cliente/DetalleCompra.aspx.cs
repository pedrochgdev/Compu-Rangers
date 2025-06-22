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
        public DetalleCompra()
        {
            this.clientWS = new ClienteWSClient();
            this.ordenWS = new OrdenDeVentaWSClient();
            this.invWS = new InventoryWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                var master = (SiteMaster)this.Master;
                var carrito = master.ShoppingCart;

                rptConfirmacionCompra.DataSource = carrito.items;
                rptConfirmacionCompra.DataBind();

                lblTotalPedido.Text = carrito.total.ToString("N2");

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

            ordenDeVenta nuevaOrden = new ordenDeVenta
            {
                clienteId = Convert.ToInt32(Session["user"]),
                fecha = DateTime.Now,
                total = carrito.total,
                direccion = txtDireccion.Text,
                estado = "EN_PROCESO"
            };

            // Primero creas la orden en la BD y obtienes el ID
            int ordenId = ordenWS.addOrden(nuevaOrden);
            nuevaOrden.id = ordenId;

            // Luego llenas los detalles (esto no se guarda todavía, solo para reservar)
            List<detalleVenta> detallesList = new List<detalleVenta>();
            foreach (itemCarrito item in carrito.items)
            {
                detalleVenta detalle = new detalleVenta
                {
                    producto = item.producto,
                    cantidad = item.cantidad,
                    subtotal = item.subtotal,
                    devuelto = 0,
                    idOrdenVenta = ordenId
                };
                detallesList.Add(detalle);
            }

            nuevaOrden.detalles = detallesList.ToArray();

            // Reservas el inventario con la orden ya creada y detalles listos
            invWS.reservarInventario(nuevaOrden);

            // Generas el link de pago
            string linkPago = clientWS.payment(nuevaOrden);
            Session["linkPago"] = linkPago;

            Response.Redirect("PaymentResponse/WaitingPayment.aspx");
        }
    }
}