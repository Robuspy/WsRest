package com.pablosrl.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Usuarios;
import com.pablosrl.util.AppUtils;

@Path("WsRegistroId")
@Consumes(MediaType.APPLICATION_JSON)
public class WsRegistroId {
	Logger logger  = Logger.getLogger(WsRegistroId.class);

	public WsRegistroId() {
		BasicConfigurator.configure();
	}

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getIt(Usuarios[] reg) {
		Usuarios c = reg[0];

		String sql = "update fv_vendedores set id_google = '"+c.getIdGoogle()+"' "
				   + "where cod_empresa = '"+c.getCodEmpresa()+"' and cod_vendedor = '"+c.getCodUsuario()+"'";

		logger.debug("consulta "+sql);

		try {
			AppUtils.realizaCarga(sql);
			AppUtils.cerrarConsulta();
			return Response.status(200).entity("OK").build();
		} catch (SQLException e) {
			System.out.println("Error en la carga del vendedor ");
			e.printStackTrace();
			logger.error("Error realizando la asignacion de id google al vendedor "+sql,e);
			c = null;
			AppUtils.cerrarConsulta();
			return Response.status(200).entity("Error asignando el Id en el servidor "+e).build();
		}
	}
}
