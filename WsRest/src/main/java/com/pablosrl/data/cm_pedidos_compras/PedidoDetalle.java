package com.pablosrl.data.cm_pedidos_compras;

import java.math.BigDecimal;

public class PedidoDetalle {
    private String codEmpresa;
    private String tipComprobante;
    private String serComprobante;
    private Integer nroComprobante;
    private Integer nroOrden;
    private String codArticulo;
    private String descArticulo;
    private String nroLote;
    
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal precioUnitarioCIVA;
    private BigDecimal montoTotal;
    private BigDecimal montoTotalCIVA;
    
   /* 
    private BigDecimal totalIva;
    private String codUnidadMedida;
    private BigDecimal cantidadUb;
    private BigDecimal porcIva;
    private BigDecimal porcGravadas;
    private BigDecimal mult;
    private BigDecimal div;
    private BigDecimal montoGravadas;
    private BigDecimal montoExentas;
    private String codIva; 
    
    */

    // Getters and setters for each field

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getTipComprobante() {
        return tipComprobante;
    }

    public void setTipComprobante(String tipComprobante) {
        this.tipComprobante = tipComprobante;
    }

    public String getSerComprobante() {
        return serComprobante;
    }

    public void setSerComprobante(String serComprobante) {
        this.serComprobante = serComprobante;
    }

    public Integer getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(Integer nroComprobante) {
        this.nroComprobante = nroComprobante;
    }
    
    public Integer getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }
/*
    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public String getCodUnidadMedida() {
        return codUnidadMedida;
    }

    public void setCodUnidadMedida(String codUnidadMedida) {
        this.codUnidadMedida = codUnidadMedida;
    }

    public BigDecimal getCantidadUb() {
        return cantidadUb;
    }

    public void setCantidadUb(BigDecimal cantidadUb) {
        this.cantidadUb = cantidadUb;
    }
*/
    public BigDecimal getPrecioUnitarioCIVA() {
        return precioUnitarioCIVA;
    }

    public void setPrecioUnitarioCIVA(BigDecimal precioUnitarioCIVA) {
        this.precioUnitarioCIVA = precioUnitarioCIVA;
    }

    public BigDecimal getMontoTotalCIVA() {
        return montoTotalCIVA;
    }

    public void setMontoTotalCIVA(BigDecimal montoTotalCIVA) {
        this.montoTotalCIVA = montoTotalCIVA;
    }
/*
    public BigDecimal getPorcIva() {
        return porcIva;
    }

    public void setPorcIva(BigDecimal porcIva) {
        this.porcIva = porcIva;
    }

    public BigDecimal getPorcGravadas() {
        return porcGravadas;
    }

    public void setPorcGravadas(BigDecimal porcGravadas) {
        this.porcGravadas = porcGravadas;
    }

    public BigDecimal getMult() {
        return mult;
    }

    public void setMult(BigDecimal mult) {
        this.mult = mult;
    }

    public BigDecimal getDiv() {
        return div;
    }

    public void setDiv(BigDecimal div) {
        this.div = div;
    }

    public BigDecimal getMontoGravadas() {
        return montoGravadas;
    }

    public void setMontoGravadas(BigDecimal montoGravadas) {
        this.montoGravadas = montoGravadas;
    }

    public BigDecimal getMontoExentas() {
        return montoExentas;
    }

    public void setMontoExentas(BigDecimal montoExentas) {
        this.montoExentas = montoExentas;
    }

    public String getCodIva() {
        return codIva;
    }

    public void setCodIva(String codIva) {
        this.codIva = codIva;
    }

    public BigDecimal getTotalPesoArt() {
        return totalPesoArt;
    }

    public void setTotalPesoArt(BigDecimal totalPesoArt) {
        this.totalPesoArt = totalPesoArt;
    }
*/
    public String getNroLote() {
        return nroLote;
    }

    public void setNroLote(String nroLote) {
        this.nroLote = nroLote;
    }



     public String getDescArticulo() {
        return descArticulo;
    }

    public void setDescArticulo(String descArticulo) {
        this.descArticulo = descArticulo;
    }
}

