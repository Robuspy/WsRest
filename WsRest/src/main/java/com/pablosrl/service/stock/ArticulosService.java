package com.pablosrl.service.stock;

import java.awt.PageAttributes.MediaType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
    
    
    public List<ArticulosExistencias> buscarArticulosConExistencia(int codEmpresa, String filtro, int offset, int limit,
            Integer diasUltCompraDesde, Integer diasUltCompraHasta,
            String esNovedad){
    	List<ArticulosExistencias> articulos = new ArrayList<>(); 
        List<Object> parametros = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
                SELECT * FROM (
                    SELECT 
                        a.cod_empresa,
                        a.cod_articulo,
                        a.descripcion AS desc_articulo,
                        NVL(ee.cant_bloqueo, 0) AS cant_bloqueo,
                        NVL(ee.cant_dispon, 0) AS cant_dispon,
                        NVL(ee.cant_total, 0) AS cant_total,
                        trae_precio_venta(a.cod_empresa, a.cod_articulo, SYSDATE, '01') AS precio_01,
                        trae_precio_venta(a.cod_empresa, a.cod_articulo, SYSDATE, '02') AS precio_02,
                        CASE 
                            WHEN NVL(a.es_novedad, 'N') = 'S' THEN 'NOVEDAD'
                            ELSE 'REPOSICION'
                        END AS tipo_compra,
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
                    WHERE 1=1
            """);

            // Filtro fijo: empresa
            sql.append(" AND a.cod_empresa = ? ");
            parametros.add(codEmpresa);

         // Filtro opcional: artículo o descripción
            if (filtro != null && !filtro.trim().isEmpty()) {
                sql.append(" AND (UPPER(a.cod_articulo) LIKE ? OR UPPER(a.descripcion) LIKE ?) ");
                String filtroUpper = "%" + filtro.toUpperCase().trim() + "%";
                parametros.add(filtroUpper);
                parametros.add(filtroUpper);
            }
            // Si filtro es null o vacío, no agrega nada => trae todo.


            // Filtro opcional: días de última compra desde
            if (diasUltCompraDesde != null) {
                sql.append(" AND a.dias_ult_compra >= ? ");
                parametros.add(diasUltCompraDesde);
            }

            // Filtro opcional: días de última compra hasta
            if (diasUltCompraHasta != null) {
                sql.append(" AND a.dias_ult_compra <= ? ");
                parametros.add(diasUltCompraHasta);
            }

         // Filtro opcional: novedad
            if (esNovedad != null && !esNovedad.trim().isEmpty()) {
                sql.append(" AND a.es_novedad = ? ");
                parametros.add(esNovedad);
            }

            // Cierra la subconsulta
            sql.append(" ) WHERE rn BETWEEN ? AND ? ");
            parametros.add(offset + 1);
            parametros.add(offset + limit);

            try (Connection con = AppUtils.getConnection();
                 PreparedStatement stmt = con.prepareStatement(sql.toString())) {

                for (int i = 0; i < parametros.size(); i++) {
                    stmt.setObject(i + 1, parametros.get(i));
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        ArticulosExistencias articulo = new ArticulosExistencias();
                        articulo.setCodArticulos(rs.getString("cod_articulo"));
                        articulo.setDescArticulos(rs.getString("desc_articulo"));
                        articulo.setCantBloqueo(rs.getBigDecimal("cant_bloqueo"));
                        articulo.setCantDispon(rs.getBigDecimal("cant_dispon"));
                        articulo.setCantTotal(rs.getBigDecimal("cant_total"));
                        articulo.setPrecio01(rs.getBigDecimal("precio_01"));
                        articulo.setPrecio02(rs.getBigDecimal("precio_02"));
                        articulo.setTipoCompra(rs.getString("tipo_compra"));
                        articulos.add(articulo);
                    }
                }

            } catch (SQLException e) {
            	e.printStackTrace();
        }

        return articulos;
    }
    
    
    public List<String> buscarCodigosArticulosConExistencia() {
        List<String> codigosArticulos = new ArrayList<>();

        String sql = "SELECT e.cod_empresa, " +
                     "       e.cod_articulo, " +
                     "       SUM(NVL(e.cant_dispon, 0)) AS cant_dispon " +
                     "FROM st_existencia_art e " +
                     "WHERE e.cod_empresa = 1 " +
                     "  AND e.cod_articulo IS NOT NULL " +
                     "  AND e.cant_dispon <> 0 " +
                     "GROUP BY e.cod_empresa, e.cod_articulo " +
                     "ORDER BY cant_dispon DESC";

        try (Connection con = AppUtils.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql)) {

       

               try (ResultSet rs = stmt.executeQuery()) {
                   while (rs.next()) {
                       codigosArticulos.add(rs.getString("cod_articulo"));
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace(); // Puedes cambiarlo por logger si lo deseas
           }

           return codigosArticulos;
       }
    
    /*
     * POST
     * 
     * **/
    

    


}
