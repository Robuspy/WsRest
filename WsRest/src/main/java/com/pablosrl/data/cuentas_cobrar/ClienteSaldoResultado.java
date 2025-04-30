package com.pablosrl.data.cuentas_cobrar;

import java.util.List;

public class ClienteSaldoResultado {
	private List<ClienteSaldo> saldos;
    private double totalSaldo;
    
    
	public List<ClienteSaldo> getSaldos() {
		return saldos;
	}
	public void setSaldos(List<ClienteSaldo> saldos) {
		this.saldos = saldos;
	}
	public double getTotalSaldo() {
		return totalSaldo;
	}
	public void setTotalSaldo(double totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
    
    

}
