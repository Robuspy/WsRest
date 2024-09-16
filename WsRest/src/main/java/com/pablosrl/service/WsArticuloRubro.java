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

import com.pablosrl.data.ArticuloFichaRubro;
import com.pablosrl.util.AppUtils;

@Path("WsArticuloRubro")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticuloRubro {
	Logger logger  = Logger.getLogger(WsArticuloRubro.class);

	public WsArticuloRubro() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ArticuloFichaRubro> getIt() {
		ArticuloFichaRubro c = null;
		List<ArticuloFichaRubro> lista = new ArrayList<>();

		String sql = "SELECT COD_EMPRESA, COD_RUBRO, DESCRIPCION FROM WSV_RUBROS";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new ArticuloFichaRubro();
            	c.setCodEmpresa(rs.getString(1));
            	c.setCodigo(rs.getString(2));
            	c.setDescripcion(rs.getString(3));

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
