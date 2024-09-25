package com.pablosrl.controllers;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.dto.PedidoCompletoDTO;
import com.pablosrl.service.CmPedidoService;

@Path("/cmpedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCmPedidos {

    @Inject
    private CmPedidoService pedidoService;

    private static final Logger logger = Logger.getLogger(WsCmPedidos.class);

 // GET para obtener un pedido completo (cabecera + detalles)
    @GET
    @Path("/completo/{codEmpresa}/{tipComprobante}/{serComprobante}/{nroComprobante}")
    public Response obtenerPedidoCompleto(
        @PathParam("codEmpresa") String codEmpresa,
        @PathParam("tipComprobante") String tipComprobante,
        @PathParam("serComprobante") String serComprobante,
        @PathParam("nroComprobante") String nroComprobante) {
        
        try {
            PedidoCompletoDTO pedidoCompleto = pedidoService.obtenerPedidoCompleto(codEmpresa, tipComprobante, serComprobante, nroComprobante);
            
            // Si no se encuentra el pedido, devolver NO_CONTENT
            if (pedidoCompleto != null) {
                return Response.ok(pedidoCompleto).build();  // Devolver el pedido completo, incluyendo campos con null
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();  // Si no se encuentra el pedido, devolver NO_CONTENT
            }
        } catch (Exception e) {
            logger.error("Error obteniendo pedido completo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error obteniendo pedido completo").build();
        }
    }


 // POST para insertar un pedido completo (cabecera + detalles)
    @POST
    @Path("/completo")
    public Response insertarPedidoCompleto(PedidoCompletoDTO pedidoCompleto) {
        if (pedidoCompleto == null || pedidoCompleto.getCabecera() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos incompletos en el pedido").build();
        }

        try {
            logger.info("Insertando pedido completo para empresa: " + pedidoCompleto.getCabecera().getCodEmpresa());
            String resultado = pedidoService.insertarPedidoCompleto(pedidoCompleto);

            if (resultado != null) {
                return Response.status(Response.Status.CREATED).entity(resultado).build();  // Devolver el mensaje con el número de comprobante
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error insertando el pedido completo").build();
            }

        } catch (Exception e) {
            logger.error("Error insertando pedido completo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error insertando el pedido completo").build();
        }
    }

}
