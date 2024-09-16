/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablosrl.data;

/**
 *
 * @author Usuario
 */
public class Persona {

    private String codPersona;
    private String nombre;
    private String nombreFantasia;
    private String ruc;
    private String ci;

    public Persona() {
    }

    public String getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(String codPersona) {
        this.codPersona = codPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }



}
