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

import com.pablosrl.data.ListaPrecioCab;
import com.pablosrl.util.AppUtils;

@Path("WsListaPrecioCab")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsListaPrecioCab {
Logger logger  = Logger.getLogger(WsListaPrecioCab.class);

	public WsListaPrecioCab() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ListaPrecioCab> getIt() {
		ListaPrecioCab c = null;
		List<ListaPrecioCab> lista = new ArrayList<>();

		String sql = "select cod_lista_precio,					"+
					"	       descripcion,							"+
					"	       cod_moneda,							"+
					"	       cod_empresa							"+
					"	from WSV_LISTA_PRECIO_CAB					";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new ListaPrecioCab();
            	c.setCodListaPrecio(rs.getString(1));
            	c.setDescripcion(rs.getString(2));
            	c.setCodMoneda(rs.getString(3));
            	c.setCodEmpresa(rs.getString(4));

            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Lista Precio cab "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}

}
