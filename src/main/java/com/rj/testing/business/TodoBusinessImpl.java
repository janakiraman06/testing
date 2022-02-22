package com.rj.testing.business;

import com.rj.testing.api.ToDoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {

    private ToDoService toDoService;

    public TodoBusinessImpl(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user){
        List<String> filteredTodos = new ArrayList<>();
        List<String> allTodos = toDoService.retrieveTodos(user);
        for(String todo: allTodos){
            if(todo.contains("Spring")){
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> allTodos = toDoService.retrieveTodos(user);
        for (String todo : allTodos) {
            if (!todo.contains("Spring")) {
                toDoService.deleteTodo(todo);
            }
        }
    }
}
