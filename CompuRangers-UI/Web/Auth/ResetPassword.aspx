<%@ Page Title="Reset Password Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="ResetPassword.aspx.cs" Inherits="Web.Auth.ResetPassword" %>

<asp:Content ID="ResetPassword" ContentPlaceHolderID="MainContent" runat="server">
    <div class="d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg" style="width: 100%; max-width: 500px;">
            <div class="card-body p-5">
                <div class="text-center mb-4">
                    <span class="fw-bold" style="color: black; font-size: calc(1.5rem + 0.3vw);">COMPU</span>
                    <span class="fw-bold" style="color: #EB484C; font-size: calc(1.5rem + 0.3vw);">RANGERS</span>
                </div>

                <h5 class="text-center mb-4">Establecer nueva contraseña</h5>
                <p class="small text-center text-muted mb-4">Ingresa tu nueva contraseña y confírmala para continuar.</p>

                <div class="mb-3">
                    <label for="newPassword" class="form-label">Nueva contraseña</label>
                    <asp:TextBox ID="txtNewPassword" runat="server"
                                 Style="width: 100% !important; max-width: 100% !important;"
                                 CssClass="form-control"
                                 TextMode="Password"
                                 placeholder="Nueva contraseña" />
                </div>

                <div class="mb-4">
                    <label for="confirmPassword" class="form-label">Confirmar contraseña</label>
                    <asp:TextBox ID="txtConfirmPassword" runat="server"
                                 Style="width: 100% !important; max-width: 100% !important;"
                                 CssClass="form-control"
                                 TextMode="Password"
                                 placeholder="Confirma tu contraseña" />
                </div>

                <div class="d-flex justify-content-center">
                    <asp:Button runat="server"
                                ID="btnResetPassword"
                                Text="Guardar nueva contraseña"
                                CssClass="btn btn-danger w-100 py-2"
                                OnClick="btnResetPassword_Click" />
                </div>

                <div class="mt-3 text-center">
                    <p class="small">¿Ya tienes cuenta? 
                        <asp:LinkButton runat="server"
                                        OnClientClick="showModal('form-modal-login'); return false;"
                                        class="text-decoration-none"
                                        style="color: #EB484C;">Inicia sesión</asp:LinkButton>
                    </p>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
