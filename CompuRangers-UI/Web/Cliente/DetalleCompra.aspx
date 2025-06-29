<%@ Page Title="Detalle Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="DetalleCompra.aspx.cs" Inherits="Web.DetalleCompra" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">

    <main>
        <h2 class="mb-4">Confirmar Pago</h2>
        <asp:Repeater ID="rptConfirmacionCompra" runat="server">
            <ItemTemplate>
                <div class="card shadow-sm p-3 mb-2">
                    <div class="row g-2 align-items-center">
                        <!-- Imagen -->
                        <div class="col-3 col-md-1">
                            <img src='<%# Eval("producto.id", "/MostrarImagen.ashx?id={0}") %>'
                                    class="img-fluid rounded"
                                    style="object-fit: contain;">  
                        </div>
                        <!-- Nombre y precio unitario -->
                        <div class="col-9 col-md-5">
                            <h6 class="mb-1"><%# Eval("producto.nombre") %></h6>
                            <small class="text-muted">Precio unitario: $<%# Eval("producto.precioVenta", "{0:N2}") %></small>
                        </div>

                        <!-- Cantidad -->
                        <div class="col-6 col-md-3 text-center">
                            <span class="fw-semibold">Cantidad: <%# Eval("cantidad") %></span>
                        </div>

                        <!-- Total -->
                        <div class="col-6 col-md-3 text-end">
                            <strong class="d-block">Total: $<%# Eval("subtotal", "{0:N2}") %></strong>
                        </div>
                    </div>
                </div>
            </ItemTemplate>
        </asp:Repeater>


        <hr class="my-4">

        <div class="text-end">
            <h5 class="mb-3">Total del Pedido: <strong>$<asp:Label ID="lblTotalPedido" runat="server" Text="0.00" /></strong></h5>

            <asp:LinkButton ID="btnMostrarModal" runat="server" OnClientClick="showModal('modalDireccion'); return false;" CssClass="btn btn-primary btn-lg">Pagar</asp:LinkButton>
        </div>
    </main>
    <!-- Modal para ingresar dirección -->
    <div class="modal fade" id="modalDireccion" tabindex="-1" aria-labelledby="modalDireccionLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Confirmar Dirección</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <asp:Label ID="lblInfoDireccion" runat="server" CssClass="form-label" Text="Dirección de entrega:" />
                    <asp:TextBox ID="txtDireccion" runat="server" CssClass="form-control" />
                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnConfirmarPago" runat="server" CssClass="btn btn-success" Text="Confirmar Pago" OnClick="ConfirmarPago_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>


</asp:Content>
