package pl.dziewulskij.luxmedinterviewapp.infrastructure.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziewulskij.luxmedinterviewapp.domain.company.Company;

public interface CompanyJpaRepository extends JpaRepository<Company, Long> {
}
