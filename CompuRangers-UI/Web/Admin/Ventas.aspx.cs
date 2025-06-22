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

        public Ventas()
        {
            clientWS = new ClienteWSClient();
            ordenWS = new OrdenDeVentaWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            cargarMetricasDashboard();
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
    }
}