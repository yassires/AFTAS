package com.youcode.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Family name cannot be null")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String familyName;

    @NotNull(message = "Access date cannot be null")
    @PastOrPresent(message = "Access date must be in the past or present")
    private Date accessDate;

    @NotNull(message = "nationality cannot be null")
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity number cannot be null")
    @Size(min = 2, max = 50, message = "Identity number must be between 2 and 50 characters")
    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    @ToString.Exclude
    private List<Hunting> hunting;



    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Member member = (Member) o;
        return getId() != null && Objects.equals(getId(), member.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
