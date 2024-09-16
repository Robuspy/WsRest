package com.pablosrl.data;

public class Imagenes {

	private Long id;
	private String codigo;
	private String nombre;
	private String tipo;
	private String blob;

	public Imagenes() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getBlob() {
		return blob;
	}
	public void setBlob(String blob) {
		this.blob = blob;
	}
}
