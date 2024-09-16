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
public class PlanillaCobranzaCab {
    private String codEmpresa;
    private String codCliente;
//    private String codSucursal;
    private String nroPlanilla;
    private String codCobrador;
//    private String fecPlanilla;
//    private String fecRendicion;
//    private String codMoneda;
//    private String tipCambio;
//    private String tipPlanilla;
//    private String serPlanilla;
    private String totComprobante;
//    private String totSaldo;
//    private String codZona;
    private String diasCobro;

    public PlanillaCobranzaCab() {
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getDiasCobro() {
        return diasCobro;
    }

    public void setDiasCobro(String diasCobro) {
        this.diasCobro = diasCobro;
    }


    public String getNroPlanilla() {
        return nroPlanilla;
    }

    public void setNroPlanilla(String nroPlanilla) {
        this.nroPlanilla = nroPlanilla;
    }
/*
    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

*/
    public String getCodCobrador() {
        return codCobrador;
    }

    public void setCodCobrador(String codCobrador) {
        this.codCobrador = codCobrador;
    }
/*
    public String getFecPlanilla() {
        return fecPlanilla;
    }

    public void setFecPlanilla(String fecPlanilla) {
        this.fecPlanilla = fecPlanilla;
    }

    public String getFecRendicion() {
        return fecRendicion;
    }

    public void setFecRendicion(String fecRendicion) {
        this.fecRendicion = fecRendicion;
    }

    public String getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

    public String getTipCambio() {
        return tipCambio;
    }

    public void setTipCambio(String tipCambio) {
        this.tipCambio = tipCambio;
    }

    public String getTipPlanilla() {
        return tipPlanilla;
    }

    public void setTipPlanilla(String tipPlanilla) {
        this.tipPlanilla = tipPlanilla;
    }

    public String getSerPlanilla() {
        return serPlanilla;
    }

    public void setSerPlanilla(String serPlanilla) {
        this.serPlanilla = serPlanilla;
    }
*/

    public String getTotComprobante() {
        return totComprobante;
    }

    public void setTotComprobante(String totComprobante) {
        this.totComprobante = totComprobante;
    }
/*
    public String getTotSaldo() {
        return totSaldo;
    }

    public void setTotSaldo(String totSaldo) {
        this.totSaldo = totSaldo;
    }

    public String getCodZona() {
        return codZona;
    }

    public void setCodZona(String codZona) {
        this.codZona = codZona;
    }
*/
}
