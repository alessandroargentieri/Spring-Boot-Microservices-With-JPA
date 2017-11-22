package com.quicktutorialz.learnmicroservices.ToDoMicroservice.utilities;

public class UserNotInDatabaseException extends Exception {

    public UserNotInDatabaseException(String message){
        super(message);
    }
}
