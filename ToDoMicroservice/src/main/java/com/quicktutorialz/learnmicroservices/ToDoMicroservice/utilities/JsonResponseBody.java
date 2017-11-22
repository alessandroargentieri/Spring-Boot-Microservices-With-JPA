package com.quicktutorialz.learnmicroservices.ToDoMicroservice.utilities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class JsonResponseBody {

    @Getter @Setter
    private  int server;
    @Getter @Setter
    private Object response;
}


//http response -> java object ResponseEntity<JsonResponseBody>

//header (jwt)

//body - html page or a JsonMessage(JsonResponseBody(int server, Object response))