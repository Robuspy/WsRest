// Paso 1: Crear un nuevo servicio REST en tu API
package com.pablosrl.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/util")
public class PingController {

    @Context
    private HttpServletRequest request;

    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ping() {
        String ip = request.getRemoteAddr(); // IP del cliente

        String entorno;
        if (ip.startsWith("192.168.100.")) {
            entorno = "casa"; // conexión desde red local de pruebas
        } else if (ip.startsWith("192.168.0.")) {
            entorno = "empresa"; // conexión desde red interna de la empresa
        } else {
            entorno = "publico"; // conexión desde internet o red externa
        }

        return Response.ok(new PingRespuesta(entorno)).build();
    }

    public static class PingRespuesta {
        public String entorno;

        public PingRespuesta(String entorno) {
            this.entorno = entorno;
        }
    }
}


// Paso 2: Asegúrate que esta clase esté registrada en tu archivo web.xml o escaneada por tu framework (si usás JAX-RS con WildFly ya lo detecta automáticamente).

// Paso 3: Probar los endpoints:
// http://192.168.100.225:8585/ApiERP/rest/ping/local
// http://192.168.0.59:8585/ApiERP/rest/ping/local
// http://45.170.129.27:8585/ApiERP/rest/ping/publico
