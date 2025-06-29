<%@ Page Title="Password Recovery Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="PasswordRecovery.aspx.cs" Inherits="Web.Auth.PasswordRecovery" %>

<asp:Content ID="PasswordRecovery" ContentPlaceHolderID="MainContent" runat="server">
   <div class="d-flex justify-content-center align-items-center vh-100" style="max-width: 720px; width: 100%;">
        <div class="card shadow-lg" style="width: 100%; max-width: 500px;">
            <div class="card-body p-5">
                <div class="text-center mb-4">
                    <span class="fw-bold" style="color: black; font-size: calc(1.5rem + 0.3vw);">COMPU</span>
                    <span class="fw-bold" style="color: #EB484C; font-size: calc(1.5rem + 0.3vw);">RANGERS</span>
                </div>

                <h5 class="text-center mb-4">¿Olvidaste tu contraseña?</h5>
                <p class="small text-center text-muted mb-4">Introduce tu correo y te enviaremos instrucciones para restablecerla.</p>


                    <div class="mb-3 d-flex flex-column justify-content-center align-items-center">
                        <div class="text-start w-100">
                            <label for="email" class="form-label">Correo electrónico</label>
                        </div>
                        <div class="w-100">
                            <asp:TextBox ID="txtEmail" runat="server"
                                         Style="width: 100% !important; max-width: 100% !important;"
                                         CssClass="form-control w-100"
                                         TextMode="Email"
                                         name="email"
                                         placeholder="Tu correo" />
                        </div>
                    </div>

                    <div class="d-flex justify-content-center">
                        <asp:Button runat="server" type="submit" class="btn btn-danger w-100 py-2" Text="Enviar enlace" OnClick="btnEnviar_Click" style="width:100%"/>
                    </div>

                <div class="mt-3 text-center">
                    <p class="small">¿Ya lo recordaste? <asp:LinkButton runat="server" OnClientClick="showModal('form-modal-login'); return false;" class="text-decoration-none" style="color: #EB484C;">Inicia sesión</asp:LinkButton></p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
