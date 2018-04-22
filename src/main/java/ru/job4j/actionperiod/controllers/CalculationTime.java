package ru.job4j.actionperiod.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.actionperiod.crudRepositories.UserRepository;
import ru.job4j.actionperiod.models.MessageWorkTime;
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
        User user = userRepository.findByLogin(auth.getName()).get();
        user.setWorkStart(timeStart);
        user.setAtWork(true);
        userRepository.save(user);
    }

    @RequestMapping(value = "/stopTime", method = RequestMethod.GET, consumes = "application/json")
    @ResponseBody
    public void getStopTime(@RequestParam Long timeStop) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName()).get();
        long fullTime = user.getWorkFull();
        fullTime += timeStop - user.getWorkStart();
        user.setWorkFull(fullTime);
        user.setWorkStart(0);
        user.setAtWork(false);
        userRepository.save(user);
    }

    @RequestMapping(value = "/finishTime", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public MessageWorkTime getFinishTime(@RequestParam Long timeFinish) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByLogin(auth.getName()).get();
        MessageWorkTime message = new MessageWorkTime();
        if (user.isAtWork()) {
            long fullTime = user.getWorkFull();
            fullTime += timeFinish - user.getWorkStart();
            user.setWorkFull(0);
            user.setWorkStart(0);
            user.setAtWork(false);
            message.setFullTime(getTimeFormat(fullTime));
        } else {
            long fullTime = user.getWorkFull();
            user.setWorkFull(0);
            message.setFullTime(getTimeFormat(fullTime));
        }
        userRepository.save(user);
        return message;
    }

    private String getTimeFormat(long time) {
        return String.format("%02d:%02d:%02d", time / 1000 / 3600, time / 1000 / 60 % 60, time / 1000 % 60);
    }
}
