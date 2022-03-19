package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository {
    Article find(long id);
    List<Article> findAll();
    List<Article> findAll(long userId);
    void changeHidden(long id, boolean newHidden);
    void save(Article article);
}
