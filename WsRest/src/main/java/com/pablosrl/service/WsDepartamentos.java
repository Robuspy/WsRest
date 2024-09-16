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

import com.pablosrl.data.Departamentos;
import com.pablosrl.util.AppUtils;

@Path("WsDepartamentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsDepartamentos {

    Logger logger = LoggerFactory.getLogger(WsDepartamentos.class);

    public WsDepartamentos() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Departamentos> getIt() {
        Departamentos c = null;
        List<Departamentos> lista = new ArrayList<>();

        String sql = "select cod_pais, cod_departamento, descripcion, abreviatura from wsv_departamentos";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new Departamentos();
                c.setCodPais(rs.getString(1));
                c.setCodDepartamento(rs.getString(2));
                c.setDescripcion(rs.getString(3));
                c.setAbreviatura(rs.getString(4));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de departamentos " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

}
