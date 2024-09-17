package com.pablosrl.data;

public class Usuarios {
	
	    private String codPersona;
	    private String codUsuario;
	    private String nombre;
	    private String codGrupo;
	    
		    // Nuevas variables para dbUser y dbPass
	private String dbUser;
	private String dbPass;
	
	// Getters and Setters para codPersona, codUsuario, nombre, codGrupo
	
	public String getCodPersona() {
	    return codPersona;
	}
	
	public void setCodPersona(String codPersona) {
	    this.codPersona = codPersona;
	}
	
	public String getCodUsuario() {
	    return codUsuario;
	}
	
	public void setCodUsuario(String codUsuario) {
	    this.codUsuario = codUsuario;
	}
	
	public String getNombre() {
	    return nombre;
	}
	
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}
	
	public String getCodGrupo() {
	    return codGrupo;
	}
	
	public void setCodGrupo(String codGrupo) {
	    this.codGrupo = codGrupo;
	}

// Getters y Setters para dbUser y dbPass

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
    }
}



