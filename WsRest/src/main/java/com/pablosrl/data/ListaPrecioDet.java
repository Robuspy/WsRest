package com.pablosrl.data;


public class ListaPrecioDet {


	private String codArticulo;
	private String codListaPrecio;
	private String codMoneda;
	private String precio;
	private String porcDescuento;
	private String fecVigencia;
	private String codEmpresa;

	public ListaPrecioDet() {
		super();
	}

	public String getCodArticulo() {
		return codArticulo;
	}
	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}
	public String getCodListaPrecio() {
		return codListaPrecio;
	}
	public void setCodListaPrecio(String codListaPrecio) {
		this.codListaPrecio = codListaPrecio;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getFecVigencia() {
		return fecVigencia;
	}
	public void setFecVigencia(String fecVigencia) {
		this.fecVigencia = fecVigencia;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public String getPorcDescuento() {
		return porcDescuento;
	}

	public void setPorcDescuento(String porcDescuento) {
		this.porcDescuento = porcDescuento;
	}
}
