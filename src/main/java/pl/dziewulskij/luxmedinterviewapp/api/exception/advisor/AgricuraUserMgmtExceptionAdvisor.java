package pl.dziewulskij.luxmedinterviewapp.api.exception.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.dziewulskij.luxmedinterviewapp.api.exception.ErrorDetails;
import pl.dziewulskij.luxmedinterviewapp.infrastructure.exception.CompanyNotFundException;

@Slf4j
@ControllerAdvice
public class AgricuraUserMgmtExceptionAdvisor {

    @ExceptionHandler(CompanyNotFundException.class)
    public ResponseEntity<ErrorDetails> handleCompanyNotFundException(CompanyNotFundException exception) {
        var errorDetails = ErrorDetails.of(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity
                .status(errorDetails.status())
                .body(errorDetails);
    }

}
