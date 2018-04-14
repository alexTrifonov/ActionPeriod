package ru.job4j.actionperiod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.actionperiod.crudRepositories.UserRepository;
import ru.job4j.actionperiod.models.User;


/**
 *  Figure out time of work.
 *  @author atrifonov.
 *  @version 1.
 *  @since 14.04.2018
 */
@Controller
public class CalculationTime {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/startTime", method = RequestMethod.GET,  consumes = "application/json")
    @ResponseBody
    public void getStartTime(@RequestParam Long timeStart) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName()).iterator().next();
        user.setWorkStart(timeStart);
        user.setAtWork(true);
        userRepository.save(user);
    }

    @RequestMapping(value = "/stopTime", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public void getStopTime(@RequestParam Long timeStop) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName()).iterator().next();
        long fullTime = user.getWorkFull();
        fullTime += timeStop - user.getWorkStart();
        user.setWorkFull(fullTime);
        user.setWorkStart(0);
        user.setAtWork(false);
        userRepository.save(user);
    }

    @RequestMapping(value = "/finishTime", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getFinishTime(@RequestParam Long timeFinish) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName()).iterator().next();
        StringBuilder sb = new StringBuilder();
        if (user.isAtWork()) {
            long fullTime = user.getWorkFull();
            fullTime += timeFinish - user.getWorkStart();
            String fullTimeStr = String.format("%02d:%02d:%02d",
                    fullTime / 1000 / 3600, fullTime / 1000 / 60 % 60, fullTime / 1000 % 60);
            user.setWorkFull(0);
            user.setWorkStart(0);
            user.setAtWork(false);
            sb.append("{\"fullTime\" : \"").append(fullTimeStr).append("\"}");
        } else {
            long fullTime = user.getWorkFull();
            String fullTimeStr = String.format("%02d:%02d:%02d",
                    fullTime / 1000 / 3600, fullTime / 1000 / 60 % 60, fullTime / 1000 % 60);
            user.setWorkFull(0);
            sb.append("{\"fullTime\" : \"").append(fullTimeStr).append("\"}");
        }
        userRepository.save(user);
        return sb.toString();
    }
}
