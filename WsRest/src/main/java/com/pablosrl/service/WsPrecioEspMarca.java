/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.pablosrl.data.PrecioEspMarca;
import com.pablosrl.util.AppUtils;

/**
 *
 * @author Abel
 */
@Path("WsPrecioEspMarca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsPrecioEspMarca {

    Logger logger = Logger.getLogger(PrecioEspMarca.class);

    public WsPrecioEspMarca() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PrecioEspMarca> getIt() {
        PrecioEspMarca c = null;
        List<PrecioEspMarca> lista = new ArrayList<>();

        String sql = "select v.cod_cliente, v.cod_vendedor, v.cod_marca, v.cod_linea,v.cod_lista_precio, v.cod_proveedor from inv.vtv_precios_esp_marca v join wsv_clientes c on c.cod_cliente = v.cod_cliente";

        logger.debug("consulta " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new PrecioEspMarca();

                c.setCodCliente(rs.getString(1));
                c.setCodVendedor(rs.getString(2));
                c.setCodMarca(rs.getString(3));
                c.setCodLinea(rs.getString(4));
                c.setCodListaPrecio(rs.getString(5));
                c.setCodProveedor(rs.getString(6));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de monedas " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

}
