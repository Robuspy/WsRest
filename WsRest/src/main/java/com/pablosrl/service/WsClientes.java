

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
import javax.ws.rs.core.Response;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.pablosrl.data.Clientes;
import com.pablosrl.util.AppUtils;

@Path("WsClientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class WsClientes {

    Logger logger = Logger.getLogger(WsClientes.class);

    public WsClientes() {
        BasicConfigurator.configure();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Clientes> getIt(@QueryParam("codUsuario") String codigo) {
        Clientes c = null;
        List<Clientes> lista = new ArrayList<>();

        String join = " join wsv_usuarios u on u.cod_empresa = c.cod_empresa and u.cod_vendedor = c.cod_vendedor";

        String sql = "select c.es_fisica, c.nombre, c.nomb_fantasia, c.detalle direccion, c.telefono1, c.telefono2, "
                + "       c.direc_electronica email, c.ruc, "
                + "       c.ci, c.cod_vendedor, c.cod_cliente, c.cod_persona, c.cod_condicion_venta, "
                + "       c.limite_credito, c.cod_moneda_limite, c.cod_lista_precio, c.contacto, "
                + "		c.cod_vendedor_lacteos, c.cod_empresa, c.fec_ultima_compra, 'S' ind_envio, c.tipo_impuesto,"
                + "		c.porc_desc_volumen, c.porc_desc_canal, c.porc_desc_categoria, c.porc_desc_visibilidad1, c.porc_desc_visibilidad2,"
                + "		c.porc_desc_visibilidad3, c.porc_desc_alin_cultural, c.tip_cliente, c.dna, c.fec_vencimiento_dna, c.monto_vendido, c.objetivo_venta"
                + " from wsv_clientes c " + join
                + "  where  u.cod_usuario = '" + codigo + "'";

        logger.info("consulta wsclientes --> " + sql);

        try {
            ResultSet rs = AppUtils.realizaConsulta(sql);

            while (rs.next()) {
                c = new Clientes();
                c.setTipoPersona(rs.getString(1));
                c.setNombre(rs.getString(2));
                c.setNombreFantasia(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setTelefono1(rs.getString(5));
                c.setTelefono2(rs.getString(6));
                c.setEmail(rs.getString(7));
                c.setRuc(rs.getString(8));
                c.setCedula(rs.getString(9));
//                c.setCodCobrador(rs.getString(10));
                c.setCodVendedor(rs.getString(10));
                c.setCodCliente(rs.getString(11));
                c.setCodPersona(rs.getString(12));
                c.setCondicionVenta(rs.getString(13));
                c.setLimiteCredito(rs.getString(14));
                c.setCodMonedaCredito(rs.getString(15));
                c.setListaPrecio(rs.getString(16));
                c.setEncargado(rs.getString(17));
                c.setCodVendedorLacteo(rs.getString(18));
                c.setCodEmpresa(rs.getString(19));
                c.setFecUltimaCompra(rs.getString(20));
                c.setIndEnvio(rs.getString(21));
                c.setTipoImpuesto(rs.getString(22));
                c.setPorcDescuentoVolumen(rs.getString(23));
                c.setPorcDescuentoCanal(rs.getString(24));
                c.setPorcDescuentoCategoria(rs.getString(25));
                c.setPorcDescuentoVisibilidad1(rs.getString(26));
                c.setPorcDescuentoVisibilidad2(rs.getString(27));
                c.setPorcDescuentoVisibilidad3(rs.getString(28));
                c.setPorcDescuentoCultural(rs.getString(29));
                c.setTipCliente(rs.getString(30));
                c.setDna(rs.getString(31));
                c.setFecVencDna(rs.getString(32));
                c.setMontoVendido(rs.getString(33));
                c.setObjetivoVenta(rs.getString(34));
//                c.setCodCiudad(rs.getString(37));
//                c.setEstado(rs.getString(38));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta ");
            e.printStackTrace();
            logger.error("Error realizando la consulta de clientes " + sql, e);
            c = null;
        }
        AppUtils.cerrarConsulta();
        return lista;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(Clientes[] reg) {
        String sql = "insert into ws_clientes (cod_empresa, "
                + "es_fisica, "
                + "nombre, "
                + "apellido, "
                + "nombre_fantasia, "
                + "representante_legal, "
                + "direccion, "
                + "telefono_uno, "
                + "telefono_dos, "
                + "email,"
                + "ruc, "
                + "cedula, "
                + "cod_vendedor, "
                + "cod_cliente, "
                + "cod_persona, "
                + "condicion_venta, "
                + "limite_credito, "
                + "moneda_credito, "
                + "sector_econ, "
                + "lista_precio, "
                + "encargado, "
                + "ind_procesado, "
                + "latitud, "
                + "longitud) values(", values = new String();

        for (Clientes element : reg) {
            values = "'" + element.getCodEmpresa() + "',"
                    + "'" + element.getTipoPersona() + "',"
                    + "'" + element.getNombre() + "',"
                    + "'" + element.getApellido() + "',"
                    + "'" + element.getNombreFantasia() + "',"
                    + "'" + element.getRepLegal()+ "',"
                    + "'" + element.getDireccion() + "',"
                    + "'" + element.getTelefono1() + "',"
                    + "'" + element.getTelefono2() + "',"
                    + "'" + element.getEmail() + "',"
                    + "'" + element.getRuc() + "',"
                    + "'" + element.getCedula() + "',"
                    + "'" + element.getCodVendedor() + "',"
                    + "'" + element.getCodCliente() + "',"
                    + "'" + element.getCodPersona() + "',"
                    + "'" + element.getCondicionVenta() + "',"
                    + "'" + element.getLimiteCredito() + "',"
                    + "'" + element.getCodMonedaCredito() + "',"
                    + "'" + element.getSectorEconomico()+ "',"
                    + "'" + element.getListaPrecio() + "',"
                    + "'" + element.getEncargado() + "',"
                    + "'N',"
                    + "'" + element.getLatitud() + "',"
                    + "'" + element.getLongitud() + "')";

            try {
                AppUtils.realizaCarga(sql + values);
            } catch (SQLException e) {
                logger.error("Error al insertar el cliente ", e);
                e.printStackTrace();
                return Response.status(400).entity("Error al insertar cliente " + e.getMessage()).build();
            }
        }

        return Response.status(200).entity("OK").build();
    }
}
