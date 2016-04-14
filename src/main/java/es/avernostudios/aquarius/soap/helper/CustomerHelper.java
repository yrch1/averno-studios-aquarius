package es.avernostudios.aquarius.soap.helper;


import es.avernostudios.aquarius.soap.magento.CustomerCustomerEntity;
import es.avernostudios.aquarius.soap.magento.Mage_Api_Model_Server_V2_HandlerPortType;
import es.avernostudios.aquarius.soap.magento.MagentoServiceLocator;
import org.apache.log4j.Logger;


public class CustomerHelper {

    private CustomerHelper() {
    }

    public static CustomerHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {

        private final static CustomerHelper instance = new CustomerHelper();
    }
    /**
     *
     */
    private static Logger log = Logger.getLogger(CustomerHelper.class);


        /**
     * Obtiene la informacion del usuario
     * @param id identificador del usuario
     * @return
     */
    public CustomerCustomerEntity getCustomerInfo(String id, String handlerPortEndpointAddress) {
        CustomerCustomerEntity customerInfo = null;
        try {

            MagentoServiceLocator service = new MagentoServiceLocator();
            service.setMage_Api_Model_Server_V2_HandlerPortEndpointAddress(handlerPortEndpointAddress);
            Mage_Api_Model_Server_V2_HandlerPortType magento = service.getMage_Api_Model_Server_V2_HandlerPort();

            String sessionId = magento.login("soap", "test123");
            customerInfo = magento.customerCustomerInfo(sessionId, Integer.parseInt(id), null);


            magento.endSession(sessionId);

        } catch (Exception e) {
            log.error("Error en getCustomerInfo para el usuario " + id, e);

        }

        return customerInfo;
    }
}
