package com.pablosrl.data;

import java.util.List;

public class CajasCabecera {

	private Long id;
	private String nroMovCaj;
	private String fecha;
	private String total;
	private String codMoneda;
	private String tipCambio;
	private String codCliente;
	private String tipPlanilla;
	private String nroPlanilla;
	private String latitud;
	private String longitud;
	private String horaCarga;
	private String indEnvio;
	private String nroRecibo;
	private String idRegistro;
	private String nombreCliente;
	private String codEmpresa;
	private String codSucursal;

	private List<CajasComprobantes> comprobantesList;
	private List<FormaCobro> formaCobroList;

	public CajasCabecera(){

	}







	public String getCodSucursal() {
		return codSucursal;
	}







	public void setCodSucursal(String codSucursal) {
		this.codSucursal = codSucursal;
	}







	public String getCodEmpresa() {
		return codEmpresa;
	}



	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNroMovCaj() {
		return nroMovCaj;
	}

	public void setNroMovCaj(String nroMovCaj) {
		this.nroMovCaj = nroMovCaj;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCodMoneda() {
		return codMoneda;
	}

	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	public String getTipCambio() {
		return tipCambio;
	}

	public void setTipCambio(String tipCambio) {
		this.tipCambio = tipCambio;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getTipPlanilla() {
		return tipPlanilla;
	}

	public void setTipPlanilla(String tipPlanilla) {
		this.tipPlanilla = tipPlanilla;
	}

	public String getNroPlanilla() {
		return nroPlanilla;
	}

	public void setNroPlanilla(String nroPlanilla) {
		this.nroPlanilla = nroPlanilla;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getHoraCarga() {
		return horaCarga;
	}

	public void setHoraCarga(String horaCarga) {
		this.horaCarga = horaCarga;
	}

	public String getIndEnvio() {
		return indEnvio;
	}

	public void setIndEnvio(String indEnvio) {
		this.indEnvio = indEnvio;
	}

	public String getNroRecibo() {
		return nroRecibo;
	}

	public void setNroRecibo(String nroRecibo) {
		this.nroRecibo = nroRecibo;
	}

	public String getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(String idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public List<CajasComprobantes> getComprobantesList() {
		return comprobantesList;
	}

	public void setComprobantesList(List<CajasComprobantes> comprobantesList) {
		this.comprobantesList = comprobantesList;
	}

	public List<FormaCobro> getFormaCobroList() {
		return formaCobroList;
	}

	public void setFormaCobroList(List<FormaCobro> formaCobroList) {
		this.formaCobroList = formaCobroList;
	}


}
