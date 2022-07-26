package ru.redcollar.summer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.redcollar.summer.entity.Guest;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query("select guest " +
            "from Guest guest " +
            "left join fetch guest.signedTalks " +
            "where guest.id = :id")
    Optional<Guest> findWithTalksById(Long id);

    @Modifying(clearAutomatically = true)
    @Query("update Guest guest " +
            "set guest.isStudent = false")
    void updateStudents();
}
