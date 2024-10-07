package pl.dziewulskij.luxmedinterviewapp.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE)
    Set<Team> teams = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company company;

    public static Department ofId(Long id) {
        return Department.builder()
                .id(id)
                .build();
    }

}
