package com.pablosrl.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Clientes;
import com.pablosrl.util.AppUtils;

@Path("WsUbicacion")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsUbicacion {

    Logger logger = Logger.getLogger(WsUbicacion.class);

    public WsUbicacion() {
        BasicConfigurator.configure();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Clientes cliente) {
        String sql = "UPDATE INV.CC_DATOS_CLIENTES "
                + "SET LATITUD = '" + cliente.getLatitud() + "' , LONGITUD = '" + cliente.getLongitud() + "' "
                + "WHERE COD_CLIENTE = '" + cliente.getCodCliente() + "'";
        try {
            AppUtils.realizaCarga(sql);
        } catch (Exception e) {
            logger.error("Error al actualizar ubicacion ", e);
            e.printStackTrace();
            return Response.status(400).entity("Error al actualizar ubicacion " + e.getMessage() + " " + sql).build();
        }
        return Response.status(200).entity("OK").build();
    }
}
