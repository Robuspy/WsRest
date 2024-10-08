package com.pablosrl.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Usuarios;
import com.pablosrl.util.AppUtils;

@Path("WsUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsUsuario {

    Logger logger = Logger.getLogger(WsUsuario.class);

    public WsUsuario() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> getIt(@QueryParam("codUsuario") String codigo) {
        Usuarios c = null;
        List<Usuarios> usuarioList = new ArrayList<>();
        String sql = consulta() + " WHERE cod_usuario = ?";
        System.out.println("Consulta " + sql);

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    c = new Usuarios();
                    c.setCodPersona(rs.getString(1));
                    c.setCodUsuario(rs.getString(2));
                    c.setNombre(rs.getString(3));
                    c.setCodGrupo(rs.getString(4));
                    usuarioList.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de usuario " + sql, e);
        }
        return usuarioList;
    }

    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> getItWithPass(@QueryParam("codUsuario") String codigo, @QueryParam("pass") String pass) {
        Usuarios c = null;
        List<Usuarios> usuarioList = new ArrayList<>();
        String sql = consulta() + " WHERE cod_usuario = ? AND pass = ?";
        System.out.println("Consulta " + sql);

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            stmt.setString(2, pass);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    c = new Usuarios();
                    c.setCodPersona(rs.getString(1));
                    c.setCodUsuario(rs.getString(2));
                    c.setNombre(rs.getString(3));
                    c.setCodGrupo(rs.getString(4));
                    usuarioList.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de usuario " + sql, e);
        }
        return usuarioList;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Usuarios usuario) {
        try {
            System.out.println("Usuario: " + usuario.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    private String consulta() {
        return (
                "   SELECT u.cod_persona, " +
                "          u.cod_usuario, " +
                "          per.nombre AS desc_persona, " +
                "          u.cod_grupo " +
                "   FROM usuarios u " +
                "   JOIN personas per ON u.cod_persona = per.cod_persona " +
                "   AND u.cod_empresa = 1"
        );
    }
}
