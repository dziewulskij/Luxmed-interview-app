package pl.dziewulskij.luxmedinterviewapp.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "TEAM")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, length = 70)
    String name;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    Project project;

    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

}
