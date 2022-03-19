package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class ArticlePage extends Page {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            setMessage("Login before creating article");
            throw new RedirectException("/index");
        }
    }

    private void create(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        if (getUser() == null) {
            throw new RedirectException("/index");
        }

        Article article = new Article();
        article.setUserId(getUser().getId());
        article.setTitle(request.getParameter("title"));
        article.setText(request.getParameter("text"));

        articleService.validateCreate(article);
        articleService.create(article);
        setMessage("Article has been created successfully");

        throw new RedirectException("/index");
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
