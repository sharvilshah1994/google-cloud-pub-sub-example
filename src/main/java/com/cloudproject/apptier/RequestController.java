package com.cloudproject.apptier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {

    private final UserRepository userRepository;

    @Autowired
    public RequestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
