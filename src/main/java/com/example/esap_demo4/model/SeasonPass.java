package com.example.esap_demo4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "season_pass")
@Getter
@Setter
@RequiredArgsConstructor
public class SeasonPass implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //.SEQUENCE, generator = "sequenceGenerator")
//    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "season_pass_id_seq", allocationSize = 1)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "duration_m")
    private Integer durationM;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "gym_id")
    private Gym gym;


    @Override
    public String toString() {
        return toMap().toString();
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("fullName", fullName);
        map.put("durationM", durationM.toString());
        map.put("gymID", gym.getId().toString());
        return map;
    }

    public SeasonPass(String fullName, Integer durationM, Gym gym) {
        this.fullName = fullName;
        this.durationM = durationM;
        this.gym = gym;
    }
}
