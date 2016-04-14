package es.avernostudios.aquarius.soap.helper;

import es.avernostudios.aquarius.aquarius.bean.Product;
import es.avernostudios.aquarius.aquarius.util.HibernateUtil;
import es.avernostudios.aquarius.soap.magento.CatalogInventoryStockItemEntity;
import es.avernostudios.aquarius.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import es.avernostudios.aquarius.soap.magento.MagentoServiceLocator;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * @author yrch
 */
public class ProductInfoHelper {

    private static Logger log = Logger.getLogger(ProductInfoHelper.class);

    // El constructor privado no permite que se genere un constructor por defecto
    // (con mismo modificador de acceso que la definicion de la clase)
    private ProductInfoHelper() {
    }

    public static ProductInfoHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private final static ProductInfoHelper instance = new ProductInfoHelper();
    }

    public HashMap<String, Product> getInfo() {
        HashMap<String, Product> result = new HashMap<String, Product>();


        try {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            List<Product> listaProductos = session.createQuery("from Product").list();

            for (Product product : listaProductos) {

                result.put(product.getSku(), product);

            }

            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            log.error("Exception",e);
        }


        return result;
    }

    public HashMap<String, Product> getInfo(Session session) {
        HashMap<String, Product> result = new HashMap<String, Product>();


        try {
            session.beginTransaction();

            List<Product> listaProductos = session.createQuery("from Product").list();

            for (Product product : listaProductos) {

                result.put(product.getSku(), product);

            }

            session.getTransaction().commit();

        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            log.error("Exception",e);
        }


        return result;
    }

    /**
     *
     * @param session
     * @param offset
     * @param pageSize
     * @param productsInfoListSize
     * @return
     */
    public List<Product> getInfoList(int offset, int pageSize, int[] productsInfoListSize, String fieldSort, String ascDesc) {
        List<Product> result = new ArrayList<Product>();

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();


            Query q = session.createQuery("from Product product order by product." + fieldSort + " " + ascDesc);
            q.setFirstResult(offset);
            q.setMaxResults(pageSize);
            result = q.list();

            q = session.createQuery("select count(product) from Product product");
            productsInfoListSize[0] = ((Long) q.uniqueResult()).intValue();


            session.getTransaction().commit();

        } catch (Exception e) {
            log.error("Exception",e);
        }

        return result;
    }

    public Product getProductInfo(String sku) {
        Product result = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {

            session.beginTransaction();
            result = (Product) session.createQuery("from Product as product where product.sku = ?").setString(0, sku).uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Exception",e);
        }
        return result;
    }

    /**
     *
     * @param session
     * @param product
     * @return
     */
    public int add(Product product) {
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            session.save(product);

            session.getTransaction().commit();

            result = 1;
        } catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Exception",e);
        }
        return result;

    }

    /**
     * 
     * @param session
     * @param sku
     * @return
     */
    public int delete(String sku) {
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String hqlDelete = "delete Product product where product.sku = :sku";
            result = session.createQuery(hqlDelete).setString("sku", sku).executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Exception",e);
        }
        return result;

    }

    /**
     *
     * @param product
     * @return
     */
    public int update(Product product) {
        int result = -1;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            String hqlUpdate = "update Product p set p.owner = :owner, p.additional = :additional, p.supplier = :supplier where p.sku = :sku";
            result = session.createQuery(hqlUpdate).setString("sku", product.getSku()).setString("owner", product.getOwner()).setString("additional", product.getAdditional()).setString("supplier", product.getSupplier()).executeUpdate();

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Exception",e);
        }
        return result;
    }

    /**
     * 
     * @param productsIds
     * @param handlerPortEndpointAddress
     * @return
     */
    public List<CatalogInventoryStockItemEntity> getStockList(String[] productsIds, String handlerPortEndpointAddress) {


        List<CatalogInventoryStockItemEntity> result = null;
        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            CatalogInventoryStockItemEntity[] temp = magento.catalogInventoryStockItemList(sessionId, productsIds);

            result = Arrays.asList(temp);

            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("Error en getOrderList", e);

        }
        return result;
    }

    /**
     *
     * @param sku
     * @return
     */
    public List<Product> searchProduct(String sku) {
        List<Product> result = null;



        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();

            Criteria crit = session.createCriteria(Product.class);
            crit.add(Restrictions.like("sku", "%"+sku + "%"))
            .addOrder( Order.asc("sku") );

            result = crit.list();


            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Exception",e);
        }

        return result;

    }
}
