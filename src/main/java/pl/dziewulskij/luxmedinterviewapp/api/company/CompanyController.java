package pl.dziewulskij.luxmedinterviewapp.api.company;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyCollectionResponse;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyResponse;
import pl.dziewulskij.luxmedinterviewapp.application.CompanyService;

@Validated
@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CompanyCollectionResponse getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompanyById(@PathVariable("id") Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateCompany(@PathVariable("id") Long id,
                                         @RequestBody @Valid CompanyRequest companyRequest) {
        return companyService.updateCompany(id, companyRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
    }

}
