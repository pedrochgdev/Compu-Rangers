using System;
using System.Collections.Generic;
using System.Linq;
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

            if (password != confirmarPassword)
            {
                // Puedes mostrar un mensaje de error en pantalla usando un Label
                // lblError.Text = "Las contraseñas no coinciden.";
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

            clientWS.addCliente(cli);
            // Aquí podrías guardar los datos en la base de datos
            // Ejemplo ficticio:
            // Usuario nuevoUsuario = new Usuario(username, nombre, telefono, correo, direccion, password);
            // UsuarioDAO.Insertar(nuevoUsuario);

            // También puedes redirigir o mostrar un mensaje
            Response.Redirect("../Catalogo/Home.aspx");
        }
    }
}