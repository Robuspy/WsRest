package com.pablosrl.controllers.compras;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.data.compras.CondicionCompra;
import com.pablosrl.service.compras.CondicionCompraService;

@Path("/condiciones-compra")
public class WsCondicionesCompra {

	 @Inject
	    private CondicionCompraService condicionCompraService;

	    private static final Logger logger = Logger.getLogger(WsCondicionesCompra.class);

	    // GET para obtener todas las condiciones de compra con cod_empresa = 1
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response obtenerTodasCondicionesCompra() {
	        try {
	            List<CondicionCompra> condiciones = condicionCompraService.obtenerTodasCondicionesCompra();
	            return Response.ok(condiciones).build();  // Devolver la lista completa
	        } catch (Exception e) {
	            logger.error("Error obteniendo todas las condiciones de compra", e);
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                    .entity("Error obteniendo todas las condiciones de compra").build();
	        }
	    }
	}