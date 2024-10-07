package pl.dziewulskij.luxmedinterviewapp.application.mapper

import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest
import pl.dziewulskij.luxmedinterviewapp.faker.CompanyFaker
import spock.lang.Specification

class CompanyMapperTest extends Specification {

    def "should map Company to CompanyResponse"() {
        given:
        def company = CompanyFaker.fakeCompany()

        when:
        def result = CompanyMapper.toResponse(company)

        then:
        verifyAll {
            result.id() == company.id
            result.name() == company.name
            result.departmentIds() == company.departmentIds
        }
    }

    def "should map collection of Company to CompanyCollectionResponse"() {
        given:
        def company1 = CompanyFaker.fakeCompany()
        def company2 = CompanyFaker.fakeCompany()

        when:
        def result = CompanyMapper.toResponse([company1, company2])

        then:
        verifyAll {
            result.companies().size() == 2
            result.companies()[0].name() == company1.name
            result.companies()[1].name() == company2.name
            result.companies()[0].departmentIds() == company1.departmentIds
            result.companies()[1].departmentIds() == company2.departmentIds
        }
    }

    def "should map CompanyRequest to Company entity"() {
        given:
        def companyRequest = new CompanyRequest("New Company", [10L].toSet())

        when:
        def result = CompanyMapper.toEntity(companyRequest)

        then:
        verifyAll {
            result.name == "New Company"
            result.departments.isEmpty()
        }
    }

}
