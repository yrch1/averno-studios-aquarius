package es.avernostudios.aquarius.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dortega on 14/04/2016.
 */
@Slf4j
@Controller
@RequestMapping("/products")
public class ProductsController {


    @RequestMapping
    public ModelAndView index(){
        LOGGER.debug("/products/");
        ModelAndView result = new ModelAndView();

        result.setViewName("products/index");
        return result;
    }

}
