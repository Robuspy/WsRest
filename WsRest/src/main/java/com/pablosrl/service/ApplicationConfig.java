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
        
        
        resources.add(com.pablosrl.controllers.ExampleService.class);
        //Base - Sistema Base
        resources.add(com.pablosrl.controllers.WsUsuario.class);
        resources.add(com.pablosrl.controllers.WsSucursales.class);
        resources.add(com.pablosrl.controllers.WsMonedas.class);
         
        //Compras
        resources.add(com.pablosrl.controllers.compras.WsCondicionesCompra.class);
        resources.add(com.pablosrl.controllers.WsProveedores.class);
        resources.add(com.pablosrl.controllers.WsCmPedidos.class);
        
        
        
    }



}
