package com.pablosrl.controllers.cuentas_cobrar;

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

import com.pablosrl.data.MensajeRespuesta;
import com.pablosrl.data.cuentas_cobrar.Clientes;
import com.pablosrl.service.cuentas_cobrar.ClientesService;

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
    
    @GET
    @Path("/actualizar-precio/{codEmpresa}/{codCliente}")
    public Response actualizarPrecioMayorista(
            @PathParam("codEmpresa") String codEmpresa,
            @PathParam("codCliente") String codCliente) {

        try {
            String mensaje = clientesService.actualizarPrecioMayorista(codEmpresa, codCliente);

            // Retornar JSON bien formado
            return Response.ok(new MensajeRespuesta(mensaje)).build();

        } catch (Exception e) {
            logger.error("Error al actualizar precio mayorista", e);

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new MensajeRespuesta("Error al ejecutar procedimiento")).build();
        }
    }

}
