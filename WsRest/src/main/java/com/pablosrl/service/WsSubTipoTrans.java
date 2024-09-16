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

import com.pablosrl.data.SubTipoTrans;
import com.pablosrl.util.AppUtils;

@Path("WsSubTipoTrans")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsSubTipoTrans {

    Logger logger = Logger.getLogger(WsSubTipoTrans.class);

    public WsSubTipoTrans() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubTipoTrans> getIt() {
        SubTipoTrans c = null;
        List<SubTipoTrans> lista = new ArrayList<>();

        String sql = "select COD_EMPRESA, TIP_DOCUMENTO, SUBTIPO_TRANS, "
                + "DESCRIPCION, CARGA_VALORES, CARGA_OTROS, TIPO_TRANS "
                + "from wsv_subtipotrans ";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new SubTipoTrans();
                c.setCodEmpresa(rs.getString(1));
                c.setTipDocumento(rs.getString(2));
                c.setSubTipo(rs.getString(3));
                c.setDescripcion(rs.getString(4));
                c.setCargaValores(rs.getString(5));
                c.setCargaOtros(rs.getString(6));
                c.setTipoTrans(rs.getString(7));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de Sucursales " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }
}
