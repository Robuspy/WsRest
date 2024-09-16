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

import com.pablosrl.data.Monedas;
import com.pablosrl.util.AppUtils;

@Path("WsMonedas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsMonedas {
	Logger logger  = Logger.getLogger(WsMonedas.class);

	public WsMonedas() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Monedas> getIt() {
		Monedas c = null;
		List<Monedas> lista = new ArrayList<>();

		String sql = "select cod_moneda, descripcion, tipo_cambio_dia, siglas, decimales from wsv_monedas";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new Monedas();
            	c.setCodMoneda(rs.getString(1));
            	c.setDescripcion(rs.getString(2));
            	c.setTipoCambio(rs.getString(3));
            	c.setSiglas(rs.getString(4));
            	c.setDecimales(rs.getString(5));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de monedas "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}

}
