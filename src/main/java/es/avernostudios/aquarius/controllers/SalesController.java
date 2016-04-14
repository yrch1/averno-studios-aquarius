package es.avernostudios.aquarius.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dortega on 14/04/2016.
 */
@Controller
public class SalesController {

    @RequestMapping("/sales")
    public String greeting(Model model) {
        return "";
    }

}