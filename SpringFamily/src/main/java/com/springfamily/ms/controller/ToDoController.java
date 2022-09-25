package com.springfamily.ms.controller;

import com.springfamily.ms.config.aop.LogTime;
import com.springfamily.ms.dto.ToDoDto;
import com.springfamily.ms.dto.ToDoRequest;
import com.springfamily.ms.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@Slf4j
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToDoDto createToDoAction(@RequestBody ToDoRequest request) {
        return toDoService.toDoService(request);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @LogTime
    public List<ToDoDto> getAllToDoAction() {
        return toDoService.getAllToDoService();
    }
}
