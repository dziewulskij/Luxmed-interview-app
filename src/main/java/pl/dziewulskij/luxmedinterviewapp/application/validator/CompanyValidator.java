package pl.dziewulskij.luxmedinterviewapp.application.validator;

import org.springframework.stereotype.Component;
import pl.dziewulskij.luxmedinterviewapp.api.company.model.CompanyRequest;

@Component
public class CompanyValidator {

    public void validateCreation(CompanyRequest request) {
        // TODO [ Not implemented due to task description ]
        //  Code checking if:
        //  1. Name is available
        //  2. All passed department ids dont have assigned company
    }

    public void validateModification(CompanyRequest request) {
        // TODO [ Not implemented due to task description ]
        //  Code checking if:
        //  1. Name is available
        //  2. All passed department ids dont have assigned company
    }

}
