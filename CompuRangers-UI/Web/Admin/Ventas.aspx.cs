using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web
{
    public partial class Ventas : Page
    {
        private ClienteWSClient clientWS;
        private OrdenDeVentaWSClient ordenWS;
        private ProductoWSClient prodWS;

        public Ventas()
        {
            clientWS = new ClienteWSClient();
            ordenWS = new OrdenDeVentaWSClient();
            prodWS = new ProductoWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            cargarMetricasDashboard();
            CargarProductosMasVendidos();
            CargarPedidosPorDia();
        }

        private void cargarMetricasDashboard()
        {
            try
            {
                lblTotalVentas.Text = ordenWS.getTotalHistorico().ToString("N2");
                lblPedidosHoy.Text = ordenWS.getPedidosHoy().ToString();
                lblClientesNuevos.Text = clientWS.getClientesNuevos().ToString();
            }
            catch (Exception ex)
            {
                lblTotalVentas.Text = "Error al cargar las métricas.";
                lblPedidosHoy.Text = "Error al cargar las métricas.";
                lblClientesNuevos.Text = "Error al cargar las métricas.";
            }
        }
        private void CargarProductosMasVendidos()
        {
            var productos = prodWS.getRanking();
            rptMasVendidos.DataSource = productos;
            rptMasVendidos.DataBind();
        }

        private void CargarPedidosPorDia()
        {
            var pedidos = ordenWS.getPedidosSemanal();
            rptPedidosPorDia.DataSource = pedidos;
            rptPedidosPorDia.DataBind();
        }
    }
}