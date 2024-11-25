package com.pablosrl.controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pablosrl.data.AuthResponse;
import com.pablosrl.data.Credentials;
import com.pablosrl.util.AppUtils;

@Path("/auth")
public class AuthController {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        // Validar que el nombre de usuario y contraseña no estén vacíos
        if (credentials.getUsername() == null || credentials.getUsername().isEmpty() ||
            credentials.getPassword() == null || credentials.getPassword().isEmpty()) {
            AuthResponse response = new AuthResponse(false, "Usuario o contraseña no proporcionados", null);
            return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
        }

        try (Connection connection = AppUtils.getConnection(credentials.getUsername(), credentials.getPassword())) {
            // Si la conexión es exitosa
            AuthResponse response = new AuthResponse(true, "Conexión exitosa", credentials.getUsername());
            return Response.ok(response).build();
        } catch (SQLException e) {
            // Error de autenticación
            AuthResponse response = new AuthResponse(false, "Credenciales inválidas o error de autenticación", null);
            return Response.status(Response.Status.UNAUTHORIZED).entity(response).build();
        } catch (Exception e) {
            // Error general
            AuthResponse response = new AuthResponse(false, "Error interno del servidor", null);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }
    }
}