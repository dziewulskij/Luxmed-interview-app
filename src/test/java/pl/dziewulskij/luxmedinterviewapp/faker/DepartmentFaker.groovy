package pl.dziewulskij.luxmedinterviewapp.faker

import pl.dziewulskij.luxmedinterviewapp.domain.department.Department

class DepartmentFaker extends AbstractFaker {

    static Department fake() {
        return Department.builder()
                .id(faker.number().randomNumber())
                .build()
    }

}
