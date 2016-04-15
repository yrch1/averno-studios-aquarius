/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.avernostudios.aquarius;

import es.avernostudios.aquarius.bean.SessionBean;
import es.avernostudios.aquarius.bean.Store;
import es.avernostudios.aquarius.soap.helper.OrderHelper;
import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
import es.avernostudios.aquarius.soap.magento.AssociativeEntity;
import es.avernostudios.aquarius.soap.magento.CatalogProductEntity;
import es.avernostudios.aquarius.soap.magento.CatalogProductReturnEntity;
import es.avernostudios.aquarius.soap.magento.ComplexFilter;
import es.avernostudios.aquarius.soap.magento.Filters;
import es.avernostudios.aquarius.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import es.avernostudios.aquarius.soap.magento.MagentoServiceLocator;
import es.avernostudios.aquarius.soap.magento.SalesOrderEntity;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.hibernate.SessionFactory;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author yrch
 */
public class NewClass {

    private static Logger log = Logger.getLogger(NewClass.class);
    SessionFactory sessionFactory = null;

    @Before
    public void setUp() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml

            File cfg = new File("src/test/java/com/gruporon2005/aquarius/hibernate.cfg.test.xml");

            System.out.println(cfg.getAbsoluteFile());
            log.debug(cfg.getAbsolutePath());

            sessionFactory = new Configuration().configure(cfg).buildSessionFactory();

            assertTrue(sessionFactory != null);
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

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
            log.fatal("Exception",e);
        }
        catch (NamingException e) {
            log.fatal("Exception",e);

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
                    log.error("Exception",e);
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
            log.error("Exception",ex);
            assertTrue(false);
        }

    }

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
            log.error("Exception",ex);
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
            log.error("Exception",ex);
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
            log.error("Exception",ex);
            assertTrue(false);
        }

    }

    
    @Test
    public void test6() {
        try {

            OutputStream out = new FileOutputStream("prueba.xls");
            //String[] orderIds = new String[]{"200000610,200000609,200000602,200000601,200000591"};
            //String[] orderIds = new String[]{"400000753"};
            String[] orderIds = new String[]{"100031870","100031868","100031865","100000289","100000290"};
            //String[] orderIds = new String[]{"400001510","400001519"};
             
            
             
            String apiUrl = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
            SessionBean sessionBean = new SessionBean();
            sessionBean.setStoreId(20);
            Store store = new Store(20, "GBC", "GUSTO", apiUrl, 2);
            sessionBean.setStoreInfoHash(new HashMap<Integer, Store>());



            sessionBean.getStoreInfoHash().put(20, store);
            sessionBean.setProductInfoHash(ProductInfoHelper.getInfo(sessionFactory.getCurrentSession()));
            OrderHelper.getInstance().exportOrder(out, orderIds, sessionBean, apiUrl, true);
        }
        catch (Exception e) {
            log.debug("Exception", e);

        }
    }
    
    @Ignore
    @Test
    public void testCatalogProductList() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        //String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        String handlerPortEndpointAddress = "http://www.colomerandsons.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento = null;
        String sessionId = "";
		try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            sessionId  = magento.login("soap", "test123");
            

			
            	 CatalogProductEntity[] result = magento.catalogProductList(sessionId, null, null);
                assertTrue(result!=null && result.length>0);
          
        }
        catch (Exception ex) {
            log.error("Exception",ex);
            assertTrue(false);
        }finally{
        	if(sessionId != null && sessionId.length()>0){
        		try {
					magento.endSession(sessionId);
				} catch (RemoteException e) {
					log.error("Exception",e);
				}
        	}
        }

    }
    
    @Ignore
    @Test
    public void testCatalogProductInfoYQUDE() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento = null;
        String sessionId = "";
		try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            sessionId  = magento.login("soap", "test123");
            
            List<String> listaItem = new ArrayList<String>();
            listaItem.add("ROLIO");
            listaItem.add("SPACESCO");
			for(String item : listaItem ){
            	CatalogProductReturnEntity result = magento.catalogProductInfo(sessionId, item, null, null, null);
                assertTrue(result!=null);
            }
            
            
            
             
        }
        catch (Exception ex) {
            log.error("Exception",ex);
            assertTrue(false);
        }finally{
        	if(sessionId != null && sessionId.length()>0){
        		try {
					magento.endSession(sessionId);
				} catch (RemoteException e) {
					log.error("Exception",e);
				}
        	}
        }

    }
    
    @Ignore
    @Test
    public void testCatalogProductInfoCOLOMER() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.colomerandsons.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento = null;
        String sessionId = "";
		try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            sessionId  = magento.login("soap", "test123");
            
            List<String> listaItem = new ArrayList<String>();
            listaItem.add("AIRFORCES");
			for(String item : listaItem ){
            	CatalogProductReturnEntity result = magento.catalogProductInfo(sessionId, item, "4", null, null);
                assertTrue(result!=null);
            }
        }
        catch (Exception ex) {
            log.error("Exception",ex);
            assertTrue(false);
        }finally{
        	if(sessionId != null && sessionId.length()>0){
        		try {
					magento.endSession(sessionId);
				} catch (RemoteException e) {
					log.error("Exception",e);
				}
        	}
        }

    }
    
    @Ignore
    @Test
    public void testCatalogProductInfoSLANKET() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.slanket.es/api/v2_soap?wsdl=1";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento = null;
        String sessionId = "";
		try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            sessionId  = magento.login("soap", "test123");
            
            CatalogProductReturnEntity result = magento.catalogProductInfo(sessionId, "CANGRA", null, null, null);
            assertTrue(result!=null);
            result = magento.catalogProductInfo(sessionId, "HOTDOG", null, null, null);
            assertTrue(result!=null);
        }
        catch (Exception ex) {
            log.error("Exception",ex);
            assertTrue(false);
        }finally{
        	if(sessionId != null && sessionId.length()>0){
        		try {
					magento.endSession(sessionId);
				} catch (RemoteException e) {
					log.error("Exception",e);
				}
        	}
        }

    }
    
    @Ignore
    @Test
    public void testSalesOrderList() {
        MagentoServiceLocator service = new MagentoServiceLocator();
        String handlerPortEndpointAddress = "http://www.yoquierounodeesos.com/index.php/api/v2_soap/index/";
        service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
        Mage_Api_Model_Server_V2_HandlerPortType magento = null;
        String sessionId = "";
		try {

            magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            sessionId  = magento.login("soap", "test123");
            
            ComplexFilter cmp[] = new ComplexFilter[1];
            cmp[0] = new ComplexFilter("created_at", new AssociativeEntity("gt", "2014-01-01 00:00:00"));
            
            Filters filtros = new Filters();
            filtros.setComplex_filter(cmp);
            
            
           // SalesOrderEntity[]  result = magento.salesOrderList(sessionId, null);
            SalesOrderEntity[]  result2 = magento.salesOrderList(sessionId, filtros);
            //log.debug("El numero de pedidos es : " + result.length);
            //assertTrue(result!=null);
            assertTrue(result2!=null);
            log.debug("El numero de pedidos ahora es : " + result2.length);
            //assertTrue(result.length>result2.length);
        }
        catch (Exception ex) {
            log.error("Exception",ex);
            assertTrue(false);
        }finally{
        	if(sessionId != null && sessionId.length()>0){
        		try {
					magento.endSession(sessionId);
				} catch (RemoteException e) {
					log.error("Exception",e);
				}
        	}
        }

    }
    
    
    
    
    
}
