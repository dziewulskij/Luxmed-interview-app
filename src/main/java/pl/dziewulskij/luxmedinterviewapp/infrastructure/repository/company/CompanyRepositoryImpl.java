package pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.company;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.dziewulskij.luxmedinterviewapp.domain.company.Company;
import pl.dziewulskij.luxmedinterviewapp.domain.company.CompanyRepository;
import pl.dziewulskij.luxmedinterviewapp.infrastructure.exception.CompanyNotFundException;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

    private final CompanyJpaRepository companyJpaRepository;


    @Override
    public List<Company> findAll() {
        return companyJpaRepository.findAll();
    }

    @Override
    public Company findByIdOrElseThrow(Long id) {
        return companyJpaRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFundException(id));
    }

    @Override
    public Company save(Company company) {
        return companyJpaRepository.save(company);
    }

    @Override
    public void deleteByIdOrElseThrow(Long id) {
        if (companyJpaRepository.existsById(id)) {
            companyJpaRepository.deleteById(id);
        } else {
            throw new CompanyNotFundException(id);
        }
    }
}
