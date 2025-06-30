using System;
using System.Web;

namespace Web.Middleware
{
    public class AdminPage : System.Web.UI.Page
    {
        protected override void OnInit(EventArgs e)
        {
            base.OnInit(e);

            object isAdminObj = HttpContext.Current?.Session?["isAdmin"];
            bool isAdmin = isAdminObj is bool b && b;
            int userId = Convert.ToInt32(Session["user"]);
            if ((!isAdmin && userId>0) || userId<=0 )
            {
                HttpContext.Current.Response.Redirect("~/Catalogo/Home.aspx",true);
            }
        }
    }
}
