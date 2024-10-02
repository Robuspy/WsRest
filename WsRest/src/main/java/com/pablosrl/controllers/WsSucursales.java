package com.pablosrl.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.pablosrl.data.Sucursales;
import com.pablosrl.service.base.SucursalesService;

import java.util.List;

@Path("/sucursales")
public class WsSucursales {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerSucursales() {
	    try {
	        List<Sucursales> sucursales = SucursalesService.obtenerTodasSucursales();
	        return Response.ok(sucursales).build();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                       .entity("Error al obtener las sucursales")
	                       .build();
	    }
	}
}