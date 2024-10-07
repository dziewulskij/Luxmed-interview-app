package pl.dziewulskij.luxmedinterviewapp.faker

import pl.dziewulskij.luxmedinterviewapp.domain.company.Company

class CompanyFaker extends AbstractFaker {

    static fakeCompany() {
        return Company.builder()
                .id(faker.number().randomNumber())
                .name(faker.name().title())
                .departments(
                        [
                                DepartmentFaker.fakeDepartment(),
                                DepartmentFaker.fakeDepartment()
                        ].toSet()
                )
                .build()
    }

}
