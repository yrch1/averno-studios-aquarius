/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.action.products;

import com.gruporon2005.aquarius.bean.Product;
import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.struts.action.GenericAction;
import com.gruporon2005.soap.helper.ProductInfoHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;

/**
 *
 * @author yrch
 */
public class ViewAction extends GenericAction {

    private static Logger log = Logger.getLogger(ViewAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward result = mapping.findForward("error");
        Product product = null;


        try {

            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
            if (sessionBean != null) {

                String sku = "PROFTU";
                if (request.getParameter("sku") != null && !request.getParameter("sku").equals("")) {
                    sku = request.getParameter("sku");

                    Session session = sessionBean.getSession();
                    product = ProductInfoHelper.getInstance().getProductInfo(sku);

                    if (product != null) {
                        request.setAttribute("product", product);
                        result = mapping.findForward("success");
                    } else {
                        log.error("El producto con SKU " + sku + " no existe");
                        result = mapping.findForward("error");
                    }                    

                } else {
                    log.debug("Campos para add");
                    result = mapping.findForward("success");
                }

            } else {
                result = mapping.findForward("error");
            }


            result = mapping.findForward("success");
        } catch (Exception e) {
            log.fatal("Exception",e);
        }

        return result;

    }
}
