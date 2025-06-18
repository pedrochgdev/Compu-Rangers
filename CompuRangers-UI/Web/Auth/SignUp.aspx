<%@ Page Title="SignUp Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="SignUp.aspx.cs" Inherits="Web.SignUp" %>

<asp:Content ID="SignUpContent" ContentPlaceHolderID="MainContent" runat="server">
    <main class="bg-light min-vh-100 d-flex justify-content-center align-items-center">

        <div class="card shadow border-0 rounded-4 p-4" style="max-width: 720px; width: 100%;">
            <div class="text-center mb-4">
                <h1 class="fw-bold" style="font-size: 2rem;">
                    <span style="color: black;">COMPU</span>
                    <span style="color: #EB484C;">RANGERS</span>
                </h1>
                <p class="text-muted mb-0">Crea una nueva cuenta</p>
            </div>

 
                <div class="row g-3">
                    <div class="col-md-6">
                        <label for="username" class="form-label">Usuario</label>
                        <asp:TextBox ID="txtUsername" runat="server" CssClass="form-control" placeholder="Ej. drocho01" required="true" />
                    </div>

                    <div class="col-md-6">
                        <label for="nombre" class="form-label">Nombre completo</label>
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Ej. Juan Pérez" required="true" />
                    </div>

                    <div class="col-md-6">
                        <label for="telefono" class="form-label">Teléfono</label>
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" placeholder="+51XXXXXXXXX" required="true" />
                        <div class="form-text">Formato: +[código][número]</div>
                    </div>

                    <div class="col-md-6">
                        <label for="correo" class="form-label">Correo electrónico</label>
                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" placeholder="Ej. usuario@correo.com" required="true" />
                    </div>

                    <div class="col-12">
                        <label for="direccion" class="form-label">Dirección</label>
                        <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control" placeholder="Dirección completa" required="true" />
                    </div>

                    <div class="col-md-6">
                        <label for="password" class="form-label">Contraseña</label>
                        <asp:TextBox ID="txtPassword" runat="server" TextMode="Password" CssClass="form-control" placeholder="Crea una contraseña" required="true" />
                    </div>

                    <div class="col-md-6">
                        <label for="password_confirmation" class="form-label">Confirmar contraseña</label>
                        <asp:TextBox ID="txtConfirmarPassword" runat="server" TextMode="Password" CssClass="form-control" placeholder="Repite la contraseña" required="true" />
                    </div>
                </div>

                <div class="mt-4">
                    <asp:Button ID="btnRegistrarse" runat="server" CssClass="btn btn-danger w-100 py-2" Text="Registrarse" OnClick="btnRegistrarse_Click" />
                </div>

                <div class="text-center mt-3">
                    <small>¿Ya tienes cuenta? 
                        <asp:LinkButton runat="server" OnClientClick="showModal('form-modal-login'); return false;" class="text-decoration-none" style="color: #EB484C;">Inicia sesión</asp:LinkButton>
                    </small>
                </div>

        </div>

    </main>
</asp:Content>