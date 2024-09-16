package com.pablosrl.data;

public class ExistenciasArt {

	private String codEmpresa;
	private String codArticulo;
	private String cantidad;
	private String codSucursal;



	public ExistenciasArt(String codEmpresa, String codArticulo, String cantidad, String codSucursal) {
		super();
		this.codEmpresa = codEmpresa;
		this.codArticulo = codArticulo;
		this.cantidad = cantidad;
		this.codSucursal = codSucursal;
	}

	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
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
}
