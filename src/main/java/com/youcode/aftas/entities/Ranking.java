package com.youcode.aftas.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {

    @EmbeddedId
    private RankingKey id;
    private Integer rank;
    private Integer score;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Competition competition;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Ranking ranking = (Ranking) o;
        return getId() != null && Objects.equals(getId(), ranking.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
