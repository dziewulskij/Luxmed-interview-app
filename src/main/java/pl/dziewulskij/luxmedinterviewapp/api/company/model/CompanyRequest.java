package pl.dziewulskij.luxmedinterviewapp.api.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CompanyRequest(@NotBlank @JsonProperty("name") String name,
                             @JsonProperty("departmentIds") Set<Long> departmentIds) {
}
