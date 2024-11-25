package com.pablosrl.data;



public class AuthResponse {

    private boolean success; // Indica si la conexión fue exitosa
    private String message;  // Mensaje descriptivo
    private String username; // Nombre del usuario

    public AuthResponse() {
    }

    public AuthResponse(boolean success, String message, String username) {
        this.success = success;
        this.message = message;
        this.username = username;
    }

    // Getters y setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}