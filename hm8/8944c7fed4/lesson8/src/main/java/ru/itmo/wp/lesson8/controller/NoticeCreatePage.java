package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.form.NoticeCredentials;
import ru.itmo.wp.lesson8.form.validator.NoticeCredentialsCreateValidator;
import ru.itmo.wp.lesson8.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticeCreatePage extends Page {
    private final NoticeService noticeService;
    private final NoticeCredentialsCreateValidator noticeCredentialsCreateValidator;


    public NoticeCreatePage(NoticeService noticeService, NoticeCredentialsCreateValidator noticeCredentialsCreateValidator) {
        this.noticeService = noticeService;
        this.noticeCredentialsCreateValidator = noticeCredentialsCreateValidator;
    }

    @InitBinder("noticeCredentials")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(noticeCredentialsCreateValidator);
    }

    @GetMapping("/notice-create")
    public String create(Model model, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            setMessage(httpSession, "Login before creating notice");
            return "redirect:";
        }
        model.addAttribute("createForm", new NoticeCredentials());
        return "NoticeCreatePage";
    }

    @PostMapping("/notice-create")
    public String create(@Valid @ModelAttribute("createForm") NoticeCredentials createForm, BindingResult bindingResult, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            setMessage(httpSession, "Login before creating notice");
            return "redirect:";
        }

        if (bindingResult.hasErrors() || createForm.getContent().trim().isEmpty()) {
            return "NoticeCreatePage";
        }

        noticeService.save(createForm);
        setMessage(httpSession,"Notice successfully created");
        return "redirect:";
    }
}
