package ua.kostenko.carinfo.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kostenko.carinfo.common.entities.RegistrationInformationEntity;
import ua.kostenko.carinfo.rest.controllers.utils.ResponseCreatorHelper;
import ua.kostenko.carinfo.rest.data.persistent.services.SearchService;
import ua.kostenko.carinfo.rest.data.presentation.CombinedInformation;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class MainRestController {
    private final SearchService searchService;
    private final ResponseCreatorHelper responseCreatorHelper;

    @Autowired
    public MainRestController(SearchService searchService, ResponseCreatorHelper responseCreatorHelper) {
        Preconditions.checkNotNull(searchService);
        Preconditions.checkNotNull(responseCreatorHelper);
        this.searchService = searchService;
        this.responseCreatorHelper = responseCreatorHelper;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/search/{number}", method = RequestMethod.GET)
    public ResponseEntity<String> searchByNumber(@PathVariable String number) throws JsonProcessingException {
        number = number.toUpperCase();
        log.info("Processing request by url /search/{}", number);
        LocalDateTime before = LocalDateTime.now();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        List<CombinedInformation> combinedInformation = searchService.searchAllByRegistrationNumber(number.toUpperCase());
        ResponseEntity<String> responseEntity = new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combinedInformation), HttpStatus.OK);
        log.info("Processing of request by url /search/{} finished, time: {} ms", number, Duration.between(before, LocalDateTime.now()).toMillis());
        return responseEntity;
    }

}
