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

    // Obtener todas las condiciones de compra con cod_empresa = 1
    public List<CondicionCompra> obtenerTodasCondicionesCompra() {
        List<CondicionCompra> condicionesCompra = new ArrayList<>();
        String sql = "SELECT cc.cod_condicion_compra, cc.descripcion " +
                     "FROM cm_condiciones_compras cc " +
                     "WHERE cc.cod_empresa = 1 " +
                     "ORDER BY TO_NUMBER(cc.cod_condicion_compra)";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CondicionCompra condicion = new CondicionCompra();
                condicion.setCodCondicionCompra(rs.getString("cod_condicion_compra"));
                condicion.setDescCondicionCompra(rs.getString("descripcion"));
                condicionesCompra.add(condicion);
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return condicionesCompra;
    }
}
