package com.pablosrl.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

import com.pablosrl.data.cm_pedidos_compras.PedidoCabecera;
import com.pablosrl.data.cm_pedidos_compras.PedidoDetalle;
import com.pablosrl.dto.PedidoCompletoDTO;
import com.pablosrl.util.AppUtils;

@ApplicationScoped
public class CmPedidoService {

    // Método para obtener el próximo número de comprobante
    private int obtenerProximoNroComprobante(String codEmpresa, String tipComprobante, String serComprobante) {
        String sql = "SELECT MAX(NRO_COMPROBANTE) AS ultimo_nro FROM cm_pedidos_cabecera "
                     + "WHERE COD_EMPRESA = ? AND TIP_COMPROBANTE = ? AND SER_COMPROBANTE = ?";
        int ultimoNro = 0;
        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, codEmpresa);
            stmt.setString(2, tipComprobante);
            stmt.setString(3, serComprobante);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ultimoNro = rs.getInt("ultimo_nro") + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el próximo número de comprobante: " + e.getMessage());
        }
        return ultimoNro;
    }

    // Método para obtener un pedido completo (cabecera + detalles)
    public PedidoCompletoDTO obtenerPedidoCompleto(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) throws SQLException {
        PedidoCompletoDTO pedidoCompleto = new PedidoCompletoDTO();
        pedidoCompleto.setCabecera(obtenerCabeceraPedido(codEmpresa, tipComprobante, serComprobante, nroComprobante));
        pedidoCompleto.setDetalles(obtenerDetallePedidos(codEmpresa, tipComprobante, serComprobante, nroComprobante));
        return pedidoCompleto;
    }

    // Método para obtener la cabecera de un pedido
    private PedidoCabecera obtenerCabeceraPedido(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        PedidoCabecera pedido = null;
        String sql = "SELECT * FROM cm_pedidos_cabecera WHERE COD_EMPRESA = ? AND TIP_COMPROBANTE = ? AND SER_COMPROBANTE = ? AND NRO_COMPROBANTE = ?";
        
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
                    pedido.setTipComprobante(rs.getString("TIP_COMPROBANTE"));
                    pedido.setSerComprobante(rs.getString("SER_COMPROBANTE"));
                    pedido.setNroComprobante(rs.getInt("NRO_COMPROBANTE"));
                    // Agregar el resto de los campos según tu estructura
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
        String sql = "SELECT * FROM cm_pedidos_detalle WHERE COD_EMPRESA = ? AND TIP_COMPROBANTE = ? AND SER_COMPROBANTE = ? AND NRO_COMPROBANTE = ?";

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
                    // Agregar el resto de los campos según tu estructura
                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los detalles del pedido: " + e.getMessage());
        }
        return detalles;
    }

    // Método para insertar un pedido completo (cabecera + detalles)
    public boolean insertarPedidoCompleto(PedidoCompletoDTO pedidoCompleto) {
        try (Connection con = AppUtils.getConnection()) {
            con.setAutoCommit(false);

            // Insertar la cabecera
            insertarPedidoCabecera(pedidoCompleto.getCabecera(), con);

            // Insertar cada detalle
            for (PedidoDetalle detalle : pedidoCompleto.getDetalles()) {
                insertarPedidoDetalle(detalle, con);
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error insertando el pedido completo: " + e.getMessage());
            return false;
        }
    }

    // Inserción de la cabecera del pedido
    private void insertarPedidoCabecera(PedidoCabecera pedido, Connection con) throws SQLException {
        int nuevoNroComprobante = obtenerProximoNroComprobante(pedido.getCodEmpresa(), pedido.getTipComprobante(), pedido.getSerComprobante());
        pedido.setNroComprobante(nuevoNroComprobante);

        String sql = "INSERT INTO cm_pedidos_cabecera (COD_EMPRESA, COD_SUCURSAL, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, FEC_COMPROBANTE, COD_PROVEEDOR) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pedido.getCodEmpresa());
            stmt.setString(2, pedido.getCodSucursal());
            stmt.setString(3, pedido.getTipComprobante());
            stmt.setString(4, pedido.getSerComprobante());
            stmt.setInt(5, pedido.getNroComprobante());
            stmt.setDate(6, java.sql.Date.valueOf(pedido.getFecComprobante()));
            stmt.setString(7, pedido.getCodProveedor());
            stmt.executeUpdate();
        }
    }

    // Inserción del detalle del pedido
    private void insertarPedidoDetalle(PedidoDetalle detalle, Connection con) throws SQLException {
        String sql = "INSERT INTO cm_pedidos_detalle (COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, COD_ARTICULO, CANTIDAD, PRECIO_UNITARIO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, detalle.getCodEmpresa());
            stmt.setString(2, detalle.getTipComprobante());
            stmt.setString(3, detalle.getSerComprobante());
            stmt.setInt(4, detalle.getNroComprobante());
            stmt.setString(5, detalle.getCodArticulo());
            stmt.setBigDecimal(6, detalle.getCantidad());
            stmt.setBigDecimal(7, detalle.getPrecioUnitario());
            stmt.executeUpdate();
        }
    }
}


