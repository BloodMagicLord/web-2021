package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
    List<Talk> findTalk(long userId);
    void save(Talk talk);
}
