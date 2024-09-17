package com.pablosrl.data.cm_pedidos_compras;

import java.math.BigDecimal;
import java.sql.Date;

//com.pablosrl.data.PedidoCabecera.java
public class PedidoCabecera {
	private String codEmpresa;
    private String tipComprobante;
    private String serComprobante;
    private String nroComprobante;
    private String codSucursal;
    private Date fecComprobante;
    private String codProveedor;
    private String codCondicionCompra;
    private double totComprobante;
    private double totGravadas;
    private double totExentas;
    private double totIva;
    private double descuento;
    private String codMoneda;
    private double tipCambio;
    private String verificadora;
    private String transporte;
    private String via;
    private Date fecEmbarque;
    private Date fecConfirmacion;
    private String estado;
    private Date fecEstado;
    private String codUsuario;
    private Date fecAlta;
    private boolean anulado;
    private double cambioMonedaPrecio;
    private String tipComprobanteRef;
    private String serComprobanteRef;
    private String nroComprobanteRef;
    private String referencia;
    private boolean indIvaIncluido;
    private double totalPeso;
    private String codTecnico;
    private boolean indRecibido;
    private String deposito;
    private Date fecLlegada;
    private String codSucursalPed;
    private String descSucursalPed;
    private String entrega;
    private String etiqueta;
    private double costoEtiqueta;
    
    
    
    
	public String getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getTipComprobante() {
		return tipComprobante;
	}
	public void setTipComprobante(String tipComprobante) {
		this.tipComprobante = tipComprobante;
	}
	public String getSerComprobante() {
		return serComprobante;
	}
	public void setSerComprobante(String serComprobante) {
		this.serComprobante = serComprobante;
	}
	public String getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	public String getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}
	public Date getFecComprobante() {
		return fecComprobante;
	}
	public void setFecComprobante(Date fecComprobante) {
		this.fecComprobante = fecComprobante;
	}
	public String getCodProveedor() {
		return codProveedor;
	}
	public void setCodProveedor(String codProveedor) {
		this.codProveedor = codProveedor;
	}
	public String getCodCondicionCompra() {
		return codCondicionCompra;
	}
	public void setCodCondicionCompra(String codCondicionCompra) {
		this.codCondicionCompra = codCondicionCompra;
	}
	public double getTotComprobante() {
		return totComprobante;
	}
	public void setTotComprobante(double totComprobante) {
		this.totComprobante = totComprobante;
	}
	public double getTotGravadas() {
		return totGravadas;
	}
	public void setTotGravadas(double totGravadas) {
		this.totGravadas = totGravadas;
	}
	public double getTotExentas() {
		return totExentas;
	}
	public void setTotExentas(double totExentas) {
		this.totExentas = totExentas;
	}
	public double getTotIva() {
		return totIva;
	}
	public void setTotIva(double totIva) {
		this.totIva = totIva;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public double getTipCambio() {
		return tipCambio;
	}
	public void setTipCambio(double tipCambio) {
		this.tipCambio = tipCambio;
	}
	public String getVerificadora() {
		return verificadora;
	}
	public void setVerificadora(String verificadora) {
		this.verificadora = verificadora;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public Date getFecEmbarque() {
		return fecEmbarque;
	}
	public void setFecEmbarque(Date fecEmbarque) {
		this.fecEmbarque = fecEmbarque;
	}
	public Date getFecConfirmacion() {
		return fecConfirmacion;
	}
	public void setFecConfirmacion(Date fecConfirmacion) {
		this.fecConfirmacion = fecConfirmacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFecEstado() {
		return fecEstado;
	}
	public void setFecEstado(Date fecEstado) {
		this.fecEstado = fecEstado;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Date getFecAlta() {
		return fecAlta;
	}
	public void setFecAlta(Date fecAlta) {
		this.fecAlta = fecAlta;
	}
	public boolean isAnulado() {
		return anulado;
	}
	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
	public double getCambioMonedaPrecio() {
		return cambioMonedaPrecio;
	}
	public void setCambioMonedaPrecio(double cambioMonedaPrecio) {
		this.cambioMonedaPrecio = cambioMonedaPrecio;
	}
	public String getTipComprobanteRef() {
		return tipComprobanteRef;
	}
	public void setTipComprobanteRef(String tipComprobanteRef) {
		this.tipComprobanteRef = tipComprobanteRef;
	}
	public String getSerComprobanteRef() {
		return serComprobanteRef;
	}
	public void setSerComprobanteRef(String serComprobanteRef) {
		this.serComprobanteRef = serComprobanteRef;
	}
	public String getNroComprobanteRef() {
		return nroComprobanteRef;
	}
	public void setNroComprobanteRef(String nroComprobanteRef) {
		this.nroComprobanteRef = nroComprobanteRef;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public boolean isIndIvaIncluido() {
		return indIvaIncluido;
	}
	public void setIndIvaIncluido(boolean indIvaIncluido) {
		this.indIvaIncluido = indIvaIncluido;
	}
	public double getTotalPeso() {
		return totalPeso;
	}
	public void setTotalPeso(double totalPeso) {
		this.totalPeso = totalPeso;
	}
	public String getCodTecnico() {
		return codTecnico;
	}
	public void setCodTecnico(String codTecnico) {
		this.codTecnico = codTecnico;
	}
	public boolean isIndRecibido() {
		return indRecibido;
	}
	public void setIndRecibido(boolean indRecibido) {
		this.indRecibido = indRecibido;
	}
	public String getDeposito() {
		return deposito;
	}
	public void setDeposito(String deposito) {
		this.deposito = deposito;
	}
	public Date getFecLlegada() {
		return fecLlegada;
	}
	public void setFecLlegada(Date fecLlegada) {
		this.fecLlegada = fecLlegada;
	}
	public String getCodSucursalPed() {
		return codSucursalPed;
	}
	public void setCodSucursalPed(String codSucursalPed) {
		this.codSucursalPed = codSucursalPed;
	}
	public String getDescSucursalPed() {
		return descSucursalPed;
	}
	public void setDescSucursalPed(String descSucursalPed) {
		this.descSucursalPed = descSucursalPed;
	}
	public String getEntrega() {
		return entrega;
	}
	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	public double getCostoEtiqueta() {
		return costoEtiqueta;
	}
	public void setCostoEtiqueta(double costoEtiqueta) {
		this.costoEtiqueta = costoEtiqueta;
	}
 
 
	 
    
    

}
