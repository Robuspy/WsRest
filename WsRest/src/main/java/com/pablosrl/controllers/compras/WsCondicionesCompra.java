package com.pablosrl.controllers.compras;

import com.pablosrl.data.compras.CondicionCompra;
import com.pablosrl.service.compras.CondicionCompraService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/condiciones-compra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCondicionesCompra {

    @Inject
    private CondicionCompraService condicionCompraService;

    private static final Logger logger = Logger.getLogger(WsCondicionesCompra.class);

    // GET para obtener una lista paginada de condiciones de compra
    @GET
    @Path("/paginado/{codEmpresa}/{offset}/{limit}")
    public Response obtenerCondicionesCompraPaginadas(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("offset") int offset,
            @PathParam("limit") int limit) {

        try {
            List<CondicionCompra> condiciones = condicionCompraService.obtenerCondicionesCompraPaginadas(codEmpresa, offset, limit);
            if (condiciones != null && !condiciones.isEmpty()) {
                return Response.ok(condiciones).build();  // Devolver la lista paginada
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();  // Si no se encuentran condiciones, devolver NO_CONTENT
            }
        } catch (Exception e) {
            logger.error("Error obteniendo condiciones de compra paginadas", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error obteniendo condiciones de compra paginadas").build();
        }
    }

    // GET para buscar condiciones de compra por filtro
    @GET
    @Path("/buscar/{codEmpresa}/{filtro}/{limit}")
    public Response buscarCondicionesCompra(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("filtro") String filtro,
            @PathParam("limit") int limit) {

        try {
            List<CondicionCompra> condiciones = condicionCompraService.buscarCondicionesCompra(filtro, codEmpresa, limit);
            if (condiciones != null && !condiciones.isEmpty()) {
                return Response.ok(condiciones).build();  // Devolver las condiciones que coinciden con el filtro
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();  // Si no se encuentran condiciones, devolver NO_CONTENT
            }
        } catch (Exception e) {
            logger.error("Error buscando condiciones de compra", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando condiciones de compra").build();
        }
    }
}
