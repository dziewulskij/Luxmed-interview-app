package pl.dziewulskij.luxmedinterviewapp.api.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CompanyCollectionResponse(@JsonProperty("companies") List<CompanyResponse> companies) {
}
