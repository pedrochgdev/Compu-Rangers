package com.compurangers.platform.core.domain.catalog;

public class Producto {

    private int id;
    private String sku;
    private String nombre;
    private String descripcion;
    private double precioVenta;
    private Categoria categoria;
    private Marca marca;
    private int cantidadVendida;
    private byte[] imagenReferencial;

    // Constructor vacío
    public Producto() {
    }

    // Constructor completo
    public Producto(int id, String sku, String nombre, String descripcion,
            double precioVenta, Categoria categoria, Marca marca,
            int cantidadVendida, byte[] imagenReferencial) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.marca = marca;
        this.cantidadVendida = cantidadVendida;
        this.imagenReferencial = imagenReferencial;
    }
    public Producto(int id, String sku, String nombre, String descripcion,
            double precioVenta, Categoria categoria, Marca marca,
            int cantidadVendida) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.marca = marca;
        this.cantidadVendida = cantidadVendida;
        this.imagenReferencial = null;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public byte[] getImagenReferencial() {
        return this.imagenReferencial;
    }

    public void setImagenReferencial(byte[] imagenReferencial) {
        this.imagenReferencial = imagenReferencial;
    }

    @Override
    public String toString() {
        return "Producto{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", descripcion='" + descripcion + '\''
                + ", sku='" + sku + '\''
                + ", precioVenta=" + precioVenta
                + ", categoria=" + (categoria != null ? categoria.getNombre() : "null")
                + ", marca=" + (marca != null ? marca.getNombre() : "null")
                + '}';
    }
}
