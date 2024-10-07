package pl.dziewulskij.luxmedinterviewapp.domain;

import java.util.List;

public interface CompanyRepository {

    List<Company> findAll();

    Company findByIdOrElseThrow(Long id);

    Company save(Company company);

    void deleteByIdOrElseThrow(Long id);

}
