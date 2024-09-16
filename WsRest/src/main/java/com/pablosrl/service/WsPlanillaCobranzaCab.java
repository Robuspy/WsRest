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

import com.pablosrl.data.PlanillaCobranzaCab;
import com.pablosrl.data.PlanillaCobranzaDet;
import com.pablosrl.util.AppUtils;

@Path("WsPlanillaCobranza")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsPlanillaCobranzaCab {

    Logger logger = Logger.getLogger(WsPlanillaCobranzaCab.class);

    public WsPlanillaCobranzaCab() {
        BasicConfigurator.configure();
    }

    @GET
    @Path("/cab")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanillaCobranzaCab> getIt(@QueryParam("codEmpresa") String codEmpresa, @QueryParam("codUsuario") String codUsuario) {
        PlanillaCobranzaCab c = null;
        List<PlanillaCobranzaCab> lista = new ArrayList<>();

        String sql = armarWhereCab(codUsuario, codEmpresa);

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new PlanillaCobranzaCab();
                c.setCodEmpresa(rs.getString(1));
                c.setCodCobrador(rs.getString(2));
                c.setNroPlanilla(rs.getString(3));
                c.setCodCliente(rs.getString(4));
                c.setDiasCobro(rs.getString(5));

//                c.setCodEmpresa(rs.getString(1));
//                c.setCodSucursal(rs.getString(2));
//                c.setNroPlanilla(rs.getString(3));
//                c.setCodCobrador(rs.getString(4));
//                c.setFecPlanilla(rs.getString(5));
//                c.setFecRendicion(rs.getString(6));
//                c.setCodMoneda(rs.getString(7));
//                c.setTipCambio(rs.getString(8));
//                c.setTipPlanilla(rs.getString(9));
//                c.setSerPlanilla(rs.getString(10));
//                c.setTotComprobante(rs.getString(11));
//                c.setTotSaldo(rs.getString(12));
//                c.setCodZona(rs.getString(13));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de monedas " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    @GET
    @Path("/det")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanillaCobranzaDet> getItDet(@QueryParam("codEmpresa") String codEmpresa, @QueryParam("codUsuario") String codUsuario) {
        PlanillaCobranzaDet c = null;
        List<PlanillaCobranzaDet> lista = new ArrayList<>();

        String sql = armarWhereDet(codUsuario, codEmpresa);

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new PlanillaCobranzaDet();
                c.setCodEmpresa(rs.getString(1));
                c.setTipComprobante(rs.getString(2));
                c.setNroPlanilla(rs.getString(3));
                c.setSerComprobante(rs.getString(4));
                c.setNroComprobante(rs.getString(5));
                c.setNroCuota(rs.getString(6));
                c.setCodMoneda(rs.getString(7));
                c.setMontoCuota(rs.getString(8));
                c.setCodCliente(rs.getString(9));
                c.setSaldoCuota(rs.getString(10));

//                c.setCodEmpresa(rs.getString(1));
//                c.setCodSucursal(rs.getString(2));
//                c.setNroPlanilla(rs.getString(3));
//                c.setTipComprobante(rs.getString(4));
//                c.setSerComprobante(rs.getString(5));
//                c.setNroComprobante(rs.getString(6));
//                c.setEstado(rs.getString(7));
//                c.setNroCuota(rs.getString(8));
//                c.setMontoCuota(rs.getString(9));
//                c.setCodCliente(rs.getString(10));
//                c.setSaldoCuota(rs.getString(11));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de monedas " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    public String armarWhereDet(String codCobrador, String codEmpresa) {
        StringBuilder sb = new StringBuilder();
        sb.append("select d.cod_empresa, ");
        sb.append("       d.tipo_comprobante, ");
        sb.append("       d.nro_planilla,");
        sb.append("       d.ser_comprobante, ");
        sb.append("       d.nro_comprobante,");
        sb.append("       d.nro_cuota,");
        sb.append("       d.cod_moneda,");
        sb.append("       d.monto_cuota, ");
        sb.append("       d.cod_cliente, ");
        sb.append("       d.saldo_cuota");
        sb.append(" from  wsv_planilla_cobranza_det d");
        sb.append("       join wsv_planilla_cobranza_cab c on c.cod_empresa = d.cod_empresa ");
//        sb.append(" from  wsv_planilla_cobranza_det_2 d");
//        sb.append("       join wsv_planilla_cobranza_cab2 c on c.cod_empresa = d.cod_empresa ");
        sb.append("                                       and c.cod_cliente = d.cod_cliente ");
//        sb.append("                                       and c.nro_planilla = d.nro_planilla ");
        sb.append("   where d.cod_empresa = '").append(codEmpresa).append("'");
        sb.append("   and c.cod_cobrador = '").append(codCobrador).append("'");
        sb.append("   and d.saldo_cuota <> 0 ");
//        sb.append("select d.cod_empresa, ");
//        sb.append("       d.cod_sucursal, ");
//        sb.append("       d.nro_planilla,");
//        sb.append("       d.tip_comprobante, ");
//        sb.append("       d.ser_comprobante, ");
//        sb.append("       d.nro_comprobante,");
//        sb.append("       d.estado,");
//        sb.append("       d.nro_cuota,");
//        sb.append("       d.monto_cuota, ");
//        sb.append("       d.cod_cliente, ");
//        sb.append("       d.saldo_cuota");
//        sb.append(" from  wsv_planilla_cobranza_det d");
//        sb.append("       join wsv_planilla_cobranza_cab c on c.cod_empresa = d.cod_empresa ");
//        sb.append("                                       and c.cod_sucursal = d.cod_sucursal ");
//        sb.append("                                       and c.nro_planilla = d.nro_planilla ");
//        sb.append("   where d.cod_empresa = '").append(codEmpresa).append("'");
//        sb.append("   and c.cod_cobrador = '").append(codCobrador).append("'");
//        sb.append("   and c.tot_saldo > 0 ");
        return sb.toString();
    }

    public String armarWhereCab(String codCobrador, String codEmpresa) {
        StringBuilder sb = new StringBuilder();
        sb.append("select cod_empresa, ");
        sb.append("       cod_cobrador, ");
        sb.append("       nro_planilla, ");
        sb.append("       cod_cliente, ");
        sb.append("       dias_cobro ");
//        sb.append("  from wsv_planilla_cobranza_cab2 ");
        sb.append("  from wsv_planilla_cobranza_cab ");
        sb.append("   where cod_empresa = '").append(codEmpresa).append("'");
        sb.append("   and cod_cobrador = '").append(codCobrador).append("'");

//        sb.append("select cod_empresa, ");
//        sb.append("       cod_sucursal, ");
//        sb.append("       nro_planilla,");
//        sb.append("       cod_cobrador, ");
//        sb.append("       fec_planilla, ");
//        sb.append("       fec_rendicion,");
//        sb.append("       cod_moneda,");
//        sb.append("       tip_cambio,");
//        sb.append("       tip_planilla, ");
//        sb.append("       ser_planilla, ");
//        sb.append("       tot_comprobante, ");
//        sb.append("       tot_saldo, ");
//        sb.append("       cod_zona ");
//        sb.append("       dias_cobro ");
//        sb.append("  from wsv_planilla_cobranza_cab ");
//        sb.append(" where tot_saldo > 0 ");
//        sb.append("   and cod_empresa = '").append(codEmpresa).append("'");
//        sb.append("   and cod_cobrador = '").append(codCobrador).append("'");
        return sb.toString();
    }
}
