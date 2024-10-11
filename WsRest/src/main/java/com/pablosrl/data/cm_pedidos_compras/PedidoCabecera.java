package com.pablosrl.data.cm_pedidos_compras;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.pablosrl.util.AppUtils;

//com.pablosrl.data.PedidoCabecera.java
public class PedidoCabecera {
	
	private String codEmpresa;
    private String tipComprobante;
    private String serComprobante;
    private Integer  nroComprobante;  // NUMBER(8)
    
    private String codMoneda;
    private BigDecimal tipCambio;
    private BigDecimal cambioMonedaPrecio; 
    
    private String codSucursal;
    
    private String codSucursalPed;
    private String descSucursalPed;
    
    private String codProveedor;
    
    private String codCondicionCompra;
    
    private String codCliente;
    private String referencia;
    
    
    
    
    
    /*private LocalDate fecComprobante;
     
    private BigDecimal totComprobante;  // NUMBER(18,3)
    private BigDecimal totGravadas;
    private BigDecimal totExentas;
    private BigDecimal totIva;
    private BigDecimal descuento;
    
    private String verificadora;
    private String transporte;
    private String via;
    private Date fecEmbarque;
    private Date fecConfirmacion;
    private String estado;
    private LocalDate fecEstado;
    private String codUsuario;
    private Date fecAlta;
    private String anulado;  // VARCHAR2(1) interpretado como String
     // NUMBER(9,4)
    private String tipComprobanteRef;
    private String serComprobanteRef;
    private int nroComprobanteRef;  // NUMBER(8)
    private String referencia;
    private String indIvaIncluido;  // VARCHAR2(1) interpretado como String
    private BigDecimal totalPeso;  // NUMBER
    private String codTecnico;
    private String indRecibido;  // VARCHAR2(1) interpretado como String
    private String deposito;
    private Date fecLlegada;
    
    private String entrega;
    private String etiqueta;
    private BigDecimal costoEtiqueta;  // NUMBER(10,3)
    */
    
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
	public Integer  getNroComprobante() {
		return nroComprobante;
	}
	public void setNroComprobante(Integer  nroComprobante) {
		this.nroComprobante = nroComprobante;
	}
	
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public BigDecimal getTipCambio() {
		return tipCambio;
	}
	public void setTipCambio(BigDecimal tipCambio) {
		this.tipCambio = tipCambio;
	}
	public BigDecimal getCambioMonedaPrecio() {
		return cambioMonedaPrecio;
	}
	public void setCambioMonedaPrecio(BigDecimal cambioMonedaPrecio) {
		this.cambioMonedaPrecio = cambioMonedaPrecio;
	}
	

	public String getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
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
	
	public String getCodCliente() {
		return codCliente;
	}
	
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
    
}
