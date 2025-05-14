<%@ Page Title="LogIn Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="LogIn.aspx.cs" Inherits="Web.LogIn" %>

<asp:Content ID="LogInContent" ContentPlaceHolderID="MainContent" runat="server">

    <main>
         <div class="d-flex justify-content-center align-items-center vh-100 bg-dark">
            <div class="card shadow-lg" style="width: 100%; max-width: 400px;">
                <div class="card-body p-5">
                    <div class="text-center mb-4">
                        <span class="fw-bold" style="color: black; font-size: calc(1.5rem + 0.3vw);">COMPU</span>
                        <span class="fw-bold" style="color: #EB484C; font-size: calc(1.5rem + 0.3vw);">RANGERS</span>
                    </div>

                    <h5 class="text-center mb-4">Inicia sesión en tu cuenta</h5>

                    <form method="POST">
                        <div class="mb-3">
                            <label for="email" class="form-label">Correo electrónico</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="Introduce tu correo" required>
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Introduce tu contraseña" required>
                        </div>

                        <div class="d-flex justify-content-between mb-3">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="remember" name="remember">
                                <label class="form-check-label" for="remember">
                                    Recordarme
                                </label>
                            </div>
                            <a href="#" class="text-decoration-none small" style="color: #EB484C;">¿Olvidaste tu contraseña?</a>
                        </div>

                        <button type="submit" class="btn btn-danger w-100 py-2">Iniciar sesión</button>
                    </form>

                    <div class="mt-3 text-center">
                        <p class="small">¿No tienes cuenta? <a href="#" class="text-decoration-none" style="color: #EB484C;">Regístrate aquí</a></p>
                    </div>
                </div>
            </div>
        </div>
    </main>

</asp:Content>

