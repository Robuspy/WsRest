package com.pablosrl.controllers.cuentas_cobrar;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.data.cuentas_cobrar.ClienteSaldo;
import com.pablosrl.service.cuentas_cobrar.ClienteSaldoService;
import com.pablosrl.service.pdf.PdfGeneratorService;

@Path("/clientesaldo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsClienteSaldo {

    @Inject
    private ClienteSaldoService clienteSaldoService;
    
    @Inject
    private PdfGeneratorService pdfGeneratorService;

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
    
    
    
    @GET
    @Path("/consultar-pdf/{codEmpresa}/{codCliente}")
    @Produces("application/pdf")
    public Response consultarSaldosClientePDF(
            @PathParam("codEmpresa") int codEmpresa,
            @PathParam("codCliente") int codCliente,
            @QueryParam("tipos") String tiposComprobante) {

        try {
            if (codEmpresa != 1) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Por ahora solo se permite codEmpresa = 1").build();
            }

            // ✅ Se permiten tipos = null o vacío para consultar todos
            List<ClienteSaldo> saldos = clienteSaldoService.buscarSaldosCliente(codCliente, tiposComprobante);

            if (saldos.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

            byte[] pdfBytes = pdfGeneratorService.generarPdfSaldo(saldos);

            return Response.ok(pdfBytes)
                    .type("application/pdf")
                    .header("Content-Disposition", "attachment; filename=saldo_cliente_" + codCliente + ".pdf")
                    .build();

        } catch (Exception e) {
            logger.error("Error generando PDF de saldo del cliente", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error generando PDF de saldo del cliente").build();
        }
    }
    
    
    
}
