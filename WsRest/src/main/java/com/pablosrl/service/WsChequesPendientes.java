
package com.pablosrl.service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.ChequesPendientes;
import com.pablosrl.util.AppUtils;


@Path("WsChequesPendientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsChequesPendientes {

    Logger logger = Logger.getLogger(WsChequesPendientes.class);

    public WsChequesPendientes() {
        BasicConfigurator.configure();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ChequesPendientes> getIt() {
    	ChequesPendientes c = null;
        List<ChequesPendientes> lista = new ArrayList<>();

        String sql  = " select pc.cod_cliente,"
        			+ "       TO_CHAR(pc.fec_emision, 'dd/mm/yyyy'),"
        			+ "       TO_CHAR(pc.fec_vencimiento, 'dd/mm/yyyy'),"
        			+ "       pc.dias_vencimiento,"
        			+ "       pc.tip_documento,"
        			+ "       pc.nro_valor,"
        			+ "       pc.estado_valor,"
        			+ "       pc.monto_valor"
        			+ "  from wsv_cheques_pendientes pc";

        logger.debug("consulta " + sql);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                c = new ChequesPendientes();
                c.setCodCliente(rs.getString(1));
                c.setFecEmision(rs.getString(2));
                c.setFecVencimiento(rs.getString(3));
                c.setDiasVencimiento(rs.getString(4));
                c.setTipoDocumento(rs.getString(5));
                c.setNroValor(rs.getString(6));
                c.setEstadoValor(rs.getString(7));
                c.setMontoValor(rs.getString(8));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de Valores Cheques " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return lista;
    }

}
