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
        private AdminWSClient adminWS;

        public Ventas()
        {
            clientWS = new ClienteWSClient();
            ordenWS = new OrdenDeVentaWSClient();
            prodWS = new ProductoWSClient();
            adminWS = new AdminWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            cargarMetricasDashboard();
            CargarProductosMasVendidos();
            CargarPedidosPorDia();
            CargarGananciasPorMes();
        }
        private void CargarGananciasPorMes()
        {
            try
            {
                var lista = adminWS.getGananciasMensuales(); // Retorna lista de DTOs
                rptGananciasMensuales.DataSource = lista;
                rptGananciasMensuales.DataBind();
            }
            catch (Exception ex)
            {
                rptGananciasMensuales.DataSource = new List<object>();
                rptGananciasMensuales.DataBind();
            }
        }
        protected string ObtenerNombreMes(string yearMonth)
        {
            if (DateTime.TryParse(yearMonth + "-01", out DateTime fecha))
                return fecha.ToString("MMMM yyyy", new System.Globalization.CultureInfo("es-ES")); // Ej: junio 2025
            return yearMonth;
        }

        private void cargarMetricasDashboard()
        {
            try
            {
                lblTotalVentas.Text = adminWS.getTotalHistorico().ToString("C2");
                lblGananciaMes.Text = adminWS.getGananciaMes().ToString("C2"); // ← nuevo
                lblPedidosHoy.Text = adminWS.getPedidosHoy().ToString();
                lblClientesNuevos.Text = clientWS.getClientesNuevos().ToString();
            }
            catch (Exception ex)
            {
                lblTotalVentas.Text = "Error";
                lblGananciaMes.Text = "Error"; // ← nuevo
                lblPedidosHoy.Text = "Error";
                lblClientesNuevos.Text = "Error";
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
            var pedidos = adminWS.getPedidosSemanal();
            rptPedidosPorDia.DataSource = pedidos;
            rptPedidosPorDia.DataBind();
        }
    }
}