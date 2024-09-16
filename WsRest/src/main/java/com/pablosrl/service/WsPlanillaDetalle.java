package com.pablosrl.service;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pablosrl.data.PlanillaDetalle;
import com.pablosrl.util.AppUtils;
import com.pablosrl.util.ParametrosProcedimiento;

/**
 *
 * @author Ivan
 */
@Path("WsPlanillaDetalle")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WsPlanillaDetalle {

    Logger logger = LoggerFactory.getLogger(WsPlanillaDetalle.class);

    public WsPlanillaDetalle() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanillaDetalle> getIt(@QueryParam("codUsuario") String codigo) {

        List<PlanillaDetalle> lista = new ArrayList<>();

        String sql1 = "SELECT COD_MOTIVO_REBOTE, COD_SUCURSAL, ESTADO, "
                + "TO_CHAR(FEC_CAMBIO_ESTADO,'DD/MM/YYYY HH24:MI:SS'), TO_CHAR(FECHA_HORA_ENTREGA,'DD/MM/YYYY HH24:MI:SS'), "
                + "NC_GENERA, NRO_COMPROBANTE, "
                + "NRO_COMPROBANTE_REF, NROPLANILLA, NRO_TIMBRADO_REF, PUNTO_EMISION_REF, "
                + "REBOTE, SER_COMPROBANTE, SER_COMPROBANTE_REF, SUC_EMISION_REF, "
                + "TIP_COMPROBANTE, TIP_COMPROBANTE_REF, COD_CLIENTE, "
                + "TOT_COMPROBANTE, COD_MONEDA, TIP_ATRIBUTO, GRU_ATRIBUTO, "
                + "COD_ATRIBUTO, COD_CV, ES_VALE, LATITUD, LONGITUD, "
                + "TIP_CONDICION, IND_ENVIO, VENDEDOR, TIPO_IMPRESION, "
                + "ANIO, TO_CHAR(FEC_SYN,'DD/MM/YYYY HH24:MI:SS'), TIP_CAMBIO, COD_EMPRESA, CAJAS, PESO, "
                + "ORDEN_ENTREGA "
                + "FROM WSINV.WSV_PLANILLA_DET wpd WHERE wpd.VENDEDOR = '" + codigo + "'";

        String sql = sqlDet(codigo);
        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                PlanillaDetalle planillaDetalle = new PlanillaDetalle();
                planillaDetalle.setCodMotivoRebote(rs.getString(1));
                planillaDetalle.setCodSucursal(rs.getString(2));
                planillaDetalle.setEstado(rs.getString(3));
                planillaDetalle.setFecCambioEstado(rs.getString(4));
                planillaDetalle.setFecHoraEntrega(rs.getString(5));
                planillaDetalle.setNcGenera(rs.getString(6));
                planillaDetalle.setNroComprobante(rs.getString(7));
                planillaDetalle.setNroComprobanteRef(rs.getString(8));
                planillaDetalle.setNroPlanilla(rs.getString(9));
                planillaDetalle.setNroTimbradoRef(rs.getString(10));
                planillaDetalle.setPuntoEmisionRef(rs.getString(11));
                planillaDetalle.setRebote(rs.getString(12));
                planillaDetalle.setSerComprobante(rs.getString(13));
                planillaDetalle.setSerComprobanteRef(rs.getString(14));
                planillaDetalle.setSucEmisionRef(rs.getString(15));
                planillaDetalle.setTipComprobante(rs.getString(16));
                planillaDetalle.setTipComprobanteRef(rs.getString(17));
                planillaDetalle.setCodCliente(rs.getString(18));
                planillaDetalle.setTotComprobante(rs.getString(19));
                planillaDetalle.setCodMoneda(rs.getString(20));
                planillaDetalle.setTipAtributo(rs.getString(21));
                planillaDetalle.setGruAtributo(rs.getString(22));
                planillaDetalle.setCodAtributo(rs.getString(23));
                planillaDetalle.setCodCondVenta(rs.getString(24));
                planillaDetalle.setEsVale(rs.getString(25));
                planillaDetalle.setLatitud(rs.getString(26));
                planillaDetalle.setLongitud(rs.getString(27));
                planillaDetalle.setTipCondicion(rs.getString(28));
                planillaDetalle.setIndEnvio(rs.getString(29));
                planillaDetalle.setVendedor(rs.getString(30));
                planillaDetalle.setTipoImpresion(rs.getString(31));
                planillaDetalle.setAnio(rs.getString(32));
                planillaDetalle.setFecSyn(rs.getString(33));
                planillaDetalle.setTipCambio(rs.getString(34));
                planillaDetalle.setCodEmpresa(rs.getString(35));
                planillaDetalle.setCajas(rs.getString(36));
                planillaDetalle.setPeso(rs.getString(37));
                planillaDetalle.setOrdenEntrega(rs.getString(38));
                lista.add(planillaDetalle);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            logger.error("Error realizando la consulta de planilla detalle " + sql, e);
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    private String sqlDet(String user) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT wpd.COD_MOTIVO_REBOTE,													");
        sb.append("       wpd.COD_SUCURSAL, WPD.ESTADO,                                             ");
        sb.append("       TO_CHAR(wpd.FEC_CAMBIO_ESTADO,'DD/MM/YYYY HH24:MI:SS'),                   ");
        sb.append("       TO_CHAR(wpd.FECHA_HORA_ENTREGA,'DD/MM/YYYY HH24:MI:SS'),                  ");
        sb.append("       wpd.NC_GENERA,                                                            ");
        sb.append("       wpd.NRO_COMPROBANTE,                                                      ");
        sb.append("       wpd.NRO_COMPROBANTE_REF,                                                  ");
        sb.append("       wpd.NROPLANILLA,                                                          ");
        sb.append("       wpd.NRO_TIMBRADO_REF,                                                     ");
        sb.append("       wpd.PUNTO_EMISION_REF,                                                    ");
        sb.append("       wpd.REBOTE,                                                               ");
        sb.append("       wpd.SER_COMPROBANTE,                                                      ");
        sb.append("       wpd.SER_COMPROBANTE_REF,                                                  ");
        sb.append("       wpd.SUC_EMISION_REF,                                                      ");
        sb.append("       wpd.TIP_COMPROBANTE,                                                      ");
        sb.append("       wpd.TIP_COMPROBANTE_REF,                                                  ");
        sb.append("       wpd.COD_CLIENTE,                                                          ");
        sb.append("       wpd.TOT_COMPROBANTE,                                                      ");
        sb.append("       wpd.COD_MONEDA,                                                           ");
        sb.append("       wpd.TIP_ATRIBUTO,                                                         ");
        sb.append("       wpd.GRU_ATRIBUTO,                                                         ");
        sb.append("       wpd.COD_ATRIBUTO,                                                         ");
        sb.append("       wpd.COD_CV,                                                               ");
        sb.append("       wpd.ES_VALE,                                                              ");
        sb.append("       wpd.LATITUD,                                                              ");
        sb.append("       wpd.LONGITUD,                                                             ");
        sb.append("       wpd.TIP_CONDICION,                                                        ");
        sb.append("       wpd.IND_ENVIO,                                                            ");
        sb.append("       wpd.VENDEDOR,                                                             ");
        sb.append("       wpd.TIPO_IMPRESION,                                                       ");
        sb.append("       wpd.ANIO,                                                                 ");
        sb.append("       TO_CHAR(wpd.FEC_SYN,'DD/MM/YYYY HH24:MI:SS'),                             ");
        sb.append("       wpd.TIP_CAMBIO,                                                           ");
        sb.append("       wpd.COD_EMPRESA,                                                          ");
        sb.append("       wpd.CAJAS,                                                                ");
        sb.append("       wpd.PESO,                                                                 ");
        sb.append("       wpd.ORDEN_ENTREGA                                                         ");
        sb.append("  FROM WSINV.WSV_PLANILLA_DET wpd                                                ");
        sb.append("       join wsinv.wsv_planilla_cab wpc on wpc.cod_empresa = wpd.cod_empresa      ");
        sb.append("                                      and wpc.nro_planilla = wpd.nroPlanilla     ");
        sb.append(" WHERE wpc.cod_usuario = '").append(user).append("' ");
        return sb.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(PlanillaDetalle[] reg) {
        String sql = "";
        try {
            for (PlanillaDetalle element : reg) {
                sql = setSQLUpdateOrInsert(element);
                logger.info(sql);
            }
        } catch (SQLException e) {
            logger.error("Error al insertar o actualizar PlanillaDetalle ", e);
            e.printStackTrace();
            return Response.status(400).entity("Error al insertar o actualizar PlanillaDetalle " + e.getMessage()).build();
        }
        return Response.status(200).entity("OK").build();
    }

    private String setSQLUpdateOrInsert(PlanillaDetalle reg) throws SQLException {
        String sql = "";
        if (Boolean.valueOf(reg.getActualiza())) {
            sql = "{CALL WSINV.ACTUALIZA_PLANILLA_DET(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ArrayList<ParametrosProcedimiento> paramsList = new ArrayList<>();
            paramsList.add(new ParametrosProcedimiento(1, reg.getCodMotivoRebote()));
            paramsList.add(new ParametrosProcedimiento(2, reg.getCodSucursal()));
            paramsList.add(new ParametrosProcedimiento(3, reg.getEstado()));
            paramsList.add(new ParametrosProcedimiento(4, AppUtils.convertirStringAFecha(reg.getFecCambioEstado())));
            paramsList.add(new ParametrosProcedimiento(5, AppUtils.convertirStringAFecha(reg.getFecHoraEntrega())));
            paramsList.add(new ParametrosProcedimiento(6, reg.getNcGenera()));
            paramsList.add(new ParametrosProcedimiento(7, reg.getNroComprobante()));
            paramsList.add(new ParametrosProcedimiento(8, reg.getNroComprobanteRef()));
            paramsList.add(new ParametrosProcedimiento(9, reg.getNroPlanilla()));
            paramsList.add(new ParametrosProcedimiento(10, reg.getNroTimbradoRef()));
            paramsList.add(new ParametrosProcedimiento(11, reg.getPuntoEmisionRef()));
            paramsList.add(new ParametrosProcedimiento(12, reg.getRebote()));
            paramsList.add(new ParametrosProcedimiento(13, reg.getSerComprobante()));
            paramsList.add(new ParametrosProcedimiento(14, reg.getSerComprobanteRef()));
            paramsList.add(new ParametrosProcedimiento(15, reg.getSucEmisionRef()));
            paramsList.add(new ParametrosProcedimiento(16, reg.getTipComprobante()));
            paramsList.add(new ParametrosProcedimiento(17, reg.getTipComprobanteRef()));
            paramsList.add(new ParametrosProcedimiento(18, reg.getCodCliente()));
            paramsList.add(new ParametrosProcedimiento(19, reg.getTotComprobante()));
            paramsList.add(new ParametrosProcedimiento(20, reg.getCodMoneda()));
            paramsList.add(new ParametrosProcedimiento(21, reg.getTipAtributo()));
            paramsList.add(new ParametrosProcedimiento(22, reg.getGruAtributo()));
            paramsList.add(new ParametrosProcedimiento(23, reg.getCodAtributo()));
            paramsList.add(new ParametrosProcedimiento(24, reg.getCodCondVenta()));
            paramsList.add(new ParametrosProcedimiento(25, reg.getEsVale()));
            paramsList.add(new ParametrosProcedimiento(26, reg.getLatitud()));
            paramsList.add(new ParametrosProcedimiento(27, reg.getLongitud()));
            paramsList.add(new ParametrosProcedimiento(28, reg.getTipCondicion()));
            paramsList.add(new ParametrosProcedimiento(29, reg.getIndEnvio()));
            paramsList.add(new ParametrosProcedimiento(30, reg.getVendedor()));
            paramsList.add(new ParametrosProcedimiento(31, reg.getTipoImpresion()));
            paramsList.add(new ParametrosProcedimiento(32, reg.getAnio()));
            paramsList.add(new ParametrosProcedimiento(33, AppUtils.convertirStringAFecha(reg.getFecSyn())));
            paramsList.add(new ParametrosProcedimiento(34, reg.getTipCambio()));
            paramsList.add(new ParametrosProcedimiento(35, reg.getCodEmpresa()));
            paramsList.add(new ParametrosProcedimiento(36, reg.getCajas()));
            paramsList.add(new ParametrosProcedimiento(37, reg.getPeso()));
            paramsList.add(new ParametrosProcedimiento(38, reg.getOrdenEntrega()));
            AppUtils.ejecutarProcedimiento(sql, paramsList);
        } else {
            sql = "INSERT INTO WSINV.WS_PLANILLA_DET SET VALUES("
                    + "COD_MOTIVO_REBOTE='" + reg.getCodMotivoRebote() + "',"
                    + "COD_SUCURSAL='" + reg.getCodSucursal() + "',"
                    + "ESTADO='" + reg.getEstado() + "',"
                    + "FEC_CAMBIO_ESTADOL='" + reg.getFecCambioEstado() + "',"
                    + "FECHA_HORA_ENTREGA='" + reg.getFecHoraEntrega() + "',"
                    + "NC_GENERA='" + reg.getNcGenera() + "',"
                    + "NRO_COMPROBANTE='" + reg.getNroComprobante() + "',"
                    + "NRO_COMPROBANTE_REF='" + reg.getNroComprobanteRef() + "',"
                    + "NROPLANILLA='" + reg.getNroPlanilla() + "',"
                    + "NRO_TIMBRADO_REF='" + reg.getNroTimbradoRef() + "',"
                    + "PUNTO_EMISION_REF='" + reg.getPuntoEmisionRef() + "',"
                    + "REBOTE='" + reg.getRebote() + "',"
                    + "SER_COMPROBANTE='" + reg.getSerComprobante() + "',"
                    + "SER_COMPROBANTE_REF='" + reg.getSerComprobanteRef() + "',"
                    + "SUC_EMISION_REF='" + reg.getSucEmisionRef() + "',"
                    + "TIP_COMPROBANTE='" + reg.getTipComprobante() + "',"
                    + "TIP_COMPROBANTE_REF='" + reg.getTipComprobanteRef() + "',"
                    + "COD_CLIENTE='" + reg.getCodCliente() + "',"
                    + "TOT_COMPROBANTE='" + reg.getTotComprobante() + "',"
                    + "COD_MONEDA='" + reg.getCodMoneda() + "'," + "TIP_ATRIBUTO='" + reg.getTipAtributo() + "',"
                    + "GRU_ATRIBUTO='" + reg.getGruAtributo() + "'," + "COD_ATRIBUTO='" + reg.getCodAtributo() + "',"
                    + "COD_CV='" + reg.getCodCondVenta() + "',"
                    + "ES_VALE='" + reg.getEsVale() + "',"
                    + "LATITUD='" + reg.getLatitud() + "'," + "LONGITUD='" + reg.getLongitud() + "',"
                    + "TIP_CONDICION='" + reg.getTipCondicion() + "'," + "IND_ENVIO='" + reg.getIndEnvio() + "',"
                    + "VENDEDOR='" + reg.getVendedor() + "'," + "TIPO_IMPRESION='" + reg.getTipoImpresion() + "',"
                    + "ANIO='" + reg.getAnio() + "'," + "FEC_SYN='" + reg.getFecSyn() + "',"
                    + "TIP_CAMBIO='" + reg.getTipCambio() + "'," + "COD_EMPRESA='" + reg.getCodEmpresa() + "',"
                    + "CAJAS='" + reg.getCajas() + "'," + "PESO='" + reg.getPeso() + "'," + "ORDEN_ENTREGA='" + reg.getOrdenEntrega() + "');";
            AppUtils.realizaCarga(sql);
        }
        return sql;
    }

}
