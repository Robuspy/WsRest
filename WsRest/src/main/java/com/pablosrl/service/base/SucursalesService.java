package com.pablosrl.service.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pablosrl.data.Sucursales;
import com.pablosrl.util.AppUtils;

public class SucursalesService {

    public static List<Sucursales> obtenerTodasSucursales() {
        List<Sucursales> sucursales = new ArrayList<>();
        String sql = "SELECT s.cod_sucursal, s.descripcion AS desc_sucursal " +
                     "FROM sucursales s " +
                     "WHERE s.cod_empresa = 1 " +
                     "ORDER BY TO_NUMBER(s.cod_sucursal) ";

        try (Connection con = AppUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

               while (rs.next()) {
                    Sucursales sucursal = new Sucursales();
                    sucursal.setCodSucursal(rs.getString("cod_sucursal"));
                    sucursal.setDescripcion(rs.getString("desc_sucursal"));
                    sucursales.add(sucursal);
               }
        } catch (Exception e) {
            e.printStackTrace();  // Manejo de errores
        }

        return sucursales;
    }
}