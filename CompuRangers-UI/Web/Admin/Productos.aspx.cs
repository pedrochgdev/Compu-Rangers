using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;
namespace Web.Admin
{
    public partial class Productos : Web.Middleware.AdminPage
    {
        private ProductoWSClient productoWS;
        private readonly CategoriaWSClient categoriaWS;
        private readonly MarcaWSClient marcaWS;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarFiltros();
                CargarProductos(); // carga inicial
            }
        }
        public Productos()
        {
            this.productoWS = new ProductoWSClient();
            this.categoriaWS = new CategoriaWSClient();
            this.marcaWS = new MarcaWSClient();
        }
        private void CargarFiltros()
        {
            // Cargar marcas y categorías al Dropdown
            // Suponiendo que ya tienes métodos en tu WS o manejas de otra fuente

            // Ejemplo simulado (reemplaza con llamada real o valores estáticos):
            categoria[] categoriasRaiz = categoriaWS.getAllCategorias();
            List<categoria> hojas = ObtenerCategoriasHoja(categoriasRaiz.ToList());

            ddlCategoria.Items.Clear();
            ddlCategoria.Items.Add(new ListItem("Todas las categorías", ""));

            foreach (categoria hoja in hojas)
            {
                ddlCategoria.Items.Add(new ListItem(hoja.nombre, Convert.ToString(hoja.id)));
            }
            ddlMarca.Items.Clear();
            marca[] marcas = marcaWS.getAllMarcas();
            ddlMarca.Items.Add(new ListItem("Todas las marcas", ""));
            foreach (marca marca in marcas)
            {
                ddlMarca.Items.Add(new ListItem(marca.nombre, Convert.ToString(marca.id)));
            }
        }
        private List<categoria> ObtenerCategoriasHoja(List<categoria> categorias)
        {
            List<categoria> hojas = new List<categoria>();

            foreach (var cat in categorias)
            {
                if (cat.subcategorias == null || cat.subcategorias.Length == 0)
                {
                    hojas.Add(cat);
                }
                else
                {
                    hojas.AddRange(ObtenerCategoriasHoja(cat.subcategorias.ToList()));
                }
            }

            return hojas;
        }
        private void CargarProductos()
        {
            string nombre = txtNombre.Text.Trim();

            int categoriaId = int.TryParse(ddlCategoria.SelectedValue, out int c) ? c : 0;
            int marcaId = int.TryParse(ddlMarca.SelectedValue, out int m) ? m : 0;

            var productos = productoWS.getProductosFiltrados(nombre, categoriaId, marcaId);

            gvProductos.DataSource = productos;
            gvProductos.DataBind();
        }


        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            CargarProductos();
        }
        protected override void Render(HtmlTextWriter writer)
        {
            for (int i = 0; i <= gvProductos.PageCount; i++)
            {
                ClientScript.RegisterForEventValidation(gvProductos.UniqueID, $"Page${i}");
            }

            base.Render(writer);
        }
        protected void gvProductos_RowCreated(object sender, GridViewRowEventArgs e)
        {
            if (e.Row.RowType == DataControlRowType.Pager)
            {
                TableCell pager = e.Row.Cells[0];
                pager.Attributes["class"] = "text-center";

                int currentPage = gvProductos.PageIndex;
                int totalPages = gvProductos.PageCount;

                string paginationHtml = "<nav class='mt-4'><ul class='pagination justify-content-center'>";

                // Prev
                if (currentPage > 0)
                {

                    paginationHtml += $"<li class='page-item'><a class='page-link' href=\"javascript:__doPostBack('{gvProductos.UniqueID}', 'Page${currentPage}')\">&laquo;</a></li>";
                }
                else
                {
                    paginationHtml += "<li class='page-item disabled'><span class='page-link'>&laquo;</span></li>";
                }

                // Page numbers (mostrar 1-based, pero pasar 0-based)
                for (int i = 0; i < totalPages; i++)
                {
                    if (i == currentPage)
                    {
                        paginationHtml += $"<li class='page-item active'><span class='page-link'>{i + 1}</span></li>";
                    }
                    else
                    {
                        paginationHtml += $"<li class='page-item'><a class='page-link' href=\"javascript:__doPostBack('{gvProductos.UniqueID}', 'Page${i+1}')\">{i + 1}</a></li>";
                    }
                }

                // Next
                if (currentPage < totalPages - 1)
                {
                    paginationHtml += $"<li class='page-item'><a class='page-link' href=\"javascript:__doPostBack('{gvProductos.UniqueID}', 'Page${currentPage + 2}')\">&raquo;</a></li>";
                }
                else
                {
                    paginationHtml += "<li class='page-item disabled'><span class='page-link'>&raquo;</span></li>";
                }

                paginationHtml += "</ul></nav>";

                pager.Controls.Clear();
                pager.Controls.Add(new LiteralControl(paginationHtml));
            }
        }
        protected void btnLimpiar_Click(object sender, EventArgs e)
        {
            txtNombre.Text = "";
            ddlMarca.SelectedIndex = 0;
            ddlCategoria.SelectedIndex = 0;
            CargarProductos(); // muestra todos los productos sin filtros
        }



        protected void gvProductos_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvProductos.PageIndex = e.NewPageIndex;
            CargarProductos();
        }
    }
}