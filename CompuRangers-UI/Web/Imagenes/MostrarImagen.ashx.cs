using System;
using System.IO;
using System.Web;
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

            // Cargar imagen predeterminada desde el disco
            string rutaImagen = context.Server.MapPath("~/Imagenes/predeterminado.png");
            if (File.Exists(rutaImagen))
            {
                byte[] imagenPorDefecto = File.ReadAllBytes(rutaImagen);
                context.Response.BinaryWrite(imagenPorDefecto);
                context.Response.End();
            }
            else
            {
                // Fallback: 404 si ni la imagen por defecto existe
                context.Response.StatusCode = 404;
                context.Response.End();
            }
        }

        public bool IsReusable => false;
    }
}
