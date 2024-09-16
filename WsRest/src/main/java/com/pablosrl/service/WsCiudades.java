package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Ciudades;
import com.pablosrl.util.AppUtils;

@Path("WsCiudades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCiudades {

    Logger logger = Logger.getLogger(WsArticulos.class);

    public WsCiudades() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ciudades> getIt() {
        Ciudades c = null;
        List<Ciudades> lista = new ArrayList<>();

        String sql = "select cod_pais, cod_ciudad,descripcion, abreviatura from wsv_ciudades";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new Ciudades();
                c.setCodPais(rs.getString(1));
                c.setCodCiudad(rs.getString(2));
                c.setDescripcion(rs.getString(3));
                c.setAbreviatura(rs.getString(4));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de ciudades " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

}
