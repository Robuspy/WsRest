package com.pablosrl.data;

public class CondicionVenta {

	private Long id;
	private String codCondicionVenta;
	private String descripcion;
	private String nroCuotas;
	private String codListaPrecio;
	private String diasInicial;
	private String perCuotas;
	private String plazo;
	private String codMoneda;
	private String codEmpresa;

	public CondicionVenta() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodCondicionVenta() {
		return codCondicionVenta;
	}
	public void setCodCondicionVenta(String codCondicionVenta) {
		this.codCondicionVenta = codCondicionVenta;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNroCuotas() {
		return nroCuotas;
	}
	public void setNroCuotas(String nroCuotas) {
		this.nroCuotas = nroCuotas;
	}
	public String getCodListaPrecio() {
		return codListaPrecio;
	}
	public void setCodListaPrecio(String codListaPrecio) {
		this.codListaPrecio = codListaPrecio;
	}
	public String getDiasInicial() {
		return diasInicial;
	}
	public void setDiasInicial(String diasInicial) {
		this.diasInicial = diasInicial;
	}
	public String getPerCuotas() {
		return perCuotas;
	}
	public void setPerCuotas(String perCuotas) {
		this.perCuotas = perCuotas;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}


}
