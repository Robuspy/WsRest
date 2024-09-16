package com.pablosrl.data;

/**
 *  @author ArielTalavera
 *
 */
public class Bancos {

	private Long id;
	private String codigo;
	private String descripcion;
	private String campo1,campo2,campo3;

	public Bancos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bancos(Long id, String codigo, String descripcion, String campo1, String campo2, String campo3) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.campo3 = campo3;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCampo1() {
		return campo1;
	}
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	public String getCampo3() {
		return campo3;
	}
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}


}