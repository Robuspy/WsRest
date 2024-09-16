package com.pablosrl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.util.AppUtils;

@Path("WsImagenes")
public class WsImagenes {

    private static final Logger logger = Logger.getLogger(WsImagenes.class);

    public WsImagenes() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces("image/jpeg")
    public Response getImagen(@QueryParam("codigo") String codigo) {
        String sql = "SELECT id, codigo, foto FROM WSV_IMAGENES WHERE codigo = '" + codigo + "'";
    	//String sql = "SELECT id, codigo, foto FROM WSV_IMAGENES";
    	ResultSet rs = null;
        String filePath = null;

        try {
            rs = AppUtils.realizaConsulta(sql);
            if (rs.next()) {
                filePath = rs.getString("foto");

                if (filePath == null || filePath.isEmpty()) {
                    logger.error("La ruta del archivo es nula o está vacía para el registro con código: " + codigo);
                    return Response.status(Response.Status.NOT_FOUND).entity("File not found").build();
                }

                // Ajuste de la ruta si es necesario
                if (filePath.startsWith("Z:\\")) {
                	filePath = filePath.replace("Z:\\", AppUtils.IMAGE_DIRECTORY);
                } else if (filePath.startsWith("\\\\")) {
                    // Escapar los backslashes para rutas UNC
                    filePath = filePath.replace("\\\\", "\\\\\\\\");
                }

                File file = new File(filePath);
                if (!file.exists()) {
                    logger.error("El archivo no existe en la ruta: " + filePath);
                    return Response.status(Response.Status.NOT_FOUND).entity("File not found").build();
                }

                return Response.ok((StreamingOutput) output -> {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        byte[] buffer = new byte[8192];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            output.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        logger.error("Error al leer el archivo", e);
                    }
                }).header("Content-Disposition", "inline; filename=\"" + file.getName() + "\"").build();

            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("File not found").build();
            }
        } catch (SQLException e) {
            logger.error("Error en la consulta", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error interno del servidor").build();
        } finally {
            AppUtils.cerrarConsulta();
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("Error cerrando ResultSet", e);
                }
            }
        }
    }

}

