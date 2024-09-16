package com.pablosrl.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Imagenes;
import com.pablosrl.util.AppUtils;
import com.pablosrl.util.sendfile;

@Path("WsImagenes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsImagenes_orig {

    private static final Logger logger = Logger.getLogger(WsImagenes.class);

    public WsImagenes_orig() {
        BasicConfigurator.configure();
    }


    //@GET
    //public Response getIt(@QueryParam("codigo, COD_PAIS_ORIGEN, COD_MARCA, COD_RUBRO, COD_FAMILIA, COD_LINEA, COD_GRUPO") String codigo, String COD_PAIS_ORIGEN, String COD_MARCA, String COD_RUBRO, String COD_FAMILIA, String COD_LINEA, String COD_GRUPO ) throws IOException {
    @GET
    public Response getIt(
        @QueryParam("codigo") String codigo,
        @QueryParam("COD_PAIS_ORIGEN") String COD_PAIS_ORIGEN,
        @QueryParam("COD_MARCA") String COD_MARCA,
        @QueryParam("COD_RUBRO") String COD_RUBRO,
        @QueryParam("COD_FAMILIA") String COD_FAMILIA,
        @QueryParam("COD_LINEA") String COD_LINEA,
        @QueryParam("COD_GRUPO") String COD_GRUPO
    ) throws IOException {
    List<Imagenes> lista = new ArrayList<>();

        String sql;

        sql = "SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES "
    			+ "WHERE (CODIGO             = '"+codigo+"'               OR        '"+codigo+"'             IS NULL)"
				/*+ "AND   (COD_PAIS_ORIGEN    = '"+COD_PAIS_ORIGEN+"'      OR        '"+COD_PAIS_ORIGEN+"'    IS NULL)"
				+ "AND   (COD_MARCA          = '"+COD_MARCA+"'            OR        '"+COD_MARCA+"'          IS NULL)"
				+ "AND   (COD_RUBRO          = '"+COD_RUBRO+"'            OR        '"+COD_RUBRO+"'          IS NULL)"
				+ "AND   (COD_FAMILIA        = '"+COD_FAMILIA+"'          OR        '"+COD_FAMILIA+"'        IS NULL)"
				+ "AND   (COD_LINEA          = '"+COD_LINEA+"'            OR        '"+COD_LINEA+"'          IS NULL)"
				+ "AND   (COD_GRUPO          = '"+COD_GRUPO+"'            OR        '"+COD_GRUPO+"'          IS NULL)"*/;


        /*
        String sql;
        if (codigo != null && !codigo.trim().isEmpty()) {
        	//sql = "SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES";
            //sql = "SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES WHERE CODIGO = 'NIX261'"; //'"+codigo+"'";
        	sql = "SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES "
        			+ "WHERE (CODIGO = '"+codigo+"' OR '"+codigo+"' IS NULL) ";
        } else {
            sql = "SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES";
        }
        */
        ResultSet rs = null;
        try {
        	rs = AppUtils.realizaConsulta(sql);
            /*
        	if (codigo != null && !codigo.trim().isEmpty()) {
                rs = AppUtils.realizaConsulta(sql); // Asumiendo que realizaConsulta soporta PreparedStatement
            } else {
                rs = AppUtils.realizaConsulta(sql);
            }

			*/

            while (rs.next()) {
                Imagenes c = new Imagenes();
                c.setId(rs.getLong(1));
                c.setCodigo(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setTipo(rs.getString(4));
                //c.setBlob(rs.getString(5));

               String filePath = rs.getString(5);

                try {
                    byte[] fileContent = sendfile.readFile(filePath);
                    c.setBlob(Base64.encodeBase64String(fileContent));
                    lista.add(c);
                } catch (FileNotFoundException | java.nio.file.NoSuchFileException e) {
                    logger.warn("Archivo no encontrado: " + filePath);
                    // Pasar a la siguiente foto
                    continue;
                }


            }

            return Response.ok(lista).build();
        } catch (SQLException e) {
            logger.error("Error en la consulta", e);
            logger.error("Error realizando la consulta de Articulos " + sql, e);
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





/*
 *
 @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imagenes> getIt(@QueryParam("codigo") String codigo) {
		Imagenes c = null;
		List<Imagenes> lista = new ArrayList<>();
		//String sql = " SELECT ID, CODIGO, NOMBRE, TIPO, BLOB FROM WSV_IMAGENES WHERE CODIGO = '"+codigo+"'";
		String sql = " SELECT id, codigo, foto, 'JPG', foto FROM WSV_IMAGENES WHERE CODIGO = '"+codigo+"'";
		logger.debug("consulta "+sql);
		try {
			ResultSet rs = AppUtils.realizaConsulta(sql);
			while (rs.next()) {
				c = new Imagenes();
            	c.setId(rs.getLong(1));
            	c.setCodigo(rs.getString(2));
            	c.setNombre(rs.getString(3));
            	c.setTipo(rs.getString(4));
            	BFILE bfile = ((OracleResultSet)rs).getBFILE(5);
            	bfile.openFile();
            	InputStream in = bfile.getBinaryStream();
            	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            	try{
	            	int nRead;
	            	byte[] data = new byte[16384];

	            	while ((nRead = in.read(data, 0, data.length)) != -1) {
	            	  buffer.write(data, 0, nRead);
	            	}
	            	in.close();
            	}catch (Exception e) {
					e.printStackTrace();
				}
            	bfile.closeFile();
            	c.setBlob(Base64.encodeBase64String(buffer.toByteArray())); //JAVA 8 o inferior
//            	c.setBlob(Base64.getEncoder().encodeToString(buffer.toByteArray())); //JAVA 8
            	lista.add(c);
            }
		} catch (SQLException e) {
			System.out.println("Error en la consulta ");
			e.printStackTrace();
			logger.error("Error realizando la consulta de Articulos "+sql,e);
			c = null;
		}
		AppUtils.cerrarConsulta();
		return lista;
	}
	*/

