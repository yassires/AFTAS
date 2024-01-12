package com.youcode.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double averageWeight;

    @OneToMany(mappedBy = "fish")
    @ToString.Exclude
    @JsonIgnore
    private List<Hunting> hunting;

    @ManyToOne
    private Level level;


}
