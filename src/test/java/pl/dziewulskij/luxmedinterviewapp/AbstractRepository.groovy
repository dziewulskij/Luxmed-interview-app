package pl.dziewulskij.luxmedinterviewapp

import org.springframework.beans.factory.annotation.Autowired
import pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.company.CompanyJpaRepository
import pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.department.DepartmentJpaRepository
import spock.lang.Specification

abstract class AbstractRepository extends Specification {

    @Autowired
    protected CompanyJpaRepository companyJpaRepository
    @Autowired
    protected DepartmentJpaRepository departmentJpaRepository

}
