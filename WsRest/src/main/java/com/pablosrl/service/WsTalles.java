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

import com.pablosrl.data.Talles;
import com.pablosrl.util.AppUtils;


@Path("WsTalles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsTalles {

    Logger logger = Logger.getLogger(WsTalles.class);

    public WsTalles() {
        BasicConfigurator.configure();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Talles> getIt() {
        Talles c = null;
        List<Talles> lista = new ArrayList<>();

        String sql = " select * from wsv_Talles order by cod_articulo";
        logger.debug("consulta " + sql);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                c = new Talles();
                c.setCodTalle(rs.getString(2));
                c.setTalle(rs.getString(3));
                c.setcodArticulo(rs.getString(4));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de Talles " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return lista;
    }

}