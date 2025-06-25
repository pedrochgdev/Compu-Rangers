using System;
using System.Web;
using System.Net;
using Web.WebService;

namespace Web
{
    public class MostrarImagen : IHttpHandler
    {
        public void ProcessRequest(HttpContext context)
        {
            context.Response.Clear();
            context.Response.ContentType = "image/jpeg";

            int id;
            if (int.TryParse(context.Request.QueryString["id"], out id))
            {
                var ws = new ProductoWSClient();
                var producto = ws.searchProductoID(id);

                if (producto != null && producto.imagenReferencial != null && producto.imagenReferencial.Length > 0)
                {
                    context.Response.BinaryWrite(producto.imagenReferencial);
                    context.Response.End();
                    return;
                }
            }

            // Si no hay imagen: descargar la imagen por defecto y devolverla como bytes
            using (var client = new WebClient())
            {
                context.Response.Redirect("/Imagenes/predeterminado.jpg", endResponse: false);
            }
        }

        public bool IsReusable => false;
    }
}

