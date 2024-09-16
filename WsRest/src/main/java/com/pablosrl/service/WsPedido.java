package com.pablosrl.service;

//import com.itextpdf.text.Document;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.PedidosCabecera;
import com.pablosrl.data.PedidosDetalle;
import com.pablosrl.util.AppUtils;

@Path("WsPedido")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsPedido {

    Logger logger = Logger.getLogger(WsPedido.class);

    public WsPedido() {
        BasicConfigurator.configure();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(PedidosCabecera[] reg) {
        String vendedor = "";
        String mensaje = "";
        int size = reg.length;
        String sqlCabecera = "insert into ws_pedidos_cabecera "
                + " (tip_comprobante, "
                + " ser_comprobante, "
                + " nro_comprobante, "
                + " fecha, "
                + " cod_sucursal, "
                + " cod_cliente, "
                + " cod_vendedor, "
                + " cod_condicion_venta, "
                + " cod_lista_precio, "
                + " cod_moneda, "
                + " tip_cambio, "
                + " tot_comprobante, "
                + " tot_gravadas, "
                + " tot_exentas, "
                + " tot_iva, "
                + " tot_descuento, "
                + " hora_inicio, "
                + " hora_fin, "
                + " no_venta, "
                + " posicion, "
                + " fecha_recepcion, "
                + " ind_borrado, "
                + " hora_borrado, "
                + " ind_cliente, "
                + " id, "
                + " cod_empresa, "
                + " comentario) "
                + " values(",
                sqlDetalle = "insert into ws_pedidos_detalle "
                + " (tip_comprobante, "
                + "  ser_comprobante, "
                + "  nro_comprobante, "
                + "  cod_articulo, "
                + "  cod_um, "
                + "  cantidad, "
                + "  cantidad_ub, "
                + "  precio_unitario, "
                + "  precio_c_iva, "
                + "  porc_descuento, "
                + "  porc_descuento_volumen, "
                + "  porc_descuento_canal, "
                + "  porc_descuento_categoria, "
                + "  porc_descuento_visibilidad1, "
                + "  porc_descuento_visibilidad2, "
                + "  porc_descuento_visibilidad3, "
                + "  porc_descuento_cultural, "
                + "  porc_descuento_ori, "
                + "  total_iva, "
                + "  monto_gravada, "
                + "  monto_exenta, "
                + "  monto_total, "
                + "  monto_descuento, "
                + "  ind_stock, "
                + "  cant_stock,"
                + "  id_cabecera, "
                + "  DESCUENTO_CANAL, "
                + "  DESCUENTO_VOLUMEN, "
                + "  DESCUENTO_CATEGORIA, "
                + "  DESCUENTO_VISIBILIDAD1, "
                + "  DESCUENTO_VISIBILIDAD2, "
                + "  DESCUENTO_VISIBILIDAD3, "
                + "  DESCUENTO_ALIN_CULT, "
                + "  COD_EMPRESA, "
                + "  NRO_LOTE, "
                + "  TALLES, "
                + "  COLORES)"
                + " values(", values = new String();
        try {
            for (PedidosCabecera element : reg) {
                //procesamos el detalle primero.
                for (PedidosDetalle det : element.getDetalle()) {
                    values = "'" + det.getTipComprobante() + "',"
                            + "'" + det.getSerComprobante() + "',"
                            + "'" + det.getNroComprobante() + "',"
                            + "'" + det.getCodArticulo() + "',"
                            + "'" + det.getCodUm() + "',"
                            + "'" + det.getCantidad() + "',"
                            + "'" + det.getCantidadUb() + "',"
                            + "'" + det.getPrecioUnitario() + "',"
                            + "'" + det.getPrecioUnitarioCiva() + "',"
                            + "'" + det.getPorcDescuento() + "',"
                            + "'" + det.getPorcDescuentoVolumen() + "',"
                            + "'" + det.getPorcDescuentoCanal() + "',"
                            + "'" + det.getPorcDescuentoCategoria() + "',"
                            + "'" + det.getPorcDescuentoVisibilidad1() + "',"
                            + "'" + det.getPorcDescuentoVisibilidad2() + "',"
                            + "'" + det.getPorcDescuentoVisibilidad3() + "',"
                            + "'" + det.getPorcDescuentoCultural() + "',"
                            + "'" + det.getPorcDescuentoOri() + "',"
                            + "'" + det.getTotalIva() + "',"
                            + "'" + det.getMontoGravadas() + "',"
                            + "'" + det.getMontoExentas() + "',"
                            + "'" + det.getMontoTotal() + "',"
                            + "'" + det.getMontoDescuento() + "',"
                            + "'" + det.getIndStock() + "',"
                            + "'" + det.getCantStock() + "',"
                            + "'" + det.getIdentificador() + "',"
                            + "'" + det.getDescuentoCanal() + "',"
                            + "'" + det.getDescuentoVolumen() + "',"
                            + "'" + det.getDescuentoCategoria() + "',"
                            + "'" + det.getDescuentoVisibilidad1() + "',"
                            + "'" + det.getDescuentoVisibilidad2() + "',"
                            + "'" + det.getDescuentoVisibilidad3() + "',"
                            + "'" + det.getDescuentoCultural() + "',"
                            + "'" + det.getCodEmpresa() + "',"
                            + "'" + det.getCodLote() + "',"
                            + "'" + det.getTalles() + "',"
                            + "'" + det.getColores()+ "')";

                    AppUtils.realizaCarga(sqlDetalle + values);
                }

                vendedor = element.getCodVendedor();
                mensaje += "" + element.getTipComprobante() + " - " + element.getSerComprobante() + " - " + element.getNroComprobante() + " con el monto de " + element.getTotComprobante() + ".\n ";

                values = "'" + element.getTipComprobante() + "',"
                        + "'" + element.getSerComprobante() + "',"
                        + "'" + element.getNroComprobante() + "',"
                        + "'" + element.getFecha() + "',"
                        + "'" + element.getCodSucursal() + "',"
                        + "'" + element.getCodCliente() + "',"
                        + "'" + element.getCodVendedor() + "',"
                        + "'" + element.getCodCondVenta() + "',"
                        + "'" + element.getCodListaPrecio() + "',"
                        + "'" + element.getCodMoneda() + "',"
                        + "'" + element.getTipCambio() + "',"
                        + "'" + element.getTotComprobante() + "',"
                        + "'" + element.getTotGravadas() + "',"
                        + "'" + element.getTotExentas() + "',"
                        + "'" + element.getTotIva() + "',"
                        + "'" + element.getTotDescuento() + "',"
                        + "'" + element.getHoraInicio() + "',"
                        + "'" + element.getHoraFin() + "',"
                        + "'" + element.getNoVenta() + "',"
                        + "'" + element.getPosicion() + "',"
                        + "'" + element.getFechaRecepcion() + "',"
                        + "'" + element.getIndBorrado() + "',"
                        + "'" + element.getHoraBorrado() + "',"
                        + "'" + element.getIndCliente() + "',"
                        + "'" + element.getIdentificador() + "',"
                        + "'" + element.getCodEmpresa() + "',"
                        + "'" + element.getComentario() + "')";

                AppUtils.realizaCarga(sqlCabecera + values);

                /*if(reg[i].getImpresion() != null && !reg[i].getImpresion().isEmpty()) {
					System.out.println(reg[i].getImpresion());
					StringTokenizer token = new StringTokenizer(reg[i].getImpresion(), "$");
					List<String> lista = new ArrayList<>();

					while(token.hasMoreTokens()) {
						lista.add(token.nextToken());
					}

					crearPdf(lista);
					sendfile send = new sendfile("contabilidad@comercialarmin.com.py",
												 "comercialarminrp@gmail.com",
												 "smtp.gmail.com",
												 "c:\\inv\\fichero.pdf",
												 "true");
					send.sendMail();
				}*/
            }//gcasco@nakatomi.com.py
        } catch (Exception e) {
            logger.error("Error al insertar el pedido ", e);
            e.printStackTrace();
            return Response.status(400).entity("Error al insertar pedido " + e.getMessage() + " " + sqlDetalle + values).build();
        }
        /*sendfile send = new sendfile("gcasco@nakatomi.com.py",
				"inventivaAPIS@gmail.com",
				"smtp.gmail.com",
				"c:\\inv\\fichero.pdf",
				"Se generaron nuevo pedidos del Vendedor "+vendedor+"\n "+mensaje,
				"true");
		send.sendMail();*/
        return Response.status(200).entity("OK").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PedidosCabecera> obtenerPedidos(@QueryParam("codEmpresa") String codEmpresa, @QueryParam("codVendedor") String codVendedor) {
        PedidosCabecera c = null;
        List<PedidosCabecera> lista = new ArrayList<>();
        String sql = selectPedidosCab(codEmpresa, codVendedor);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new PedidosCabecera();
                c.setCodEmpresa(rs.getString(1));
                c.setCodSucursal(rs.getString(2));
                c.setCodCliente(rs.getString(3));
                c.setTipComprobante(rs.getString(4));
                c.setSerComprobante(rs.getString(5));
                c.setNroComprobante(rs.getString(6));
                c.setFecha(rs.getString(7));
                c.setCodVendedor(rs.getString(8));
                c.setEstado(rs.getString(9));
                c.setIdentificador(rs.getString(10));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de clientes " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return lista;
    }

    public String selectPedidosCab(String codEmpresa, String codVendedor) {
        StringBuilder sb = new StringBuilder();
        /*
        sb.append("select p.cod_empresa,           ");
        sb.append("       p.cod_sucursal,          ");
        sb.append("       p.cod_cliente,           ");
        sb.append("       p.tip_comprobante,       ");
        sb.append("       p.ser_comprobante,       ");
        sb.append("       p.nro_comprobante,       ");
        sb.append("       p.fec_comprobante,       ");
        sb.append("       p.cod_vendedor,          ");
        sb.append("       p.estado,                ");
        sb.append("       p.id_ped_mov             ");
        sb.append("  from wsv_pedidos_cabecera p   ");
        sb.append(" where p.cod_empresa ='").append(codEmpresa).append("' ");
        sb.append("   and p.cod_vendedor ='").append(codVendedor).append("' ");
        return sb.toString();*/
        sb.append("select p.cod_empresa,           ");
        sb.append("       p.cod_sucursal,          ");
        sb.append("       p.cod_cliente,           ");
        sb.append("       p.tip_comprobante,       ");
        sb.append("       p.ser_comprobante,       ");
        sb.append("       p.nro_comprobante,       ");
        sb.append("       p.fec_comprobante,       ");
        sb.append("       p.cod_vendedor,          ");
        sb.append("       p.estado                 ");
        sb.append("  from wsv_pedidos_cabecera p   ");
        sb.append(" where p.cod_empresa ='").append(codEmpresa).append("' ");
        sb.append("   and p.cod_vendedor ='").append(codVendedor).append("' ");
        return sb.toString();
    }

    /**
     * genera el pdf de la factura
     *
     * @param lista
     */
//    private void crearPdf(List<String> lista) {
//        try {
//            Document documento = new Document();
//            FileOutputStream ficheroPdf = new FileOutputStream("c:\\inv\\fichero.pdf");
//            PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
//            documento.open();
//            Paragraph linea = new Paragraph();
//            String text;
//            for (String reg : lista) {
//                text = reg.substring(2);
//                linea = new Paragraph(text, FontFactory.getFont("arial", 8));
//                documento.add(linea);
//            }
//            documento.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
