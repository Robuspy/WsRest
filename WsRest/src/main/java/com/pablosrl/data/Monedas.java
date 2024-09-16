package com.pablosrl.data;

public class Monedas {
	private String codMoneda;
	private String descripcion;
	private String siglas;
	private String tipoCambio;
	private String decimales;

	public Monedas() {
		super();
	}


	public String getDecimales() {
		return decimales;
	}



	public void setDecimales(String decimales) {
		this.decimales = decimales;
	}



	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSiglas() {
		return siglas;
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
}
