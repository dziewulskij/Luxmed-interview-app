package pl.dziewulskij.luxmedinterviewapp.domain.company;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department;

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
    @OneToMany(
            mappedBy = "company",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    Set<Department> departments = new HashSet<>();


    public List<Long> getDepartmentIds() {
        return Stream.ofNullable(this.getDepartments())
                .flatMap(Collection::stream)
                .map(Department::getId)
                .collect(Collectors.toList());
    }

    public void update(CompanyRequest request, List<Department> newDepartments) {
        this.name = request.name();
        this.departments.stream()
                .filter(department -> !newDepartments.contains(department))
                .collect(Collectors.toSet())
                .forEach(this::removeDepartment);

        newDepartments.forEach(this::addDepartment);
    }

    public void addDepartment(Department department) {
        if (!this.getDepartments().contains(department)) {
            this.departments.add(department);
            department.setCompany(this);
        }
    }

    public void removeDepartment(Department department) {
        this.departments.remove(department);
        department.setCompany(null);
    }

}
