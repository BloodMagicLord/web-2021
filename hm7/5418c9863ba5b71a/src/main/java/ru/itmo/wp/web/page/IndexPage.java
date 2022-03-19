package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** @noinspection unused*/
public class IndexPage extends Page{
    private final ArticleService articleService = new ArticleService();
    private final UserService usersService = new UserService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
    }

    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        List<Article> articles = articleService.findAll();
        List<User> users = new ArrayList<>();
        for (Article article : articles) {
            users.add(usersService.findUser(article.getUserId()));
        }
        view.put("articles", articles);
        view.put("users", users);
    }

    private void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
