package com.desafio.cadastroelogin.controller;

import com.desafio.cadastroelogin.model.User;
import com.desafio.cadastroelogin.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class ApplicationController {

    private String attribute;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        return new ModelAndView("register");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid User user, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors() || !user.getPassword().equals(user.getPassword2()) || userService.findByName(user.getName()) == true || user.getPassword().isBlank()) {
            return "redirect:/register";
        } else {
            userService.save(user);
            return "success";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(@Valid User user, BindingResult result, RedirectAttributes attributes, String name, String password, HttpServletRequest session) {
        if (result.hasErrors() || !userService.findByNameAndPassword(name, password)) {
            return "redirect:/login";
        } else {
            session.setAttribute("name", user.getName());
            attribute = (String) session.getAttribute("name");
            return "redirect:/mainPage";
        }
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String invalidateSession(HttpSession session) {
        session.invalidate();
        attribute = null;
        return "redirect:/login";
    }

    @RequestMapping(value = "/mainPage", method = RequestMethod.GET)
    public ModelAndView getMainPage(HttpServletRequest request, User user) {

        if (attribute == null) {
            return new ModelAndView("redirect:/login");
        }
        else {
            ModelAndView mv = new ModelAndView("mainPage");

            mv.addObject("user", attribute);
            System.out.println(user.getName());
            return mv;
        }
    }
}
