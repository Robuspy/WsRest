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

import com.pablosrl.data.PlanillaCabecera;
import com.pablosrl.util.AppUtils;
import com.pablosrl.util.ParametrosProcedimiento;

/**
 * @author Ivan
 */
@Path("WsPlanillaCabecera")
@Produces(MediaType.APPLICATION_JSON)
public class WsPlanillaCabecera {

    Logger logger = LoggerFactory.getLogger(WsPlanillaCabecera.class);

    public WsPlanillaCabecera() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlanillaCabecera> getIt(@QueryParam("codUsuario") String codigo) {

        List<PlanillaCabecera> lista = new ArrayList<>();

        String sql1 = "SELECT wpc.NRO_PLANILLA, wpc.COD_SUCURSAL, "
                + " wpc.COD_VEHICULO, wpc.ESTADO, TO_CHAR(wpc.FEC_CIERRE,'DD/MM/YYYY HH24:MI:SS'), "
                + " TO_CHAR(wpc.FEC_PLANILLA,'DD/MM/YYYY HH24:MI:SS'),"
                + " TO_CHAR(wpc.FEC_RENDICION,'DD/MM/YYYY HH24:MI:SS'), wpc.IND_REFRIGERADO, wpc.COD_EMPRESA "
                + " FROM WSINV.WSV_PLANILLA_CAB wpc "
                + " JOIN WSINV.WSV_PLANILLA_DET wpd ON wpc.NRO_PLANILLA = wpd.NROPLANILLA "
                + " WHERE wpd.VENDEDOR = '" + codigo + "' GROUP BY wpc.NRO_PLANILLA, wpc.COD_EMPRESA, "
                + " wpc.COD_SUCURSAL, wpc.COD_VEHICULO, wpc.ESTADO, wpc.FEC_CIERRE, wpc.FEC_PLANILLA, "
                + " wpc.FEC_RENDICION, wpc.IND_REFRIGERADO ORDER BY wpc.NRO_PLANILLA ASC";

        String sql = sqlCab(codigo);
        logger.debug("consulta " + sql);

        try {

            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                PlanillaCabecera planillaCabecera = new PlanillaCabecera();
                planillaCabecera.setNroPlanilla(rs.getString(1));
                planillaCabecera.setCodSucursal(rs.getString(2));
                planillaCabecera.setCodVehiculo(rs.getString(3));
                planillaCabecera.setEstado(rs.getString(4));
                planillaCabecera.setFecCierre(rs.getString(5));
                planillaCabecera.setFecPlanilla(rs.getString(6));
                planillaCabecera.setFecRendicion(rs.getString(7));
                planillaCabecera.setIndRefrigerado(rs.getString(8));
                planillaCabecera.setCodEmpresa(rs.getString(9));
                lista.add(planillaCabecera);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            logger.error("Error realizando la consulta de planilla cabecera " + sql, e);
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    private String sqlCab(String user) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT wpc.NRO_PLANILLA, 									");
        sb.append("       wpc.COD_SUCURSAL,                                     ");
        sb.append("       wpc.COD_VEHICULO,                                     ");
        sb.append("       nvl(wpc.ESTADO,'P'),                                           ");
        sb.append("       TO_CHAR(wpc.FEC_CIERRE,'DD/MM/YYYY HH24:MI:SS'),      ");
        sb.append("       TO_CHAR(wpc.FEC_PLANILLA,'DD/MM/YYYY HH24:MI:SS'),    ");
        sb.append("       TO_CHAR(wpc.FEC_RENDICION,'DD/MM/YYYY HH24:MI:SS'),   ");
        sb.append("       wpc.IND_REFRIGERADO,                                  ");
        sb.append("       wpc.COD_EMPRESA                                       ");
        sb.append("  FROM WSINV.WSV_PLANILLA_CAB wpc                            ");
        sb.append(" WHERE wpc.cod_usuario = '").append(user).append("'          ");
        sb.append(" ORDER BY wpc.nro_planilla asc                               ");
        return sb.toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(PlanillaCabecera[] reg) {
        String sql = "";
        try {
            for (PlanillaCabecera element : reg) {
                sql = setSQLUpdateOrInsert(element);
                logger.info(sql);
            }
        } catch (SQLException e) {
            logger.error("Error al insertar o actualizar el cliente ", e);
            e.printStackTrace();
            return Response.status(400).entity("Error al insertar o actualizar cliente " + e.getMessage()).build();
        }
        return Response.status(200).entity("OK").build();
    }

    private String setSQLUpdateOrInsert(PlanillaCabecera reg) throws SQLException {
        String sql = "";
        if (Boolean.valueOf(reg.getActualiza())) {
            sql = "{CALL WSINV.ACTUALIZA_PLANILLA_CAB(?,?,?,?,?,?,?,?,?,?)";
            ArrayList<ParametrosProcedimiento> paramsList = new ArrayList<>();
            paramsList.add(new ParametrosProcedimiento(1, reg.getNroPlanilla()));
            paramsList.add(new ParametrosProcedimiento(2, reg.getCodSucursal()));
            paramsList.add(new ParametrosProcedimiento(3, reg.getCodVehiculo()));
            paramsList.add(new ParametrosProcedimiento(4, reg.getEstado()));
            paramsList.add(new ParametrosProcedimiento(5, AppUtils.convertirStringAFecha(reg.getFecCierre())));
            paramsList.add(new ParametrosProcedimiento(6, AppUtils.convertirStringAFecha(reg.getFecPlanilla())));
            paramsList.add(new ParametrosProcedimiento(7, AppUtils.convertirStringAFecha(reg.getFecRendicion())));
            paramsList.add(new ParametrosProcedimiento(8, reg.getIndRefrigerado()));
            paramsList.add(new ParametrosProcedimiento(9, reg.getCodEmpresa()));
            paramsList.add(new ParametrosProcedimiento(10, reg.getNroPlanilla()));
            AppUtils.ejecutarProcedimiento(sql, paramsList);
        } else {
            sql = "insert into WSINV.WS_PLANILLA_CAB (NRO_PLANILLA, "
                    + "COD_SUCURSAL, COD_VEHICULO, ESTADO, FEC_CIERRE,"
                    + "FEC_PLANILLA, FEC_RENDICION, IND_REFRIGERADO, COD_EMPRESA)"
                    + " values("
                    + "'" + reg.getNroPlanilla() + "',"
                    + "'" + reg.getCodSucursal() + "',"
                    + "'" + reg.getCodVehiculo() + "',"
                    + "'" + reg.getEstado() + "',"
                    + "'" + reg.getFecCierre() + "',"
                    + "'" + reg.getFecPlanilla() + "',"
                    + "'" + reg.getFecRendicion() + "',"
                    + "'" + reg.getIndRefrigerado() + "',"
                    + "'" + reg.getCodEmpresa() + "')";
        }
        return sql;
    }

}
