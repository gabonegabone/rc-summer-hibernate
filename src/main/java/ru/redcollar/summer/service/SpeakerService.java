package ru.redcollar.summer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redcollar.summer.entity.Speaker;
import ru.redcollar.summer.repository.SpeakerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpeakerService {

    private final SpeakerRepository speakerRepository;

    @Transactional
    public void findAll() {
        List<Speaker> speakers = speakerRepository.findAll();

        speakers.forEach(speaker -> speaker.getTalks().forEach(talk -> talk.getName()));
    }

    @Transactional
    public void findAllFetched() {
        List<Speaker> speakers = speakerRepository.findAllFetched();

        speakers.forEach(speaker -> speaker.getTalks().forEach(talk -> talk.getName()));
    }

    @Transactional
    public void findAllViaGraph() {
        List<Speaker> speakers = speakerRepository.findAllByNameLike("%");

        speakers.forEach(speaker -> speaker.getTalks().forEach(talk -> talk.getName()));
    }

    public void findAllFullFetched() {
        Speaker byIdWithTalks = speakerRepository.findByIdWithTalks(1L);
        speakerRepository.findByIdWithThemes(1L);

        System.out.println(byIdWithTalks.getThemes() + " " + byIdWithTalks.getTalks());
    }
}
