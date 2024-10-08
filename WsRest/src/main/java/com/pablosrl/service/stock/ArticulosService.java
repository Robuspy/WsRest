package com.pablosrl.service.stock;

import com.pablosrl.data.stock.Articulos;
import com.pablosrl.util.AppUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ArticulosService {

    public List<Articulos> buscarArticulos(String filtro, int codEmpresa, int limit) {
        List<Articulos> articulos = new ArrayList<>();
        String filtroUpper = filtro.toUpperCase(); // Convertir el filtro a mayúsculas antes de usarlo en la consulta

        // Modificar la consulta para usar UPPER en las columnas 'cod_articulo' y 'descripcion'
        String sql = "SELECT a.cod_articulo, a.descripcion, round(trae_costo_prom(a.cod_empresa, a.cod_articulo, sysdate)) as costo_promedio " +
                     "FROM st_articulos a " +
                     "WHERE a.cod_empresa = ? " +
                     "AND a.estado = 'A' " +
                     "AND (UPPER(a.cod_articulo) LIKE ? OR UPPER(a.descripcion) LIKE ?) " +  // Buscar en ambos, código y descripción
                     "ORDER BY a.cod_articulo ASC " +
                     "FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, "%" + filtroUpper + "%");  // Filtro para el código de artículo
            stmt.setString(3, "%" + filtroUpper + "%");  // Filtro para la descripción del artículo
            stmt.setInt(4, limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Articulos articulo = new Articulos();
                    articulo.setCodArticulos(rs.getString("cod_articulo"));
                    articulo.setDescArticulos(rs.getString("descripcion"));
                    articulo.setCostoPromedioUnitario(rs.getBigDecimal("costo_promedio"));
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return articulos;
    }
}
