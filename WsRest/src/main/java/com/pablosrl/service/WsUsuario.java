package com.pablosrl.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Usuarios;
import com.pablosrl.util.AppUtils;

@Path("WsUsuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsUsuario {

    Logger logger = Logger.getLogger(WsUsuario.class);

    public WsUsuario() {
        BasicConfigurator.configure();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> getIt(@QueryParam("codUsuario") String codigo) {
        Usuarios c = null;
        List<Usuarios> usuarioList = new ArrayList<>();
        String where = " where cod_usuario = '" + codigo + "'";
        String sql = consulta() + where ;
        System.out.println("consulta " + sql);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                c = new Usuarios();
                c.setCodUsuario(rs.getString(1));
                c.setSucursal(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setCodCobrador(rs.getString(4));
                c.setCodVendedor(rs.getString(5));
                c.setSeriePedido(rs.getString(6));
                c.setSerieFco(rs.getString(7));
                c.setSerieNcr(rs.getString(8));
                c.setNroPedido(rs.getString(9));
                c.setNroContado(rs.getString(10));
                c.setNroNcr(rs.getString(11));
                c.setCanal(rs.getString(12));
                c.setCodEmpresa(rs.getString(13));
                c.setNombreEmpresa(rs.getString(14));
                c.setRucEmpresa(rs.getString(15));
                c.setDireccion(rs.getString(16));
                c.setTimbrado(rs.getString(17));
                c.setFecInicio(rs.getString(18));
                c.setFecFin(rs.getString(19));
                c.setSerieLegal(rs.getString(20));
                usuarioList.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de usuario " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return usuarioList;
    }

    @GET
    @Path("/obtener")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuarios> getItWithPass(@QueryParam("codUsuario") String codigo,@QueryParam("pass") String pass) {
        Usuarios c = null;
        List<Usuarios> usuarioList = new ArrayList<>();
        String where = " where cod_usuario = '" + codigo + "' and pass = '"+pass+"'";
        String sql = consulta() + where ;
        System.out.println("consulta " + sql);
        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);
            while (rs.next()) {
                c = new Usuarios();
                c.setCodUsuario(rs.getString(1));
                c.setSucursal(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setCodCobrador(rs.getString(4));
                c.setCodVendedor(rs.getString(5));
                c.setSeriePedido(rs.getString(6));
                c.setSerieFco(rs.getString(7));
                c.setSerieNcr(rs.getString(8));
                c.setNroPedido(rs.getString(9));
                c.setNroContado(rs.getString(10));
                c.setNroNcr(rs.getString(11));
                c.setCanal(rs.getString(12));
                c.setCodEmpresa(rs.getString(13));
                c.setNombreEmpresa(rs.getString(14));
                c.setRucEmpresa(rs.getString(15));
                c.setDireccion(rs.getString(16));
                c.setTimbrado(rs.getString(17));
                c.setFecInicio(rs.getString(18));
                c.setFecFin(rs.getString(19));
                c.setSerieLegal(rs.getString(20));
                usuarioList.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de usuario " + sql, e);
            c = null;
        }

        AppUtils.cerrarConsulta();
        return usuarioList;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String create(Usuarios usuario) {
        try {
            System.out.println("usuario " + usuario.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Error";
    }

    public String consulta() {
        return("	SELECT cod_usuario, " +
                "	   cod_sucursal, " +
                "	   nombre, " +
                " 	   cod_cobrador," +
                " 	   cod_vendedor, " +
                " 	   serie_pedido, " +
                " 	   serie_fco, " +
                " 	   serie_ncr, " +
                " 	   nro_pedido," +
                "        nro_contado, " +
                " 	   nro_ncr,  " +
                " 	   canal_vendedor, " +
                " 	   cod_empresa, " +
                " 	   nombre_empresa, " +
                "        ruc_empresa," +
                " 	   direccion," +
                " 	   timbrado," +
                " 	   fec_inicio, " +
                " 	   fec_fin,    " +
                " 	   serie_legal " +
                "   FROM WSV_USUARIOS ");
    }
}

