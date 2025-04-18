package com.compurangers.platform.core.domain.inventory;

import java.time.LocalDate;

public class Lote {
    private int id;
    private LocalDate fechaCreacion;
    private String estado;
    private int documentoCompraNo;
    
    public Lote() {}

    public Lote(int id, LocalDate fechaCreacion, String estado, int documentoCompraNo) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.documentoCompraNo = documentoCompraNo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getDocumentoCompraId() { return documentoCompraNo; }
    public void setDocumentoCompraId(int documentoCompraNo) { this.documentoCompraNo = documentoCompraNo; }
}
