package com.quicktutorialz.learnmicroservices.ToDoMicroservice.utilities;

public class UserNotLoggedException extends Exception {

    public UserNotLoggedException(String message){
        super(message);
    }
}
