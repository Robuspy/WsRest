package com.pablosrl.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
            // Verificar los datos que llegan en el PedidoCompletoDTO
            logger.info("Insertando pedido completo para empresa: " + pedidoCompleto.getCabecera().getCodEmpresa());
            logger.info("Cabecera: " + pedidoCompleto.getCabecera().toString());
            logger.info("Detalles: " + pedidoCompleto.getDetalles().size() + " detalles recibidos");

            // Intentar insertar el pedido completo
            String resultado = pedidoService.insertarPedidoCompleto(pedidoCompleto);

            if (resultado != null) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Pedido completo insertado correctamente. Número de comprobante: " + resultado);
                return Response.status(Response.Status.CREATED).entity(response).build();  // Devolver el mensaje con el número de comprobante en formato JSON
            } else {
                logger.error("Error en la inserción del pedido completo: el servicio devolvió null o un resultado inesperado.");
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error insertando el pedido completo: resultado inesperado").build();
            }

        } catch (Exception e) {
            // Registrar el mensaje completo del error
            logger.error("Error insertando pedido completo: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error insertando el pedido completo: " + e.getMessage())
                           .build();
        }
    }


}
