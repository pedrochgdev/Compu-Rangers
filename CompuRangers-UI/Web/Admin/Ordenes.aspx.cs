using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web.Admin
{
    public partial class Ordenes : Web.Middleware.AdminPage
    {
        private readonly OrdenDeVentaWSClient ordenWS;
        private const int OrdenesPorPagina = 10;
        private int PaginaActual
        {
            get { return ViewState["PaginaActual"] != null ? (int)ViewState["PaginaActual"] : 0; }
            set { ViewState["PaginaActual"] = value; }
        }
        public Ordenes()
        {
            this.ordenWS = new OrdenDeVentaWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                PaginaActual = 0;
                CargarOrdenesConPaginacion();
            }
        }
        private void CargarOrdenesConPaginacion()
        {
            var ordenes = ordenWS.getAllOrdenes()
                .OrderByDescending(o => o.id)
                .ToList();

            foreach (var orden in ordenes)
            {
                orden.detalles = AgruparDetalles(orden.detalles);
            }

            PagedDataSource pagedData = new PagedDataSource();
            pagedData.DataSource = ordenes;
            pagedData.AllowPaging = true;
            pagedData.PageSize = OrdenesPorPagina;
            pagedData.CurrentPageIndex = PaginaActual;

            rptHistorialCompras.DataSource = pagedData;
            rptHistorialCompras.DataBind();

            btnAnterior.Enabled = !pagedData.IsFirstPage;
            btnSiguiente.Enabled = !pagedData.IsLastPage;

            lblPagina.Text = $"Página {PaginaActual + 1} de {pagedData.PageCount}";
        }
        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            PaginaActual--;
            CargarOrdenesConPaginacion();
        }

        protected void btnSiguiente_Click(object sender, EventArgs e)
        {
            PaginaActual++;
            CargarOrdenesConPaginacion();
        }
        protected void btnGenerarReporteOrdenes_Click(object sender, EventArgs e)
        {
            string baseUrl = ConfigurationManager.AppSettings["BaseUrl"];
            string url = $"{baseUrl}/Reportes/ventasrealizadas";
            string script = $"window.open('{url}', '_blank');";
            ClientScript.RegisterStartupScript(this.GetType(), "AbrirReporteProductos", script, true);
        }
      
        private detalleVenta[] AgruparDetalles(detalleVenta[] detalles)
        {
            var agrupados = new Dictionary<string, detalleVenta>();

            foreach (var d in detalles)
            {
                string clave = d.producto.producto.nombre;

                if (agrupados.ContainsKey(clave))
                {
                    agrupados[clave].cantidad += d.cantidad;
                    agrupados[clave].subtotal += d.subtotal;
                }
                else
                {
                    agrupados[clave] = new detalleVenta
                    {
                        producto = d.producto,
                        cantidad = d.cantidad,
                        subtotal = d.subtotal
                    };
                }
            }

            return agrupados.Values.ToArray();
        }


        protected string GetBadgeColor(string estado)
        {
            switch (estado.ToUpper())
            {
                case "ENTREGADO": return "success";
                case "EN CAMINO": return "warning text-dark";
                case "CANCELADO": return "danger";
                default: return "secondary";
            }
        }
    }
}