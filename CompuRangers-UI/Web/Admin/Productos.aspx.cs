using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Web.WebService;
namespace Web.Admin
{
    public partial class Productos : Page
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
    }
}