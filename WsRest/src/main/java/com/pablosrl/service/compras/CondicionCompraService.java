package com.pablosrl.service.compras;

import com.pablosrl.data.compras.CondicionCompra;
import com.pablosrl.util.AppUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondicionCompraService {

    public List<CondicionCompra> obtenerCondicionesCompraPaginadas(int codEmpresa, int offset, int limit) {
        List<CondicionCompra> condicionesCompra = new ArrayList<>();
        String sql = "SELECT cc.cod_condicion_compra, cc.descripcion " +
                     "FROM cm_condiciones_compras cc " +
                     "WHERE cc.cod_empresa = ? " +
                     "ORDER BY TO_NUMBER(cc.cod_condicion_compra) " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setInt(2, offset);
            stmt.setInt(3, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CondicionCompra condicion = new CondicionCompra();
                    condicion.setCodCondicionCompra(rs.getString("cod_condicion_compra"));
                    condicion.setDescCondicionCompra(rs.getString("descripcion"));
                    condicionesCompra.add(condicion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return condicionesCompra;
    }

    public List<CondicionCompra> buscarCondicionesCompra(String filtro, int codEmpresa, int limit) {
        List<CondicionCompra> condicionesCompra = new ArrayList<>();

        // Convertir el filtro a mayúsculas
        String filtroUpper = filtro.toUpperCase();

        String sql = "SELECT cc.cod_condicion_compra, cc.descripcion " +
                     "FROM cm_condiciones_compras cc " +
                     "WHERE cc.cod_empresa = ? " +
                     "AND (UPPER(cc.cod_condicion_compra) LIKE ? OR UPPER(cc.descripcion) LIKE ?) " +
                     "ORDER BY TO_NUMBER(cc.cod_condicion_compra) " +
                     "FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, "%" + filtroUpper + "%");
            stmt.setString(3, "%" + filtroUpper + "%");
            stmt.setInt(4, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CondicionCompra condicion = new CondicionCompra();
                    condicion.setCodCondicionCompra(rs.getString("cod_condicion_compra"));
                    condicion.setDescCondicionCompra(rs.getString("descripcion"));
                    condicionesCompra.add(condicion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return condicionesCompra;
    }
}
