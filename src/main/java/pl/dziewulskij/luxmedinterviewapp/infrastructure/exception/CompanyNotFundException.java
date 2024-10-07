package pl.dziewulskij.luxmedinterviewapp.infrastructure.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CompanyNotFundException extends RuntimeException {

    public CompanyNotFundException(Long id) {
        super("Company with id `%s` does not exist".formatted(id));
    }

}
