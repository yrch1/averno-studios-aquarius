/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius;

import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.bean.Store;
import com.gruporon2005.soap.helper.OrderHelper;
import com.gruporon2005.soap.magento.AssociativeEntity;
import com.gruporon2005.soap.magento.ComplexFilter;
import com.gruporon2005.soap.magento.Filters;
import com.gruporon2005.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import com.gruporon2005.soap.magento.MagentoServiceLocator;
import com.gruporon2005.soap.magento.SalesOrderEntity;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author yrch
 */
public class NewClass {

    private static Logger log = Logger.getLogger(NewClass.class);

    public void test_1() {
        Connection conexion = null;
        try {

            System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES,
                    "org.apache.naming");

            Context initContext = new InitialContext();

            initContext.createSubcontext("java:");
            initContext.createSubcontext("java:/comp");
            initContext.createSubcontext("java:/comp/env");
            initContext.createSubcontext("java:/comp/env/jdbc");

            Context envContext = (Context) initContext.lookup("java:/comp/env");

            assertTrue(envContext != null);

            // Construct DataSource
            MysqlDataSource ds = new MysqlDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/Aquarius");
            ds.setUser("soap");
            ds.setPassword("soap123");

            initContext.bind("java:/comp/env/jdbc/AquariusDB", ds);



            conexion = ds.getConnection();


            assertTrue(conexion != null);

            conexion.close(); // Return to connection pool
            conexion = null;  // Make sure we don't close it twice


        }
        catch (SQLException e) {
            log.fatal(e);
        }
        catch (NamingException e) {
            log.fatal(e);

        }
        finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            /*if (rs != null) {
            try {
            rs.close();
            } catch (SQLException e) {
            ;
            }
            rs = null;
            }
            if (stmt != null) {
            try {
            stmt.close();
            } catch (SQLException e) {
            ;
            }
            stmt = null;
            }*/
            if (conexion != null) {
                try {
                    conexion.close();
                }
                catch (SQLException e) {
                    log.error(e);
                }
                conexion = null;
            }



        }
    }

    public void test2() {
        SalesOrderEntity orderInfo = OrderHelper.getInstance().getOrderInfo("100000289", "http://tienda.eleconomista.es/index.php/api/v2_soap/index/");
        log.error(orderInfo.getCustomer_firstname());
        assertTrue(orderInfo != null);
    }

    @Ignore
    @Test
    public void testYQUDE() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            assertTrue(sessionId != null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void testECONO() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.colomerandsons.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            assertTrue(sessionId != null);
            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[3];
            cmp[0] = new ComplexFilter("store_id", new AssociativeEntity("eq", "4"));
            cmp[1] = new ComplexFilter("status", new AssociativeEntity("eq", "Pending"));
            cmp[2] = new ComplexFilter("order_id", new AssociativeEntity("gt", "1698"));


            filtros.setComplex_filter(cmp);
            List<SalesOrderEntity> ventas = Arrays.asList(magento.salesOrderList(sessionId, filtros));
            System.out.print("prueba");
            for (SalesOrderEntity venta : ventas) {
                System.out.println(venta.getOrder_id());
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void test3() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        String ids = "100000289,100000290";
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[1];
            cmp[0] = new ComplexFilter("increment_id", new AssociativeEntity("in", ids));

            filtros.setComplex_filter(cmp);
            SalesOrderEntity[] orderList = magento.salesOrderList(sessionId, filtros);
            assertTrue(orderList.length == 2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    @Ignore
    @Test
    public void test5() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento;
        String ids = "100000289,100000290";
        try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");

            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[1];
            cmp[0] = new ComplexFilter("increment_id", new AssociativeEntity("in", ids));

            filtros.setComplex_filter(cmp);
            SalesOrderEntity[] orderList = magento.salesOrderList(sessionId, null);
            assertTrue(orderList.length > 2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            assertTrue(false);
        }

    }

    

    @Test
    public void test6() {
        try {

            OutputStream out = new FileOutputStream("prueba.xls");
            String[] orderIds = new String[]{"400001519","400001516,400001515,400001514,400001513,400001512,400001510"};
            //String[] orderIds = new String[]{"400001510","400001519"};
            String apiUrl = "http://www.gustobycolomer.com/index.php/api/v2_soap/index/";
            SessionBean sessionBean = new SessionBean();
            sessionBean.setStoreId(22);
            Store store = new Store(22, "MMX1", "MEMEMUEROPORUNO.com",apiUrl,4);
            sessionBean.setStoreInfoHash(new HashMap<Integer,Store>() );
            sessionBean.getStoreInfoHash().put(22,store);
            OrderHelper.getInstance().exportOrder(out, orderIds, sessionBean, apiUrl,true);
        }
        catch (Exception e) {
            log.debug("Exception",e);

        }
    }
}
