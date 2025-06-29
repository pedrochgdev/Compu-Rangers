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

    <!-- GridView fuera del modal y bien definido -->
    <asp:GridView ID="gvProductos" runat="server"
        AutoGenerateColumns="false"
        CssClass="table table-striped table-hover table-bordered"
        AllowPaging="true"
        PageSize="10"
        OnPageIndexChanging="gvProductos_PageIndexChanging"
        OnRowCreated="gvProductos_RowCreated"
        OnRowCommand="gvProductos_RowCommand">

        <Columns>
            <asp:BoundField DataField="id" HeaderText="ID" />
            <asp:BoundField DataField="nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="sku" HeaderText="SKU" />
            <asp:BoundField DataField="descripcion" HeaderText="Descripción" />
            <asp:BoundField DataField="precioVenta" HeaderText="Precio" DataFormatString="{0:C}" />
            <asp:TemplateField HeaderText="Acciones">
                <ItemTemplate>
                    <a href="#" 
                       class="btn btn-sm btn-outline-primary me-2 btn-editar" 
                       data-id='<%# Eval("id") %>' 
                       data-nombre='<%# Eval("nombre") %>' 
                       data-img='/MostrarImagen.ashx?id=<%# Eval("id") %>'>
                       <i class="bi bi-pencil-fill"></i>
                    </a>
                </ItemTemplate>
            </asp:TemplateField>
        </Columns>
    </asp:GridView>

    <!-- Modal para editar producto con UpdatePanel para postback parcial -->
            <div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editarModalLabel">Editar Banner del Producto</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                        </div>
                        <div class="modal-body">
                            <asp:HiddenField ID="hfProductoId" runat="server" />

                            <div class="mb-3">
                                <label class="form-label">Nombre del producto</label>
                                <asp:Label ID="lblNombreProducto" runat="server" CssClass="form-control" />
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Imagen actual</label><br />
                                <asp:Image ID="imgActual" runat="server" CssClass="img-thumbnail" Width="150" />
                            </div>

                            <div class="mb-3">
                                <label for="fuBanner" class="form-label">Nuevo Banner Promocional</label>
                                <asp:FileUpload ID="fuBanner" runat="server" CssClass="form-control" />
                            </div>
                        </div>
                        <div class="modal-footer">
                            <asp:Button ID="btnGuardarBanner" runat="server" Text="Guardar cambios" CssClass="btn btn-success" OnClick="btnGuardarBanner_Click" />
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>

</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelectorAll('.btn-editar').forEach(btn => {
            btn.addEventListener('click', function (e) {
                e.preventDefault();

                const id = this.dataset.id;
                const nombre = this.dataset.nombre;
                const imgUrl = this.dataset.img;

                document.getElementById('<%= hfProductoId.ClientID %>').value = id;
                document.getElementById('<%= lblNombreProducto.ClientID %>').innerText = nombre;

        const img = document.getElementById('<%= imgActual.ClientID %>');
          img.src = imgUrl;

          const modal = new bootstrap.Modal(document.getElementById('editarModal'));
          modal.show();
      });
    });
  });
</script>
</main>
</asp:Content>

