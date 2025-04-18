package com.pablosrl.service.cuentas_cobrar;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.pablosrl.data.cuentas_cobrar.ClienteSaldo;
import com.pablosrl.util.AppUtils;

public class ClienteSaldoService {

    public ClienteSaldo consultarSaldo(String codEmpresa, String codCliente) {
        ClienteSaldo clienteSaldo = new ClienteSaldo();

        String sql = "{ call inv_vtfactur.cab_cod_cliente_saldo_wvi(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection con = AppUtils.getConnection();
             CallableStatement stmt = con.prepareCall(sql)) {

            // Parámetros de entrada
            stmt.setString(1, codEmpresa);       // pvcod_empresa
            stmt.setString(2, codCliente);       // pccod_cliente
            
            stmt.registerOutParameter(2, Types.VARCHAR); // ← recibir posible cambio

            // Parámetros de salida
            stmt.registerOutParameter(3, Types.VARCHAR);       // pcnom_cliente
            stmt.registerOutParameter(4, Types.NUMERIC);       // pcsaldo_gs
            stmt.registerOutParameter(5, Types.NUMERIC);       // pclimite_credito
            stmt.registerOutParameter(6, Types.NUMERIC);       // pcposible
            stmt.registerOutParameter(7, Types.NUMERIC);       // pcheques_pendientes
            stmt.registerOutParameter(8, Types.NUMERIC);       // pcsaldo_favor
            stmt.registerOutParameter(9, Types.NUMERIC);       // pcnotas_credito
            stmt.registerOutParameter(10, Types.NUMERIC);      // pcsaldo_total
            stmt.registerOutParameter(11, Types.VARCHAR);      // pmensaje

            stmt.execute();

            String codClienteReal = stmt.getString(2);
            
          // Asignar datos base del paquete
             clienteSaldo.setCodCliente(codClienteReal);
	         clienteSaldo.setNombreCliente(stmt.getString(3));
	         clienteSaldo.setSaldoGs(getBigDecimalAsString(stmt.getBigDecimal(4)));
	         clienteSaldo.setLimiteCredito(getBigDecimalAsString(stmt.getBigDecimal(5)));
	         clienteSaldo.setPosibleCompra(getBigDecimalAsString(stmt.getBigDecimal(6)));
	         clienteSaldo.setChequesPendientes(getBigDecimalAsString(stmt.getBigDecimal(7)));
	         clienteSaldo.setSaldoFavor(getBigDecimalAsString(stmt.getBigDecimal(8)));
	         clienteSaldo.setNotasCredito(getBigDecimalAsString(stmt.getBigDecimal(9)));
	         clienteSaldo.setSaldoTotal(getBigDecimalAsString(stmt.getBigDecimal(10)));
	         clienteSaldo.setMensaje(stmt.getString(11));


            // Logs para depuración
            System.out.println("Consulta Paquete de Saldo del Cliente - Limites Creditos:");
            System.out.println("Cliente: " + codCliente + " - " + clienteSaldo.getNombreCliente());
            
            
            // ▶ Segunda consulta adicional con el codCliente obtenido
            String sqlInfoExtra = """
                SELECT 
                    DECODE(c.cod_lista_precio, '01', 'MAYORISTA', 'MINORISTA') AS desc_precio,
                    trae_nombre_plan_categoria(c.cod_empresa, c.cod_categoria) AS desc_plan,
                    trae_nombre_condiciones_ventas(c.cod_empresa, c.cod_condicion_venta) AS desc_condicion_venta,
                    DECODE(c.ind_consignacion, 'N', 'NO', 'SI') AS desc_consignacion,
                    trae_ult_fecha_compra_cliente(c.cod_empresa, null, null, null, c.cod_cliente) AS fecha_ult_compra,
                    trae_ult_monto_compra_cliente(c.cod_empresa, c.cod_cliente) AS monto_ult_compra
                FROM cc_clientes c
                WHERE c.cod_empresa = ? AND c.cod_cliente = ?
            """;

            try (PreparedStatement ps = con.prepareStatement(sqlInfoExtra)) {
                ps.setString(1, codEmpresa);
                ps.setString(2, codClienteReal);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        clienteSaldo.setDescPrecio(rs.getString("desc_precio"));
                        clienteSaldo.setDescPlan(rs.getString("desc_plan"));
                        clienteSaldo.setDescCondicionVenta(rs.getString("desc_condicion_venta"));
                        clienteSaldo.setDescConsignacion(rs.getString("desc_consignacion"));
                        clienteSaldo.setFechaUltCompra(rs.getDate("fecha_ult_compra"));
                        clienteSaldo.setMontoUltCompra(getBigDecimalAsString(rs.getBigDecimal("monto_ult_compra")));
                    }
                }
            }

            System.out.println("Consulta completa - Cliente: " + codClienteReal);
            
            

        } catch (SQLException e) {
            e.printStackTrace();
            clienteSaldo.setMensaje("Error al consultar saldo: " + e.getMessage());
        }

        return clienteSaldo;
    }

    private String getBigDecimalAsString(BigDecimal value) {
        return value != null ? value.toPlainString() : "0";
    }
}
