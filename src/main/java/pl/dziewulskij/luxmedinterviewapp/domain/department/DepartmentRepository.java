package pl.dziewulskij.luxmedinterviewapp.domain.department;

import java.util.Collection;
import java.util.List;

public interface DepartmentRepository {

    List<Department> findAllByIds(Collection<Long> ids);

}
