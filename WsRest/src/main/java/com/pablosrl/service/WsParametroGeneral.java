package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.ParametrosGenerales;
import com.pablosrl.util.AppUtils;

@Path("WsParametroGeneral")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsParametroGeneral {

	Logger logger  = Logger.getLogger(WsParametroGeneral.class);

	public WsParametroGeneral() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ParametrosGenerales> getIt(@QueryParam("codUsuario") String codUsuario) {
		ParametrosGenerales c = null;
		List<ParametrosGenerales> lista = new ArrayList<>();

		String sql = "select nvl(valor,' ') valor, nvl(parametro,' ') parametro "
				+ "from inv.parametros_generales where cod_modulo = 'MV' or parametro in ('COD_GRUPO_DIAGEO','DECIMALES_PEDIDOS') "
				+ "UNION ALL "
				+ "SELECT PERMISO VALOR, PARAMETRO FROM INV.PERMISOS_OPCIONES WHERE COD_USUARIO = '"+codUsuario+"' AND NOM_FORMA = 'MVAPPMOV'";
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
