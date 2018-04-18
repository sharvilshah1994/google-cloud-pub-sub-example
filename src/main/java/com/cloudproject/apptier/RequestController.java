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

    // TODO: SHIVANI : handle the request here by making a function! Call the function you make inside the consumer.

    /* TODO: SHIVANI: Inside the function, find the user from db by the id in message. If user not found, send
    message to producer accordingly that "user not found". If user found, get the location and find the doctors in
    3 mile radius by getting all the doctors from the database. Making another function here (private) for
    filtering the location. When you get the list of doctors - 1. Send push notification to doctor using
    Firebase messaging 2. Send text message to doctor using Twilio (Refer zip file sent by runyu)
    33.3333 123.06 - haversine formula*/
}
