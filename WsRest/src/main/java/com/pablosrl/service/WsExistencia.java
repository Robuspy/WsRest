package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.ExistenciasArt;
import com.pablosrl.util.AppUtils;

@Path("WsExistencia")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsExistencia {
	Logger logger  = Logger.getLogger(WsExistencia.class);

	public WsExistencia() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ExistenciasArt> getIt(@QueryParam("codVendedor") String reg) {
		List<ExistenciasArt> lista = new ArrayList<>();
		ResultSet rs;
		String sql;

		try {
			sql = "select cod_empresa, cod_sucursal, cod_articulo, cant_dispon from wsv_existencias";// where cod_vendedor = '"+reg+"'";
			rs = AppUtils.realizaConsulta(sql);
			while(rs.next()) {
				lista.add(new ExistenciasArt(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(2)));
			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Existencia ",e);
			lista = null;
		}

		AppUtils.cerrarConsulta();

		return lista;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ExistenciasArt> getIt(ExistenciasArt[] reg) {
		List<ExistenciasArt> lista = new ArrayList<>();
		ResultSet rs;
		String sql;

		try {
			for (ExistenciasArt element : reg) {
				sql = "select consultar_stock('"+element.getCodEmpresa()+"','"+element.getCodSucursal()+"','"+element.getCodArticulo()+"') from dual";
				logger.error("Consulta existencia "+sql);
				rs = AppUtils.realizaConsulta(sql);
				if(rs.next()) {
					element.setCantidad(rs.getString(1));
				}
			}

			lista = Arrays.asList(reg);

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Existencia ",e);
			lista = null;
		}

		AppUtils.cerrarConsulta();

		return lista;
	}
}
