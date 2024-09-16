/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.pablosrl.data.SectorEcon;
import com.pablosrl.util.AppUtils;

/**
 *
 * @author Ivan
 */

@Path("WsSectorEcon")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsSectorEcon {
    	Logger logger  = LoggerFactory.getLogger(WsSectorEcon.class);

	public WsSectorEcon() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SectorEcon> getIt() {
                logger.debug("Prueba ==============> ");
		SectorEcon c = null;
		List<SectorEcon> lista = new ArrayList<>();

		String sql = "select cod_sector, descripcion from inv.SECTORES_ECON";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new SectorEcon();
                                c.setCod(rs.getString(1));
                                c.setDescripcion(rs.getString(2));
                                lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de sector economico "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
}
