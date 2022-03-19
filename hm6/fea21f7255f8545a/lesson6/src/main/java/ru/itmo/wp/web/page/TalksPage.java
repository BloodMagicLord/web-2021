package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TalksPage extends Page {
    private final TalkService talkService = new TalkService();
    private final UserService userService = new UserService();
    private long targetUserId;

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            setMessage("You should login to see your talks");
            throw new RedirectException("/index");
        }


        view.put("users", userService.findAll());
        view.put("currUser", getUser());
        view.put("talks", talkService.findTalk(getUser().getId()));
    }

    private void send(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Talk talk = new Talk();
        talk.setSourceUserId(getUser().getId());
        talk.setTargetUserId(Long.parseLong(request.getParameter("targetUserId")));
        talk.setText(request.getParameter("text"));

        talkService.validateSend(talk);
        talkService.send(talk);

        throw new RedirectException("/talks");
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
