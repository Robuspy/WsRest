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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pablosrl.data.ArticuloDetalle;
import com.pablosrl.util.AppUtils;

@Path("WsArticulosDetalle")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticulosDetalle {

    Logger logger = LoggerFactory.getLogger(WsArticulosDetalle.class);

    public WsArticulosDetalle() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticuloDetalle> getIt() {
        ArticuloDetalle c = null;
        List<ArticuloDetalle> lista = new ArrayList<>();

        String sql = "select cod_articulo, tipo, caracteristicas from wsv_articulos_detalles";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new ArticuloDetalle();
                c.setCodArticulo(rs.getString(1));
                c.setTipo(rs.getString(2));
                c.setCaracteristica(rs.getString(3));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de detalle de articulos " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

}
