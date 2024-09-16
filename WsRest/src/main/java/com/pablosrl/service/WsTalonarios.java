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

import com.pablosrl.data.Talonarios;
import com.pablosrl.util.AppUtils;

@Path("WsTalonarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsTalonarios {

Logger logger  = Logger.getLogger(WsTalonarios.class);

	public WsTalonarios() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Talonarios> getIt(@QueryParam("codUsuario") String codigo) {
		Talonarios c = null;
		List<Talonarios> lista = new ArrayList<>();
		String sql = "SELECT * FROM(SELECT ID, TIP_TALONARIO, SERIE, NRO_TALONARIO, NRO_TIMBRADO, NUMERO_INICIAL, NUMERO_FINAL, "
				+ " NRO_ACTUAL, COD_COBRADOR, COD_USUARIO FROM WSV_TALONARIOS_COBRADORES "
				+ "WHERE COD_USUARIO = '"+codigo+"' ORDER BY NRO_TALONARIO DESC) WHERE ROWNUM  <= 1";

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);
			while (rs.next()) {
				c = new Talonarios();
				c.setId(rs.getLong(1));
            	c.setTipTalonario(rs.getString(2));
            	c.setSerTalonario(rs.getString(3));
            	c.setNroTalonario(rs.getString(4));
            	c.setNroTimbrado(rs.getString(5));
            	c.setNumInicial(rs.getString(6));
            	c.setNumFinal(rs.getString(7));
                c.setNumActual(rs.getString(8));
            	c.setCodigoU(rs.getString(9));
            	c.setCodUsuario(rs.getString(10));
				lista.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de clientes "+sql,e);
			c = null;
		}

	AppUtils.cerrarConsulta();
		return lista;
	}
}
