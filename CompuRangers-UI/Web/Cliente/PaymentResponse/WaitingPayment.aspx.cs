using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Configuration;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web.Cliente.PaymentResponse
{
    public partial class WaitingPayment : Web.Middleware.ClientePage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                string linkPago = Session["linkPago"] as string;
                string baseUrl = WebConfigurationManager.AppSettings["BaseUrl"];
                string script = $@"
                    window.BaseUrl = '{baseUrl.TrimEnd('/')}';
                    window.open('{linkPago}', '_blank', 'width=500,height=700,menubar=no,toolbar=no,location=no,status=no,resizable=no');";
                ScriptManager.RegisterStartupScript(this, this.GetType(), "abrirPago", script, true);
            }
        }
    }
}