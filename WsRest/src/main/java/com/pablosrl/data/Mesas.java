package com.pablosrl.data;

public class Mesas {
    private Long id;
    private String codEmpresa;
    private String codSucursal;
    private String codMesa;
    private String descripcion;
    private String estado;
    private String cantPersonas;

    public Mesas() {

    }



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public String getCantPersonas() {
		return cantPersonas;
	}



	public void setCantPersonas(String cantPersonas) {
		this.cantPersonas = cantPersonas;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getCodMesa() {
		return codMesa;
	}
	public void setCodMesa(String codMesa) {
		this.codMesa = codMesa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


}
