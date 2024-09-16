package com.pablosrl.util;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class AppUtils {

    static Logger logger = Logger.getLogger(AppUtils.class);

    private static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.100.225:1521:ORCL";//DESARROLLO INV casa robus 29-07-2023
    // private static final String DATABASE_URL = "jdbc:oracle:thin:@201.222.48.18:3321:cab";//DESARROLLO INV REMOTO
    //private static final String DATABASE_URL = "jdbc:oracle:thin:@192.168.0.56:1521:ORCL";//PRODUCCION
    
 // Añadir la constante para la ruta de las imágenes
    public static final String IMAGE_DIRECTORY = "\\\\192.168.100.225\\fotos_articulos\\"; //prueba local casa robus
    //public static final String IMAGE_DIRECTORY = "\\\\\\\\192.168.0.56\\\\inventiva\\\\INVENTIVA\\\\EXE\\\\FOTOS_ARTICULOS\\\\";

    private static final String DATABASE_USER = "wsinv";
    private static final String DATABASE_PASS = "wsinv";

    private static Connection con;
    private static Statement stmt;
    private static CallableStatement cstmt;

    public static ResultSet realizaConsulta(String sql) throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        con.setAutoCommit(true);
        stmt = con.createStatement();
        return stmt.executeQuery(sql);
    }

    public static void realizaCarga(String sql) throws SQLException {
        logger.info("sql inserta " + sql);
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
        con.setAutoCommit(true);
        stmt = con.createStatement();
        stmt.executeUpdate(sql);
        cerrarConsulta();
    }

    public static void cerrarConsulta(){
		try{
			con.close();
		}
		catch(Exception e){
			logger.error("error al cerrar la conexion de la base de datos",e);
		}

		try{
			if(stmt != null) {
				stmt.close();
			}
                        if(cstmt != null) {
							cstmt.close();
						}
		}
		catch(Exception e){
			logger.error("error al cerrar la conexion de la base de datos",e);
		}
    }

    public static void ejecutarProcedimiento(String procedimiento, ArrayList<ParametrosProcedimiento> params) throws SQLException{
                logger.debug("ejecuta procedimiento: "+procedimiento);
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		con = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASS);
		con.setAutoCommit(true);
                cstmt = con.prepareCall(procedimiento);
                if(params == null) {
					cstmt.execute();
				} else{
                    for(ParametrosProcedimiento p : params){
                        Object valorParam = p.getValorParametro();
                        if(valorParam instanceof String){
                            cstmt.setString(p.getPosicionParametro(),(String) valorParam);
                        }
                        else if(valorParam instanceof Integer){
                            cstmt.setInt(p.getPosicionParametro(),(int) valorParam);
                        }
                        else if(valorParam instanceof LocalDateTime){
                            cstmt.setTimestamp(p.getPosicionParametro(),Timestamp.valueOf((LocalDateTime) p.getValorParametro()));
                        }
                        else if(valorParam == null){
                            cstmt.setObject(p.getPosicionParametro(), valorParam);
                        }
                    }
                    cstmt.execute();
                }
                cerrarConsulta();
        }
    public static LocalDateTime convertirStringAFecha(String fecha){
        try{
            if(fecha != null){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
                return dateTime;
            }else{
                return null;
            }

        }catch(DateTimeParseException e){
            logger.error("No se pudo parsear la fecha "+fecha+" "+ e);
            return null;
        }
    }

	public static ResultSet realizaConsultaConParametros(String sql, String codigo) {
		// TODO Auto-generated method stub
		return null;
	}




}

