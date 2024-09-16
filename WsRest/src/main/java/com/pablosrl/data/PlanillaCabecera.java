package com.pablosrl.data;

/**
 *
 * @author Ivan
 */
public class PlanillaCabecera {

    private Long id;
    private String nroPlanilla;
    private String codSucursal;
    private String codVehiculo;
    private String estado;
    private String fecCierre;
    private String fecPlanilla;
    private String fecRendicion;
    private String indRefrigerado;
    private String codEmpresa;
    private String actualiza;

    public PlanillaCabecera(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNroPlanilla() {
        return nroPlanilla;
    }

    public void setNroPlanilla(String nroPlanilla) {
        this.nroPlanilla = nroPlanilla;
    }

    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    public String getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(String codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecCierre() {
        return fecCierre;
    }

    public void setFecCierre(String fecCierre) {
        this.fecCierre = fecCierre;
    }

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

    public String getIndRefrigerado() {
        return indRefrigerado;
    }

    public void setIndRefrigerado(String indRefrigerado) {
        this.indRefrigerado = indRefrigerado;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getActualiza() {
        return actualiza;
    }

    public void setActualiza(String actualiza) {
        this.actualiza = actualiza;
    }



}
