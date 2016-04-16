/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.avernostudios.aquarius.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import es.avernostudios.aquarius.jpa.repositories.ProductRepository;
import es.avernostudios.aquarius.jpa.repositories.StoreRepository;
import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
import es.avernostudios.aquarius.soap.helper.StoreInfoHelper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author yrch
 */
@Component()
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS, value="session")
@Data
@Slf4j
public class SessionBean implements Serializable{
    private Map<String,Product> productInfoHash;
    private Map<Integer, Store> storeInfoHash;
    private boolean userLoggedIn = false;
    private int storeId = 1;
    private boolean isNew=true;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository storeRepository;

    public void init() {

        //Iniciamos el hash que contiene los informacion de los productos

        setProductInfoHash(ProductInfoHelper.getInstance().getInfo(productRepository.findAll()));

        //Iniciamos el hash que contiene los informacion de los productos

        setStoreInfoHash(StoreInfoHelper.getInfo(storeRepository.findAll()));
        isNew=false;
    }
}
