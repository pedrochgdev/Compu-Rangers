<%@ Page Title="Home Page" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Web.Home" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">
    <script src="../Scripts/CompuRangers/Login.js"></script>

    <main>
        
            <div class="row g-3 mb-4">
                <div class="col-md-4">
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Buscar por nombre" />
                </div>
                <div class="col-md-3">
                    <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="ddlCategoria_SelectedIndexChanged" />
                </div>
                <div class="col-md-3">
                    <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control" placeholder="Precio máximo" TextMode="Number" />
                </div>
                <div class="col-md-2">
                    <asp:Button ID="btnFiltrar" runat="server" CssClass="btn btn-primary w-100" Text="Filtrar" OnClick="btnFiltrar_Click" />
                </div>
            </div>

            <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <div class="col">
                            <div class="card h-100">
                                <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="producto">
                                <div class="card-body">
                                    <asp:HiddenField ID="hiddenId" runat="server" Value='<%# Eval("id") %>' />
                                    <h5 id="lblNombre" runat="server" class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text"><%# Eval("descripcion") %></p>
                                    <div class="d-flex gap-2">
                                        <span class="badge bg-secondary"><%# Eval("categoria.nombre") %></span>
                                    </div>
                                </div>
                                <div class="card-footer d-flex justify-content-between align-items-center">
                                    <strong id="lblPrecioVenta" runat="server" class="text-primary">$<%# Eval("precioVenta", "{0:N2}") %></strong>
                                    <div class="d-flex flex-row justify-content-end align-item-center gap-2">
                                        <a class="btn btn-sm btn-warning" href='VerMas.aspx?id=<%# Eval("id") %>'>Ver más</a>
                                        <asp:LinkButton ID="btnAddCart" runat="server" CssClass="btn btn-sm btn-success" OnClick="btnAddCart" Text="Añadir al carrito"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
    </main>

</asp:Content>
