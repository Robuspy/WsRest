package com.pablosrl.util;

public class ParametrosProcedimiento {

    private int posicionParametro;
    private Object valorParametro;

    public ParametrosProcedimiento(int posicionParametro, Object valorParametro) {
        this.posicionParametro = posicionParametro;
        this.valorParametro = valorParametro;
    }

    public ParametrosProcedimiento() {}

    public int getPosicionParametro() {
        return posicionParametro;
    }

    public void setPosicionParametro(int posicionParametro) {
        this.posicionParametro = posicionParametro;
    }

    public Object getValorParametro() {
        return valorParametro;
    }

    public void setValorParametro(Object valorParametro) {
        this.valorParametro = valorParametro;
    }


}
