using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web.Cliente.PaymentResponse
{
    public partial class Success : Web.Middleware.ClientePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string vadsOrderId = Request.QueryString["vads_order_id"];
                int idOrden = int.Parse(vadsOrderId.Split('-')[1]);
                string baseUrl = System.Configuration.ConfigurationManager.AppSettings["BaseUrl"];
                string reporteUrl = $"{baseUrl}/Reportes/reportes/detalleVenta?idOrden={idOrden}";

                ClientScript.RegisterStartupScript(this.GetType(), "redirectAfterDelay", $@"
                    setTimeout(() => {{
                        window.opener.postMessage('pago-completado', '*');
                        window.location.href = '{reporteUrl}';
                    }}, 2000);
                ", true);
            }
        }
    }
}