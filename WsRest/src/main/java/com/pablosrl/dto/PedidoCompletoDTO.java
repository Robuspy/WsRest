package com.pablosrl.dto;

import com.pablosrl.data.cm_pedidos_compras.PedidoCabecera;
import com.pablosrl.data.cm_pedidos_compras.PedidoDetalle;

import java.util.List;

public class PedidoCompletoDTO {
    private PedidoCabecera cabecera;
    private List<PedidoDetalle> detalles;

    // Getters y Setters
    public PedidoCabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(PedidoCabecera cabecera) {
        this.cabecera = cabecera;
    }

    public List<PedidoDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PedidoDetalle> detalles) {
        this.detalles = detalles;
    }
}
