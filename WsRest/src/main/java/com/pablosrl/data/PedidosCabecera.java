package com.pablosrl.data;

import java.util.List;

public class PedidosCabecera {

    private Long id;
    private String tipComprobante;
    private String serComprobante;
    private String nroComprobante;
    private String fecha;
    private String codSucursal;
    private String codCliente;
    private String codVendedor;
    private String codCondVenta;
    private String codListaPrecio;
    private String codMoneda;
    private String tipCambio;
    private String totComprobante;
    private String totGravadas;
    private String totExentas;
    private String totIva;
    private String totLineas;
    private String totDescuento;
    private String horaInicio;
    private String horaFin;
    private String noVenta;
    private String posicion;
    private String fechaRecepcion;
    private String indEnvio;
    private String indBorrado;
    private String horaBorrado;
    private String identificador;
    private String indCliente;
    private String codEmpresa;
    private String comentario;
    private String nroCaja;

    private List<PedidosDetalle> detalle;
    private String indVerifEscalon;

    private String tipoImpuesto; // guarda si el cliente su tipo de impuesto es (G)ravado o (E)xento, esto es auxiliar. no se guarda en la BD

    private String impresion;

    private String estado;

    public PedidosCabecera() {

    }

    public String getNroCaja() {
        return nroCaja;
    }

    public void setNroCaja(String nroCaja) {
        this.nroCaja = nroCaja;
    }

    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public String getImpresion() {
        return impresion;
    }

    public void setImpresion(String impresion) {
        this.impresion = impresion;
    }

    public String getIndVerifEscalon() {
        return indVerifEscalon;
    }

    public void setIndVerifEscalon(String indVerifEscalon) {
        this.indVerifEscalon = indVerifEscalon;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(String codVendedor) {
        this.codVendedor = codVendedor;
    }

    public String getCodCondVenta() {
        return codCondVenta;
    }

    public void setCodCondVenta(String codCondVenta) {
        this.codCondVenta = codCondVenta;
    }

    public String getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(String codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
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

    public String getTotComprobante() {
        return totComprobante;
    }

    public void setTotComprobante(String totComprobante) {
        this.totComprobante = totComprobante;
    }

    public String getTotGravadas() {
        return totGravadas;
    }

    public void setTotGravadas(String totGravadas) {
        this.totGravadas = totGravadas;
    }

    public String getTotExentas() {
        return totExentas;
    }

    public void setTotExentas(String totExentas) {
        this.totExentas = totExentas;
    }

    public String getTotIva() {
        return totIva;
    }

    public void setTotIva(String totIva) {
        this.totIva = totIva;
    }

    public String getTotLineas() {
        return totLineas;
    }

    public void setTotLineas(String totLineas) {
        this.totLineas = totLineas;
    }

    public String getTotDescuento() {
        return totDescuento;
    }

    public void setTotDescuento(String totDescuento) {
        this.totDescuento = totDescuento;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getNoVenta() {
        return noVenta;
    }

    public void setNoVenta(String noVenta) {
        this.noVenta = noVenta;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getIndEnvio() {
        return indEnvio;
    }

    public void setIndEnvio(String indEnvio) {
        this.indEnvio = indEnvio;
    }

    public String getIndBorrado() {
        return indBorrado;
    }

    public void setIndBorrado(String indBorrado) {
        this.indBorrado = indBorrado;
    }

    public String getHoraBorrado() {
        return horaBorrado;
    }

    public void setHoraBorrado(String horaBorrado) {
        this.horaBorrado = horaBorrado;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getIndCliente() {
        return indCliente;
    }

    public void setIndCliente(String indCliente) {
        this.indCliente = indCliente;
    }

    public String getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(String codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<PedidosDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PedidosDetalle> detalle) {
        this.detalle = detalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
