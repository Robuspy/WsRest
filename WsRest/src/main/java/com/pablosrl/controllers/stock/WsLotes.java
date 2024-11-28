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

import com.pablosrl.data.stock.Lotes;
import com.pablosrl.service.stock.LotesService;

@Path("/lotes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsLotes {

    @Inject
    private LotesService lotesService;

    private static final Logger logger = Logger.getLogger(WsLotes.class);

    @GET
    @Path("/buscar/{codArticulo}")
    public Response buscarLotesPorArticulo(@PathParam("codArticulo") String codArticulo) {
        try {
            List<Lotes> lotes = lotesService.buscarLotesPorArticulo(codArticulo);
            return lotes.isEmpty() ?
                Response.status(Response.Status.NO_CONTENT).build() :
                Response.ok(lotes).build();
        } catch (Exception e) {
            logger.error("Error buscando lotes por código de artículo", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando lotes por código de artículo").build();
        }
    }
}
