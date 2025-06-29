
<%@ Page Title="Ordenes del admin" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Ordenes.aspx.cs" Inherits="Web.Admin.Ordenes" %>

<asp:Content ID="PurchaseHistoryContent" ContentPlaceHolderID="MainContent" runat="server">
    <main class="py-4">
        <div class="container">
            <h3 class="mb-4 text-center fw-bold">Historial de Compras</h3>

            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Pedido #</th>
                            <th>Estado</th>
                            <th>Fecha</th>
                            <th>Total</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <asp:Repeater ID="rptHistorialCompras" runat="server">
                            <ItemTemplate>
                                <tr class="accordion-toggle" data-target='<%# "#detalles" + Eval("id") %>'>
                                    <td><%# Eval("id") %></td>
                                    <td>
                                        <span class="badge bg-<%# GetBadgeColor(Eval("estado").ToString()) %>">
                                            <%# Eval("estado") %>
                                        </span>
                                    </td>
                                    <td><%# Eval("fecha", "{0:dd MMM yyyy}") %></td>
                                    <td class="text-success fw-bold">$<%# Eval("total", "{0:F2}") %></td>
                                    <td>
                                        <i class="bi bi-chevron-down"></i>
                                    </td>
                                </tr>
                                <tr class="collapse-row" id='<%# "detalles" + Eval("id") %>' style="display: none;">
                                    <td colspan="5">
                                        <asp:Repeater ID="rptDetalle" runat="server" DataSource='<%# Eval("detalles") %>'>
                                            <HeaderTemplate>
                                                <table class="table table-bordered table-sm mb-0">
                                                    <thead>
                                                        <tr>
                                                            <th>Producto</th>
                                                            <th class="text-center">Cantidad</th>
                                                            <th class="text-end">Subtotal</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                            </HeaderTemplate>
                                            <ItemTemplate>
                                                <tr>
                                                    <td><%# Eval("producto.producto.nombre") %></td>
                                                    <td class="text-center"><%# Eval("cantidad") %></td>
                                                    <td class="text-end">$<%# Eval("subtotal", "{0:F2}") %></td>
                                                </tr>
                                            </ItemTemplate>
                                            <FooterTemplate>
                                                    </tbody>
                                                </table>
                                            </FooterTemplate>
                                        </asp:Repeater>
                                    </td>
                                </tr>

                            </ItemTemplate>
                        </asp:Repeater>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            const rows = document.querySelectorAll('tr.accordion-toggle');

            rows.forEach(row => {
                row.addEventListener('click', function () {
                    const targetId = row.getAttribute('data-target');
                    const targetRow = document.querySelector(targetId);

                    if (!targetRow) return;

                    const isVisible = targetRow.style.display !== 'none';

                    // Ocultar todos
                    document.querySelectorAll('tr.collapse-row').forEach(r => r.style.display = 'none');

                    // Mostrar si estaba oculto
                    if (!isVisible) {
                        targetRow.style.display = 'table-row';
                    }
                });
            });
        });
    </script>


</asp:Content>





