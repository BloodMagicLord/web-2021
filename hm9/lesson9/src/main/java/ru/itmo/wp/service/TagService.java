package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> save(String[] tags) {
        return Arrays.stream(tags)
                .distinct()
                .map(tag -> tagRepository.findByName(tag).orElseGet(() -> tagRepository.save(new Tag(tag))))
                .collect(Collectors.toList());

//        List<Tag> savedTags = new ArrayList<>();
//        Set<String> savedTagsNames = new HashSet<>();
//        for (String tag : tags) {
//            if (savedTagsNames.contains(tag)) {
//                continue;
//            }
//            Tag savedTag = tagRepository.findByName(tag);
//            if (savedTag == null) {
//                savedTag = tagRepository.save(new Tag(tag));
//            }
//            savedTagsNames.add(tag);
//            savedTags.add(savedTag);
//        }
//
//        return savedTags;
    }
}
