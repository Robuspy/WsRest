package com.pablosrl.service;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.FormaCobro;
import com.pablosrl.data.Recibos;
import com.pablosrl.data.RecibosDetalle;
import com.pablosrl.util.AppUtils;

@Path("WsRecibos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsRecibos {
Logger logger  = Logger.getLogger(WsRecibos.class);

	public WsRecibos() {
		BasicConfigurator.configure();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Recibos[] reg) {
		String cobrador = "";
		String mensaje = "";
		int size = reg.length;
		String sqlCabecera = "insert into ws_recibos_cabecera "+
									  " (ID, "+
									  " COD_EMPRESA, "+
									  " SUCURSAL, "+
									  " TIP_REC, "+
									  " SER_REC, "+
									  " NRO_REC, "+
									  " FECHA, "+
									  " COD_CLIENTE, "+
									  " COD_COBRADOR, "+
									  " COD_MONEDA, "+
									  " TIPO_CAMBIO, "+
									  " COMENTARIO, "+
									  " MOV_CAJA_ID, "+
									  " ESTADO, "+
									  " TOTAL) "+
									  " values(",
			  sqlDetalle = "insert into ws_recibos_detalle "+
						 " (ID, "+
						 "  CABECERA_ID, "+
						 "  TIPO_TRANS, "+
						 "  SUB_TIPO_TRANS, "+
						 "  COMPROBANTE_ID, "+
						 "  TIP_COMP_REF, "+
						 "  SER_COMP_REF, "+
						 "  NRO_COMP_REF, "+
						 "  NRO_CUOTA, "+
						 "  FEC_VENCIMIENTO, "+
						 "  MONTO_CUOTA, "+
						 "  COD_MONEDA, "+
						 "  TIPO_CAMBIO, "+
						 "  TOTAL)"+
						" values(", values = new String();

		String sqlCobro	= "insert into ws_formas_cobros(id,nro_caja,nro_sequencia,tipo_trans,sutipo_trans,cod_per_juridica," +
														"nro_valor,cod_moneda_cobro,tip_cambio,fec_emision,fec_vencimiento," +
														"tip_documento,nro_cuenta,monto,id_registro_cab,documento_librador,nombre_librador,ind_procesado) values(";
		try{
			for (Recibos element : reg) {
				//procesamos el detalle primero.
				String idcab = element.getId()+":"+new Date()+":"+element.getCobrador_id();

                                for(FormaCobro fc : element.getFormasCobros()) {
					values = new String();
					values +="'"+idcab+"',";
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
                                        values +="'"+fc.getDocumentoLibrador()+"',";
                                        values +="'"+fc.getNombreLibrador()+"',";
					values +="'N')";

					AppUtils.realizaCarga(sqlCobro+values);
				}

				for(RecibosDetalle det : element.getDetalles()){
					values = "'"+det.getId()+":"+new Date()+"',"+
							 "'"+idcab+"',"+
							 "'"+det.getTipo_trans()+"',"+
							 "'"+det.getSubtipo_trans()+"',"+
							 "'"+det.getComprobante_id()+"',"+
							 "'"+det.getTipo_comp_ref()+"',"+
							 "'"+det.getSer_comp_ref()+"',"+
							 "'"+det.getNro_comp_ref()+"',"+
							 "'"+det.getNro_cuota()+"',"+
							 "'"+det.getFec_vencimiento()+"',"+
							 "'"+det.getMonto_cuota()+"',"+
							 "'"+det.getMoneda_id()+"',"+
							 "'"+det.getTipoCambio()+"',"+
							 "'"+det.getTotal()+"')";

					AppUtils.realizaCarga(sqlDetalle+values);
				}

				cobrador = element.getCobrador_id();
				mensaje += ""+element.getTipoRecibo()+" - "+element.getSerieRecibo()+" - "+element.getNumero()+"\n ";

				values = "'"+idcab+"',"+
						 "'1',"+
						 "'"+element.getSucursal_id()+"',"+
						 "'"+element.getTipoRecibo()+"',"+
						 "'"+element.getSerieRecibo()+"',"+
						 "'"+element.getNumero()+"',"+
						 "'"+element.getFecha()+"',"+
						 "'"+element.getCliente_id()+"',"+
						 "'"+element.getCobrador_id()+"',"+
						 "'"+element.getMoneda_id()+"',"+
						 "'"+element.getTipoCambio()+"',"+
						 "'"+element.getComentario()+"',"+
						 "'"+element.getMovCaja_id()+"',"+
						 "'"+element.getEstado()+"',"+
						 "'"+element.getTotal()+"')";

				AppUtils.realizaCarga(sqlCabecera+values);



//				if(reg[i].getImpresion() != null && !reg[i].getImpresion().isEmpty()) {
//					System.out.println(reg[i].getImpresion());
//					StringTokenizer token = new StringTokenizer(reg[i].getImpresion(), "$");
//					List<String> lista = new ArrayList<>();
//
//					while(token.hasMoreTokens()) {
//						lista.add(token.nextToken());
//					}
//
//					crearPdf(lista);
//					sendfile send = new sendfile("contabilidad@comercialarmin.com.py",
//												 "comercialarminrp@gmail.com",
//												 "smtp.gmail.com",
//												 "c:\\inv\\fichero.pdf",
//												 "true");
//					send.sendMail();
//				}

			}//gcasco@nakatomi.com.py
			//new Thread(new Runnable() {
			    //@Override public void run() {

			   // }
			//}).start();
		} catch (Exception e) {
			logger.error("Error al insertar el pedido ",e);
			e.printStackTrace();
			return Response.status(400).entity("Error al insertar pedido "+e.getMessage() + " "+sqlDetalle+values).build();
		}
//		sendfile send = new sendfile("gcasco@nakatomi.com.py",
//				"inventivaAPIS@gmail.com",
//				"smtp.gmail.com",
//				"c:\\inv\\fichero.pdf",
//				"Se generaron nuevo recibos del cobrador "+cobrador+"\n "+mensaje,
//				"true");
//		send.sendMail();
		return Response.status(200).entity("OK").build();
    }
}
