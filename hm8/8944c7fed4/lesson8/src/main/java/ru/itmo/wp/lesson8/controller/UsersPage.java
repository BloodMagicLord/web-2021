package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.form.DisabledCredentials;
import ru.itmo.wp.lesson8.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("disableForm", new DisabledCredentials());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String setDisabled(@Valid @ModelAttribute("disableForm") DisabledCredentials disabledForm, BindingResult bindingResult, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            setMessage(httpSession, "Login before disabling users");
            return "redirect:/";
        }

        if (!bindingResult.hasErrors()) {
            boolean newDisable = disabledForm.isDisabled();
            User user = userService.findById(disabledForm.getUserId());

            if (user == null) {
                setMessage(httpSession, "Invalid user id");
                return "redirect:/users/all";
            }

            if (user.equals(getUser(httpSession))) {
                setMessage(httpSession, "You cannot disable yourself");
                return "redirect:/users/all";
            }


            DisabledCredentials disabledCredentials = new DisabledCredentials();
            disabledCredentials.setUserId(user.getId());
            disabledCredentials.setDisabled(newDisable);
            userService.setDisable(disabledCredentials);

            setMessage(httpSession, "User status successfully updated");
        }
        return "redirect:/users/all";
    }
}
