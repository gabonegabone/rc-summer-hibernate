package ru.redcollar.summer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.redcollar.summer.entity.Guest;
import ru.redcollar.summer.repository.GuestRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    @Transactional
    public void deleteById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow();

        guestRepository.delete(guest);
    }
//dirty checking
    @Transactional
    public void graduateStudents() {
        guestRepository.findAll().stream().map(Guest::getIsStudent).forEach(System.out::println);

        guestRepository.updateStudents();

        guestRepository.findAll().stream().map(Guest::getIsStudent).forEach(System.out::println);
    }


}
