package com.pablosrl.data.stock;

public class Lotes {
	private String codArticulo;
    private String nroLote;
    private String codTalle;
    private String descTalle;
    private String codColor;
    private String descColor;

    // Constructor vacío
    public Lotes() {
    }

    // Constructor con todos los atributos
    public Lotes(String codArticulo, String nroLote, String codTalle, String descTalle, String codColor, String descColor) {
        this.codArticulo = codArticulo;
        this.nroLote = nroLote;
        this.codTalle = codTalle;
        this.descTalle = descTalle;
        this.codColor = codColor;
        this.descColor = descColor;
    }



	// Getters y setters
    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getNroLote() {
        return nroLote;
    }

    public void setNroLote(String nroLote) {
        this.nroLote = nroLote;
    }

    public String getCodTalle() {
        return codTalle;
    }

    public void setCodTalle(String codTalle) {
        this.codTalle = codTalle;
    }

    public String getDescTalle() {
        return descTalle;
    }

    public void setDescTalle(String descTalle) {
        this.descTalle = descTalle;
    }

    public String getDescColor() {
        return descColor;
    }

    public void setDescColor(String descColor) {
        this.descColor = descColor;
    }

    public String getCodColor() {
		return codColor;
	}

	public void setCodColor(String codColor) {
		this.codColor = codColor;
	}
}