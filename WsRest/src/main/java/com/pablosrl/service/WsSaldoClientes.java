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

import com.pablosrl.data.SaldoClientes;
import com.pablosrl.util.AppUtils;

@Path("WsSaldoClientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsSaldoClientes {
	Logger logger  = Logger.getLogger(WsArticulos.class);

	public WsSaldoClientes() {
		BasicConfigurator.configure();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SaldoClientes> getIt(@QueryParam("codEmpresa") String codEmpresa,
									 @QueryParam("codVendedor") String codVendedor) {
		SaldoClientes c = null;
		List<SaldoClientes> lista = new ArrayList<>();

		String sql = " select cod_empresa, cod_sucursal, tipo_comprobante, ser_comprobante, nro_comprobante, cod_cliente, "
				   + "        nro_cuota, TO_CHAR(FEC_VENCIMIENTO,'dd/MM/yyyy'), dias_atraso, saldo_cuota, cod_moneda_cuota, tip_cambio_us "
				   + " from wsv_saldos_clientes "
				   + " where cod_empresa = to_number('"+codEmpresa+"')"
				   + "   and cod_vendedor = to_number('"+codVendedor+"')"
				   + " order by 3,4,5,7,8";

		logger.debug("consulta "+sql);

		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);

			while (rs.next()) {
				c = new SaldoClientes();
            	c.setCodEmpresa(rs.getString(1));
            	c.setCodSucursal(rs.getString(2));
                    c.setTipComprobante(rs.getString(3));
            	c.setSerComprobante(rs.getString(4));
            	c.setNroComprobante(rs.getString(5));
            	c.setCodCliente(rs.getString(6));
            	c.setNroCuota(rs.getString(7));
            	c.setFecVencimiento(rs.getString(8));
            	c.setDiasAtraso(rs.getString(9));
            	c.setSaldoCuota(rs.getString(10));
            	c.setCodMoneda(rs.getString(11));
            	c.setTipCambio(rs.getString(12));
            	lista.add(c);
            }
		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Saldo Clientes "+sql,e);
			c = null;
		}

		AppUtils.cerrarConsulta();

		return lista;
	}
}
