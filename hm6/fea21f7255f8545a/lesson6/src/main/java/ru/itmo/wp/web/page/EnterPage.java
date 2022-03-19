package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends Page {
    private final UserService userService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");
        User user = userService.validateEnter(loginOrEmail, password);
        if (getUser() == null) {
            setUser(user);
            setMessage("Hello, " + user.getLogin());
        }
        throw new RedirectException("/index");
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
