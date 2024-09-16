/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablosrl.data;

/**
 *
 * @author Usuario
 */
public class PlanillaCobranzaDet {

    private String codEmpresa;
//    private String codSucursal;
    private String nroPlanilla;
    private String tipComprobante;
    private String serComprobante;
    private String nroComprobante;
//    private String estado;
    private String nroCuota;
    private String codMoneda;
    private String montoCuota;
    private String saldoCuota;
    private String codCliente;

    public PlanillaCobranzaDet() {
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }
/*
    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }


*/
    public String getNroPlanilla() {
        return nroPlanilla;
    }

    public void setNroPlanilla(String nroPlanilla) {
        this.nroPlanilla = nroPlanilla;
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

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

/*
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
*/
    public String getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(String nroCuota) {
        this.nroCuota = nroCuota;
    }

    public String getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(String montoCuota) {
        this.montoCuota = montoCuota;
    }

    public String getSaldoCuota() {
        return saldoCuota;
    }

    public void setSaldoCuota(String saldoCuota) {
        this.saldoCuota = saldoCuota;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }


}
