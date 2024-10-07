package com.pablosrl.controllers.stock;

import com.pablosrl.data.stock.Articulos;
import com.pablosrl.service.stock.ArticulosService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/articulos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticulos {

    @Inject
    private ArticulosService articulosService;

    private static final Logger logger = Logger.getLogger(WsArticulos.class);

    @GET
    @Path("/paginado/{codEmpresa}/{offset}/{limit}")
    public Response obtenerArticulosPaginados(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("offset") int offset,
            @PathParam("limit") int limit) {
        
        try {
            List<Articulos> articulos = articulosService.buscarArticulosPorCodigo("", codEmpresa, limit);
            return articulos.isEmpty() ? 
                Response.status(Response.Status.NO_CONTENT).build() :
                Response.ok(articulos).build();
        } catch (Exception e) {
            logger.error("Error al obtener artículos paginados", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener artículos").build();
        }
    }

    @GET
    @Path("/buscar/{codEmpresa}/{filtro}/{limit}")
    public Response buscarArticulos(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("filtro") String filtro,
            @PathParam("limit") int limit) {
        
        try {
            List<Articulos> articulos = articulosService.buscarArticulosPorCodigo(filtro, codEmpresa, limit);
            return articulos.isEmpty() ?
                Response.status(Response.Status.NO_CONTENT).build() :
                Response.ok(articulos).build();
        } catch (Exception e) {
            logger.error("Error buscando artículos", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando artículos").build();
        }
    }
}
