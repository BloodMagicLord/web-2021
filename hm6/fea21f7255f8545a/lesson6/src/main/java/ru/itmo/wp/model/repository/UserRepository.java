package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository {
    User findUser(long id);
    User findUser(String loginOrEmail);
    User findUser(String loginOrEmail, String passwordSha);
    long findCount();
    List<User> findAll();
    void save(User user, String passwordSha);
}
