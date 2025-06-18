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
        public DetalleCompra()
        {
            this.clientWS = new ClienteWSClient();
            this.ordenWS = new OrdenDeVentaWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                var master = (SiteMaster)this.Master;
                var lista = master.ListItems;

                rptConfirmacionCompra.DataSource = lista;
                rptConfirmacionCompra.DataBind();

                lblTotalPedido.Text = lista.Sum(i => i.subtotal).ToString("N2");

                int userId = Convert.ToInt32(Session["user"]);
                cliente usuario = clientWS.searchCliente(userId); // Ajusta al nombre real del método

                if (usuario != null)
                {
                    txtDireccion.Text = usuario.direccionPreferida;
                }
            }
        }

        protected void ConfirmarPago_Click(object sender, EventArgs e)
        {
            ordenDeVenta nuevaOrden = new ordenDeVenta
            {
                clienteId = Convert.ToInt32(Session["user"]),
                fecha = DateTime.Now,
                total = Convert.ToDouble(lblTotalPedido.Text),
                direccion = txtDireccion.Text,
                estado = "EN_PROCESO"
            };  

            int ordenId = ordenWS.addOrden(nuevaOrden);

            nuevaOrden.id = ordenId;

            string linkPago = clientWS.payment(nuevaOrden);

            string script = $"window.open('{linkPago}', '_blank');";

            ScriptManager.RegisterStartupScript(this, this.GetType(), "abrirPago", script, true);

        }

    }
}