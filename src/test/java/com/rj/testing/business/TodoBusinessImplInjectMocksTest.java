package com.rj.testing.business;

import com.rj.testing.api.ToDoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class TodoBusinessImplInjectMocksTest {



    @Mock
    ToDoService toDoService;

    @InjectMocks
    TodoBusinessImpl todoBusiness;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void retrieveTodosRelatedToSpring() {

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(toDoService.retrieveTodos("dummy")).thenReturn(allTodos);


        List<String> todos = todoBusiness.retrieveTodosRelatedToSpring("dummy");
        assertEquals(2, todos.size());
    }

    //Test by mocking the get method of List class
    @Test
    void mockListGet_usingBDD(){
        //Given
        List<String> listMock = mock(List.class);
        given(listMock.get(anyInt())).willReturn("Output");

        //when
        String outputString = listMock.get(1);

        //then
        assertThat(outputString, is("Output"));
    }


    @Test
    void deleteTodosNotRelatedToSpring() {

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(toDoService.retrieveTodos("Dummy")).willReturn(todos);


        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        verify(toDoService, times(2)).deleteTodo("Learn to Dance");
        verify(toDoService, never()).deleteTodo("Learn Spring");
        verify(toDoService, atLeast(2)).deleteTodo("Learn to Dance");

        //using BDD then
        then(toDoService).should(times(2)).deleteTodo("Learn to Dance");
        then(toDoService).should(never()).deleteTodo("Learn Spring");
        then(toDoService).should(never()).deleteTodo("Learn Spring MVC");
    }

    @Test
    void deleteTodosNotRelatedToSpring_usingBDD_argumentCapture(){

        List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");

        given(toDoService.retrieveTodos("Dummy")).willReturn(todos);

        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        then(toDoService).should().deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue(),is("Learn to Dance"));
    }

    @Test
    void deleteTodosNotRelatedToSpring_usingBDD_multipleArgumentCapture(){

        List<String> todos = Arrays.asList("Learn to Rock", "Learn Spring", "Learn to Dance");

        given(toDoService.retrieveTodos("Dummy")).willReturn(todos);

        //when
        todoBusiness.deleteTodosNotRelatedToSpring("Dummy");

        //then
        then(toDoService).should(times(2)).deleteTodo(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getAllValues().size(),is(2));
    }
}