package pl.dziewulskij.luxmedinterviewapp.application.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyCollectionResponse;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyResponse;
import pl.dziewulskij.luxmedinterviewapp.domain.company.Company;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyMapper {

    public static CompanyResponse toResponse(Company company) {
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getDepartmentIds()
        );
    }

    public static CompanyCollectionResponse toResponse(Collection<Company> companies) {
        var companyResponseList = companies.stream()
                .map(CompanyMapper::toResponse)
                .toList();

        return new CompanyCollectionResponse(companyResponseList);
    }

    public static Company toEntity(CompanyRequest companyRequest) {
        return Company.builder().name(companyRequest.name()).build();
    }

}
