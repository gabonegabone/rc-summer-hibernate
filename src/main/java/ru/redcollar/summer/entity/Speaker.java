package ru.redcollar.summer.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
@NamedEntityGraph(name = "speakersWithTalks", attributeNodes = {
        @NamedAttributeNode("talks")
})
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.LAZY)
    private List<Talk> talks;

    @OneToMany(mappedBy = "speaker", fetch = FetchType.LAZY)
    private List<Theme> themes;
}
