package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateCreate(Article article) throws ValidationException {
        if (Strings.isNullOrEmpty(article.getTitle())) {
            throw new ValidationException("Title can't be empty");
        }
        if (article.getTitle().length() >= 256) {
            throw new ValidationException("Title is too long");
        }

        if (Strings.isNullOrEmpty(article.getText())) {
            throw new ValidationException("Article can't be empty");
        }
    }

    public void validateChangeHidden(Article article, User curUser) throws ValidationException {
        if (article.getUserId() != curUser.getId()) {
            throw new ValidationException("You can't hide articles of other users");
        }
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAll(long userId) {
        return articleRepository.findAll(userId);
    }

    public void changeHidden(long id, boolean newHidden) {
        articleRepository.changeHidden(id, newHidden);
    }

    public void create(Article article) {
        articleRepository.save(article);
    }

}
