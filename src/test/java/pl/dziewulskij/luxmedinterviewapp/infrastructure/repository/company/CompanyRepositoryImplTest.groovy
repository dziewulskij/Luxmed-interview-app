package pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.company

import pl.dziewulskij.luxmedinterviewapp.faker.CompanyFaker
import pl.dziewulskij.luxmedinterviewapp.infrastructure.exception.CompanyNotFundException
import spock.lang.Specification

class CompanyRepositoryImplTest extends Specification {

    private CompanyJpaRepository companyJpaRepository = Mock()
    private CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl(companyJpaRepository)

    def "should invoke repository method when find all companies"() {
        when:
        companyRepository.findAll()

        then:
        1 * companyJpaRepository.findAll()
    }

    def "should invoke repository method when find company by id"() {
        given:
        def company = CompanyFaker.fake()
        1 * companyJpaRepository.findById(_ as Long) >> Optional.of(company)

        when:
        def result = companyRepository.findByIdOrElseThrow(10L)

        then:
        verifyAll {
            result.id == company.id
            result.name == company.name
        }
    }

    def "should throw exception when find company by id is empty"() {
        given:
        1 * companyJpaRepository.findById(_ as Long) >> Optional.empty()

        when:
        companyRepository.findByIdOrElseThrow(10L)

        then:
        def exceptionResult = thrown(CompanyNotFundException.class)
        exceptionResult.message == 'Company with id `10` does not exist'
    }

    def "should invoke repository method when save company"() {
        given:
        def company = CompanyFaker.fake()

        when:
        companyRepository.save(company)

        then:
        1 * companyJpaRepository.save(company)
    }

    def "should invoke repository method when delete company"() {
        given:
        1 * companyJpaRepository.existsById(_ as Long) >> true

        when:
        companyRepository.deleteByIdOrElseThrow(10L)

        then:
        1 * companyJpaRepository.deleteById(_ as Long)
    }

    def "should throw exception when delete company does not exist"() {
        given:
        1 * companyJpaRepository.existsById(_ as Long) >> false

        when:
        companyRepository.deleteByIdOrElseThrow(10L)

        then:
        0 * companyJpaRepository.deleteById(_ as Long)
        def exceptionResult = thrown(CompanyNotFundException.class)
        exceptionResult.message == 'Company with id `10` does not exist'
    }
}
