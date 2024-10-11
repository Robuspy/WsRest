package com.pablosrl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

import org.apache.log4j.Logger;

import com.pablosrl.data.cm_pedidos_compras.PedidoCabecera;
import com.pablosrl.data.cm_pedidos_compras.PedidoDetalle;
import com.pablosrl.dto.PedidoCompletoDTO;
import com.pablosrl.util.AppUtils;

@ApplicationScoped
public class CmPedidoService {
	
	// Inicialización del logger
    private static final Logger logger = Logger.getLogger(CmPedidoService.class);

 // Obtener el próximo número de comprobante usando la conexión existente
    private int obtenerProximoNroComprobante(String codEmpresa, String tipComprobante, String serComprobante, Connection con) throws SQLException {
        String sql = "SELECT MAX(NRO_COMPROBANTE) AS ultimo_nro FROM cm_pedidos_cabecera "
                     + "WHERE COD_EMPRESA = ? AND TIP_COMPROBANTE = ? AND SER_COMPROBANTE = ?";
        int ultimoNro = 0;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codEmpresa);
            stmt.setString(2, tipComprobante);
            stmt.setString(3, serComprobante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ultimoNro = rs.getInt("ultimo_nro") + 1;
                }
            }
        }
        return ultimoNro;
    }

 // Método para obtener un pedido completo (cabecera + detalles) sin verificar si los campos son null
    public PedidoCompletoDTO obtenerPedidoCompleto(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        PedidoCompletoDTO pedidoCompleto = new PedidoCompletoDTO();

        // Obtener la cabecera del pedido
        PedidoCabecera cabecera = obtenerCabeceraPedido(codEmpresa, tipComprobante, serComprobante, nroComprobante);
        
        // Si no hay cabecera, devolver null (el pedido no existe)
        if (cabecera == null) {
            return null;
        }

        // Obtener los detalles del pedido
        List<PedidoDetalle> detalles = obtenerDetallePedidos(codEmpresa, tipComprobante, serComprobante, nroComprobante);
        
        // Establecer la cabecera y los detalles en el objeto DTO
        pedidoCompleto.setCabecera(cabecera);
        pedidoCompleto.setDetalles(detalles);

        return pedidoCompleto;
    }

    // Método para obtener la cabecera de un pedido
    private PedidoCabecera obtenerCabeceraPedido(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        PedidoCabecera pedido = null;
        String sql = "SELECT cod_empresa, cod_sucursal, tip_comprobante, ser_comprobante, nro_comprobante "
        		   + "FROM cm_pedidos_cabecera "
        		   + "WHERE COD_EMPRESA = ? "
        		   + "AND TIP_COMPROBANTE = ? "
        		   + "AND SER_COMPROBANTE = ? "
        		   + "AND NRO_COMPROBANTE = ? ";
        
        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codEmpresa);
            stmt.setString(2, tipComprobante);
            stmt.setString(3, serComprobante);
            stmt.setString(4, nroComprobante);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pedido = new PedidoCabecera();
                    pedido.setCodEmpresa(rs.getString("COD_EMPRESA"));
                    pedido.setCodSucursal(rs.getString("COD_SUCURSAL"));
                    pedido.setTipComprobante(rs.getString("TIP_COMPROBANTE"));
                    pedido.setSerComprobante(rs.getString("SER_COMPROBANTE"));
                    pedido.setNroComprobante(rs.getInt("NRO_COMPROBANTE"));
                    
                    /*pedido.setFecComprobante(rs.getDate("FEC_COMPROBANTE").toLocalDate());
                    pedido.setCodProveedor(rs.getString("COD_PROVEEDOR"));
                    pedido.setCodCondicionCompra(rs.getString("COD_CONDICION_COMPRA"));
                    pedido.setTotComprobante(rs.getBigDecimal("TOT_COMPROBANTE"));
                    pedido.setTotGravadas(rs.getBigDecimal("TOT_GRAVADAS"));
                    pedido.setTotExentas(rs.getBigDecimal("TOT_EXENTAS"));
                    pedido.setTotIva(rs.getBigDecimal("TOT_IVA"));
                    pedido.setCodMoneda(rs.getString("COD_MONEDA"));
                    pedido.setTipCambio(rs.getBigDecimal("TIP_CAMBIO"));
                    pedido.setEstado(rs.getString("ESTADO"));
                    pedido.setFecEstado(rs.getDate("FEC_ESTADO").toLocalDate());
                    pedido.setCodUsuario(rs.getString("COD_USUARIO"));
                    pedido.setAnulado(rs.getString("ANULADO"));
                    pedido.setCambioMonedaPrecio(rs.getBigDecimal("CAMBIO_MONEDA_PRECIO"));
                    pedido.setReferencia(rs.getString("REFERENCIA"));
                    pedido.setIndIvaIncluido(rs.getString("IND_IVA_INCLUIDO"));
                    pedido.setTotalPeso(rs.getBigDecimal("TOTAL_PESO"));
                    pedido.setCodSucursalPed(rs.getString("COD_SUCURSAL_PED"));
                    pedido.setDescSucursalPed(rs.getString("DESC_SUCURSAL_PED"));
                    pedido.setEntrega(rs.getString("ENTREGA"));*/
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la cabecera del pedido: " + e.getMessage());
        }
        return pedido;
    }

    // Método para obtener los detalles de un pedido
    public List<PedidoDetalle> obtenerDetallePedidos(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        List<PedidoDetalle> detalles = new ArrayList<>();
        String sql = "SELECT cod_empresa, tip_comprobante, ser_comprobante, nro_comprobante,"
        		   + "nro_orden, "
        		   + "cod_articulo, desc_articulo, "
        		   + "nro_lote, "
        		   + "cantidad, "
        		   + "precio_unitario, precio_unitario_c_iva, "
        		   + "monto_total, monto_total_c_iva "
        		   + "FROM cm_pedidos_detalle "
        		   + "WHERE COD_EMPRESA = ? "
        		   + "AND TIP_COMPROBANTE = ? "
        		   + "AND SER_COMPROBANTE = ? "
        		   + "AND NRO_COMPROBANTE = ?";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codEmpresa);
            stmt.setString(2, tipComprobante);
            stmt.setString(3, serComprobante);
            stmt.setString(4, nroComprobante);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PedidoDetalle detalle = new PedidoDetalle();
                    detalle.setCodEmpresa(rs.getString("COD_EMPRESA"));
                    detalle.setTipComprobante(rs.getString("TIP_COMPROBANTE"));
                    detalle.setSerComprobante(rs.getString("SER_COMPROBANTE"));
                    detalle.setNroComprobante(rs.getInt("NRO_COMPROBANTE"));
                    detalle.setNroOrden(rs.getInt("NRO_ORDEN"));
                    detalle.setCodArticulo(rs.getString("COD_ARTICULO"));
                    detalle.setDescArticulo(rs.getString("DESC_ARTICULO"));
                    detalle.setNroLote(rs.getString("NRO_LOTE"));
                    detalle.setCantidad(rs.getBigDecimal("CANTIDAD"));
                    detalle.setPrecioUnitario(rs.getBigDecimal("PRECIO_UNITARIO"));
                    detalle.setPrecioUnitarioCIVA(rs.getBigDecimal("PRECIO_UNITARIO_C_IVA"));
                    detalle.setMontoTotal(rs.getBigDecimal("MONTO_TOTAL"));       
                    detalle.setMontoTotalCIVA(rs.getBigDecimal("MONTO_TOTAL_C_IVA"));
                    
                    
                    /*
                     * 
                    detalle.setTotalIva(rs.getBigDecimal("TOTAL_IVA"));
                    detalle.setCodUnidadMedida(rs.getString("COD_UNIDAD_MEDIDA"));
                    detalle.setCantidadUb(rs.getBigDecimal("CANTIDAD_UB"));
                    
                    
                    detalle.setPorcIva(rs.getBigDecimal("PORC_IVA"));
                    detalle.setPorcGravadas(rs.getBigDecimal("PORC_GRAVADAS"));
                    detalle.setMult(rs.getBigDecimal("MULT"));
                    detalle.setDiv(rs.getBigDecimal("DIV"));
                    detalle.setMontoGravadas(rs.getBigDecimal("MONTO_GRAVADAS"));
                    detalle.setMontoExentas(rs.getBigDecimal("MONTO_EXENTAS"));
                    detalle.setCodIva(rs.getString("COD_IVA"));
                    detalle.setTotalPesoArt(rs.getBigDecimal("TOTAL_PESO_ART"));
                    
                    
                    */
                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los detalles del pedido: " + e.getMessage());
        }
        return detalles;
    }

 // Método para insertar un pedido completo (cabecera + detalles)
    public String insertarPedidoCompleto(PedidoCompletoDTO pedidoCompleto) {
        Connection con = null;
        try {
            con = AppUtils.getConnection();
            con.setAutoCommit(false);  // Iniciar la transacción

            // Obtener el próximo número de comprobante
            int nuevoNroComprobante = obtenerProximoNroComprobante(
                pedidoCompleto.getCabecera().getCodEmpresa(),
                pedidoCompleto.getCabecera().getTipComprobante(),
                pedidoCompleto.getCabecera().getSerComprobante(),
                con
            );
            pedidoCompleto.getCabecera().setNroComprobante(nuevoNroComprobante);

            logger.info("Insertando cabecera del pedido con nroComprobante: " + nuevoNroComprobante);
            // Insertar la cabecera del pedido
            insertarPedidoCabecera(pedidoCompleto.getCabecera(), con);

            logger.info("Insertando detalles del pedido");
            // Insertar cada detalle asociado al pedido
            for (PedidoDetalle detalle : pedidoCompleto.getDetalles()) {
                detalle.setNroComprobante(nuevoNroComprobante);  // Asignar el nroComprobante de la cabecera al detalle
                insertarPedidoDetalle(detalle, con);
            }

            // Si todo sale bien, hacer commit
            con.commit();
            logger.info("Pedido completo insertado correctamente con nroComprobante: " + nuevoNroComprobante);

            // Devolver el número de comprobante generado
            return "Pedido completo insertado correctamente. Número de comprobante: " + nuevoNroComprobante;

        } catch (SQLException e) {
            logger.error("Error insertando el pedido completo, realizando rollback: " + e.getMessage(), e);
            if (con != null) {
                try {
                    con.rollback();  // Hacer rollback en caso de error
                } catch (SQLException ex) {
                    logger.error("Error realizando rollback: " + ex.getMessage(), ex);
                }
            }
            return null;

        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);  // Volver a activar el autocommit
                    con.close();  // Cerrar la conexión
                } catch (SQLException e) {
                    logger.error("Error cerrando la conexión: " + e.getMessage(), e);
                }
            }
        }
    }



    // Inserción de la cabecera del pedido con transacción
    public void insertarPedidoCabecera(PedidoCabecera pedido, Connection con) throws SQLException {
        int nuevoNroComprobante = obtenerProximoNroComprobante(pedido.getCodEmpresa(), pedido.getTipComprobante(), pedido.getSerComprobante(), con);
        pedido.setNroComprobante(nuevoNroComprobante);

        String sql = "INSERT INTO cm_pedidos_cabecera (COD_EMPRESA, COD_SUCURSAL, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, " +
									                  "COD_SUCURSAL_PED, DESC_SUCURSAL_PED, COD_MONEDA, TIP_CAMBIO, CAMBIO_MONEDA_PRECIO, " +
									                  "COD_PROVEEDOR, COD_CONDICION_COMPRA, COD_CLIENTE, REFERENCIA) " +
									                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (PreparedStatement stmt = con.prepareStatement(sql)) {
        	
        	stmt.setString(1, pedido.getCodEmpresa());
            stmt.setString(2, pedido.getCodSucursal());
            stmt.setString(3, pedido.getTipComprobante());
            stmt.setString(4, pedido.getSerComprobante());
            stmt.setInt(5, pedido.getNroComprobante());
            
            stmt.setString(6, pedido.getCodSucursalPed());
            stmt.setString(7, pedido.getDescSucursalPed());
            stmt.setString(8, pedido.getCodMoneda());
            stmt.setBigDecimal(9, pedido.getTipCambio());
            stmt.setBigDecimal(10, pedido.getCambioMonedaPrecio());

            stmt.setString(11, pedido.getCodProveedor());
            stmt.setString(12, pedido.getCodCondicionCompra());
            stmt.setString(13, pedido.getCodCliente());
            stmt.setString(14, pedido.getReferencia());
        	
        	
            /*
            
            
            stmt.setInt(7, pedido.getNroComprobante());
            stmt.setDate(8, java.sql.Date.valueOf(pedido.getFecComprobante()));
            stmt.setString(9, pedido.getCodProveedor());
            stmt.setString(10, pedido.getCodCondicionCompra());
            stmt.setString(11, pedido.getCodMoneda());
            stmt.setBigDecimal(12, pedido.getTipCambio());
            stmt.setBigDecimal(13, pedido.getCambioMonedaPrecio());
            stmt.setString(14, pedido.isIndIvaIncluido());
            stmt.setBigDecimal(15, pedido.getTotIva());
            stmt.setBigDecimal(16, pedido.getTotExentas());
            stmt.setBigDecimal(17, pedido.getTotGravadas());
            stmt.setBigDecimal(18, pedido.getTotComprobante());*/

            stmt.executeUpdate();
        }
    }

    // Inserción del detalle del pedido con transacción
    public void insertarPedidoDetalle(PedidoDetalle detalle, Connection con) throws SQLException {
        String sql = "INSERT INTO cm_pedidos_detalle (COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, "
        		                                   + "COD_ARTICULO, NRO_LOTE, CANTIDAD ) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, detalle.getCodEmpresa());
            stmt.setString(2, detalle.getTipComprobante());
            stmt.setString(3, detalle.getSerComprobante());
            stmt.setInt(4, detalle.getNroComprobante());
            stmt.setString(5, detalle.getCodArticulo());
            stmt.setString(6, detalle.getNroLote());
            stmt.setBigDecimal(7, detalle.getCantidad());
            
            /*stmt.setBigDecimal(6, detalle.getCantidad());
            stmt.setBigDecimal(7, detalle.getPrecioUnitario());
            stmt.setBigDecimal(8, detalle.getMontoTotal());
            stmt.setBigDecimal(9, detalle.getTotalIva());*/

            stmt.executeUpdate();
        }
    }

}


