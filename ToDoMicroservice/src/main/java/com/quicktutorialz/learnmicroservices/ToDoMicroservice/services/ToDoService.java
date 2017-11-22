package com.quicktutorialz.learnmicroservices.ToDoMicroservice.services;

import com.quicktutorialz.learnmicroservices.ToDoMicroservice.entities.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> getToDos(String email);

    ToDo addToDo(ToDo toDo);

    void deleteToDo(Integer id);
}
