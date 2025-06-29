using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web
{
    public partial class SignUp : Page
    {
        private readonly ClienteWSClient clientWS;

        public SignUp()
        {
            this.clientWS = new ClienteWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnRegistrarse_Click(object sender, EventArgs e)
        {
            string username = txtUsername.Text.Trim();
            string nombre = txtNombre.Text.Trim();
            string telefono = txtTelefono.Text.Trim();
            string correo = txtCorreo.Text.Trim();
            string direccion = txtDireccion.Text.Trim();
            string password = txtPassword.Text;
            string confirmarPassword = txtConfirmarPassword.Text;

            string patronInyeccion = @"['"";#--]";
            if (System.Text.RegularExpressions.Regex.IsMatch(username, patronInyeccion) ||
                System.Text.RegularExpressions.Regex.IsMatch(nombre, patronInyeccion) ||
                //System.Text.RegularExpressions.Regex.IsMatch(telefono, patronInyeccion) ||
                System.Text.RegularExpressions.Regex.IsMatch(correo, patronInyeccion) ||
                System.Text.RegularExpressions.Regex.IsMatch(direccion, patronInyeccion) ||
                System.Text.RegularExpressions.Regex.IsMatch(password, patronInyeccion))
            {
                lblError.Text = "Entrada inválida. No se permiten caracteres especiales.";
                return;
            }
            if (!System.Text.RegularExpressions.Regex.IsMatch(correo, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                lblError.Text = "El formato del correo electrónico no es válido.";
                return;
            }
            if (!Regex.IsMatch(telefono, @"^\+\d{7,15}$"))
            {
                lblError.Text = "Formato inválido de teléfono. Usa: +[código][número]. Solo se permiten números.";
                return;
            }

            if (password != confirmarPassword)
            {
                lblError.Text = "Las contraseñas no coinciden.";
                return;
            }

            cliente cli = new cliente
            {
                admin = false,
                contrasena = password,
                correoElectronico = correo,
                direccion = direccion,
                nombreCompleto = nombre,
                telefono = telefono,
                username = username
            };

            try
            {
                clientWS.addCliente(cli);
                Response.Redirect("../Catalogo/Home.aspx");
            }
            catch (Exception ex)
            {
                string error = ex.ToString(); // o ex.Message si el mensaje directo contiene lo necesario

                switch (true)
                {
                    case bool _ when error.Contains("Duplicate entry") && error.Contains("usuario.username"):
                        lblError.Text = "El nombre de usuario ya está en uso. Intenta con otro.";
                        break;

                    case bool _ when error.Contains("Duplicate entry") && error.Contains("usuario.correo"):
                        lblError.Text = "El correo electrónico ya está en uso. Usa uno diferente.";
                        break;

                    default:
                        lblError.Text = "Ocurrió un error al registrar. Intenta nuevamente.";
                        break;
                }
            }
        }
    }
}