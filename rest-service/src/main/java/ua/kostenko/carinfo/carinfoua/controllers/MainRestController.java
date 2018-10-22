package ua.kostenko.carinfo.carinfoua.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.kostenko.carinfo.carinfoua.controllers.utils.ResponseCreatorHelper;
import ua.kostenko.carinfo.carinfoua.data.persistent.entities.RegistrationInformationEntity;
import ua.kostenko.carinfo.carinfoua.data.persistent.services.RegistrationInformationService;
import ua.kostenko.carinfo.carinfoua.data.presentation.CombinedInformation;
import ua.kostenko.carinfo.carinfoua.utils.csv.fields.RegistrationInformationCSV;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class MainRestController {
    private final RegistrationInformationService registrationInformationService;
    private final ResponseCreatorHelper responseCreatorHelper;

    @Autowired
    public MainRestController(RegistrationInformationService registrationInformationService, ResponseCreatorHelper responseCreatorHelper) {
        Preconditions.checkNotNull(registrationInformationService);
        Preconditions.checkNotNull(responseCreatorHelper);
        this.registrationInformationService = registrationInformationService;
        this.responseCreatorHelper = responseCreatorHelper;
    }

    @RequestMapping(value = "/search/{number}", method = RequestMethod.GET)
    public ResponseEntity<String> searchByNumber(@PathVariable String number) throws JsonProcessingException {
        number = number.toUpperCase();
        log.info("Processing request by url /search/{}", number);
        LocalDateTime before = LocalDateTime.now();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        List<RegistrationInformationEntity> results = registrationInformationService.search(RegistrationInformationCSV.CAR_NEW_REGISTRATION_NUMBER, number.toUpperCase());
        List<CombinedInformation> combinedInformation = responseCreatorHelper.getCombinedInformation(results);
        ResponseEntity<String> responseEntity = new ResponseEntity<>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(combinedInformation), HttpStatus.OK);
        log.info("Processing of request by url /search/{} finished, time: {} ms", number, Duration.between(before, LocalDateTime.now()).toMillis());
        return responseEntity;
    }

}