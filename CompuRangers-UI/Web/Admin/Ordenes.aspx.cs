using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web.Admin
{
    public partial class Ordenes : System.Web.UI.Page
    {
        private readonly OrdenDeVentaWSClient ordenWS;

        public Ordenes()
        {
            this.ordenWS = new OrdenDeVentaWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int userId = Convert.ToInt32(Session["user"]);
                var ordenes = ordenWS.getAllOrdenes();

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
                case "ENTREGADO": return "success";
                case "EN CAMINO": return "warning text-dark";
                case "CANCELADO": return "danger";
                default: return "secondary";
            }
        }
    }
}