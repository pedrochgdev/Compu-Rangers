using System;
using System.Web;

namespace Web.Middleware
{
    public class NoAdminPage : System.Web.UI.Page
    {

        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

            object isAdminObj = HttpContext.Current?.Session?["isAdmin"];
            bool isAdmin = isAdminObj is bool b && b;
            int userId = Convert.ToInt32(Session["user"]);
            if (userId > 0 && isAdmin)
            {
                HttpContext.Current.Response.Redirect("~/Admin/Ventas.aspx");
            }
        }
    }
}
