package com.gruporon2005.soap.helper;

import com.gruporon2005.aquarius.bean.Store;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * 
 * @author yrch
 */
public class StoreInfoHelper {

    private static Logger log = Logger.getLogger(StoreInfoHelper.class);

    // El constructor privado no permite que se genere un constructor por defecto
    // (con mismo modificador de acceso que la definicion de la clase)
    private StoreInfoHelper() {
    }

    public static StoreInfoHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private final static StoreInfoHelper instance = new StoreInfoHelper();
    }

    public HashMap<Integer, Store> getInfo(Session session) {
        HashMap<Integer, Store> result = new HashMap<Integer, Store>();

        try {

            session.beginTransaction();



            List<Store> intermedia = session.createQuery("from Store").list();
            for (Store store : intermedia) {
                result.put(store.getId(), store);
            }



            session.getTransaction().commit();

        } catch (Exception e) {
            log.error(e);
        }



        return result;
    }
}
