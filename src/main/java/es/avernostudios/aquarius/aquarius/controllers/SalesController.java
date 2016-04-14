package es.avernostudios.aquarius.aquarius.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by dortega on 14/04/2016.
 */
@Controller
public class SalesController {

    @RequestMapping("/")
    public String greeting(Model model) {
        return "";
    }

}