package com.pablosrl.data;

public class PedidosDetalle {

	private Long id;
	private String tipComprobante;
	private String serComprobante;
	private String nroComprobante;
	private String codArticulo;
	private String codUm;
	private String cantidad;
	private String cantidadUb;
	private String precioUnitario;
	private String precioUnitarioCiva;
	private String porcDescuento;
	private String porcDescuentoVolumen;
	private String porcDescuentoCanal;
	private String porcDescuentoCategoria;
	private String porcDescuentoVisibilidad1;
	private String porcDescuentoVisibilidad2;
	private String porcDescuentoVisibilidad3;
	private String porcDescuentoCultural;
	private String porcDescuentoOri;

	private String descuentoCanal;
	private String descuentoCategoria;
	private String descuentoVisibilidad1;
	private String descuentoVisibilidad2;
	private String descuentoVisibilidad3;
	private String descuentoCultural;
	private String descuentoVolumen;

	private String totalIva;
	private String montoGravadas;
	private String montoExentas;
	private String montoTotal;
	private String idCabecera;
	private String montoDescuento;
	private String tipoBonificacion;
	private String nroBonificacion;
	private String esBonificacion;
	private String indEnvio;
	private String identificador;
	private String indStock;
	private String cantStock;
	private String codEmpresa;
    private String codLote;

    private String talles;
    private String colores;

	//atributo utilizado para saber su posicion en las listas.. para los movimientos. como en pedido y factura.
	private int posicionDetalleABM;

	private String nroEscalon;


	public PedidosDetalle() {

	}

	public String getPorcDescuentoOri() {
		return porcDescuentoOri;
	}

	public void setPorcDescuentoOri(String porcDescuentoVolumen) {
		this.porcDescuentoOri = porcDescuentoVolumen;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCodArticulo() {
		return codArticulo;
	}

	public void setCodArticulo(String codArticulo) {
		this.codArticulo = codArticulo;
	}

	public String getCodUm() {
		return codUm;
	}

	public void setCodUm(String codUm) {
		this.codUm = codUm;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getCantidadUb() {
		return cantidadUb;
	}

	public void setCantidadUb(String cantidadUb) {
		this.cantidadUb = cantidadUb;
	}

	public String getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(String precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public String getPrecioUnitarioCiva() {
		return precioUnitarioCiva;
	}

	public void setPrecioUnitarioCiva(String precioUnitarioCiva) {
		this.precioUnitarioCiva = precioUnitarioCiva;
	}

	public String getPorcDescuento() {
		return porcDescuento;
	}

	public void setPorcDescuento(String porcDescuento) {
		this.porcDescuento = porcDescuento;
	}

	public String getPorcDescuentoVolumen() {
		return porcDescuentoVolumen;
	}

	public void setPorcDescuentoVolumen(String porcDescuentoVolumen) {
		this.porcDescuentoVolumen = porcDescuentoVolumen;
	}

	public String getPorcDescuentoCanal() {
		return porcDescuentoCanal;
	}

	public void setPorcDescuentoCanal(String porcDescuentoCanal) {
		this.porcDescuentoCanal = porcDescuentoCanal;
	}

	public String getPorcDescuentoCategoria() {
		return porcDescuentoCategoria;
	}

	public void setPorcDescuentoCategoria(String porcDescuentoCategoria) {
		this.porcDescuentoCategoria = porcDescuentoCategoria;
	}

	public String getPorcDescuentoVisibilidad1() {
		return porcDescuentoVisibilidad1;
	}

	public void setPorcDescuentoVisibilidad1(String porcDescuentoVisibilidad1) {
		this.porcDescuentoVisibilidad1 = porcDescuentoVisibilidad1;
	}

	public String getPorcDescuentoVisibilidad2() {
		return porcDescuentoVisibilidad2;
	}

	public void setPorcDescuentoVisibilidad2(String porcDescuentoVisibilidad2) {
		this.porcDescuentoVisibilidad2 = porcDescuentoVisibilidad2;
	}

	public String getPorcDescuentoVisibilidad3() {
		return porcDescuentoVisibilidad3;
	}

	public void setPorcDescuentoVisibilidad3(String porcDescuentoVisibilidad3) {
		this.porcDescuentoVisibilidad3 = porcDescuentoVisibilidad3;
	}

	public String getPorcDescuentoCultural() {
		return porcDescuentoCultural;
	}

	public void setPorcDescuentoCultural(String porcDescuentoCultural) {
		this.porcDescuentoCultural = porcDescuentoCultural;
	}

	public String getTotalIva() {
		return totalIva;
	}

	public void setTotalIva(String totalIva) {
		this.totalIva = totalIva;
	}

	public String getMontoGravadas() {
		return montoGravadas;
	}

	public void setMontoGravadas(String montoGravadas) {
		this.montoGravadas = montoGravadas;
	}

	public String getMontoExentas() {
		return montoExentas;
	}

	public void setMontoExentas(String montoExentas) {
		this.montoExentas = montoExentas;
	}

	public String getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getIdCabecera() {
		return idCabecera;
	}

	public void setIdCabecera(String idCabecera) {
		this.idCabecera = idCabecera;
	}

	public String getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(String montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public String getTipoBonificacion() {
		return tipoBonificacion;
	}

	public void setTipoBonificacion(String tipoBonificacion) {
		this.tipoBonificacion = tipoBonificacion;
	}

	public String getNroBonificacion() {
		return nroBonificacion;
	}

	public void setNroBonificacion(String nroBonificacion) {
		this.nroBonificacion = nroBonificacion;
	}

	public String getEsBonificacion() {
		return esBonificacion;
	}

	public void setEsBonificacion(String esBonificacion) {
		this.esBonificacion = esBonificacion;
	}

	public String getIndEnvio() {
		return indEnvio;
	}

	public void setIndEnvio(String indEnvio) {
		this.indEnvio = indEnvio;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getIndStock() {
		return indStock;
	}

	public void setIndStock(String indStock) {
		this.indStock = indStock;
	}

	public String getCantStock() {
		return cantStock;
	}

	public void setCantStock(String cantStock) {
		this.cantStock = cantStock;
	}

	public String getCodEmpresa() {
		return codEmpresa;
	}

	public void setCodEmpresa(String codEmpresa) {
		this.codEmpresa = codEmpresa;
	}

	public int getPosicionDetalleABM() {
		return posicionDetalleABM;
	}

	public void setPosicionDetalleABM(int posicionDetalleABM) {
		this.posicionDetalleABM = posicionDetalleABM;
	}

	public String getNroEscalon() {
		return nroEscalon;
	}

	public void setNroEscalon(String nroEscalon) {
		this.nroEscalon = nroEscalon;
	}

	public String getDescuentoCanal() {
		return descuentoCanal;
	}

	public void setDescuentoCanal(String descuentoCanal) {
		this.descuentoCanal = descuentoCanal;
	}

	public String getDescuentoCategoria() {
		return descuentoCategoria;
	}

	public void setDescuentoCategoria(String descuentoCategoria) {
		this.descuentoCategoria = descuentoCategoria;
	}

	public String getDescuentoVisibilidad1() {
		return descuentoVisibilidad1;
	}

	public void setDescuentoVisibilidad1(String descuentoVisibilidad1) {
		this.descuentoVisibilidad1 = descuentoVisibilidad1;
	}

	public String getDescuentoVisibilidad2() {
		return descuentoVisibilidad2;
	}

	public void setDescuentoVisibilidad2(String descuentoVisibilidad2) {
		this.descuentoVisibilidad2 = descuentoVisibilidad2;
	}

	public String getDescuentoVisibilidad3() {
		return descuentoVisibilidad3;
	}

	public void setDescuentoVisibilidad3(String descuentoVisibilidad3) {
		this.descuentoVisibilidad3 = descuentoVisibilidad3;
	}

	public String getDescuentoCultural() {
		return descuentoCultural;
	}

	public void setDescuentoCultural(String descuentoCultural) {
		this.descuentoCultural = descuentoCultural;
	}

	public String getDescuentoVolumen() {
		return descuentoVolumen;
	}

	public void setDescuentoVolumen(String descuentoVolumen) {
		this.descuentoVolumen = descuentoVolumen;
	}

        public String getCodLote() {
            return codLote;
        }

        public void setCodLote(String codLote) {
            this.codLote = codLote;
        }


    	public String getTalles() {
    		return talles;
    	}

    	public void setTalles(String talles) {
    		this.talles = talles;
    	}

    	public String getColores() {
    		return colores;
    	}

    	public void setColores(String colores) {
    		this.colores = colores;
    	}


}
