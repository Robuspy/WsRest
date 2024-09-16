/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablosrl.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
/*import java.sql.SQLException;*/
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/*import javax.ws.rs.QueryParam;*/
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import com.pablosrl.data.Alerta;*/
import com.pablosrl.data.ArticulosProCab;
import com.pablosrl.util.AppUtils;

/**
 *
 * @author Usuario
 */
@Path("WsArticulosProCab")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticulosProCab {

    Logger logger = LoggerFactory.getLogger(WsArticulosProCab.class);

    public WsArticulosProCab() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArticulosProCab> getIt() {//@QueryParam("codEmpresa") String codEmpresa) {
        ArticulosProCab c = null;
        List<ArticulosProCab> lista = new ArrayList<>();

        String sql = crearConsulta();

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new ArticulosProCab();
                c.setCodEmpresa(rs.getString(1));
                c.setCodArticulo(rs.getString(2));
                c.setCodPromo(rs.getString(3));
                c.setFechaInicio(rs.getString(4));
                c.setFechaFin(rs.getString(5));
                c.setUsuAlta(rs.getString(6));
                c.setFechaAlta(rs.getString(7));
                c.setUsuConfirma(rs.getString(8));
                c.setFechaConfirma(rs.getString(9));
                c.setUsuCancela(rs.getString(10));
                c.setFechaCancela(rs.getString(11));
                c.setCodListaPrecio(rs.getString(12));
                c.setConfirmado(rs.getString(13));
                c.setCancelado(rs.getString(14));
                c.setObservacion(rs.getString(15));
                c.setCodCondVta(rs.getString(16));
                c.setCostoUltimo(rs.getString(17) != null ? new BigDecimal(rs.getString(17)) : null);
                c.setPrecioVenta(rs.getString(18) != null ? new BigDecimal(rs.getString(18)) : null);
                c.setCantidadCab(rs.getString(19) != null ? new BigDecimal(rs.getString(19)) : null);
                c.setTipoCambioCab(rs.getString(20) != null ? new BigDecimal(rs.getString(20)) : null);
                c.setCodPromoRel(rs.getString(21));
                c.setPromoHobby(rs.getString(22));
                lista.add(c);
            }

        } catch (Exception e) {
            System.out.println("Error en la consulta ");
            logger.error("Error realizando la consulta de alerta " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    public String crearConsulta() {
        StringBuilder sb = new StringBuilder();
//        sb.append(" SELECT CAB.COD_EMPRESA														");
//        sb.append("       ,DET.COD_ARTICULO_PRO                                                 ");
//        sb.append("       ,CAB.COD_PROMO                                                        ");
//        sb.append("       ,CAB.FECHA_INICIO                                                     ");
//        sb.append("       ,CAB.FECHA_FIN                                                        ");
//        sb.append("       ,CAB.USU_ALTA                                                         ");
//        sb.append("       ,CAB.FECHA_ALTA                                                       ");
//        sb.append("       ,CAB.USU_CONFIRMA                                                     ");
//        sb.append("       ,CAB.FECHA_CONFIRMA                                                   ");
//        sb.append("       ,CAB.USU_CANCELA                                                      ");
//        sb.append("       ,CAB.FECHA_CANCELA                                                    ");
//        sb.append("       ,CAB.COD_LISTA_PRECIO                                                 ");
//        sb.append("       ,CAB.CONFIRMADO                                                       ");
//        sb.append("       ,CAB.CANCELADO                                                        ");
//        sb.append("       ,CAB.OBSERVACION                                                      ");
//        sb.append("       ,CAB.COD_COND_VTA                                                     ");
//        sb.append("       ,CAB.COSTO_ULTIMO                                                     ");
//        sb.append("       ,CAB.PRECIO_VENTA                                                     ");
//        sb.append("       ,CAB.CANTIDAD_CAB                                                     ");
//        sb.append("       ,CAB.TIPO_CAMBIO_CAB                                                  ");
//        sb.append("       ,CAB.COD_PROMO_REL                                                    ");
//        sb.append("       ,CAB.PROMO_HOBBY                                                      ");
//        sb.append("  FROM   ST_ARTICULOS_PRO_CAB CAB                                            ");
//        sb.append("        ,ST_ARTICULOS_PRO DET                                                ");
//        sb.append(" WHERE   (NVL(CAB.CONFIRMADO, 'P') = 'C' AND NVL(CAB.CANCELADO, 'N') = 'N')  ");
//        sb.append("        AND (TRUNC(SYSDATE) BETWEEN CAB.FECHA_INICIO AND CAB.FECHA_FIN)      ");
//        sb.append("        AND CAB.COD_EMPRESA = DET.COD_EMPRESA                                ");
//        sb.append("        AND CAB.COD_ARTICULO = DET.COD_ARTICULO                              ");
//        sb.append("        AND CAB.COD_PROMO = DET.COD_PROMO                                    ");
        sb.append("        SELECT  COD_EMPRESA,             ");
        sb.append("        	   COD_ARTICULO,            ");
        sb.append("        	   COD_PROMO,               ");
        sb.append("        	   FECHA_INICIO,            ");
        sb.append("        	   FECHA_FIN,               ");
        sb.append("        	   USU_ALTA,                ");
        sb.append("        	   FECHA_ALTA,              ");
        sb.append("        	   USU_CONFIRMA,            ");
        sb.append("        	   FECHA_CONFIRMA,          ");
        sb.append("        	   USU_CANCELA,             ");
        sb.append("        	   FECHA_CANCELA,           ");
        sb.append("        	   COD_LISTA_PRECIO,        ");
        sb.append("        	   CONFIRMADO,              ");
        sb.append("        	   CANCELADO,               ");
        sb.append("        	   OBSERVACION,             ");
        sb.append("        	   COD_COND_VTA,            ");
        sb.append("        	   COSTO_ULTIMO,            ");
        sb.append("        	   PRECIO_VENTA,            ");
        sb.append("        	   CANTIDAD_CAB,            ");
        sb.append("        	   TIPO_CAMBIO_CAB,         ");
        sb.append("        	   COD_PROMO_REL,           ");
        sb.append("        	   PROMO_HOBBY              ");
        sb.append("          FROM ST_ARTICULOS_PRO_CAB      ");
        sb.append("         WHERE TO_DATE(FECHA_FIN,'DD/MM/RRRR') >= TO_DATE(TO_CHAR(sysdate,'DD/MM/RRRR'),'DD/MM/RRRR')    ");
        sb.append("           AND (NVL(CONFIRMADO, 'P') = 'C'    ");
        sb.append("           AND NVL(CANCELADO, 'N') = 'N')    ");
        sb.append("           AND (TRUNC(SYSDATE) BETWEEN FECHA_INICIO AND FECHA_FIN)    ");

////        sb.append("         WHERE cod_empresa = '").append(codEmpresa).append("'");
        return sb.toString();
    }

}
