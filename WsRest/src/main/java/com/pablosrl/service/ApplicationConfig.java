package com.pablosrl.service;

import java.util.Set;

import javax.ws.rs.core.Application;

/**
 *
 * @author Abel
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        /*resources.add(com.pablosrl.service.WsAlerta.class);*/
        resources.add(com.pablosrl.service.WsArticulos.class);
        
        resources.add(com.pablosrl.service.WsArticuloRubro.class);
        resources.add(com.pablosrl.service.WsArticuloMarca.class);
        resources.add(com.pablosrl.service.WsFamiliaArticulo.class);
        resources.add(com.pablosrl.service.WsArticuloLinea.class);
        resources.add(com.pablosrl.service.WsArticuloGrupo.class);
        resources.add(com.pablosrl.service.WsArticuloTemporada.class);
        
        resources.add(com.pablosrl.service.WsArticulosDetalle.class);
        resources.add(com.pablosrl.service.WsArticulosProCab.class);
        resources.add(com.pablosrl.service.WsArticulosProDet.class);
        resources.add(com.pablosrl.service.WsBancos.class);
        resources.add(com.pablosrl.service.WsCaja.class);
        resources.add(com.pablosrl.service.WsCiudades.class);
        resources.add(com.pablosrl.service.WsClientes.class);
        resources.add(com.pablosrl.service.WsClientesCv.class);
        resources.add(com.pablosrl.service.WsCondicionVenta.class);
        resources.add(com.pablosrl.service.WsDepartamentos.class);
        resources.add(com.pablosrl.service.WsExistencia.class);
        /*resources.add(com.pablosrl.service.WsFactura.class);*/
        
        resources.add(com.pablosrl.service.WsImagenes.class);
        resources.add(com.pablosrl.service.WsListaPrecioCab.class);
        resources.add(com.pablosrl.service.WsListaPrecioDet.class);
        resources.add(com.pablosrl.service.WsLotes.class);
        resources.add(com.pablosrl.service.WsTalles.class);
        resources.add(com.pablosrl.service.WsColores.class);
        resources.add(com.pablosrl.service.WsMesas.class);
        resources.add(com.pablosrl.service.WsMonedas.class);
        resources.add(com.pablosrl.service.WsNoVenta.class);
        resources.add(com.pablosrl.service.WsParametroGeneral.class);
        resources.add(com.pablosrl.service.WsParametrosGenerales.class);
        resources.add(com.pablosrl.service.WsPedido.class);
        resources.add(com.pablosrl.service.WsPersona.class);
        resources.add(com.pablosrl.service.WsPlanillaCabecera.class);
        resources.add(com.pablosrl.service.WsPlanillaCobranzaCab.class);
        resources.add(com.pablosrl.service.WsPlanillaDetalle.class);
        resources.add(com.pablosrl.service.WsPrecioEspMarca.class);
        resources.add(com.pablosrl.service.WsRecibos.class);
        resources.add(com.pablosrl.service.WsRegistroId.class);
        resources.add(com.pablosrl.service.WsRelaciones.class);
        resources.add(com.pablosrl.service.WsSaldoClientes.class);
        resources.add(com.pablosrl.service.WsSectorEcon.class);
        resources.add(com.pablosrl.service.WsSubTipoTrans.class);
        resources.add(com.pablosrl.service.WsSubTipoTransRec.class);
        resources.add(com.pablosrl.service.WsSucursal.class);
        resources.add(com.pablosrl.service.WsSucursales.class);
        resources.add(com.pablosrl.service.WsTalonarios.class);
        resources.add(com.pablosrl.service.WsTipoArticulo.class);
        resources.add(com.pablosrl.service.WsUbicacion.class);
        resources.add(com.pablosrl.service.WsUnidadMedida.class);
        resources.add(com.pablosrl.service.WsUsuario.class);
        resources.add(com.pablosrl.service.ExampleService.class);
        resources.add(com.pablosrl.service.WsChequesPendientes.class);
        
        
        
        
    }



}
