package com.pablosrl.service;

import com.pablosrl.data.Monedas;
import com.pablosrl.util.AppUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MonedasService {

    public List<Monedas> obtenerMonedasActivas() {
        List<Monedas> monedas = new ArrayList<>();
        String sql = "SELECT m.cod_moneda, m.descripcion, m.tipo_cambio_dia " +
                     "FROM monedas m " +
                     "WHERE m.estado = 'A'";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Monedas moneda = new Monedas();
                    moneda.setCodMoneda(rs.getString("cod_moneda"));
                    moneda.setDescMoneda(rs.getString("descripcion"));
                    moneda.setTipoCambioDia(rs.getBigDecimal("tipo_cambio_dia"));
                    monedas.add(moneda);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores adecuado
        }

        return monedas;
    }
}


