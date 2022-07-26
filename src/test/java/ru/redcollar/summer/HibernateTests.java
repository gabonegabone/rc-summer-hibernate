package ru.redcollar.summer;


import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.redcollar.summer.entity.Guest;
import ru.redcollar.summer.entity.Talk;
import ru.redcollar.summer.repository.GuestRepository;
import ru.redcollar.summer.repository.SpeakerRepository;
import ru.redcollar.summer.repository.TalkRepository;
import ru.redcollar.summer.service.GuestService;
import ru.redcollar.summer.service.SpeakerService;

import java.util.HashSet;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HibernateTests {

    private final GuestRepository guestRepository;
    private final SpeakerRepository speakerRepository;
    private final TalkRepository talkRepository;

    private final SpeakerService speakerService;
    private final GuestService guestService;

    @Test
    @Sql("classpath:db/import.sql")
    public void toStringTest() {
        speakerRepository.findById(1L).ifPresent(System.out::println);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void manyToManyUpdateTest() {
        Guest guest = guestRepository.findById(1L).orElseThrow();

        List<Talk> talks = talkRepository.findAllById(List.of(1L, 2L, 3L));

        guest.setSignedTalks(new HashSet<>(talks));
//        guest.setSignedTalks(talks);

        guestRepository.save(guest);
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void joinFetchTest_NoFetch() {
        speakerService.findAll();
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void joinFetchTest_WithFetch() {
        speakerService.findAllFetched();
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void joinFetchTest_EntityGraph() {
        speakerService.findAllViaGraph();
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void deleteTest() {
        guestService.deleteById(1L);
    }

    @Test
    @Sql("classpath:db/guests.sql")
    public void modifyingTest() {
        guestService.graduateStudents();
    }

    @Test
    @Sql("classpath:db/import.sql")
    public void multipleBagTest() {
        speakerService.findAllFullFetched();

    }
}
