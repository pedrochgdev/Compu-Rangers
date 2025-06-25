<%@ Page Title="VerMas" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="VerMas.aspx.cs" Inherits="Web.VerMas" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">
    <main class="container py-4">
        <div class="row product-container">
            <asp:HiddenField ID="hfIdProducto" runat="server" />
            <!-- Imagen -->
            <div class="col-md-4">
                <asp:Image ID="imgProducto" runat="server" CssClass="img-fluid rounded" />
            </div>

            <!-- Datos -->
            <div class="col-md-8">
                <h2 class="mb-1"><asp:Literal ID="litNombre" runat="server" /></h2>
                <div class="mb-2">
                    <asp:Literal ID="litCategorias" runat="server" />
                </div>
                <h4 class="text-success"><asp:Literal ID="litPrecio" runat="server" /></h4>

                <div class="d-flex align-items-center mb-3">
                    <button class="btn btn-outline-secondary" type="button" onclick="decreaseQuantity()">-</button>
                    <asp:TextBox ID="quantity" runat="server" ClientIDMode="Static" CssClass="form-control text-center mx-2" style="width: 60px;" Text="1" />
                    <button class="btn btn-outline-secondary" type="button" onclick="increaseQuantity()">+</button>
                </div>

                <asp:Button ID="btnAgregarCarrito" runat="server" Text="Añadir al carrito" CssClass="btn btn-success" OnClick="btnAgregarCarrito_Click" />

            </div>

            <!-- Descripción -->
            <div class="col-12 mt-4">
                <h5>Descripción</h5>
                <p><asp:Literal ID="litDescripcion" runat="server" /></p>
            </div>
        </div>

        <hr />

        <!-- Productos sugeridos -->
        <h4 class="mt-5">También te puede interesar</h4>
        <div class="overflow-auto">
            <div class="d-flex flex-row" style="gap: 1rem;">
                <asp:Repeater ID="rptSugeridos" runat="server">
                    <ItemTemplate>
                        <a href='<%# "VerMas.aspx?id=" + Eval("producto.id") %>' class="text-decoration-none text-dark">
                            <div class="card flex-shrink-0" style="width: 200px;">
                                <img src="https://www.shutterstock.com/image-photo/set-home-kitchen-appliances-room-260nw-2473408983.jpg" class="card-img-top" alt="Producto" />
                                <div class="card-body">
                                    <h6 class="card-title"><%# Eval("producto.nombre") %></h6>
                                    <p class="text-success mb-0"><%# Eval("producto.precioVenta", "{0:C2}") %></p>
                                </div>
                            </div>
                        </a>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>
    </main>
    <script>
    function increaseQuantity() {
        const input = document.getElementById('quantity');
        let value = parseInt(input.value) || 1;
        input.value = value + 1;
    }

    function decreaseQuantity() {
        const input = document.getElementById('quantity');
        let value = parseInt(input.value) || 1;
        if (value > 1) input.value = value - 1;
    }
                    </script>
</asp:Content>
