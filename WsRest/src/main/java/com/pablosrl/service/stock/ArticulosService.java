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

    public List<Articulos> buscarArticulosPorCodigo(String filtro, int codEmpresa, int limit) {
        List<Articulos> articulos = new ArrayList<>();
        String filtroUpper = filtro.toUpperCase(); // Convertir el filtro a mayúsculas antes de usarlo en la consulta

        // Modificar la consulta para usar UPPER en la columna y el filtro
        String sql = "SELECT a.cod_articulo, a.descripcion, trae_costo_prom(a.cod_empresa, a.cod_articulo, sysdate) as costo_promedio " +
                     "FROM st_articulos a " +
                     "WHERE a.cod_empresa = ? " +
                     "AND a.estado = 'A' " +
                     "AND UPPER(a.cod_articulo) LIKE UPPER(?) " +  // Usar UPPER para hacer la comparación insensible a mayúsculas y minúsculas
                     "ORDER BY a.cod_articulo ASC " +
                     "FETCH NEXT ? ROWS ONLY";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, "%" + filtroUpper + "%");  // Pasar el filtro ya convertido en mayúsculas
            stmt.setInt(3, limit);

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
