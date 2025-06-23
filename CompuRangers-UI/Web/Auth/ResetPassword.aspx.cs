using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web.Auth
{
    public partial class ResetPassword : System.Web.UI.Page
    {
        private string token;
        private TokenRecuperacionWSClient tokenWS;
        private UsuarioWSClient userWS;

        private int UserId
        {
            get => ViewState["UserId"] != null ? (int)ViewState["UserId"] : -1;
            set => ViewState["UserId"] = value;
        }

        public ResetPassword()
        {
            this.tokenWS = new TokenRecuperacionWSClient();
            this.userWS = new UsuarioWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                token = Request.QueryString["token"];
                if (string.IsNullOrEmpty(token))
                {
                    ShowError("Token inválido o no proporcionado.");
                    return;
                }

                // Aquí haces una validación desde tu backend:
                tokenRecuperacion tokenValido = tokenWS.searchToken(token);
                if (tokenValido.usado)
                {
                    ShowError("El token ha expirado o no es válido.");
                    btnResetPassword.Enabled = false;
                }
                else
                {
                    UserId = tokenValido.userId;

                }
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
                bool success = userWS.changePassword(UserId, newPassword);
                if (success)
                {
                    Response.Redirect("~/Catalogo/Home.aspx?msg=reset_success");
                }
                else
                {
                    ShowError("Ocurrió un error al actualizar la contraseña.");
                }
        }

        private void ShowError(string message)
        {
            // Puedes usar un Label visible o un Literal en la vista
            ClientScript.RegisterStartupScript(this.GetType(), "alert", $"alert('{message}');", true);
        }
    }
}