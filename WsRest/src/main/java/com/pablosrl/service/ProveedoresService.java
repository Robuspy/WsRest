package com.pablosrl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pablosrl.data.Proveedores;
import com.pablosrl.util.AppUtils;

public class ProveedoresService {

    public List<Proveedores> obtenerProveedoresPaginados(int codEmpresa, int offset, int limit) {
        List<Proveedores> proveedores = new ArrayList<>();
        String sql = "SELECT cm.COD_PROVEEDOR, cm.nomb_proveedor " +
                     "FROM cm_proveedores cm " +
                     "WHERE cm.COD_EMPRESA = ? " +
                     "AND NVL(cm.ESTADO, 'N') = 'A' " +
                     "ORDER BY TO_NUMBER(cm.COD_PROVEEDOR) DESC " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setInt(2, offset);
            stmt.setInt(3, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Proveedores proveedor = new Proveedores();
                    proveedor.setCodProveedor(rs.getString("COD_PROVEEDOR"));
                    proveedor.setDescProveedor(rs.getString("nomb_proveedor"));
                    proveedores.add(proveedor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return proveedores;
    }

    public List<Proveedores> buscarProveedores(String filtro, int codEmpresa, int limit) {
        List<Proveedores> proveedores = new ArrayList<>();

        // Convertir el filtro a mayúsculas
        String filtroUpper = filtro.toUpperCase();

        String sql = "SELECT cm.COD_PROVEEDOR, cm.nomb_proveedor " +
                     "FROM cm_proveedores cm " +
                     "WHERE cm.COD_EMPRESA = ? " +
                     "AND NVL(cm.ESTADO, 'N') = 'A' " +
                     "AND (UPPER(cm.COD_PROVEEDOR) LIKE ? OR UPPER(cm.nomb_proveedor) LIKE ?) " +
                     "ORDER BY TO_NUMBER(cm.COD_PROVEEDOR) DESC " +
                     "FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, "%" + filtroUpper + "%");
            stmt.setString(3, "%" + filtroUpper + "%");
            stmt.setInt(4, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Proveedores proveedor = new Proveedores();
                    proveedor.setCodProveedor(rs.getString("COD_PROVEEDOR"));
                    proveedor.setDescProveedor(rs.getString("nomb_proveedor"));
                    proveedores.add(proveedor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return proveedores;
    }


}

