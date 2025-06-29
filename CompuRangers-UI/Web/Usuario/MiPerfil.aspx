<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="MiPerfil.aspx.cs" Inherits="Web.Usuario.MiPerfil" %>

<asp:Content ID="PerfilContent" ContentPlaceHolderID="MainContent" runat="server">
    <main class="py-4">
        <style>
            input.editable {
                background-color: #fff9f9;
                border: 2px solid #dc3545;
                box-shadow: 0 0 4px rgba(220, 53, 69, 0.5);
            }
        </style>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-xl-8 col-lg-10 col-md-12">
                    <div class="card shadow-lg border-0">
                        <div class="card-body p-4">
                            <div class="d-flex align-items-center mb-4">
                                <div class="bg-danger text-white rounded-circle d-flex align-items-center justify-content-center" style="width: 60px; height: 60px;">
                                    <i class="bi bi-person-fill fs-3"></i>
                                </div>
                                <div class="ms-3">
                                    <h4 class="mb-0">Perfil de Usuario</h4>
                                    <small class="text-muted">Consulta y edita tu información personal</small>
                                </div>
                            </div>

                            <asp:Panel ID="perfilForm" runat="server" DefaultButton="guardarBtn">
                                <div class="row g-3">
                                    <div class="col-md-6">
                                        <label for="nombre" class="form-label">
                                            <i class="bi bi-person me-1 text-danger"></i> Nombre completo
                                        </label>
                                        <input type="text" id="nombre" runat="server" class="form-control" readonly />
                                        <asp:Label ID="lblErrorNombre" runat="server" CssClass="text-danger" EnableViewState="false" />
                                    </div>

                                    <div class="col-md-6">
                                        <label for="username" class="form-label">
                                            <i class="bi bi-person-badge me-1 text-danger"></i> Usuario
                                        </label>
                                        <input type="text" id="username" runat="server" class="form-control" readonly />
                                        <asp:Label ID="lblErrorUsername" runat="server" CssClass="text-danger" EnableViewState="false" />
                                    </div>

                                    <div class="col-md-6">
                                        <label for="correo" class="form-label">
                                            <i class="bi bi-envelope me-1 text-danger"></i> Correo electrónico
                                        </label>
                                        <input type="email" id="correo" runat="server" class="form-control" readonly />
                                         <asp:Label ID="lblErrorCorreo" runat="server" CssClass="text-danger" EnableViewState="false" />
                                    </div>

                                    <div class="col-md-6">
                                        <label for="telefono" class="form-label">
                                            <i class="bi bi-phone me-1 text-danger"></i> Teléfono
                                        </label>
                                        <input type="text" id="telefono" runat="server" class="form-control" readonly />
                                         <asp:Label ID="lblErrorTelefono" runat="server" CssClass="text-danger" EnableViewState="false" />
                                    </div>

                                    <div class="col-12">
                                        <label for="direccion" class="form-label">
                                            <i class="bi bi-geo-alt me-1 text-danger"></i> Dirección
                                        </label>
                                        <input type="text" id="direccion" runat="server" class="form-control" readonly />
                                         <asp:Label ID="lblErrorDireccion" runat="server" CssClass="text-danger" EnableViewState="false" />
                                    </div>
                                </div>

                                <div class="d-flex justify-content-end gap-2 mt-4">
                                    <button type="button" class="btn btn-outline-danger" onclick="activarEdicion()">
                                        <i class="bi bi-pencil-square me-1"></i> Editar
                                    </button>
                                     <asp:Button ID="guardarBtn" runat="server" CssClass="btn btn-danger d-none" OnClick="Guardar_Click" Text="Guardar" />
                                </div>
                            </asp:Panel>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script type="text/javascript">
        function activarEdicion() {
            const ids = ['<%= nombre.ClientID %>', '<%= username.ClientID %>', '<%= correo.ClientID %>', '<%= telefono.ClientID %>', '<%= direccion.ClientID %>'];
        ids.forEach(function(id) {
            const input = document.getElementById(id);
            input.removeAttribute("readonly");
            input.classList.add("editable");
        });

        const btn = document.getElementById('<%= guardarBtn.ClientID %>');
            if (btn) btn.classList.remove("d-none");
        }
    </script>
</asp:Content>




