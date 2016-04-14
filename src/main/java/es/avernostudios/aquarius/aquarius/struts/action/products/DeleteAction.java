/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.avernostudios.aquarius.aquarius.struts.action.products;

import es.avernostudios.aquarius.aquarius.bean.SessionBean;
import es.avernostudios.aquarius.aquarius.struts.action.GenericAction;
import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
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
public class DeleteAction extends GenericAction {

    private static Logger log = Logger.getLogger(DeleteAction.class);

    private boolean validateProduct(HttpServletRequest request) {
        boolean result = false;

        if (request.getParameter("sku") != null && !request.getParameter("sku").equals("")) {
            result = true;
        } else {
            log.error("Falta el SKU en la request");
        }

        return result;

    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward result = mapping.findForward("error");
        String sku = "";
        try {
            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
            if (sessionBean != null) {


                if (validateProduct(request)) {
                    sku = request.getParameter("sku");

                    int addResult = ProductInfoHelper.getInstance().delete(sku);
                    if (addResult == 1) {
                        result = mapping.findForward("success");
                    } else {
                        result = mapping.findForward("deleteError");
                    }

                } else {
                    result = mapping.findForward("deleteError");
                }


            } else {
                result = mapping.findForward("error");
            }
        } catch (Exception e) {
        }

        return result;

    }
}
