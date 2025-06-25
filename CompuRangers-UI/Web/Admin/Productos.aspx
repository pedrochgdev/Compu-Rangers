<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master"
    CodeBehind="Productos.aspx.cs" Inherits="Web.Admin.Productos" %>
<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">
<main class="container mt-4">
    <div class="card shadow-sm p-4">
        <h2 class="mb-4">Búsqueda de Productos</h2>
        <div class="row g-3">
            <div class="col-md-4">
                <label for="txtNombre" class="form-label">Nombre</label>
                <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
            </div>
            <div class="col-md-4">
                <label for="ddlMarca" class="form-label">Marca</label>
                <asp:DropDownList ID="ddlMarca" runat="server" CssClass="form-select" />
            </div>
            <div class="col-md-4">
                <label for="ddlCategoria" class="form-label">Categoría</label>
                <asp:DropDownList ID="ddlCategoria" runat="server" CssClass="form-select" />
            </div>
        </div>
        <div class="text-end mt-3">
            <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-primary" OnClick="btnBuscar_Click" />
        </div>
    </div>

    <div class="mt-5">
        <asp:GridView ID="gvProductos" runat="server" AutoGenerateColumns="false" CssClass="table table-striped table-bordered">
            <Columns>
                <asp:BoundField DataField="id" HeaderText="ID" />
                <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                <asp:BoundField DataField="sku" HeaderText="SKU" />
                <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
                <asp:BoundField DataField="precioVenta" HeaderText="Precio" DataFormatString="{0:C}" />
            </Columns>
        </asp:GridView>
    </div>
</main>
</asp:Content>
