package com.pablosrl.controllers;

import com.pablosrl.data.Proveedores;
import com.pablosrl.service.ProveedoresService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/proveedores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsProveedores {

    @Inject
    private ProveedoresService proveedoresService;

    private static final Logger logger = Logger.getLogger(WsProveedores.class);

    // GET para obtener una lista paginada de proveedores
    @GET
    @Path("/paginado/{codEmpresa}/{offset}/{limit}")
    public Response obtenerProveedoresPaginados(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("offset") int offset,
            @PathParam("limit") int limit) {
        
        try {
            List<Proveedores> proveedores = proveedoresService.obtenerProveedoresPaginados(codEmpresa, offset, limit);
            if (proveedores != null && !proveedores.isEmpty()) {
                return Response.ok(proveedores).build();  // Devolver la lista paginada
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();  // Si no se encuentran proveedores, devolver NO_CONTENT
            }
        } catch (Exception e) {
            logger.error("Error obteniendo proveedores paginados", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error obteniendo proveedores paginados").build();
        }
    }

    // GET para buscar proveedores por filtro
    @GET
    @Path("/buscar/{codEmpresa}/{filtro}/{limit}")
    public Response buscarProveedores(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("filtro") String filtro,
            @PathParam("limit") int limit) {
        
        try {
            List<Proveedores> proveedores = proveedoresService.buscarProveedores(filtro, codEmpresa, limit);
            if (proveedores != null && !proveedores.isEmpty()) {
                return Response.ok(proveedores).build();  // Devolver los proveedores que coinciden con el filtro
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();  // Si no se encuentran proveedores, devolver NO_CONTENT
            }
        } catch (Exception e) {
            logger.error("Error buscando proveedores", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando proveedores").build();
        }
    }
}
