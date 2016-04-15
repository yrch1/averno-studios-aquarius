/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.avernostudios.aquarius.bean;

import java.io.Serializable;
import java.util.HashMap;

import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
import es.avernostudios.aquarius.soap.helper.StoreInfoHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author yrch
 */
@Component
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
@Data
@Slf4j
public class SessionBean implements Serializable{


    private HashMap productInfoHash;
    private HashMap<Integer, Store> storeInfoHash;
    private boolean userLoggedIn = false;
    private int storeId = -2;


    public SessionBean() {
        init();
    }


    public void init() {

        //Iniciamos el hash que contiene los informacion de los productos
        setProductInfoHash(ProductInfoHelper.getInfo());

        //Iniciamos el hash que contiene los informacion de los productos

        setStoreInfoHash(StoreInfoHelper.getInfo());
    }
}
