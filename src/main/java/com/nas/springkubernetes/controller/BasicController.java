package com.nas.springkubernetes.controller;


import com.nas.springkubernetes.config.ExampleConfig;
import com.nas.springkubernetes.model.Example;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping("/")
public class BasicController {
    private final String defaultMessage;
    private final String defaultPrefix;

    public BasicController(@Autowired ExampleConfig exampleConfig) {
        this.defaultPrefix = exampleConfig.getPrefix();
        this.defaultMessage = exampleConfig.getMessage();
        log.info("prefix '{}'", defaultPrefix);
        log.info("message '{}'", defaultMessage);
    }


    @ResponseStatus(OK)
    @GetMapping
    public Example sayHello() {
        return new Example(String.format("%s %s", defaultPrefix, defaultMessage));
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public Example sayHelloInBody(@RequestBody String message) {
        return new Example(defaultPrefix + " " + (message != null ? message : defaultMessage));
    }
}
