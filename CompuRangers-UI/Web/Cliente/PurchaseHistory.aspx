<%@ Page Title="Purchase History Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="PurchaseHistory.aspx.cs" Inherits="Web.PurchaseHistory" %>


<asp:Content ID="PruchaseHistoryContent" ContentPlaceHolderID="MainContent" runat="server">
    <main>
        <div class="container py-4">
            <h3 class="mb-4 text-center fw-bold">Historial de Compras</h3>
            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8">

                    <asp:Repeater ID="rptHistorialCompras" runat="server">
                        <ItemTemplate>
                            <div class="card mb-4 shadow-sm border-0 rounded-4">
                                <div class="card-body">
                                    <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center mb-3">
                                        <div>
                                            <span class="fw-semibold">Pedido #<%# Eval("id") %></span>
                                            <span class="badge bg-<%# GetBadgeColor(Eval("estado").ToString()) %> ms-2">
                                                <%# Eval("estado") %>
                                            </span>
                                        </div>
                                        <span class="text-muted small mt-2 mt-md-0">
                                            <i class="bi bi-calendar3"></i> <%# Eval("fecha", "{0:dd MMMM yyyy}") %>
                                        </span>
                                    </div>
                                    <asp:Repeater ID="rptDetalle" runat="server" DataSource='<%# Eval("detalles") %>'>
                                        <HeaderTemplate>
                                            <table class="table mb-2 table-borderless align-middle">
                                                <thead class="table-light">
                                                <tr>
                                                    <th class="ps-0">Producto</th>
                                                    <th class="text-center">Cant.</th>
                                                    <th class="text-end pe-0">Subtotal</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                        </HeaderTemplate>
                                        <ItemTemplate>
                                            <tr>
                                                <td class="ps-0"><%# Eval("producto.producto.nombre") %></td>
                                                <td class="text-center"><%# Eval("cantidad") %></td>
                                                <td class="text-end pe-0"><%# Eval("subtotal", "{0:C2}") %></td>
                                            </tr>
                                        </ItemTemplate>
                                        <FooterTemplate>
                                                </tbody>
                                            </table>
                                        </FooterTemplate>
                                    </asp:Repeater>
                                    <div class="d-flex justify-content-end">
                                        <div>
                                            <div class="fw-bold fs-5 text-success">Total: <%# Eval("total", "{0:C2}") %></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>


                    <!-- Puedes duplicar más compras aquí -->

                </div>
            </div>
        </div>
    </main>
</asp:Content>
