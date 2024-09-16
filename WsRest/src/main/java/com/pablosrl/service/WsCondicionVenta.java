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

import com.pablosrl.data.CondicionVenta;
import com.pablosrl.util.AppUtils;

@Path("WsCondicionVenta")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCondicionVenta {
	Logger logger  = Logger.getLogger(WsCondicionVenta.class);

	public WsCondicionVenta() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CondicionVenta> getIt(@QueryParam("codEmpresa") String codEmpresa) {
		CondicionVenta c = null;
		List<CondicionVenta> lista = new ArrayList<>();

		String sql = "select cv.cod_condicion_venta, "+
					"       cv.descripcion, "+
					"       cv.nro_cuotas, "+
					"       cv.cod_lista_precio, "+
					"       cv.dias_inicial, "+
					"       cv.per_cuotas, "+
					"       cv.plazo, "+
					"       1 cod_moneda, "+
					"       cv.cod_empresa "+
					"  from WSV_CONDICIONES_VENTAS cv " +
                                        "  where cv.cod_empresa = '"+codEmpresa+"'";
		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new CondicionVenta();
            	c.setCodCondicionVenta(rs.getString(1));
            	c.setDescripcion(rs.getString(2));
            	c.setNroCuotas(rs.getString(3));
            	c.setCodListaPrecio(rs.getString(4));
            	c.setDiasInicial(rs.getString(5));
            	c.setPerCuotas(rs.getString(6));
            	c.setPlazo(rs.getString(7));
            	c.setCodMoneda(rs.getString(8));
            	c.setCodEmpresa(rs.getString(9));

            	lista.add(c);
            }

		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de condiciones de venta "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
}
