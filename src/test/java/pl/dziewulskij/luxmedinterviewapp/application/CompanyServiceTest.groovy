package pl.dziewulskij.luxmedinterviewapp.application

import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest
import pl.dziewulskij.luxmedinterviewapp.application.validator.CompanyValidator
import pl.dziewulskij.luxmedinterviewapp.domain.company.Company
import pl.dziewulskij.luxmedinterviewapp.domain.company.CompanyRepository
import pl.dziewulskij.luxmedinterviewapp.domain.department.DepartmentRepository
import pl.dziewulskij.luxmedinterviewapp.faker.CompanyFaker
import pl.dziewulskij.luxmedinterviewapp.faker.CompanyRequestFaker
import pl.dziewulskij.luxmedinterviewapp.faker.DepartmentFaker
import spock.lang.Specification

class CompanyServiceTest extends Specification {

    private CompanyRepository companyRepository = Mock()
    private CompanyValidator companyValidator = Mock()
    private DepartmentRepository departmentRepository = Mock()
    private CompanyService companyService = new CompanyService(
            companyRepository,
            companyValidator,
            departmentRepository
    )


    def 'should return all companies'() {
        given:
        def company1 = CompanyFaker.fakeCompany()
        def company2 = CompanyFaker.fakeCompany()
        1 * companyRepository.findAll() >> [company1, company2]

        when:
        def result = companyService.getAllCompanies()

        then:
        verifyAll {
            result.companies().size() == 2
            result.companies()[0].id() == company1.id
            result.companies()[0].name() == company1.name
            result.companies()[0].departmentIds() == company1.departmentIds
            result.companies()[1].id() == company2.id
            result.companies()[1].name() == company2.name
            result.companies()[1].departmentIds() == company2.departmentIds
        }
    }

    def 'should return company by id'() {
        given:
        def company = CompanyFaker.fakeCompany()
        1 * companyRepository.findByIdOrElseThrow(_ as Long) >> company

        when:
        def result = companyService.getCompanyById(10L)

        then:
        verifyAll {
            result.id() == company.id
            result.name() == company.name
            result.departmentIds() == company.departmentIds
        }
    }

    def 'should create company'() {
        given:
        def companyRequest = CompanyRequestFaker.fakeCompanyRequest()
        1 * companyValidator.validateCreation(companyRequest)
        1 * departmentRepository.findAllByIds(companyRequest.departmentIds()) >> [DepartmentFaker.fakeDepartment()]
        1 * companyRepository.save(_ as Company)

        when:
        def result = companyService.createCompany(companyRequest)

        then:
        verifyAll {
            result.name() == companyRequest.name()
            result.departmentIds().size() == 1
        }
    }

    def 'should throw exception when create company'() {
        given:
        def companyRequest = CompanyRequestFaker.fakeCompanyRequest()
        1 * companyValidator.validateCreation(_ as CompanyRequest) >> { throw new RuntimeException() }
        0 * departmentRepository.findAllByIds(_ as Collection)
        0 * companyRepository.save(_ as Company)

        when:
        companyService.createCompany(companyRequest)

        then:
        thrown(RuntimeException.class)
    }

    def 'should update company'() {
        given:
        def companyRequest = CompanyRequestFaker.fakeCompanyRequest()
        def company = CompanyFaker.fakeCompany()
        1 * companyValidator.validateModification(companyRequest)
        1 * companyRepository.findByIdOrElseThrow(_ as Long) >> company
        1 * departmentRepository.findAllByIds(companyRequest.departmentIds()) >> [DepartmentFaker.fakeDepartment()]
        1 * companyRepository.save(company)

        when:
        def result = companyService.updateCompany(10L, companyRequest)

        then:
        result.name() == companyRequest.name()
    }

    def 'should throw exception when update company'() {
        given:
        def companyRequest = CompanyRequestFaker.fakeCompanyRequest()
        1 * companyValidator.validateModification(_ as CompanyRequest) >> { throw new RuntimeException() }
        0 * companyRepository.findByIdOrElseThrow(_ as Long)
        0 * departmentRepository.findAllByIds(_ as Collection)
        0 * companyRepository.save(_ as Company)

        when:
        companyService.updateCompany(10L, companyRequest)

        then:
        thrown(RuntimeException.class)
    }

    def 'should delete company by id'() {
        when:
        companyService.deleteCompanyById(10L)

        then:
        1 * companyRepository.deleteByIdOrElseThrow(_ as Long)
    }

}
