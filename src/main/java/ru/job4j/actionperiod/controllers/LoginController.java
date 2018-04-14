package ru.job4j.actionperiod.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for log in.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018.
 */
@Controller
public class LoginController {
    private final String ERROR_MESSAGE = "Неверно указаны имя/пароль или пользователь уже вошел в систему";
    private final String ANONYM = "anonymousUser";
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "logout", required = false) String logout, Model model,
                            HttpServletRequest req) {
        String view = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!ANONYM.equals(auth.getName())) {
            String referer = req.getHeader("referer");
            if (referer != null) {
                view = String.format("redirect:%s", referer);
            } else {
                view = "redirect:/start";
            }

        } else {
            if (logout != null) {
                model.addAttribute("message", "Вы вышли");
            }
            view = "login";
        }
        return view;
    }


    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginFail(Model model) {
        model.addAttribute("error", ERROR_MESSAGE);
        return "login";
    }
}
