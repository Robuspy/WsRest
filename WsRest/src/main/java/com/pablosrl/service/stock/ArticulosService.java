package com.pablosrl.service.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pablosrl.data.stock.Articulos;
import com.pablosrl.data.stock.ArticulosExistencias;
import com.pablosrl.util.AppUtils;

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


    public List<Articulos> buscarArticulosExacto(int codEmpresa, String filtro) {
        List<Articulos> articulos = new ArrayList<>();

        // Convertir el filtro a mayúsculas para la comparación exacta con cod_articulo
        String filtroUpper = filtro.toUpperCase();

        // Consulta SQL modificada para filtrar solo por cod_articulo
        String sql = "SELECT a.cod_articulo, a.descripcion, round(trae_costo_prom(a.cod_empresa, a.cod_articulo, sysdate)) as costo_promedio " +
                     "FROM st_articulos a " +
                     "WHERE a.cod_empresa = ? " +
                     "AND a.estado = 'A' " +
                     "AND UPPER(a.cod_articulo) = ?";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, filtroUpper);  // Filtro exacto para el código de artículo

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
    
    
    public List<ArticulosExistencias> buscarArticulosConExistencia(int codEmpresa, String filtro, int offset, int limit) {
        List<ArticulosExistencias> articulos = new ArrayList<>();
        String filtroUpper = filtro != null ? filtro.toUpperCase() : "";

        String sql = """
            SELECT * FROM (
                SELECT 
                    a.cod_empresa,
                    a.cod_articulo,
                    a.descripcion AS desc_articulo,
                    NVL(ee.cant_bloqueo, 0) AS cant_bloqueo,
                    NVL(ee.cant_dispon, 0) AS cant_dispon,
                    NVL(ee.cant_total, 0) AS cant_total,
                    ROW_NUMBER() OVER (ORDER BY NVL(ee.cant_total, 0) DESC) AS rn
                FROM st_articulos a
                LEFT JOIN (
                    SELECT 
                        e.cod_empresa,
                        e.cod_articulo,
                        SUM(NVL(e.cant_bloqueo, 0)) AS cant_bloqueo,
                        SUM(NVL(e.cant_dispon, 0)) AS cant_dispon,
                        SUM(NVL(e.cant_bloqueo, 0) + NVL(e.cant_dispon, 0)) AS cant_total
                    FROM st_existencia_art e
                    GROUP BY e.cod_empresa, e.cod_articulo
                ) ee ON ee.cod_empresa = a.cod_empresa AND ee.cod_articulo = a.cod_articulo
                WHERE a.cod_empresa = ?
                  AND (? IS NULL OR UPPER(a.cod_articulo) LIKE '%' || ? || '%' OR UPPER(a.descripcion) LIKE '%' || ? || '%')
            ) WHERE rn BETWEEN ? AND ?
        """;

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, codEmpresa);
            stmt.setString(2, filtroUpper);
            stmt.setString(3, filtroUpper);
            stmt.setString(4, filtroUpper);
            stmt.setInt(5, offset + 1);
            stmt.setInt(6, offset + limit);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ArticulosExistencias articulo = new ArticulosExistencias(); 
                    articulo.setCodArticulos(rs.getString("cod_articulo"));
                    articulo.setDescArticulos(rs.getString("desc_articulo"));
                    articulo.setCantBloqueo(rs.getBigDecimal("cant_bloqueo"));
                    articulo.setCantDispon(rs.getBigDecimal("cant_dispon"));
                    articulo.setCantTotal(rs.getBigDecimal("cant_total"));
                    articulos.add(articulo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return articulos;
    }
    


}
