package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Articulos;
import com.pablosrl.util.AppUtils;

@Path("WsArticulos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsArticulos {

    Logger logger = Logger.getLogger(WsArticulos.class);

    public WsArticulos() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Articulos> getIt() {
        Articulos c = null;
        List<Articulos> lista = new ArrayList<>();

        String sql = " select cod_articulo,"
                + "descripcion,"
                + "porc_descuento,"
                + "precio_base,"
                + "porc_iva_venta,"
                + "cod_unidad_medida,"
                + "cod_relacion_um,"
                + "cod_empresa,"
                + "cod_grupo_art,"
                + "cod_linea,"
                + "porc_desc_max,"
                + "ind_impuesto,"
                + "cod_familia,"
                + "foto,"
                + "existencia,"
                + "ESPECIFICACION,"
                + "COD_ORIGEN_ART, "
                + "ind_modifica_precio, "
                + "cod_moneda, "
                + "cod_rubro, "
                + "cod_marca, "
                + "cod_grupo, "
                + "cod_temporada, "
                + "fec_ult_compra "
                + " from wsv_articulos ";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new Articulos();
                c.setCodArticulo(rs.getString(1));
                c.setDescripcion(rs.getString(2));
                c.setPorcDescuento(rs.getString(3));
                c.setPrecioBase(rs.getString(4));
                c.setPorcIva(rs.getString(5));
                c.setCodUm(rs.getString(6));
                c.setCodUmRel(rs.getString(7));
                c.setCodEmpresa(rs.getString(8));
                c.setCodGrupoArt(rs.getString(9));
                c.setCodLinea(rs.getString(10));
                c.setPorcDescuentoMax(rs.getString(11));
                c.setIndImpuesto(rs.getString(12));
                c.setCodFamilia(rs.getString(13));
                c.setImagenNombre(rs.getString(14));
                c.setExistencia(rs.getString(15));
                c.setEspecificacion(rs.getString(16));
                c.setCodOrigen(rs.getString(17));
                c.setIndModificaPrecio(rs.getString(18));
                c.setCodMoneda(rs.getString(19));
                c.setCodRubro(rs.getString(20));
                c.setCodMarca(rs.getString(21));
                c.setCodGrupo(rs.getString(22));
                c.setCodTemporada(rs.getString(23));
                c.setFecUltCompra(rs.getString(24));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de Articulos " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();

        return lista;
    }
}
