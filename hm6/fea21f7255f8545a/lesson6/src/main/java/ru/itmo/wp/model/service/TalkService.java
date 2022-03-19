package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;

import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();

    public void validateSend(Talk talk) throws ValidationException {
        if (Strings.isNullOrEmpty(talk.getText()) && talk.getText() != "") {
            throw new ValidationException("Message cant be empty");
        }
        if (talk.getSourceUserId() == talk.getTargetUserId()) {
            throw new ValidationException("You cant send message to yourself");
        }
    }

    public List<Talk> findTalk(long userId) {
        return talkRepository.findTalk(userId);
    }

    public void send(Talk talk) {
        talkRepository.save(talk);
    }
}
