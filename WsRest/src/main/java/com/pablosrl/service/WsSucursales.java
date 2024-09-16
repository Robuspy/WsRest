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

import com.pablosrl.data.Sucursales;
import com.pablosrl.util.AppUtils;

@Path("WsSucursal2")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class WsSucursales {
	Logger logger  = Logger.getLogger(WsSucursales.class);

	public WsSucursales() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sucursales> getIt() {
		Sucursales c = null;
		List<Sucursales> lista = new ArrayList<>();

		String sql = " select cod_sucursal, descripcion, cod_empresa "+
					 " from wsv_sucursales ";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new Sucursales();
            	c.setCodigo(rs.getString(1));
            	c.setDescripcion(rs.getString(2));
            	c.setCodEmpresa(rs.getString(3));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Sucursales "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
}
