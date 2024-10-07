package pl.dziewulskij.luxmedinterviewapp.faker

import pl.dziewulskij.luxmedinterviewapp.domain.department.Department

class DepartmentFaker extends AbstractFaker {

    static Department fakeDepartment() {
        return Department.builder()
                .id(faker.number().randomNumber())
                .build()
    }

}
