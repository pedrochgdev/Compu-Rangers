<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master" CodeBehind="Ventas.aspx.cs" Inherits="Web.Ventas" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">

    <main>
        <h2 class="mb-4">Dashboard de Ventas</h2>
        <!-- Métricas principales -->
        <div class="row g-4 mb-4">
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Total Ventas</h6>
                        <h2 class="fw-bold text-success">$<asp:Label ID="lblTotalVentas" runat="server" Text="0.00" /></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Ganancias Mes</h6>
                        <h2 class="fw-bold text-primary">$<asp:Label ID="lblGananciaMes" runat="server" Text="0.00" /></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Pedidos Hoy</h6>
                        <h2 class="fw-bold text-info"><asp:Label ID="lblPedidosHoy" runat="server" Text="0" /></h2>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card shadow-sm border-0">
                    <div class="card-body text-center">
                        <h6 class="card-title text-muted">Clientes Nuevos</h6>
                        <h2 class="fw-bold text-warning"><asp:Label ID="lblClientesNuevos" runat="server" Text="0" /></h2>
                    </div>
                </div>
            </div>
        </div>

        <!-- Ganancias por mes -->
        <div class="card mb-4 shadow-sm border-0">
            <div class="card-header bg-white">
                <h5 class="mb-0">Ganancias por mes</h5>
            </div>
            <div class="card-body">
                <div class="row text-center">
                    <asp:Repeater ID="rptGananciasMensuales" runat="server">
                        <ItemTemplate>
                            <div class="col text-center">
                                <div class="fw-bold"><%# ((DateTime)Eval("Mes")).ToString("MMMM yyyy", System.Globalization.CultureInfo.CreateSpecificCulture("es-ES")) %></div>
                                <div>$<%# Eval("Ganancia", "{0:N2}") %></div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>
            </div>
        </div>

        <!-- Productos más vendidos -->
        <asp:Repeater ID="rptMasVendidos" runat="server">
            <ItemTemplate>
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <%# Eval("nombre") %>
                    <span class="badge bg-success rounded-pill"><%# Eval("cantidadVendida") %></span>
                </li>
            </ItemTemplate>
        </asp:Repeater>


        <!-- Pedidos por día -->
        <asp:Repeater ID="rptPedidosPorDia" runat="server">
            <ItemTemplate>
                <div class="col text-center">
                    <div class="fw-bold"><%# String.Format("{0:dd MMM}", Eval("dia")) %></div>
                    <div><%# Eval("cantidad") %></div>
                </div>
            </ItemTemplate>
        </asp:Repeater>

    </main>
</asp:Content>
