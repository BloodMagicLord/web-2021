package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class Page {
    private final UserService userService = new UserService();
    private HttpServletRequest request;

    User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    void loginUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    void logoutUser() {
        request.getSession().removeAttribute("user");
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
    }
}
