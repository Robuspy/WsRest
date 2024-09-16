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

import com.pablosrl.data.Relaciones;
import com.pablosrl.util.AppUtils;


@Path("WsRelaciones")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsRelaciones {
	Logger logger  = Logger.getLogger(WsListaPrecioDet.class);

	public WsRelaciones() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Relaciones> getIt() {
		Relaciones c = null;
		List<Relaciones> lista = new ArrayList<>();

		String sql = "select cod_relacion_um,							"+
				"	       cod_unidad_rel,							"+
				"	       mult,										"+
				"	       div,										"+
				"	       por_defecto,								"+
				"	       descripcion,								"+
				"	       cod_empresa								"+
				"	from wsv_relaciones_art ";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new Relaciones();
            	c.setCodUm(rs.getString(1));
            	c.setCodRelacion(rs.getString(2));
            	c.setMult(rs.getString(3));
            	c.setDiv(rs.getString(4));
            	c.setPorDefecto(rs.getString(5));
            	c.setDescUm(rs.getString(6));
            	c.setCodEmpresa(rs.getString(7));
            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Relaciones "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}

}