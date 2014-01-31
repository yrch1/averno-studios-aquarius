/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.action.sales;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gruporon2005.aquarius.Constants;
import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.struts.action.GenericAction;
import com.gruporon2005.aquarius.util.Utilities;
import com.gruporon2005.soap.helper.OrderHelper;
import com.gruporon2005.soap.magento.AssociativeEntity;
import com.gruporon2005.soap.magento.ComplexFilter;
import com.gruporon2005.soap.magento.Filters;
import com.gruporon2005.soap.magento.SalesOrderEntity;

/**
 *
 * @author yrch
 */
public class SalesCompletedAction extends GenericAction {

    private static Logger log = Logger.getLogger(SalesCompletedAction.class);

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

            int offset = Constants.OFFSET;
            int pageSize = Constants.PAGE_SIZE;
            int storeId = -1;
            int dateFrom = Constants.DEFAULT_DATE_FROM;


            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

            if (sessionBean != null) {
                storeId = sessionBean.getStoreId();
            } else {
                result = mapping.findForward("error");
            }


            if (request.getParameter("storeId") != null && !request.getParameter("storeId").equals("")) {
                storeId = Integer.valueOf(request.getParameter("storeId"));
                sessionBean.setStoreId(storeId);

            }
            if (request.getParameter("offset") != null && !request.getParameter("offset").equals("")) {
                offset = Integer.valueOf(request.getParameter("offset"));
            }

            if (request.getParameter("pageSize") != null && !request.getParameter("pageSize").equals("")) {
                pageSize = Integer.valueOf(request.getParameter("pageSize"));
            }
            if (request.getParameter(Constants.DATE_FROM_NAME) != null && !request.getParameter(Constants.DATE_FROM_NAME).equals("")) {
                dateFrom = Integer.valueOf(request.getParameter(Constants.DATE_FROM_NAME));
            }

            OrderHelper helperOrder1 = OrderHelper.getInstance();


            String handlerPortEndpointAddress = "";

            if (storeId > 0) {

                Filters filtros = new Filters();
                
                 ComplexFilter cmp[] = new ComplexFilter[3];
                cmp[0] = new ComplexFilter("status", new AssociativeEntity("eq", "complete"));
                cmp[1] = new ComplexFilter("store_id", new AssociativeEntity("eq", String.valueOf(sessionBean.getStoreInfoHash().get(storeId).getMagentoStoreId())));
                cmp[2] = new ComplexFilter("created_at", new AssociativeEntity("gt", Utilities.getInstance().getFilterDateFrom(dateFrom)));

               /*
                ComplexFilter cmp[] = new ComplexFilter[1];
                cmp[0] = new ComplexFilter("store_id", new AssociativeEntity("eq", String.valueOf(sessionBean.getStoreInfoHash().get(storeId).getMagentoStoreId())));
                 */
                

                //cmp[0] = new ComplexFilter("status", new AssociativeEntity("eq", "pending"));

                filtros.setComplex_filter(cmp);

                handlerPortEndpointAddress = sessionBean.getStoreInfoHash().get(storeId).getApiUrl();


                int[] orderListSize = new int[1];

                orderListSize[0] = -1;


                List<SalesOrderEntity> orderList = helperOrder1.getOrderList(filtros, offset, pageSize, orderListSize, handlerPortEndpointAddress);

                request.setAttribute("numPages", (int) Math.ceil(orderListSize[0] / pageSize)+1);
                request.setAttribute("orderList", orderList);
                request.setAttribute("currentPage", (offset / pageSize) + 1);
                request.setAttribute("pageSize", pageSize);
                request.setAttribute(Constants.DATE_FROM_NAME, dateFrom);
            }

            result = mapping.findForward("success");


        } catch (Exception e) {
            log.fatal("Exception",e);
        }
        return result;
    }
}
