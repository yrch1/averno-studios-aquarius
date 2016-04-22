package es.avernostudios.aquarius.controllers;

import com.google.common.collect.Lists;
import es.avernostudios.aquarius.Constants;
import es.avernostudios.aquarius.bean.Product;
import es.avernostudios.aquarius.jpa.repositories.ProductRepository;
import es.avernostudios.aquarius.soap.helper.ProductInfoHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dortega on 14/04/2016.
 */
@Slf4j
@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping
    public ModelAndView index(@RequestParam(value = "storeId", required = false, defaultValue = "-1") int storeId
            , @RequestParam(value = "offset", required = false, defaultValue = "1") int offset
            , @RequestParam(value = "pageSize", required = false, defaultValue = Constants.PAGE_SIZE + "") int pageSize
            , @RequestParam(value = "sortField", required = false, defaultValue = "sku") String sortField
            , @RequestParam(value = "ascDesc", required = false, defaultValue = "ASC") String ascDesc
            , @RequestParam(value = "q", required = false, defaultValue = "") String q
            , HttpSession session) {

        LOGGER.debug("/products/");
        ModelAndView result = new ModelAndView();

        int numPages;
        int currentPage;
        try {

            if (storeId != -1 || session.getAttribute(Constants.STORE_ID) == null) {
                session.setAttribute(Constants.STORE_ID, storeId);
            }
            List<Product> productsInfoList;
            if (!q.equals("")) {

                productsInfoList = new ArrayList<>();
                productsInfoList.add(productRepository.findBySku(q));

                numPages = 1;
                currentPage = 1;

            } else {


                Pageable pageable = new PageRequest(offset-1,pageSize, Sort.Direction.fromStringOrNull(ascDesc),sortField);
                Page<Product> page = productRepository.getInfoList(pageable);
                productsInfoList = page.getContent();

                numPages = page.getTotalPages();
                currentPage = page.getNumber() + 1;

            }

            result.addObject("productList", productsInfoList);
            result.addObject("numPages", numPages);
            result.addObject("currentPage", currentPage);
            result.addObject("pageSize", pageSize);


        } catch (Exception e) {
            LOGGER.error("Exception", e);
        }


        result.setViewName("products/index");
        return result;
    }


    @RequestMapping(value="/{sku}", method = RequestMethod.GET)
    public ModelAndView product(@PathVariable String sku
            , HttpSession session) {

        LOGGER.debug("/products/{sku}");
        ModelAndView result = new ModelAndView();
        result.addObject("product",productRepository.findBySku(sku));
        result.setViewName("products/product");
        return  result;
    }

}
