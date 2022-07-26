package ru.redcollar.summer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.redcollar.summer.entity.Talk;

import java.util.List;
import java.util.Set;

public interface TalkRepository extends JpaRepository<Talk, Long> {

    @Query("select talk " +
            "from Talk talk " +
            "left join fetch talk.guests " +
            "where talk.id in (:ids)")
    Set<Talk> findWithGuestsById(List<Long> ids);
}
