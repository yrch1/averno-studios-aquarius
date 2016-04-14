package es.avernostudios.aquarius.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dortega on 14/04/2016.
 */
@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(){
        LOGGER.debug("IndexController");
        ModelAndView result = new ModelAndView("index");
        result.addObject("name","yrch");
        return result;
    }
}
