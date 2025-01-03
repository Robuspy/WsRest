package com.pablosrl.service.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pablosrl.data.stock.Lotes;
import com.pablosrl.util.AppUtils;

public class LotesService {

    public List<Lotes> buscarLotesPorArticulo(String codArticulo) {
        List<Lotes> lotes = new ArrayList<>();

        String sql = "SELECT l.cod_articulo, l.nro_lote, l.cod_talle, l.desc_talle, l.cod_color, l.desc_color  " +
                     "FROM STV_LOTES l " +
                     "WHERE l.cod_articulo = ? " +
                     "ORDER BY l.cod_color, l.cod_talle ";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, codArticulo);  // Set the cod_articulo parameter

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Lotes lote = new Lotes();
                    lote.setCodArticulo(rs.getString("cod_articulo"));
                    lote.setNroLote(rs.getString("nro_lote"));
                    lote.setCodTalle(rs.getString("cod_talle"));
                    lote.setDescTalle(rs.getString("desc_talle"));
                    lote.setCodColor(rs.getString("cod_color"));
                    lote.setDescColor(rs.getString("desc_color"));
                    lotes.add(lote);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Manejo de errores
        }

        return lotes;
    }
}
