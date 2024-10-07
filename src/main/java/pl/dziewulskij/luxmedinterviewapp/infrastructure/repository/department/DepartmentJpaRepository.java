package pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department;

import java.util.Collection;
import java.util.List;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {

    List<Department> findAllByIdIn(Collection<Long> ids);

}
