package com.pablosrl.service;

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

	// Consulta para obtener la cabecera del pedido
	public List<PedidoCabecera> obtenerCabeceraPedidos() {
    List<PedidoCabecera> pedidos = new ArrayList<>();
    String sql = "SELECT COD_EMPRESA, TIP_COMPROBANTE, SER_COMPROBANTE, NRO_COMPROBANTE, COD_SUCURSAL, "
                 + "FEC_COMPROBANTE, COD_PROVEEDOR, COD_CONDICION_COMPRA, TOT_COMPROBANTE, TOT_GRAVADAS, "
                 + "TOT_EXENTAS, TOT_IVA, DESCUENTO, COD_MONEDA, TIP_CAMBIO, VERIFICADORA, TRANSPORTE, VIA, "
                 + "FEC_EMBARQUE, FEC_CONFIRMACION, ESTADO, FEC_ESTADO, COD_USUARIO, FEC_ALTA, ANULADO, "
                 + "CAMBIO_MONEDA_PRECIO, TIP_COMPROBANTE_REF, SER_COMPROBANTE_REF, NRO_COMPROBANTE_REF, "
                 + "REFERENCIA, IND_IVA_INCLUIDO, TOTAL_PESO, COD_TECNICO, IND_RECIBIDO, DEPOSITO, FEC_LLEGADA, "
                 + "COD_SUCURSAL_PED, DESC_SUCURSAL_PED, ENTREGA, ETIQUETA, COSTO_ETIQUETA "
                 + "FROM cm_pedidos_cabecera";

    ResultSet rs = null;
    try {
        // Usar AppUtils para realizar la consulta
        rs = AppUtils.realizaConsulta(sql);

        if (!rs.next()) {
        	System.out.println("No se encontraron datos en la tabla cm_pedidos_cabecera.");
        } else {
        	System.out.println("Datos encontrados en la tabla cm_pedidos_cabecera.");
            do {
                PedidoCabecera pedido = new PedidoCabecera();
                pedido.setCodEmpresa(rs.getString("COD_EMPRESA"));
                pedido.setTipComprobante(rs.getString("TIP_COMPROBANTE"));
                pedido.setSerComprobante(rs.getString("SER_COMPROBANTE"));
                pedido.setNroComprobante(rs.getInt("NRO_COMPROBANTE"));  // Cambio a getInt porque es NUMBER(8)
                pedido.setCodSucursal(rs.getString("COD_SUCURSAL"));
                pedido.setFecComprobante(rs.getDate("FEC_COMPROBANTE"));
                pedido.setCodProveedor(rs.getString("COD_PROVEEDOR"));
                pedido.setCodCondicionCompra(rs.getString("COD_CONDICION_COMPRA"));
                pedido.setTotComprobante(rs.getBigDecimal("TOT_COMPROBANTE"));  // Cambio a getBigDecimal para NUMBER(18,3)
                pedido.setTotGravadas(rs.getBigDecimal("TOT_GRAVADAS"));
                pedido.setTotExentas(rs.getBigDecimal("TOT_EXENTAS"));
                pedido.setTotIva(rs.getBigDecimal("TOT_IVA"));
                pedido.setDescuento(rs.getBigDecimal("DESCUENTO"));
                pedido.setCodMoneda(rs.getString("COD_MONEDA"));
                pedido.setTipCambio(rs.getBigDecimal("TIP_CAMBIO"));
                pedido.setVerificadora(rs.getString("VERIFICADORA"));
                pedido.setTransporte(rs.getString("TRANSPORTE"));
                pedido.setVia(rs.getString("VIA"));
                pedido.setFecEmbarque(rs.getDate("FEC_EMBARQUE"));
                pedido.setFecConfirmacion(rs.getDate("FEC_CONFIRMACION"));
                pedido.setEstado(rs.getString("ESTADO"));
                pedido.setFecEstado(rs.getDate("FEC_ESTADO"));
                pedido.setCodUsuario(rs.getString("COD_USUARIO"));
                pedido.setFecAlta(rs.getDate("FEC_ALTA"));
                pedido.setAnulado("Y".equals(rs.getString("ANULADO")));  // Asumiendo 'Y' como true para VARCHAR2(1)
                pedido.setCambioMonedaPrecio(rs.getBigDecimal("CAMBIO_MONEDA_PRECIO"));
                pedido.setTipComprobanteRef(rs.getString("TIP_COMPROBANTE_REF"));
                pedido.setSerComprobanteRef(rs.getString("SER_COMPROBANTE_REF"));
                pedido.setNroComprobanteRef(rs.getInt("NRO_COMPROBANTE_REF"));  // Cambio a getInt porque es NUMBER(8)
                pedido.setReferencia(rs.getString("REFERENCIA"));
                pedido.setIndIvaIncluido("Y".equals(rs.getString("IND_IVA_INCLUIDO")));  // Asumiendo 'Y' como true para VARCHAR2(1)
                pedido.setTotalPeso(rs.getBigDecimal("TOTAL_PESO"));  // Usando getBigDecimal para NUMBER
                pedido.setCodTecnico(rs.getString("COD_TECNICO"));
                pedido.setIndRecibido("Y".equals(rs.getString("IND_RECIBIDO")));  // Asumiendo 'Y' como true para VARCHAR2(1)
                pedido.setDeposito(rs.getString("DEPOSITO"));
                pedido.setFecLlegada(rs.getDate("FEC_LLEGADA"));
                pedido.setCodSucursalPed(rs.getString("COD_SUCURSAL_PED"));
                pedido.setDescSucursalPed(rs.getString("DESC_SUCURSAL_PED"));
                pedido.setEntrega(rs.getString("ENTREGA"));
                pedido.setEtiqueta(rs.getString("ETIQUETA"));
                pedido.setCostoEtiqueta(rs.getBigDecimal("COSTO_ETIQUETA"));  // Usando getBigDecimal para NUMBER(10,3)

                pedidos.add(pedido);
                
            } while (rs.next());
        }
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error en la consulta SQL: " + e.getMessage());
    } finally {
        AppUtils.cerrarConsulta();
    }
	return pedidos;

	}

    // Consulta para obtener los detalles del pedido
    public List<PedidoDetalle> obtenerDetallePedidos(String codEmpresa, String tipComprobante, String serComprobante, String nroComprobante) {
        // Lógica para obtener los detalles de un pedido específico
        return null; // Implementar
    }

    // Inserción de la cabecera del pedido
    public void insertarPedidoCabecera(PedidoCabecera pedido) {
        // Lógica para insertar el pedido en la base de datos
    }

    // Inserción del detalle del pedido
    public void insertarPedidoDetalle(PedidoDetalle detalle) {
        // Lógica para insertar el detalle en la base de datos
    }
    
}
