package com.pablosrl.controllers.cuentas_cobrar;

import com.pablosrl.data.cuentas_cobrar.Clientes;
import com.pablosrl.service.cuentas_cobrar.ClientesService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsClientes {

    @Inject
    private ClientesService clientesService;

    private static final Logger logger = Logger.getLogger(WsClientes.class);


    // GET para buscar clientes por filtro
    @GET
    @Path("/buscar/{filtro}/{limit}")
    public Response buscarClientes(
            @PathParam("filtro") String filtro,
            @PathParam("limit") int limit) {
        
        try {
            List<Clientes> clientes = clientesService.buscarClientes(filtro, limit);
            if (clientes != null && !clientes.isEmpty()) {
                return Response.ok(clientes).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
        } catch (Exception e) {
            logger.error("Error buscando clientes", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error buscando clientes").build();
        }
    }
}