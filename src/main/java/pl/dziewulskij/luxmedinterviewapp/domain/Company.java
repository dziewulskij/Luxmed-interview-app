package pl.dziewulskij.luxmedinterviewapp.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "COMPANY")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Department> departments = new HashSet<>();

    public List<Long> getDepartmentIds() {
        return Stream.ofNullable(this.getDepartments())
                .flatMap(Collection::stream)
                .map(Department::getId)
                .collect(Collectors.toList());
    }

    public void update(CompanyRequest request) {
        this.name = request.name();
        this.departments = Stream.ofNullable(request.departmentIds())
                .flatMap(Collection::stream)
                .map(Department::ofId)
                .collect(Collectors.toSet());
    }

}
