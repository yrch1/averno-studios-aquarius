/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruporon2005.aquarius.struts.action.products;

import com.gruporon2005.aquarius.Constants;
import com.gruporon2005.aquarius.bean.Product;
import com.gruporon2005.aquarius.bean.SessionBean;
import com.gruporon2005.aquarius.struts.action.GenericAction;
import com.gruporon2005.soap.helper.ProductInfoHelper;
import java.util.List;
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
public class ProductsAction extends GenericAction {

    private static Logger log = Logger.getLogger(ProductsAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward result = mapping.findForward("error");
        int numPages;
        int currentPage;
        try {


            SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

            if (sessionBean != null) {
                int[] productsInfoListSize = new int[1];
                int offset = Constants.OFFSET;
                int pageSize = Constants.PAGE_SIZE;


                List<Product> productsInfoList;

                if (request.getParameter("q") != null && !request.getParameter("q").equals("")) {

                    String q = request.getParameter("q");
                    productsInfoList = ProductInfoHelper.getInstance().searchProduct(q);
                    productsInfoListSize[0] = productsInfoList.size();

                    numPages = 1;
                    currentPage = 1;

                } else {

                    if (request.getParameter("offset") != null && !request.getParameter("offset").equals("")) {
                        offset = Integer.valueOf(request.getParameter("offset"));
                    }

                    if (request.getParameter("pageSize") != null && !request.getParameter("pageSize").equals("")) {
                        pageSize = Integer.valueOf(request.getParameter("pageSize"));
                    }

                    productsInfoListSize[0] = -1;
                    String sortField = "sku";
                    String ascDesc = "ASC";
                    if (request.getParameter("sortField") != null) {
                        sortField = request.getParameter("sortField");
                    }
                    if (request.getParameter("ascDesc") != null) {
                        ascDesc = request.getParameter("ascDesc");
                    }
                    productsInfoList = ProductInfoHelper.getInstance().getInfoList(offset, pageSize, productsInfoListSize, sortField, ascDesc);

                    numPages = (int) Math.ceil(productsInfoListSize[0] / pageSize) + 1;
                    currentPage = (offset / pageSize) + 1;

                }


                request.setAttribute("productsInfoList", productsInfoList);
                request.setAttribute("numPages", numPages);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("pageSize", pageSize);


                /*String handlerPortEndpointAddress =  sessionBean.getStoreInfoHash().get(1).getApiUrl();
                String productsIds[] = {"PROFTU","CDCAS"};
                ProductInfoHelper.getInstance().getStockList(productsIds, handlerPortEndpointAddress);*/



                result = mapping.findForward("success");

            } else {
                result = mapping.findForward("error");
            }




        } catch (Exception e) {
            log.error(e);
            result = mapping.findForward("error");
        }
        return result;




    }
}
