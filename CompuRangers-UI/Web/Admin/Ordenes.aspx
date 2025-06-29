
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
                                <tr>
                                    <td><%# Eval("id") %></td>
                                    <td>
                                        <span class="badge bg-<%# GetBadgeColor(Eval("estado").ToString()) %>">
                                            <%# Eval("estado") %>
                                        </span>
                                    </td>
                                    <td><%# Eval("fecha", "{0:dd MMM yyyy}") %></td>
                                    <td class="text-success fw-bold">$<%# Eval("total", "{0:F2}") %></td>
                                    <td>
                                        <button type="button" class="btn btn-sm btn-outline-primary" data-bs-toggle="modal" data-bs-target='<%# "#modalPedido" + Eval("id") %>'>
                                            <i class="bi bi-eye"></i>
                                        </button>
                                    </td>
                                </tr>

                            </ItemTemplate>
                        </asp:Repeater>
                    </tbody>
                </table>
            </div>
        </div>
    </main>
</asp:Content>





