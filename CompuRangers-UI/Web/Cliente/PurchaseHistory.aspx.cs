using Microsoft.Ajax.Utilities;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;


namespace Web
{
    public partial class PurchaseHistory : Web.Middleware.ClientePage
    {
        private readonly OrdenDeVentaWSClient ordenWS;
        private int paginaActual
        {
            get { return ViewState["paginaActual"] != null ? (int)ViewState["paginaActual"] : 0; }
            set { ViewState["paginaActual"] = value; }
        }

        private const int OrdenesPorPagina = 5;
        public PurchaseHistory()
        {
            this.ordenWS = new OrdenDeVentaWSClient();
        }
        protected void rptHistorialCompras_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "GenerarReporte")
            {
                int idOrden = Convert.ToInt32(e.CommandArgument);
                string baseUrl = ConfigurationManager.AppSettings["BaseUrl"];
                string url = $"{baseUrl}/Reportes/ordenes?id={idOrden}";
                string script = $"window.open('{url}', '_blank');";
                ClientScript.RegisterStartupScript(this.GetType(), "AbrirReporteProductos", script, true);
            }
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarOrdenesConPaginacion();
            }
        }
        private void CargarOrdenesConPaginacion()
        {
            int userId = Convert.ToInt32(Session["user"]);
            var ordenes = ordenWS.getAllOrdenesFromUser(userId)
                .OrderByDescending(o => o.id)
                .ToList();

            if (ordenes != null && ordenes.Count > 0)
            {
                foreach (var orden in ordenes)
                {
                    if (orden.detalles != null)
                        orden.detalles = AgruparDetalles(orden.detalles);
                }

                PagedDataSource pagedData = new PagedDataSource();
                pagedData.DataSource = ordenes;
                pagedData.AllowPaging = true;
                pagedData.PageSize = OrdenesPorPagina;
                pagedData.CurrentPageIndex = paginaActual;

                btnAnterior.Enabled = !pagedData.IsFirstPage;
                btnSiguiente.Enabled = !pagedData.IsLastPage;

                lblPagina.Text = $"Página {paginaActual + 1} de {pagedData.PageCount}";

                rptHistorialCompras.DataSource = pagedData;
                rptHistorialCompras.DataBind();
                pnlSinCompras.Visible = false;
            }
            else
            {
                rptHistorialCompras.DataSource = null;
                rptHistorialCompras.DataBind();
                pnlSinCompras.Visible = true;
            }
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
        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            paginaActual--;
            CargarOrdenesConPaginacion();
        }

        protected void btnSiguiente_Click(object sender, EventArgs e)
        {
            paginaActual++;
            CargarOrdenesConPaginacion();
        }

        protected string GetBadgeColor(string estado)
        {
            switch (estado.ToUpper())
            {
                case "PAGADO": return "success";
                case "EN_PROCESO": return "warning text-dark";
                case "CANCELADO": return "danger";
                default: return "secondary";
            }
        }
    }
}
