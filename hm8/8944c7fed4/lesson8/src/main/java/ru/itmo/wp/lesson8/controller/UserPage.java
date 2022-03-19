package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String main(@PathVariable("id") String id, Model model, HttpSession httpSession) {
        try {
            User user = userService.findById(Long.parseLong(id));
            if (user != null) {
                model.addAttribute("userInfo", user);
            }
        } catch (NumberFormatException e) {
            setMessage(httpSession, "Invalid user id");
            return "redirect:/";
        }
        return "UserPage";
    }

    @GetMapping( {"/user/", "/user"})
    public String main() {
        return "redirect:/";
    }

}
