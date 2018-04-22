package ru.job4j.actionperiod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.actionperiod.crudRepositories.RoleRepository;
import ru.job4j.actionperiod.crudRepositories.UserRepository;
import ru.job4j.actionperiod.models.Role;
import ru.job4j.actionperiod.models.User;

import java.util.Iterator;

/**
 * Adding user to database.
 * @author atrifonov.
 * @version 1.
 * @since 12.04.2018.
 */
@Controller
public class AddUserController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    private final String USER_EXIST = "Пользователь с таким логином уже существует";

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String pageUser() {
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam(name = "username") String login, @RequestParam(name = "password") String password, Model model) {
        Iterator<User> iterator = userRepository.findByLogin(login).iterator();
        if (!iterator.hasNext()) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(passwordEncoder.encode(password));
            Role role = roleRepository.findByName("ROLE_USER").iterator().next();
            user.setRole(role);
            user.setAtWork(false);
            user.setEnabled(true);
            userRepository.save(user);
        } else {
            model.addAttribute("userExist", USER_EXIST);
        }
        return "login";
    }
}
