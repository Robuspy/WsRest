package com.pablosrl.controllers;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pablosrl.data.Monedas;
import com.pablosrl.service.MonedasService;

@Path("/monedas")
public class WsMonedas {

    private MonedasService monedasService = new MonedasService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerMonedasActivas() {
        try {
            List<Monedas> monedas = monedasService.obtenerMonedasActivas();
            return Response.ok(monedas).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Error al obtener las monedas activas")
                           .build();
        }
    }
}
