package com.springfamily.ms.service;

import com.springfamily.ms.dto.ToDoDto;
import com.springfamily.ms.dto.ToDoRequest;
import com.springfamily.ms.entity.ToDo;
import com.springfamily.ms.repository.ToDoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Transactional
    public ToDoDto toDoService(ToDoRequest request) {
        ToDo save = saveToDoRequestInDatabase(request);
        return createToDoResponseDtoFromDb(save);
    }

    @Transactional
    public List<ToDoDto> getAllToDoService() {
        List<ToDo> allToDo = toDoRepository.findAll();
        return allToDo.stream().map(this::createToDoResponseDtoFromDb).collect(Collectors.toList());
    }

    private ToDo saveToDoRequestInDatabase(ToDoRequest request) {
        ToDo toDo = new ToDo();
        toDo.setDescription(request.getDescription());
        toDo.setName(request.getName());
        toDo.setCreationDate(LocalDateTime.now());
        return toDoRepository.save(toDo);
    }

    private ToDoDto createToDoResponseDtoFromDb(ToDo toDo) {
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setId(toDo.getId());
        toDoDto.setDescription(toDo.getDescription());
        toDoDto.setName(toDo.getName());
        return toDoDto;
    }
}
