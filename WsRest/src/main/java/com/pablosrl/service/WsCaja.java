package com.pablosrl.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.CajasCabecera;
import com.pablosrl.data.CajasComprobantes;
import com.pablosrl.data.FormaCobro;
import com.pablosrl.util.AppUtils;

@Path("WsCaja")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCaja {
Logger logger  = Logger.getLogger(WsCaja.class);

	public WsCaja() {
		BasicConfigurator.configure();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(CajasCabecera[] reg) {
		String sqlCaja = "insert into ws_cajas_cabecera(cod_empresa, cod_sucursal, nro_caja,fecha,total,cod_moneda,tip_cambio," +
				"                              cod_cliente,tip_planilla,nro_planilla,latitud," +
				"                              longitud,hora_carga,nro_recibo,id_registro," +
				"                              ind_procesado) values(";

		String sqlDetalle = "insert into ws_cajas_comprobantes(nro_caja,anio,cod_moneda,nro_comprobante,nro_timbrado," +
				"                                  punto_emision,ser_comprobante,suc_emision,tip_cambio," +
				"                                  tip_comprobante,tot_comprobante,id_registro_cab,ind_procesado) values(";

		String sqlCobro	= "insert into ws_formas_cobros(nro_caja,nro_sequencia,tipo_trans,sutipo_trans,cod_per_juridica," +
				"                             nro_valor,cod_moneda_cobro,tip_cambio,fec_emision,fec_vencimiento," +
				"                             tip_documento,nro_cuenta,monto,id_registro_cab,ind_procesado) values(";

		String values = "";
		try{
			for (CajasCabecera element : reg) {

				for(FormaCobro fc : element.getFormaCobroList()) {
					values = new String();
					values +="'"+fc.getNroMovCaj()+"',";
					values +="'"+fc.getNroSequencia()+"',";
					values +="'"+fc.getTipoTrans()+"',";
					values +="'"+fc.getSubTipoTrans()+"',";
					values +="'"+fc.getCodPerJuridica()+"',";
					values +="'"+fc.getNroValor()+"',";
					values +="'"+fc.getCodMonedaCobro()+"',";
					values +="'"+fc.getTipCambio()+"',";
					values +="'"+fc.getFecEmision()+"',";
					values +="'"+fc.getFecVencimiento()+"',";
					values +="'"+fc.getTipDocumento()+"',";
					values +="'"+fc.getNroCuenta()+"',";
					values +="'"+fc.getMonto()+"',";
					values +="'"+fc.getIdRegistroCab()+"',";
					values +="'N')";

					AppUtils.realizaCarga(sqlCobro+values);
				}

				for(CajasComprobantes cc : element.getComprobantesList()) {
					values = new String();
					values +="'"+cc.getNroMovCaj()+"',";
					values +="'"+cc.getAnio()+"',";
					values +="'"+cc.getCodMoneda()+"',";
					values +="'"+cc.getNroComprobante()+"',";
					values +="'"+cc.getNroTimbrado()+"',";
					values +="'"+cc.getPuntoEmision()+"',";
					values +="'"+cc.getSerComprobante()+"',";
					values +="'"+cc.getSucEmision()+"',";
					values +="'"+cc.getTipCambio()+"',";
					values +="'"+cc.getTipComprobante()+"',";
					values +="'"+cc.getTotComprobante()+"',";
					values +="'"+cc.getIdRegistroCab()+"',";
					values +="'N')";

					AppUtils.realizaCarga(sqlDetalle+values);
				}

				values = new String();
				values +="'"+element.getCodEmpresa()+"',";
				values +="'"+element.getCodSucursal()+"',";
				values +="'"+element.getNroMovCaj()+"',";
				values +="'"+element.getFecha()+"',";
				values +="'"+element.getTotal()+"',";
				values +="'"+element.getCodMoneda()+"',";
				values +="'"+element.getTipCambio()+"',";
				values +="'"+element.getCodCliente()+"',";
				values +="'"+element.getTipPlanilla()+"',";
				values +="'"+element.getNroPlanilla()+"',";
				values +="'"+element.getLatitud()+"',";
				values +="'"+element.getLongitud()+"',";
				values +="'"+element.getHoraCarga()+"',";
				values +="'"+element.getNroRecibo()+"',";
				values +="'"+element.getIdRegistro()+"',";
				values +="'N')";

				AppUtils.realizaCarga(sqlCaja+values);
			}
		} catch (SQLException e) {
			logger.error("Error al insertar el cliente ",e);
			e.printStackTrace();
			return Response.status(400).entity("Error al insertar cliente "+e.getMessage() + " "+sqlDetalle+values).build();
		}
		return Response.status(200).entity("OK").build();
    }
}
