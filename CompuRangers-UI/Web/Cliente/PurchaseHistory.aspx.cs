using Microsoft.Ajax.Utilities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.UI;
using Web.WebService;


namespace Web
{
    public partial class PurchaseHistory : Page
    {
        private readonly OrdenDeVentaWSClient ordenWS;

        public PurchaseHistory()
        {
            this.ordenWS = new OrdenDeVentaWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int userId = Convert.ToInt32(Session["user"]);
                var ordenes = ordenWS.getAllOrdenesFromUser(userId);

                foreach (var orden in ordenes)
                {
                    orden.detalles = AgruparDetalles(orden.detalles);
                }

                rptHistorialCompras.DataSource = ordenes;
                rptHistorialCompras.DataBind();
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
