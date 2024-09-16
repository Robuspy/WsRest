package com.pablosrl.data;

public class ListaPreciosCab {

	private Long id;
    private String codListaPrecio;
    private String descripcion;
    private String codMoneda;
    private String codEmpresa;

    public ListaPreciosCab(){

    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodListaPrecio() {
		return codListaPrecio;
	}
	public void setCodListaPrecio(String codListaPrecio) {
		this.codListaPrecio = codListaPrecio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
