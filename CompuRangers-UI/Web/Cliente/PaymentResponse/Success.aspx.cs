using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web.Cliente.PaymentResponse
{
    public partial class Success : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string vadsOrderId = Request.QueryString["vads_order_id"];
                if (!string.IsNullOrEmpty(vadsOrderId) && vadsOrderId.Contains("-"))
                {
                    int idOrden = int.Parse(vadsOrderId.Split('-')[1]);

                    ClientScript.RegisterStartupScript(this.GetType(), "postPagoCompletado", $@"
                        window.onload = function () {{
                            setTimeout(function () {{
                                window.opener.postMessage({{
                                    tipo: 'pago-completado',
                                    idOrden: {idOrden}
                                }}, '*');
                                window.close();
                            }}, 1000); // Espera 3 segundos antes de cerrar
                        }};
                    ", true);
                }
                else
                {
                    Response.Redirect("~/Error.aspx");
                }
            }
        }
    }
}