package com.pablosrl.data;

import java.util.List;

public class Recibos {

	private Long id;
	private String sucursal_id;
	private String tipoRecibo;
	private String serieRecibo;
	private String numero;
	private String fecha;
	private String cliente_id;
	private String cobrador_id;
	private String moneda_id;
	private String tipoCambio;
	private String comentario;
	private String movCaja_id;
	private String estado;
	private String total;
	private String indEnvio;
	public String nombreCliente;
	private List<RecibosDetalle> detalles;
	private List<FormaCobro> formasCobros;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSucursal_id() {
		return sucursal_id;
	}

	public void setSucursal_id(String sucursal_id) {
		this.sucursal_id = sucursal_id;
	}

	public String getTipoRecibo() {
		return tipoRecibo;
	}

	public void setTipoRecibo(String tipoRecibo) {
		this.tipoRecibo = tipoRecibo;
	}

	public String getSerieRecibo() {
		return serieRecibo;
	}

	public void setSerieRecibo(String serieRecibo) {
		this.serieRecibo = serieRecibo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(String cliente_id) {
		this.cliente_id = cliente_id;
	}

	public String getCobrador_id() {
		return cobrador_id;
	}

	public void setCobrador_id(String cobrador_id) {
		this.cobrador_id = cobrador_id;
	}

	public String getMoneda_id() {
		return moneda_id;
	}

	public void setMoneda_id(String moneda_id) {
		this.moneda_id = moneda_id;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getMovCaja_id() {
		return movCaja_id;
	}

	public void setMovCaja_id(String movCaja_id) {
		this.movCaja_id = movCaja_id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getIndEnvio() {
		return indEnvio;
	}

	public void setIndEnvio(String indEnvio) {
		this.indEnvio = indEnvio;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<RecibosDetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<RecibosDetalle> detalles) {
		this.detalles = detalles;
	}

	public List<FormaCobro> getFormasCobros() {
		return formasCobros;
	}

	public void setFormasCobros(List<FormaCobro> formasCobros) {
		this.formasCobros = formasCobros;
	}
}
