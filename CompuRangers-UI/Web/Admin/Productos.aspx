<%@ Page Language="C#" AutoEventWireup="true" MasterPageFile="~/Site.Master"
    CodeBehind="Productos.aspx.cs" Inherits="Web.Admin.Productos" %>

<asp:Content ID="HomeContent" ContentPlaceHolderID="MainContent" runat="server">
<main class="container py-4">
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
        <div class="text-end mt-2">
            <asp:Button ID="btnLimpiar" runat="server" Text="Limpiar" CssClass="btn btn-secondary me-2" OnClick="btnLimpiar_Click" />
            <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-primary" OnClick="btnBuscar_Click" />
        </div>
    </div>

    <div class="card mt-5 shadow-sm p-3">
       <asp:GridView ID="gvProductos" runat="server"
            AutoGenerateColumns="false"
            CssClass="table table-striped table-hover table-bordered"
            AllowPaging="true"
            PageSize="10"
            OnPageIndexChanging="gvProductos_PageIndexChanging"
            OnRowCreated="gvProductos_RowCreated">
            <Columns>
                <asp:BoundField DataField="id" HeaderText="ID" />
                <asp:BoundField DataField="nombre" HeaderText="Nombre" />
                <asp:BoundField DataField="sku" HeaderText="SKU" />
                <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
                <asp:BoundField DataField="precioVenta" HeaderText="Precio" DataFormatString="{0:C}" />
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <a href='<%# "EditarProducto.aspx?id=" + Eval("id") %>' class="btn btn-sm btn-outline-primary me-2" title="Editar">
                            <i class="bi bi-pencil-fill"></i>
                        </a>
                        <a href='<%# "VerProducto.aspx?id=" + Eval("id") %>' class="btn btn-sm btn-outline-secondary" title="Ver detalles">
                            <i class="bi bi-eye-fill"></i>
                        </a>
                    </ItemTemplate>
                </asp:TemplateField>

            </Columns>
        </asp:GridView>


    </div>
</main>
</asp:Content>
