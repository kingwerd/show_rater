package com.edward.beltexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.edward.beltexam.models.Rating;
import com.edward.beltexam.models.Show;
import com.edward.beltexam.models.User;
import com.edward.beltexam.services.RatingService;
import com.edward.beltexam.services.ShowService;
import com.edward.beltexam.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Shows {

    private final ShowService showService;
    private final RatingService ratingService;
    private final UserService userService;

    public Shows(ShowService showService, RatingService ratingService, UserService userService) {
        this.showService = showService;
        this.ratingService = ratingService;
        this.userService = userService;
    }

    @RequestMapping("/shows/{id}")
    public String singleShow(Model model, @PathVariable("id") long id, @ModelAttribute("rating") Rating rating, HttpSession session, RedirectAttributes flash) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        Show show = showService.findShow(id);
        User u = userService.findUser(userId);
        model.addAttribute("show", show);
        model.addAttribute("user", u);
        model.addAttribute("rating", rating);
        return "show.jsp";
    }

    @RequestMapping("/new")
    public String newShow(@ModelAttribute("show") Show show) {
        return "new.jsp";
    }

    @PostMapping("/shows")
    public String create(@Valid @ModelAttribute("show") Show show, BindingResult result, RedirectAttributes flash, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        if (result.hasErrors()) {
            return "new.jsp";
        } else {
            showService.create(show);
            return "redirect:/home";
        }
    }

    @RequestMapping("/shows/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes flash) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        Show show = showService.findShow(id);
        model.addAttribute("shows", show);
        return "edit.jsp";
    }

    @RequestMapping(value = "/shows/{id}", method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("shows") Show show, BindingResult result, HttpSession session, RedirectAttributes flash) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        if (result.hasErrors()) {
            return "edit.jsp";
        } else {
            showService.update(show);
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/shows/{id}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id, RedirectAttributes flash, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        showService.deleteShow(id);
        return "redirect:/home";
    }

    @PostMapping("/ratings")
    public String rate(Model model, HttpSession session, @Valid @ModelAttribute("rating") Rating rating, @RequestParam(value="show_id") Long show_id, RedirectAttributes flash, BindingResult result) {
        Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			flash.addFlashAttribute("error", "You must be logged in to view that page!");
			return "redirect:/";
        }
        if (result.hasErrors()) {
            model.addAttribute("show", showService.findShow(show_id));
            model.addAttribute("user", userService.findUser(userId));
            return "show.jsp";
        } else {
            User u = userService.findUser(userId);
            Show s = showService.findShow(show_id);
            List<Rating> ratings = s.getShowRatings();
            for (Rating r: ratings) {
                if (r.getUser().getId() == u.getId()) {
                    flash.addFlashAttribute("error", "You have rated this show already");
					return String.format("redirect:/shows/%d", show_id);
                }
            }
            rating.setId(null);
            Rating newRating = ratingService.create(rating);
            ratingService.addRatingToShow(newRating, s);
            ratingService.addRatingToUser(newRating, u);
            if (s.getAverageRating() == null) {
                s.setAverageRating(newRating.getRate()/1.0);
                showService.update(s);
            } else {
                Double ratingSum = 0.0;
                for (Rating r: ratings) {
                    ratingSum += r.getRate();
                }
                ratingSum += newRating.getRate();
                s.setAverageRating(ratingSum/(ratings.size()+1));
                showService.update(s);
            }
            return String.format("redirect:/shows/%d", show_id);
        }
    }
}