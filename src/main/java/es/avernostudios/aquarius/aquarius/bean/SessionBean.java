/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.avernostudios.aquarius.aquarius.bean;

import java.util.HashMap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author yrch
 */
public class SessionBean {

    private static Logger log = Logger.getLogger(SessionBean.class);

    private HashMap productInfoHash;
    private HashMap<Integer, Store> storeInfoHash;
    private boolean userLoggedIn = false;

    private int storeId = -1;


    public SessionBean() {

    }

    /**
     * @return the productInfoHash
     */
    public HashMap getProductInfoHash() {
        return productInfoHash;
    }

    /**
     * @param productInfoHash the productInfoHash to set
     */
    public void setProductInfoHash(HashMap productInfoHash) {
        this.productInfoHash = productInfoHash;
    }

    /**
     * @return the storeInfoHash
     */
    public HashMap<Integer, Store> getStoreInfoHash() {
        return storeInfoHash;
    }

    /**
     * @param storeInfoHash the storeInfoHash to set
     */
    public void setStoreInfoHash(HashMap<Integer, Store> storeInfoHash) {
        this.storeInfoHash = storeInfoHash;
    }

    /**
     * @return the storeId
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * @param storeId the storeId to set
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

   /**
     * @return the userLoggedIn
     */
    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    /**
     * @param userLoggedIn the userLoggedIn to set
     */
    public void setUserLoggedIn(boolean userLoggedIn) {
        this.userLoggedIn = userLoggedIn;
    }

    private Session buildSession() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory().getCurrentSession();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            log.fatal("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() {
        return buildSession();
    }




}
