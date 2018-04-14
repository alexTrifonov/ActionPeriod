package ru.job4j.actionperiod.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for start page.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018.
 */
@Controller
public class Start {
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String getStartPage(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String redirectStart() {
        return "redirect:/start";
    }
}
