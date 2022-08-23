package no.ntnu.idatt2104.oving5.controller;

import no.ntnu.idatt2104.oving5.model.Result;
import no.ntnu.idatt2104.oving5.service.PythonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestController {
    Logger logger = LoggerFactory.getLogger(RestController.class);

    @PostMapping("/")
    public Result result (@RequestParam(name = "script") String script) throws IOException {
        PythonService pythonService = new PythonService();
        String answer;
        answer = pythonService.execute(script);
        logger.info(String.format("Ny forespørsel; svar: %s",answer));
        return new Result(answer);
    }


}
