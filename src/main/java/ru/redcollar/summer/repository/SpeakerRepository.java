package ru.redcollar.summer.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.redcollar.summer.entity.Speaker;

import java.util.List;

public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    @Query("select speaker " +
            "from Speaker speaker " +
            "left join fetch speaker.talks " +
            "where speaker.id = :id")
    Speaker findByIdWithTalks(Long id);

    @Query("select speaker " +
            "from Speaker speaker " +
            "left join fetch speaker.themes " +
            "where speaker.id = :id")
    Speaker findByIdWithThemes(Long id);

    @Query("select speaker " +
            "from Speaker speaker " +
            "left join fetch speaker.talks")
    List<Speaker> findAllFetched();

    @Query("select speaker " +
            "from Speaker speaker " +
            "left join fetch speaker.themes")
    List<Speaker> findAllWithThemes();

    @EntityGraph("speakersWithTalks")
    List<Speaker> findAllByNameLike(String nameLIke);

    @Query("select speaker " +
            "from Speaker speaker " +
            "left join fetch speaker.talks " +
            "left join fetch speaker.themes ")
    List<Speaker> findAllFullFetched();
}
