package com.pablosrl.service.cuentas_cobrar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pablosrl.data.cuentas_cobrar.Clientes;
import com.pablosrl.util.AppUtils;

public class ClientesService {

    public List<Clientes> buscarClientes(String filtro, int limit) {
        List<Clientes> clientes = new ArrayList<>();
        String filtroUpper = filtro.toUpperCase(); // Convertir el filtro a mayúsculas antes de usarlo en la consulta

        String sql = "SELECT c.COD_CLIENTE, c.nombre AS DESC_CLIENTE, c.ruc " +
                     "FROM cc_clientes c " +
                     "WHERE c.COD_EMPRESA = 1 " +
                     "AND c.ESTADO = 'A' " +
                     "AND (UPPER(c.COD_CLIENTE) LIKE ? OR UPPER(c.nombre) LIKE ? OR UPPER(c.ruc) LIKE ?) " +
                     "ORDER BY c.COD_CLIENTE ASC " +
                     "FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + filtroUpper + "%");  // Filtro para el código del cliente
            stmt.setString(2, "%" + filtroUpper + "%");  // Filtro para la descripción del cliente
            stmt.setString(3, "%" + filtroUpper + "%");  // Filtro para el RUC
            stmt.setInt(4, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Clientes cliente = new Clientes();
                    cliente.setCodCliente(rs.getString("COD_CLIENTE"));
                    cliente.setDescCliente(rs.getString("DESC_CLIENTE"));
                    cliente.setRuc(rs.getString("RUC"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return clientes;
    }
}
