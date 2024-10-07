package pl.dziewulskij.luxmedinterviewapp.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyCollectionResponse;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyResponse;
import pl.dziewulskij.luxmedinterviewapp.application.mapper.CompanyMapper;
import pl.dziewulskij.luxmedinterviewapp.application.validator.CompanyValidator;
import pl.dziewulskij.luxmedinterviewapp.domain.company.CompanyRepository;
import pl.dziewulskij.luxmedinterviewapp.domain.department.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyValidator companyCreationValidator;
    private final DepartmentRepository departmentRepository;

    public CompanyCollectionResponse getAllCompanies() {
        var companies = companyRepository.findAll();
        return CompanyMapper.toResponse(companies);
    }

    public CompanyResponse getCompanyById(Long id) {
        var company = companyRepository.findByIdOrElseThrow(id);
        return CompanyMapper.toResponse(company);
    }

    @Transactional
    public CompanyResponse createCompany(CompanyRequest request) {
        companyCreationValidator.validateCreation(request);
        var company = CompanyMapper.toEntity(request);
        departmentRepository.findAllByIds(request.departmentIds()).forEach(company::addDepartment);
        companyRepository.save(company);
        return CompanyMapper.toResponse(company);
    }

    @Transactional
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        companyCreationValidator.validateModification(request);
        var company = companyRepository.findByIdOrElseThrow(id);
        var newDepartments = departmentRepository.findAllByIds(request.departmentIds());
        company.update(request, newDepartments);
        companyRepository.save(company);
        return CompanyMapper.toResponse(company);
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteByIdOrElseThrow(id);
    }

}
