using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web.Auth
{
    public partial class PasswordRecovery : System.Web.UI.Page
    {
        private readonly UsuarioWSClient authWS;

        public PasswordRecovery()
        {
            this.authWS = new UsuarioWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnEnviar_Click(object sender, EventArgs e)
        {
            string correo = txtEmail.Text.Trim();

            if (!string.IsNullOrEmpty(correo))
            {
                bool enviado = authWS.forgotPassword(correo);

                if (enviado)
                {
                    // Mostrar mensaje de éxito
                    Response.Write("<script>alert('Se ha enviado un enlace a tu correo.');</script>");
                }
                else
                {
                    // Mostrar error si el correo no existe
                    Response.Write("<script>alert('No se encontró una cuenta con ese correo.');</script>");
                }
            }
            else
            {
                Response.Write("<script>alert('Por favor, ingresa un correo.');</script>");
            }
        }
    }
}