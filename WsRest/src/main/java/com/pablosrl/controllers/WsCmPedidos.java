package com.pablosrl.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.pablosrl.data.cm_pedidos_compras.PedidoCabecera;
import com.pablosrl.data.cm_pedidos_compras.PedidoDetalle;
import com.pablosrl.service.CmPedidoService;

@Path("/cmpedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsCmPedidos {

    @Inject
    private CmPedidoService pedidoService;

    private static final Logger logger = Logger.getLogger(WsCmPedidos.class);

    @GET
    @Path("/cabecera")
    public Response obtenerCabeceraPedidos() {
        try {
            List<PedidoCabecera> pedidos = pedidoService.obtenerCabeceraPedidos();
            logger.info("Cantidad de cabeceras de pedidos encontradas: " + pedidos.size());
            if (pedidos.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(pedidos).build();
        } catch (Exception e) {
            logger.error("Error obteniendo cabeceras de pedidos", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error obteniendo cabeceras de pedidos").build();
        }
    }

    /*@GET
    @Path("/detalle/{codEmpresa}/{tipComprobante}/{serComprobante}/{nroComprobante}")
    public Response obtenerDetallePedidos(
        @PathParam("codEmpresa") String codEmpresa,
        @PathParam("tipComprobante") String tipComprobante,
        @PathParam("serComprobante") String serComprobante,
        @PathParam("nroComprobante") String nroComprobante) {
        try {
            List<PedidoDetalle> detalles = pedidoService.obtenerDetallePedidos(codEmpresa, tipComprobante, serComprobante, nroComprobante);
            logger.info("Cantidad de detalles de pedidos encontrados: " + detalles.size());
            if (detalles.isEmpty()) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(detalles).build();
        } catch (Exception e) {
            logger.error("Error obteniendo detalles de pedido", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error obteniendo detalles de pedido").build();
        }
    }
*/
    @POST
    @Path("/cabecera")
    public Response insertarPedidoCabecera(PedidoCabecera pedido) {
        // Verificar si los datos obligatorios están presentes
        if (pedido == null ||
            pedido.getCodEmpresa() == null || pedido.getCodEmpresa().isEmpty() ||
            pedido.getCodSucursal() == null || pedido.getCodSucursal().isEmpty() ||
            pedido.getCodSucursalPed() == null || pedido.getCodSucursalPed().isEmpty() ||
            pedido.getDescSucursalPed() == null || pedido.getDescSucursalPed().isEmpty() ||
            pedido.getTipComprobante() == null || pedido.getTipComprobante().isEmpty() ||
            pedido.getSerComprobante() == null || pedido.getSerComprobante().isEmpty() ||
            pedido.getFecComprobante() == null ||  // Debe haber una fecha
            pedido.getCodProveedor() == null || pedido.getCodProveedor().isEmpty() ||
            pedido.getCodCondicionCompra() == null || pedido.getCodCondicionCompra().isEmpty() ||
            pedido.getCodMoneda() == null || pedido.getCodMoneda().isEmpty() ||
            pedido.getTipCambio() == null || pedido.getTipCambio().compareTo(BigDecimal.ZERO) <= 0 ||  // El tipo de cambio no puede ser negativo o cero
            pedido.getTotComprobante() == null || pedido.getTotComprobante().compareTo(BigDecimal.ZERO) <= 0  // El total del comprobante no puede ser negativo o cero
        ) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos incompletos o incorrectos").build();
        }

        try {
            logger.info("Insertando cabecera de pedido para empresa: " + pedido.getCodEmpresa());
            
            // Insertar el pedido y obtener el número de comprobante generado
            pedidoService.insertarPedidoCabecera(pedido);
            int nroComprobanteGenerado = pedido.getNroComprobante();  // Suponiendo que el número se genera en el servicio

            // Devolver el mensaje de éxito junto con el número de pedido generado
            String mensajeExito = "Pedido insertado correctamente. Número de comprobante: " + nroComprobanteGenerado;
            return Response.status(Response.Status.CREATED).entity(mensajeExito).build();
            
        } catch (Exception e) {
            logger.error("Error insertando cabecera de pedido", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error insertando el pedido cabecera").build();
        }
    }




    /*
    @POST
    @Path("/detalle")
    public Response insertarPedidoDetalle(PedidoDetalle detalle) {
        if (detalle == null || detalle.getCodEmpresa() == null || detalle.getTipComprobante() == null || detalle.getNroComprobante() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Datos incompletos").build();
        }
        try {
            logger.info("Insertando detalle de pedido para empresa: " + detalle.getCodEmpresa() + ", comprobante: " + detalle.getNroComprobante());
            pedidoService.insertarPedidoDetalle(detalle);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            logger.error("Error insertando detalle de pedido", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error insertando el pedido detalle").build();
        }
    }*/
}
