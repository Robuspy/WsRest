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

import com.pablosrl.data.ListaPrecioDet;
import com.pablosrl.util.AppUtils;

@Path("WsListaPrecioDet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsListaPrecioDet {
	Logger logger  = Logger.getLogger(WsListaPrecioDet.class);

	public WsListaPrecioDet() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ListaPrecioDet> getIt() {
		ListaPrecioDet c = null;
		List<ListaPrecioDet> lista = new ArrayList<>();

		String sql = "select   lpf.cod_articulo, "+
				"      lpf.cod_precio_fijo, "+
				"      lpf.cod_moneda, "+
				"      lpf.precio_fijo, "+
				"      lpf.porc_descuento, "+
				"      lpf.fecha, "+
				"      lpf.cod_empresa "+
				"  from wsv_lista_precios_fijos lpf "+
                        "               join wsv_articulos a on a.cod_articulo = lpf.COD_ARTICULO ";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new ListaPrecioDet();
            	c.setCodArticulo(rs.getString(1));
            	c.setCodListaPrecio(rs.getString(2));
            	c.setCodMoneda(rs.getString(3));
            	c.setPrecio(rs.getString(4));
            	c.setPorcDescuento(rs.getString(5));
            	c.setFecVigencia(rs.getString(6));
            	c.setCodEmpresa(rs.getString(7));

            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Lista Precio det "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();

		return lista;
	}

}