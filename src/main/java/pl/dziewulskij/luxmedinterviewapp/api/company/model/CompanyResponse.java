package pl.dziewulskij.luxmedinterviewapp.api.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CompanyResponse(@JsonProperty("id") Long id,
                              @JsonProperty("name") String name,
                              @JsonProperty("departmentIds") List<Long> departmentIds) {
}
