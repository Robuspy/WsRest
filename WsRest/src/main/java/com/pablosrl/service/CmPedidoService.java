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

    // Consulta para obtener la cabecera del pedido
    public List<PedidoCabecera> obtenerCabeceraPedidos() {
        List<PedidoCabecera> pedidos = new ArrayList<>();
        String sql = "SELECT COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, COD_SUCURSAL, "
                     + "FEC_COMPROBANTE, COD_PROVEEDOR, COD_CONDICION_COMPRA, TOT_COMPROBANTE, TOT_GRAVADAS, "
                     + "TOT_EXENTAS, TOT_IVA, DESCUENTO, COD_MONEDA, TIP_CAMBIO, ESTADO, FEC_ESTADO, COD_USUARIO, "
                     + "ANULADO, CAMBIO_MONEDA_PRECIO, REFERENCIA, IND_IVA_INCLUIDO, TOTAL_PESO, COD_SUCURSAL_PED, "
                     + "DESC_SUCURSAL_PED, ENTREGA "
                     + "FROM cm_pedidos_cabecera";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PedidoCabecera pedido = new PedidoCabecera();
                pedido.setCodEmpresa(rs.getString("COD_EMPRESA"));
                pedido.setTipComprobante(rs.getString("TIP_COMPROBANTE"));
                pedido.setSerComprobante(rs.getString("SER_COMPROBANTE"));
                pedido.setNroComprobante(rs.getInt("NRO_COMPROBANTE"));
                pedido.setCodSucursal(rs.getString("COD_SUCURSAL"));
                pedido.setFecComprobante(rs.getDate("FEC_COMPROBANTE").toLocalDate());
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
                pedido.setEntrega(rs.getString("ENTREGA"));

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta SQL: " + e.getMessage());
        }
        return pedidos;
    }

    // Método para obtener los detalles de un pedido
   /* public List<PedidoDetalle> obtenerDetallePedidos(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        List<PedidoDetalle> detalles = new ArrayList<>();
        String sql = "SELECT COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, COD_ARTICULO, "
                     + "CANTIDAD, PRECIO_UNITARIO, TOTAL "
                     + "FROM cm_pedidos_detalle "
                     + "WHERE COD_EMPRESA = ? AND TIP_COMPROBANTE = ? AND SER_COMPROBANTE = ? AND NRO_COMPROBANTE = ?";

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
                    detalle.setCodArticulo(rs.getString("COD_ARTICULO"));
                    detalle.setCantidad(rs.getBigDecimal("CANTIDAD"));
                    detalle.setPrecioUnitario(rs.getBigDecimal("PRECIO_UNITARIO"));
                    detalle.setMontoTotalCIVA(rs.getBigDecimal("TOTAL"));

                    detalles.add(detalle);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener detalles del pedido: " + e.getMessage());
        }
        return detalles;
    }*/

    // Inserción de la cabecera del pedido
    public void insertarPedidoCabecera(PedidoCabecera pedido) throws SQLException {
        int nuevoNroComprobante = obtenerProximoNroComprobante(pedido.getCodEmpresa(), pedido.getTipComprobante(), pedido.getSerComprobante());
        pedido.setNroComprobante(nuevoNroComprobante);

        String sql = "INSERT INTO cm_pedidos_cabecera (COD_EMPRESA, COD_SUCURSAL, COD_SUCURSAL_PED, DESC_SUCURSAL_PED, "
                     + "TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, FEC_COMPROBANTE, COD_PROVEEDOR, COD_CONDICION_COMPRA, "
                     + "COD_MONEDA, TIP_CAMBIO, CAMBIO_MONEDA_PRECIO, IND_IVA_INCLUIDO, TOT_IVA, TOT_EXENTAS, TOT_GRAVADAS, TOT_COMPROBANTE) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, pedido.getCodEmpresa());
            stmt.setString(2, pedido.getCodSucursal());
            stmt.setString(3, pedido.getCodSucursalPed());
            stmt.setString(4, pedido.getDescSucursalPed());
            stmt.setString(5, pedido.getTipComprobante());
            stmt.setString(6, pedido.getSerComprobante());
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
            stmt.setBigDecimal(18, pedido.getTotComprobante());

            stmt.executeUpdate();
        }
    }

    // Inserción del detalle del pedido
    /*public void insertarPedidoDetalle(PedidoDetalle detalle) throws SQLException {
        /*String sql = "INSERT INTO cm_pedidos_detalle (COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, "
                     + "COD_ARTICULO, CANTIDAD, PRECIO_UNITARIO, TOTAL) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = AppUtils.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, detalle.getCodEmpresa());
            stmt.setString(2, detalle.getTipComprobante());
            stmt.setString(3, detalle.getSerComprobante());
            stmt.setInt(4, detalle.getNroComprobante());
            stmt.setString(5, detalle.getCodArticulo());
            stmt.setBigDecimal(6, detalle.getCantidad());
            stmt.setBigDecimal(7, detalle.getPrecioUnitario());
            stmt.setBigDecimal(8, detalle.getTotal());

            stmt.executeUpdate();
        }
         
    }*/
}

