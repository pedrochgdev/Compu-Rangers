package com.compurangers.platform.core.domain.inventory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lote {
    private int id;
    private Date fechaCreacion;
    private String estado;
    private int documentoCompraNo;
    private List<DetalleLote> detalle;
    
    public Lote() {this.detalle = new ArrayList<>();}

    public Lote(int id, Date fechaCreacion, String estado, int documentoCompraNo) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.documentoCompraNo = documentoCompraNo;
        this.detalle = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getDocumentoCompraNo() { return documentoCompraNo; }
    public void setDocumentoCompraNo(int documentoCompraNo) { this.documentoCompraNo = documentoCompraNo; }
    
    public List<DetalleLote> getDetalle() { return detalle; }
    public void setDetalle(List<DetalleLote> detalle) { this.detalle = detalle; }
    
}
