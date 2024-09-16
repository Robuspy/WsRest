package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Persona;
import com.pablosrl.util.AppUtils;

@Path("WsPersonas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsPersona {

    Logger logger = Logger.getLogger(WsPersona.class);

    public WsPersona() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> getIt(@QueryParam("codVendedor") String codigoVendedor,
            @QueryParam("codCobrador") String codigo) {
        Persona c = null;
        List<Persona> lista = new ArrayList<>();

        String sql = "select cod_persona, nombre, nomb_fantasia, ruc, cedula from wsv_personas";
        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new Persona();
                c.setCodPersona(rs.getString(1));
                c.setNombre(rs.getString(2));
                c.setNombreFantasia(rs.getString(3));
                c.setRuc(rs.getString(4));
                c.setCi(rs.getString(5));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de monedas " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

}
