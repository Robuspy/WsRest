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

import com.pablosrl.data.ParametrosGenerales;
import com.pablosrl.util.AppUtils;

@Path("WsParametroGeneral2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsParametrosGenerales {

	Logger logger  = Logger.getLogger(WsParametrosGenerales.class);

	public WsParametrosGenerales() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ParametrosGenerales> getIt() {
		ParametrosGenerales c = null;
		List<ParametrosGenerales> lista = new ArrayList<>();

		String sql = "select valor, parametro from wsv_parametros_generales";
		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new ParametrosGenerales();
            	c.setValor(rs.getString(1));
            	c.setParametro(rs.getString(2));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de parametro general "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
}
