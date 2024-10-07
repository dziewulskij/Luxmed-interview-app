package pl.dziewulskij.luxmedinterviewapp.api.company

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import pl.dziewulskij.luxmedinterviewapp.AbstractBaseIT
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyCollectionResponse
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyResponse
import pl.dziewulskij.luxmedinterviewapp.api.exception.ErrorDetails
import pl.dziewulskij.luxmedinterviewapp.domain.company.Company
import pl.dziewulskij.luxmedinterviewapp.domain.department.Department

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CompanyControllerIT extends AbstractBaseIT {

    def 'should return OK status when get all companies'() {
        given:
        def company1 = Company.builder().name('Company 1').build()
        def company2 = Company.builder().name('Company 2').build()
        companyJpaRepository.saveAll([company1, company2])

        when:
        def result = mockMvc
                .perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andReturn()

        then:
        def actualResult = readValueAsUtf8Content(result, CompanyCollectionResponse.class)
        verifyAll {
            actualResult.companies().size() == 2
        }
    }

    def 'should return OK status when get company by id'() {
        given: 'prepare department'
        def department = Department.builder().name('Department 1').build()
        departmentJpaRepository.save(department)

        and: 'prepare company'
        def company = Company.builder()
                .name('Company 1')
                .departments([department].toSet())
                .build()
        company.addDepartment(department)
        companyJpaRepository.save(company)
        department.setCompany(company)
        departmentJpaRepository.save(department)

        when:
        def result = mockMvc
                .perform(get("/api/companies/{id}", company.id))
                .andExpect(status().isOk())
                .andReturn()


        then:
        def actualResult = readValueAsUtf8Content(result, CompanyResponse.class)
        verifyAll {
            actualResult.id() == company.id
            actualResult.name() == company.name
            actualResult.departmentIds() == company.departmentIds
        }
    }

    def 'should return NOT_FOUND status when get company by id does not exist'() {
        given:
        def id = 50000L;

        when:
        def result = mockMvc
                .perform(get("/api/companies/{id}", id))
                .andExpect(status().isNotFound())
                .andReturn()

        then:
        def actualResult = readValueAsUtf8Content(result, ErrorDetails.class)
        verifyAll {
            actualResult.status() == HttpStatus.NOT_FOUND
            actualResult.message() == 'Company with id `%s` does not exist'.formatted(id)
        }
    }

    def 'should return CREATED status when create company'() {
        given:
        def department1 = Department.builder().name('Department 1').build()
        def department2 = Department.builder().name('Department 2').build()
        departmentJpaRepository.saveAll([department1, department2])
        def creationRequest = new CompanyRequest('Dep 1', [department1.id].toSet())

        when:
        def result = mockMvc
                .perform(
                        post('/api/companies')
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(creationRequest))
                )
                .andExpect(status().isCreated())
                .andReturn()


        then:
        def actualResult = readValueAsUtf8Content(result, CompanyResponse.class)
        def actualDbCompany = companyJpaRepository.findById(actualResult.id()).get()

        verifyAll {
            actualDbCompany.name == creationRequest.name()
            actualDbCompany.departmentIds.toSet() == creationRequest.departmentIds()
        }
    }

    def 'should return NO_CONTENT status when delete company'() {
        given:
        def company = Company.builder().name('Company 1').build()
        companyJpaRepository.save(company)

        when:
        mockMvc
                .perform(delete('/api/companies/{id}', company.id))
                .andExpect(status().isNoContent())

        then:
        companyJpaRepository.findById(company.id).isEmpty()
    }

    def 'should return NOT_FOUND status when delete company with not existing id'() {
        given:
        def id = 50000L;

        when:
        def result = mockMvc
                .perform(delete('/api/companies/{id}', id))
                .andExpect(status().isNotFound())
                .andReturn()

        then:
        def actualResult = readValueAsUtf8Content(result, ErrorDetails.class)
        verifyAll {
            actualResult.status() == HttpStatus.NOT_FOUND
            actualResult.message() == 'Company with id `%s` does not exist'.formatted(id)
        }
    }

}
