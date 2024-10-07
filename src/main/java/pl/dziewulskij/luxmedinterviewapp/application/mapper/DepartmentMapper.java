package pl.dziewulskij.luxmedinterviewapp.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DepartmentMapper {

    public static Set<Department> toDepartmentSet(Collection<Long> ids) {
        return Stream.ofNullable(ids)
                .flatMap(Collection::stream)
                .map(Department::ofId)
                .collect(Collectors.toSet());
    }

}
