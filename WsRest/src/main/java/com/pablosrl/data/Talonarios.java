package com.pablosrl.data;

public class Talonarios {

	private Long id;
	private String tipTalonario;
	private String serTalonario;
	private String nroTalonario;
	private String nroTimbrado;
	private String numInicial;
	private String numFinal;
        private String numActual;
	private String codigoU;
	private String codUsuario;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipTalonario() {
		return tipTalonario;
	}
	public void setTipTalonario(String tipTalonario) {
		this.tipTalonario = tipTalonario;
	}
	public String getSerTalonario() {
		return serTalonario;
	}
	public void setSerTalonario(String serTalonario) {
		this.serTalonario = serTalonario;
	}
	public String getNroTalonario() {
		return nroTalonario;
	}
	public void setNroTalonario(String nroTalonario) {
		this.nroTalonario = nroTalonario;
	}
	public String getNroTimbrado() {
		return nroTimbrado;
	}
	public void setNroTimbrado(String nroTimbrado) {
		this.nroTimbrado = nroTimbrado;
	}
	public String getNumInicial() {
		return numInicial;
	}
	public void setNumInicial(String numInicial) {
		this.numInicial = numInicial;
	}
	public String getNumFinal() {
		return numFinal;
	}
	public void setNumFinal(String numFinal) {
		this.numFinal = numFinal;
	}

        public String getNumActual() {
            return numActual;
        }

        public void setNumActual(String numActual) {
            this.numActual = numActual;
        }

	public String getCodigoU() {
		return codigoU;
	}
	public void setCodigoU(String codigoU) {
		this.codigoU = codigoU;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}


}
