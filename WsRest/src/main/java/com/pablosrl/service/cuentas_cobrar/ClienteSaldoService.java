package com.pablosrl.service.cuentas_cobrar;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
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

	         // Asignar valores al DTO desde los parámetros devueltos por el paquete
	         clienteSaldo.setCodCliente(stmt.getString(2)); // ← importante: obtener valor real del paquete
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
