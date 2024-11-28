package com.pablosrl.data.stock;

import java.math.BigDecimal;

public class Articulos {

	private String codArticulos;
    private String descArticulos;
    private BigDecimal costoPromedioUnitario;

	public String getCodArticulos() {
		return codArticulos;
	}
	public void setCodArticulos(String codArticulos) {
		this.codArticulos = codArticulos;
	}
	public String getDescArticulos() {
		return descArticulos;
	}
	public void setDescArticulos(String descArticulos) {
		this.descArticulos = descArticulos;
	}
	public BigDecimal getCostoPromedioUnitario() {
		return costoPromedioUnitario;
	}
	public void setCostoPromedioUnitario(BigDecimal costoPromedioUnitario) {
		this.costoPromedioUnitario = costoPromedioUnitario;
	}




}
