package ru.redcollar.summer.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long age;
    private Boolean isStudent;
    private String jobName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "guest_talk",
            joinColumns = @JoinColumn(name = "talk_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id"))
    private Set<Talk> signedTalks;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "guest_talk",
//            joinColumns = @JoinColumn(name = "talk_id"),
//            inverseJoinColumns = @JoinColumn(name = "guest_id"))
//    private List<Talk> signedTalks;
}
