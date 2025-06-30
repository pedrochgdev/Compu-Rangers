using System;
using System.Web;

namespace Web.Middleware
{
    public class ClienteOrAnonimo : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

            object isAdminObj = HttpContext.Current?.Session?["isAdmin"];
            bool isAdmin = isAdminObj is bool b && b;

            if (isAdmin)
            {
                HttpContext.Current.Response.Redirect("~/Admin/Ventas.aspx");
            }
        }
    }
}
