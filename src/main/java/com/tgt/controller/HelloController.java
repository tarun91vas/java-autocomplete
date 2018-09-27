package com.tgt.controller;

import com.tgt.model.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("/")
    public Response hello() {
        Response response = new Response();
        response.setMessage("Service is up and running");
        return response;
    }
}
