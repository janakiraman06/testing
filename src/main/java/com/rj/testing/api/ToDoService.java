package com.rj.testing.api;

import java.util.List;

//External service - lets say this comes from WunderList
public interface ToDoService {

    List<String> retrieveTodos(String user);

    void deleteTodo(String todo);

}
