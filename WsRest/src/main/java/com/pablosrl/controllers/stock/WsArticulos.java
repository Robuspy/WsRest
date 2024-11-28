package com.pablosrl.controllers.stock;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.data.stock.Articulos;
import com.pablosrl.service.stock.ArticulosService;

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
            // Asegúrate de que estás llamando al método correcto
            List<Articulos> articulos = articulosService.buscarArticulos("", codEmpresa, limit);
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
            // Asegúrate de que el nombre del método coincide con el definido en el servicio
            List<Articulos> articulos = articulosService.buscarArticulos(filtro, codEmpresa, limit);
            return articulos.isEmpty() ?
                Response.status(Response.Status.NO_CONTENT).build() :
                Response.ok(articulos).build();
        } catch (Exception e) {
            logger.error("Error buscando artículos", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando artículos").build();
        }
    }


    @GET
    @Path("/buscarArticulo/{codEmpresa}/{filtro}")
    public Response buscarArticulosExacto(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("filtro") String filtro) {

        try {
            List<Articulos> articulos = articulosService.buscarArticulosExacto(codEmpresa, filtro);
            return articulos.isEmpty() ?
                Response.status(Response.Status.NO_CONTENT).build() :
                Response.ok(articulos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando artículos").build();
        }
    }



}
