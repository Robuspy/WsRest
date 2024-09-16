
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

import com.pablosrl.data.Lotes;
import com.pablosrl.util.AppUtils;


@Path("WsLotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsLotes {

    Logger logger = Logger.getLogger(WsLotes.class);

    public WsLotes() {
        BasicConfigurator.configure();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lotes> getIt() {
        Lotes c = null;
        List<Lotes> lista = new ArrayList<>();

        String sql = " select * from wsv_lotes ";
        logger.debug("consulta " + sql);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                c = new Lotes();
                c.setCodArticulo(rs.getString(1));
                c.setCodColor(rs.getString(2));
                c.setColor(rs.getString(3));
                c.setCodTalle(rs.getString(4));
                c.setTalle(rs.getString(5));
                c.setNroLote(rs.getString(6));
                c.setCantidad(rs.getString(7));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de Lotes " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return lista;
    }

}
