using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Web.Auth
{
    public partial class ResetPassword : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                // Aquí podrías validar el token del link de recuperación si estás usando uno.
            }
        }

        protected void btnResetPassword_Click(object sender, EventArgs e)
        {
            string newPassword = txtNewPassword.Text.Trim();
            string confirmPassword = txtConfirmPassword.Text.Trim();

            if (string.IsNullOrEmpty(newPassword) || string.IsNullOrEmpty(confirmPassword))
            {
                ShowError("Ambos campos son obligatorios.");
                return;
            }

            if (newPassword != confirmPassword)
            {
                ShowError("Las contraseñas no coinciden.");
                return;
            }

            if (newPassword.Length < 6) // o reglas que tú definas
            {
                ShowError("La contraseña debe tener al menos 6 caracteres.");
                return;
            }

            try
            {
                // 🔐 Aquí debes recuperar el usuario asociado, por ejemplo desde un token en la URL:
                // string email = Request.QueryString["email"];
                // Usuario usuario = UserService.FindByEmail(email);
                // usuario.Password = Hash(newPassword);
                // UserService.Save(usuario);

                // Para ejemplo genérico:
                bool success = ActualizarPasswordUsuario(newPassword); // Implementa esta lógica
                if (success)
                {
                    // Puedes redirigir o mostrar mensaje
                    Response.Redirect("~/Auth/Login.aspx?msg=reset_success");
                }
                else
                {
                    ShowError("Ocurrió un error al actualizar la contraseña.");
                }
            }
            catch (Exception ex)
            {
                ShowError("Error interno: " + ex.Message);
            }
        }

        private void ShowError(string message)
        {
            // Puedes usar un Label visible o un Literal en la vista
            ClientScript.RegisterStartupScript(this.GetType(), "alert", $"alert('{message}');", true);
        }

        private bool ActualizarPasswordUsuario(string nuevaPassword)
        {
            // Aquí va la lógica real de persistencia. Este es un mock:
            return true;
        }
    }
}