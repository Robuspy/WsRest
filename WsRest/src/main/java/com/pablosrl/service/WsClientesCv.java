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

import com.pablosrl.data.ClientesCv;
import com.pablosrl.util.AppUtils;

@Path("WsClientesCv")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsClientesCv {
Logger logger  = Logger.getLogger(WsClientesCv.class);

	public WsClientesCv() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ClientesCv> getIt(@QueryParam("codUsuario") String codigo) {
		ClientesCv c = null;
		List<ClientesCv> lista = new ArrayList<>();

		String sql = "select cod_cliente, cod_condicion_venta, cod_lista_precio, cod_empresa "+
				"from wsv_clientes_cv "+
				"where cod_vendedor = '"+codigo+"' or cod_vendedor_lacteos = '"+codigo+"'";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new ClientesCv();
            	c.setCodCliente(rs.getString(1));
            	c.setCodCondVenta(rs.getString(2));
            	c.setCodListaPrecio(rs.getString(3));
            	c.setCodEmpresa(rs.getString(4));

            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Clientes Cv "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
}
