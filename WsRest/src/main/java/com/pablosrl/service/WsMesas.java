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

import com.pablosrl.data.Mesas;
import com.pablosrl.util.AppUtils;

@Path("WsMesas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsMesas {

Logger logger  = Logger.getLogger(WsMesas.class);

	public WsMesas() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mesas> getIt() {
		Mesas c = null;
		List<Mesas> lista = new ArrayList<>();

		String sql = "select cod_empresa, cod_sucursal, cod_mesa, descripcion, estado, cant_personas from wsv_mesas";
		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new Mesas();
            	c.setCodEmpresa(rs.getString(1));
            	c.setCodSucursal(rs.getString(2));
            	c.setCodMesa(rs.getString(3));
            	c.setDescripcion(rs.getString(4));
            	c.setEstado(rs.getString(5));
            	c.setCantPersonas(rs.getString(6));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de mesas "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}

}
