using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;

namespace Web.Usuario
{

    public partial class MiPerfil : System.Web.UI.Page
    {
        private ClienteWSClient clienteWS;
        private ClientScriptManager clientScriptManager;
        public MiPerfil()
        {
            this.clienteWS = new ClienteWSClient();
        }
        protected void Page_Init(object sender, EventArgs e)
        {
            
            this.clientScriptManager = Page.ClientScript;
            if (!IsPostBack)
            {
                int? user = Session["user"] as int?;
                if (user.HasValue)
                {
                    int userId = Convert.ToInt32(Session["user"]);
                    var cliente = clienteWS.searchCliente(userId);
                    Session["direccion_preferida"] = cliente.direccionPreferida;

                    if (cliente != null)
                    {
                        nombre.Value = cliente.nombreCompleto;
                        username.Value = cliente.username;
                        correo.Value = cliente.correoElectronico;
                        telefono.Value = cliente.telefono;
                        direccion.Value = cliente.direccion;

                        // Guardar valores originales en ViewState
                        ViewState["nombre"] = cliente.nombreCompleto;
                        ViewState["username"] = cliente.username;
                        ViewState["correo"] = cliente.correoElectronico;
                        ViewState["telefono"] = cliente.telefono;
                        ViewState["direccion"] = cliente.direccion;
                    }
                }
            }

        }
        protected void Guardar_Click(object sender, EventArgs e)
        {
            int? user = Session["user"] as int?;
            if (!user.HasValue) return;

            // Limpieza de errores anteriores
            lblErrorNombre.Text = "";
            lblErrorUsername.Text = "";
            lblErrorCorreo.Text = "";
            lblErrorTelefono.Text = "";
            lblErrorDireccion.Text = "";

            bool hasError = false;

            string patronInyeccion = @"['"";#--]";

            if (Regex.IsMatch(nombre.Value, patronInyeccion))
            {
                lblErrorNombre.Text = $"Nombre inválido: '{nombre.Value}'";
                hasError = true;
            }

            if (Regex.IsMatch(username.Value, patronInyeccion))
            {
                lblErrorUsername.Text = $"Usuario inválido: '{username.Value}'";
                hasError = true;
            }

            if (Regex.IsMatch(correo.Value, patronInyeccion))
            {
                lblErrorCorreo.Text = $"Correo inválido: '{correo.Value}'";
                hasError = true;
            }
            else if (!Regex.IsMatch(correo.Value, @"^[^@\s]+@[^@\s]+\.[^@\s]+$"))
            {
                lblErrorCorreo.Text = "Formato de correo no válido.";
                hasError = true;
            }

           // if (Regex.IsMatch(telefono.Value, patronInyeccion))
            //{
            //    lblErrorTelefono.Text = "Teléfono inválido.";
            //    hasError = true;
            //}
            else if (!Regex.IsMatch(telefono.Value, @"^\+\d{7,15}$"))
            {
                lblErrorTelefono.Text = "Formato: +[código][número]";
                hasError = true;
            }

            if (Regex.IsMatch(direccion.Value, patronInyeccion))
            {
                lblErrorDireccion.Text = $"Dirección inválida: '{direccion.Value}'";
                hasError = true;
            }

            // Si hay errores, salir
            if (hasError)
            {
                return;
            }

            var cliente = new cliente
            {
                id = user.Value,
                nombreCompleto = nombre.Value,
                username = username.Value,
                correoElectronico = correo.Value,
                telefono = telefono.Value,
                direccion = direccion.Value,
                direccionPreferida=Session["direccion_preferida"].ToString() // Asegúrate de que esta sesión esté configurada correctamente
            };
            bool actualizado;
            try
            {
                 actualizado = clienteWS.updateCliente(cliente);
                if (actualizado)
                {
                    nombre.Attributes.Add("readonly", "true");
                    username.Attributes.Add("readonly", "true");
                    correo.Attributes.Add("readonly", "true");
                    telefono.Attributes.Add("readonly", "true");
                    direccion.Attributes.Add("readonly", "true");

                    guardarBtn.CssClass += " d-none";
                    string scriptStock = "window.onload = function() { showAlert('Actualizado correctamente', 'success'); };";
                    clientScriptManager.RegisterStartupScript(GetType(), "", scriptStock, true);
                }
                else
                {
                    // Aquí puedes usar un mensaje global si quieres
                    lblErrorCorreo.Text = "Error al actualizar el perfil.";
                    string scriptStock = "window.onload = function() { showAlert('Surgió un error al actualizar', 'danger'); };";
                    clientScriptManager.RegisterStartupScript(GetType(), "", scriptStock, true);
                }
            }
            catch (Exception ex)
            {
                string error = ex.ToString(); // o ex.Message si el mensaje directo contiene lo necesario

                switch (true)
                {
                    case bool _ when error.Contains("Duplicate entry") && error.Contains("usuario.username"):
                        lblErrorUsername.Text = "El nombre de usuario ya está en uso. Intenta con otro.";
                        break;

                    case bool _ when error.Contains("Duplicate entry") && error.Contains("usuario.correo"):
                        lblErrorCorreo.Text = "El correo electrónico ya está en uso. Usa uno diferente.";
                        break;

                    default:
                        string scriptStock = "window.onload = function() { showAlert('Ocurrió un error al registrar. Intenta nuevamente.', 'danger'); };";
                        clientScriptManager.RegisterStartupScript(GetType(), "", scriptStock, true);
                        break;
                }
            }

            
        }


        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                int? user = Session["user"] as int?;
                if (user.HasValue)
                {

                    int userId = Convert.ToInt32(Session["user"]);
                    var cliente = clienteWS.searchCliente(userId);
                    Session["direccion_preferida"] = cliente.direccionPreferida;
                    if (cliente != null)
                    {
                        nombre.Value = cliente.nombreCompleto;
                        username.Value = cliente.username;
                        correo.Value = cliente.correoElectronico;
                        telefono.Value = cliente.telefono;
                        direccion.Value = cliente.direccion;
                    }
                }
                else
                {
                    // Redirigir si no hay sesión
                    Response.Redirect("~/Login.aspx");
                }
            }
        }

    }

}