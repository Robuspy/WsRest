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
    private String codSucursal;
    private String codSucursalPed;
    private String descSucursalPed;
    /*private LocalDate fecComprobante;
    private String codProveedor;
    private String codCondicionCompra;
    private BigDecimal totComprobante;  // NUMBER(18,3)
    private BigDecimal totGravadas;
    private BigDecimal totExentas;
    private BigDecimal totIva;
    private BigDecimal descuento;
    private String codMoneda;
    private BigDecimal tipCambio;
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
    private BigDecimal cambioMonedaPrecio;  // NUMBER(9,4)
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
	/*// Método para obtener fecComprobante como LocalDate
    public LocalDate getFecComprobante() {
        return fecComprobante;
    }
    public void setFecComprobante(LocalDate fecComprobante) {
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
	public BigDecimal getTotComprobante() {
		return totComprobante;
	}
	public void setTotComprobante(BigDecimal totComprobante) {
		this.totComprobante = totComprobante;
	}
	public BigDecimal getTotGravadas() {
		return totGravadas;
	}
	public void setTotGravadas(BigDecimal totGravadas) {
		this.totGravadas = totGravadas;
	}
	public BigDecimal getTotExentas() {
		return totExentas;
	}
	public void setTotExentas(BigDecimal totExentas) {
		this.totExentas = totExentas;
	}
	public BigDecimal getTotIva() {
		return totIva;
	}
	public void setTotIva(BigDecimal totIva) {
		this.totIva = totIva;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
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
	public LocalDate getFecEstado() {
        return fecEstado;
    }

    public void setFecEstado(LocalDate fecEstado) {
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
	public String isAnulado() {
		return anulado;
	}
	public void setAnulado(String anulado) {
		this.anulado = anulado;
	}
	public BigDecimal getCambioMonedaPrecio() {
		return cambioMonedaPrecio;
	}
	public void setCambioMonedaPrecio(BigDecimal cambioMonedaPrecio) {
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
	public int getNroComprobanteRef() {
		return nroComprobanteRef;
	}
	public void setNroComprobanteRef(int nroComprobanteRef) {
		this.nroComprobanteRef = nroComprobanteRef;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String isIndIvaIncluido() {
		return indIvaIncluido;
	}
	public void setIndIvaIncluido(String indIvaIncluido) {
		this.indIvaIncluido = indIvaIncluido;
	}
	public BigDecimal getTotalPeso() {
		return totalPeso;
	}
	public void setTotalPeso(BigDecimal totalPeso) {
		this.totalPeso = totalPeso;
	}
	public String getCodTecnico() {
		return codTecnico;
	}
	public void setCodTecnico(String codTecnico) {
		this.codTecnico = codTecnico;
	}
	public String isIndRecibido() {
		return indRecibido;
	}
	public void setIndRecibido(String indRecibido) {
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
	public BigDecimal getCostoEtiqueta() {
		return costoEtiqueta;
	}
	public void setCostoEtiqueta(BigDecimal costoEtiqueta) {
		this.costoEtiqueta = costoEtiqueta;
	}
    
    */
    
}
