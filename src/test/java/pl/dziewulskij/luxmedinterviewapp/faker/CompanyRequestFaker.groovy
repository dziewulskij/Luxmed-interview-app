package pl.dziewulskij.luxmedinterviewapp.faker

import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest

class CompanyRequestFaker extends AbstractFaker {

    static fake() {
        return new CompanyRequest(
                faker.name().title(),
                [
                        faker.number().randomNumber(),
                        faker.number().randomNumber()
                ].toSet()
        )
    }

}
