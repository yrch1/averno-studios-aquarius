/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.action.sales;

import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.struts.action.GenericAction;
import com.gruporon2005.soap.helper.CustomerHelper;
import com.gruporon2005.soap.helper.OrderHelper;
import com.gruporon2005.soap.magento.CustomerCustomerEntity;
import com.gruporon2005.soap.magento.SalesOrderEntity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author yrch
 */
public class OrderAction extends GenericAction {

    private static Logger log = Logger.getLogger(OrderAction.class);

    /**
     * @param mapping mapping
     * @param form form
     * @param request request
     * @param response response
     * @throws Exception Exception
     * @return ActionForward
     *
     */
    @Override
    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward result = mapping.findForward("success");
        try {
            String id = (String) request.getParameter("id");
            SalesOrderEntity orderInfo = null;
            CustomerCustomerEntity customerInfo = null;
            if (!id.equalsIgnoreCase("")) {


                SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
                int storeId = -1;

                if (sessionBean != null) {
                    storeId = sessionBean.getStoreId();
                } else {
                    result = mapping.findForward("error");
                }


                if (request.getParameter("storeId") != null && !request.getParameter("storeId").equals("")) {
                    storeId = Integer.valueOf(request.getParameter("storeId"));
                    sessionBean.setStoreId(storeId);

                }

                String handlerPortEndpointAddress = sessionBean.getStoreInfoHash().get(storeId).getApiUrl();

                OrderHelper orderHelper1 = OrderHelper.getInstance();
                orderInfo = orderHelper1.getOrderInfo(id, handlerPortEndpointAddress);
                if (orderInfo != null) {


                    CustomerHelper customerHelper = CustomerHelper.getInstance();
                    customerInfo = customerHelper.getCustomerInfo(orderInfo.getCustomer_id(),handlerPortEndpointAddress);

                    request.setAttribute("orderInfo", orderInfo);
                    request.setAttribute("customerInfo", customerInfo);

                    result = mapping.findForward("success");
                } else {
                    result = mapping.findForward("error");
                }

            } else {
                result = mapping.findForward("error");
            }

        } catch (Exception e) {
            log.fatal("Exception",e);
        }
        return result;
    }
}
