/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablosrl.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pablosrl.data.ArticulosProDet;
import com.pablosrl.util.AppUtils;

/**
 *
 * @author Usuario
 */
@Path("WsArticulosProDet")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticulosProDet {

    Logger logger = LoggerFactory.getLogger(WsArticulosProDet.class);

    public WsArticulosProDet() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticulosProDet> getIt() {//@QueryParam("codEmpresa") String codEmpresa) {
        ArticulosProDet c = null;
        List<ArticulosProDet> lista = new ArrayList<>();

        String sql = crearConsulta();

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new ArticulosProDet();
                c.setCodEmpresa(rs.getString(1));
                c.setCodArticulo(rs.getString(2));
                c.setCodArticuloPro(rs.getString(3));
                c.setDescripcion(rs.getString(4));
                c.setFechaInicio(rs.getString(5));
                c.setFechaFin(rs.getString(6));
                c.setActivo(rs.getString(7));
                c.setCantidad(rs.getString(8) != null ? new BigDecimal(rs.getString(8)) : null);
                c.setCodPromo(rs.getString(9));
                c.setCodListaPrecio(rs.getString(10));
                c.setEstadoPromo(rs.getString(11));
                c.setPrecioVentaDet(rs.getString(12) != null ? new BigDecimal(rs.getString(12)) : null);
                c.setCostoUltimoDet(rs.getString(13) != null ? new BigDecimal(rs.getString(13)) : null);
                c.setTipoCambioDet(rs.getString(14) != null ? new BigDecimal(rs.getString(14)) : null);

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            logger.error("Error realizando la consulta de alerta " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    public String crearConsulta() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COD_EMPRESA																");
        sb.append("      ,COD_ARTICULO                                                               ");
        sb.append("      ,COD_ARTICULO_PRO                                                           ");
        sb.append("      ,DESCRIPCION                                                                ");
        sb.append("      ,FECHA_INICIO                                                               ");
        sb.append("      ,FECHA_FIN                                                                  ");
        sb.append("      ,ACTIVO                                                                     ");
        sb.append("      ,CANTIDAD                                                                   ");
        sb.append("      ,COD_PROMO                                                                  ");
        sb.append("      ,COD_LISTA_PRECIO                                                           ");
        sb.append("      ,ESTADO_PROMO                                                               ");
        sb.append("      ,PRECIO_VENTA_DET                                                           ");
        sb.append("      ,COSTO_ULTIMO_DET                                                           ");
        sb.append("      ,TIPO_CAMBIO_DET                                                            ");
        sb.append(" FROM   ST_ARTICULOS_PRO                                                          ");
        sb.append(" WHERE  (COD_EMPRESA, COD_ARTICULO, COD_PROMO) IN                                 ");
        sb.append("       (SELECT S.COD_EMPRESA                                                      ");
        sb.append("              ,S.COD_ARTICULO                                                     ");
        sb.append("              ,S.COD_PROMO                                                        ");
        sb.append("        FROM   ST_ARTICULOS_PRO_CAB S                                             ");
        sb.append("        WHERE  (NVL(S.CONFIRMADO, 'P') = 'C' AND NVL(S.CANCELADO, 'N') = 'N')     ");
        sb.append("               AND (TRUNC(SYSDATE) BETWEEN S.FECHA_INICIO AND S.FECHA_FIN))       ");
//        sb.append("     SELECT COD_EMPRESA, 	 ");
//        sb.append("	       COD_ARTICULO,     ");
//        sb.append("	       COD_ARTICULO_PRO, ");
//        sb.append("	       DESCRIPCION,      ");
//        sb.append("	       FECHA_INICIO,     ");
//        sb.append("	       FECHA_FIN,        ");
//        sb.append("	       ACTIVO,           ");
//        sb.append("	       CANTIDAD,         ");
//        sb.append("	       COD_PROMO,        ");
//        sb.append("	       COD_LISTA_PRECIO, ");
//        sb.append("	       ESTADO_PROMO,     ");
//        sb.append("	       PRECIO_VENTA_DET, ");
//        sb.append("	       COSTO_ULTIMO_DET, ");
//        sb.append("	       TIPO_CAMBIO_DET   ");
//        sb.append("   FROM ST_ARTICULOS_PRO  ");
//        sb.append("   WHERE (COD_EMPRESA, COD_ARTICULO_PRO, COD_PROMO) IN (").append(subConsulta()).append(")");
////        sb.append("  WHERE cod_empresa = '").append(codEmpresa).append("'");

        return sb.toString();
    }

    public String subConsulta() {
        StringBuilder sb = new StringBuilder();
        sb.append("        SELECT  S.COD_EMPRESA,             ");
        sb.append("        	   S.COD_ARTICULO,            ");
        sb.append("        	   S.COD_PROMO                ");
        sb.append("          FROM ST_ARTICULOS_PRO_CAB S      ");
        sb.append("         WHERE TO_DATE(S.FECHA_FIN,'DD/MM/RRRR') >= TO_DATE(TO_CHAR(sysdate,'DD/MM/RRRR'),'DD/MM/RRRR')    ");
        sb.append("           AND (NVL(S.CONFIRMADO, 'P') = 'C'    ");
        sb.append("           AND NVL(S.CANCELADO, 'N') = 'N')    ");
        sb.append("           AND (TRUNC(SYSDATE) BETWEEN S.FECHA_INICIO AND S.FECHA_FIN)    ");

        return sb.toString();
    }

}
