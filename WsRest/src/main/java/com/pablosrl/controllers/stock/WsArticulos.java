package com.pablosrl.controllers.stock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import com.pablosrl.data.stock.ArticulosExistencias;
import com.pablosrl.service.stock.ArticulosService;
import com.pablosrl.util.AppUtils;

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
    
    @GET
    @Path("/imagen/{codArticulo}")
    @Produces("image/*") // Soporta cualquier formato de imagen
    public Response obtenerImagenArticulo(@PathParam("codArticulo") String codArticulo) {
        try {
            // Construir las posibles rutas de la imagen
            String jpgPath = AppUtils.IMAGE_DIRECTORY_DEV + codArticulo + ".jpg";
            String pngPath = AppUtils.IMAGE_DIRECTORY_DEV + codArticulo + ".png";

            File imageFile = null;

            // Verificar si existe el archivo .jpg
            if (new File(jpgPath).exists()) {
                imageFile = new File(jpgPath);
            } 
            // Si no, verificar si existe el archivo .png
            else if (new File(pngPath).exists()) {
                imageFile = new File(pngPath);
            }

            // Si no se encuentra el archivo
            if (imageFile == null) {
                logger.warn("Imagen no encontrada para el artículo: " + codArticulo);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Imagen no encontrada para el artículo: " + codArticulo)
                        .build();
            }

            // Leer el archivo de imagen
            byte[] imageData = Files.readAllBytes(imageFile.toPath());

            // Determinar el tipo MIME del archivo
            String mimeType = Files.probeContentType(imageFile.toPath());

            // Devolver la imagen como respuesta
            return Response.ok(imageData).type(mimeType).build();

        } catch (IOException e) {
            logger.error("Error al obtener la imagen del artículo: " + codArticulo, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al procesar la imagen del artículo: " + codArticulo).build();
        } catch (Exception e) {
            logger.error("Error inesperado al obtener la imagen del artículo: " + codArticulo, e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error inesperado al obtener la imagen").build();
        }
    }


    @GET
    @Path("/buscar-existencias/{codEmpresa}/{filtro}/{offset}/{limit}")
    public Response buscarArticulosConExistencia(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("filtro") String filtro,
            @PathParam("offset") int offset,
            @PathParam("limit") int limit) {

        try {
            List<ArticulosExistencias> articulos = articulosService.buscarArticulosConExistencia(codEmpresa, filtro, offset, limit);
            return articulos.isEmpty() ?
                    Response.status(Response.Status.NO_CONTENT).build() :
                    Response.ok(articulos).build();

        } catch (Exception e) {
            logger.error("Error buscando artículos con existencia", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando artículos con existencia").build();
        }
    }

    
    

}
