package pl.dziewulskij.luxmedinterviewapp.faker

import pl.dziewulskij.luxmedinterviewapp.domain.company.Company

class CompanyFaker extends AbstractFaker {

    static fake() {
        return Company.builder()
                .id(faker.number().randomNumber())
                .name(faker.name().title())
                .departments(
                        [
                                DepartmentFaker.fake(),
                                DepartmentFaker.fake()
                        ].toSet()
                )
                .build()
    }

}
