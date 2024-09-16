package com.pablosrl.data;

public class ClientesCv {

	private Long id;
	private String codCliente;
	private String codCondVenta;
	private String codListaPrecio;
	private String codEmpresa;


	public ClientesCv() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCodCondVenta() {
		return codCondVenta;
	}
	public void setCodCondVenta(String codCondVenta) {
		this.codCondVenta = codCondVenta;
	}
	public String getCodListaPrecio() {
		return codListaPrecio;
	}
	public void setCodListaPrecio(String codListaPrecio) {
		this.codListaPrecio = codListaPrecio;
	}
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}


}
