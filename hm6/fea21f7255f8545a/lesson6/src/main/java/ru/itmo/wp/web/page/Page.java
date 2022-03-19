package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Type;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Page {
    private final EventService eventService = new EventService();
    private final UserService userService = new UserService();
    private HttpServletRequest request;

    void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    void setUser(User user) {
        request.getSession().setAttribute("user", user);

        Event event  = new Event();
        event.setType(Type.ENTER);
        event.setUserId(getUser().getId());
        eventService.save(event);
    }

    void removeUser() {
        Event event  = new Event();
        event.setType(Type.LOGOUT);
        event.setUserId(getUser().getId());
        eventService.save(event);

        request.getSession().removeAttribute("user");
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }

        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
    }
}
