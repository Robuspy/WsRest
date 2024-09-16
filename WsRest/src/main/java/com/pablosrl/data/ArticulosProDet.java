/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablosrl.data;

import java.math.BigDecimal;

/**
 *
 * @author Usuario
 */
public class ArticulosProDet {

    private String codEmpresa;
    private String codArticulo;
    private String codArticuloPro;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String activo;
    private BigDecimal cantidad;
    private String codPromo;
    private String codListaPrecio;
    private String estadoPromo;
    private BigDecimal precioVentaDet;
    private BigDecimal costoUltimoDet;
    private BigDecimal tipoCambioDet;

    public ArticulosProDet() {
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getCodArticuloPro() {
        return codArticuloPro;
    }

    public void setCodArticuloPro(String codArticuloPro) {
        this.codArticuloPro = codArticuloPro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodPromo() {
        return codPromo;
    }

    public void setCodPromo(String codPromo) {
        this.codPromo = codPromo;
    }

    public String getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(String codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public String getEstadoPromo() {
        return estadoPromo;
    }

    public void setEstadoPromo(String estadoPromo) {
        this.estadoPromo = estadoPromo;
    }

    public BigDecimal getPrecioVentaDet() {
        return precioVentaDet;
    }

    public void setPrecioVentaDet(BigDecimal precioVentaDet) {
        this.precioVentaDet = precioVentaDet;
    }

    public BigDecimal getCostoUltimoDet() {
        return costoUltimoDet;
    }

    public void setCostoUltimoDet(BigDecimal costoUltimoDet) {
        this.costoUltimoDet = costoUltimoDet;
    }

    public BigDecimal getTipoCambioDet() {
        return tipoCambioDet;
    }

    public void setTipoCambioDet(BigDecimal tipoCambioDet) {
        this.tipoCambioDet = tipoCambioDet;
    }


}
