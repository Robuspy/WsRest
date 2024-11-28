package com.pablosrl.data;

import java.math.BigDecimal;

public class Monedas {

	private String codMoneda;
    private String descMoneda;
    private BigDecimal tipoCambioDia;


	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}
	public BigDecimal getTipoCambioDia() {
		return tipoCambioDia;
	}
	public void setTipoCambioDia(BigDecimal tipoCambioDia) {
		this.tipoCambioDia = tipoCambioDia;
	}




}