package pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department;
import pl.dziewulskij.luxmedinterviewapp.domain.department.DepartmentRepository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private final DepartmentJpaRepository departmentJpaRepository;

    @Override
    public List<Department> findAllByIds(Collection<Long> ids) {
        return departmentJpaRepository.findAllByIdIn(ids);
    }

}
