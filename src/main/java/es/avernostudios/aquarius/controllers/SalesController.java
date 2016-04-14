package es.avernostudios.aquarius.controllers;

import es.avernostudios.aquarius.Constants;
import es.avernostudios.aquarius.bean.SessionBean;
import es.avernostudios.aquarius.soap.helper.OrderHelper;
import es.avernostudios.aquarius.soap.magento.AssociativeEntity;
import es.avernostudios.aquarius.soap.magento.ComplexFilter;
import es.avernostudios.aquarius.soap.magento.Filters;
import es.avernostudios.aquarius.soap.magento.SalesOrderEntity;
import es.avernostudios.aquarius.util.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dortega on 14/04/2016.
 */
@Slf4j
@Controller
@RequestMapping("/sales")
public class SalesController {

    @RequestMapping
    public ModelAndView index(@RequestParam(value = "storeId", required = false,defaultValue = "-1") int storeId
                          , @RequestParam(value = "offset", required = false,defaultValue = "-1") int offset
            , @RequestParam(value = "pageSize", required = false,defaultValue = Constants.PAGE_SIZE+"") int pageSize
            , @RequestParam(value = "dateFrom", required = false,defaultValue = Constants.DEFAULT_DATE_FROM+"") int dateFrom
            ,Model model) {

        LOGGER.debug("/sales/");
        ModelAndView result = new ModelAndView();

        result.setViewName("sales/index");

        SessionBean sessionBean = (SessionBean) new SessionBean();//request.getSession().getAttribute("sessionBean");


        if (sessionBean != null) {
            storeId = sessionBean.getStoreId();
        } else {
            //result = mapping.findForward("error");
        }


        if (storeId != -1) {
            sessionBean.setStoreId(storeId);
        }

        OrderHelper helperOrder1 = OrderHelper.getInstance();

        String handlerPortEndpointAddress = "";

        if (storeId > 0) {

            handlerPortEndpointAddress = sessionBean.getStoreInfoHash().get(storeId).getApiUrl();


            int[] orderListSize = new int[1];

            orderListSize[0] = -1;


            Filters filtros = new Filters();

            ComplexFilter cmp[] = new ComplexFilter[2];
            cmp[0] = new ComplexFilter("store_id", new AssociativeEntity("eq", String.valueOf(sessionBean.getStoreInfoHash().get(storeId).getMagentoStoreId())));
            cmp[1] = new ComplexFilter("created_at", new AssociativeEntity("gt", Utilities.getInstance().getFilterDateFrom(dateFrom)));

            filtros.setComplex_filter(cmp);

            List<SalesOrderEntity> orderList = helperOrder1.getOrderList(filtros, offset, pageSize, orderListSize, handlerPortEndpointAddress);


/*
            request.setAttribute("numPages", (int) Math.ceil(orderListSize[0] / pageSize)+1);
            request.setAttribute("orderList", orderList);
            request.setAttribute("currentPage", (offset / pageSize) + 1);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute(Constants.DATE_FROM_NAME, dateFrom);*/
        }
        return result;
    }
}