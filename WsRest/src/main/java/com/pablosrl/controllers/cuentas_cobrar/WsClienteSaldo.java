package com.pablosrl.controllers.cuentas_cobrar;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.data.cuentas_cobrar.ClienteSaldo;
import com.pablosrl.service.cuentas_cobrar.ClienteSaldoService;

@Path("/clientesaldo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsClienteSaldo {

    @Inject
    private ClienteSaldoService clienteSaldoService;

    private static final Logger logger = Logger.getLogger(WsClienteSaldo.class);

    @GET
    @Path("/consultar/{codEmpresa}/{codCliente}")
    public Response consultarSaldoCliente(
            @PathParam("codEmpresa") String codEmpresa,
            @PathParam("codCliente") String codCliente) {

        try {
            ClienteSaldo saldo = clienteSaldoService.consultarSaldo(codEmpresa, codCliente);

            // Devolver siempre el objeto, incluso si algunos campos están vacíos
            return Response.ok(saldo).build();

        } catch (Exception e) {
            logger.error("Error consultando saldo del cliente", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error consultando saldo del cliente").build();
        }
    }
}
