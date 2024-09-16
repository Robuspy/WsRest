package com.pablosrl.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo {

    public static void main(String[] args) {
        try (Connection conn = PruebaConexion.getInstance();
             Statement  stmt = conn.createStatement();
             ResultSet resultado = stmt.executeQuery("Select * from usuarios")) {


            while (resultado.next()){
                System.out.println(resultado.getString("cod_usuario"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
