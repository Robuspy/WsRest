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

import com.pablosrl.data.NoVenta;
import com.pablosrl.util.AppUtils;

@Path("WsNoVenta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsNoVenta {
	Logger logger  = Logger.getLogger(WsNoVenta.class);

	public WsNoVenta() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NoVenta> getIt() {
		NoVenta c = null;
		List<NoVenta> lista = new ArrayList<>();

		String sql = "select cod_motivo, descripcion from vt_motivo_no_venta c where cod_empresa = '1'";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new NoVenta();
            	c.setCodigo(rs.getString(1));
            	c.setDescripcion(rs.getString(2));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de no venta "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}

}
