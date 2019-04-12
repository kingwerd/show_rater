package com.edward.beltexam.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.edward.beltexam.models.Show;
import com.edward.beltexam.models.User;
import com.edward.beltexam.services.ShowService;
import com.edward.beltexam.services.UserService;
import com.edward.beltexam.validations.UserValidator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Users {

    private final UserValidator userValidator;
    private final UserService userService;
    private final ShowService showService;

    public Users(UserValidator userValidator, UserService userService, ShowService showService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.showService = showService;
    }

    @RequestMapping("/")
    public String index(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId != null) {
			return "redirect:/home";
        }
        return "loginRegistrtion.jsp";
    }

    @PostMapping("/register")
    public String register(@RequestParam Map<String, String> body, RedirectAttributes flash, HttpSession session) {
        HashMap<String, Object> response = this.userValidator.validate(body);
        if ((boolean) response.get("valid")) {
            User user = this.userService.create(body);
            session.setAttribute("userId", user.getId());
            return "redirect:/home";
        } else {
            flash.addFlashAttribute("errors", response);
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> body, RedirectAttributes flash, HttpSession session) {
        HashMap<String, Object> response = this.userValidator.authenticate(body);
        if ((boolean) response.get("valid")) {
            User user = (User) response.get("user");
            session.setAttribute("userId", user.getId());
            return "redirect:/home";
        } else {
            flash.addFlashAttribute("errors", response);
            return "redirect:/";
        }
    }

    @RequestMapping("/home")
    public String home(Model model, HttpSession session, RedirectAttributes flash, @ModelAttribute("show") Show show) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("login", "You must login first");
            flash.addFlashAttribute("errors", map);
            return "redirect:/";
        }
        User user = userService.findUser(userId);
        List<Show> shows = showService.allShows();
        model.addAttribute("shows", shows);
        model.addAttribute("user", user);
        return "home.jsp";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/";
    }
}