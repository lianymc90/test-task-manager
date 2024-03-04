package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskCreateUpdateDto;
import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.TaskEntity;
import com.example.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@CrossOrigin(origins = "*")
@Controller
@RequestMapping( "/api")
public class TaskController {

    @Autowired
    TaskService taskService;
    @PostMapping(value = {"/create"})
    public ResponseEntity create (@RequestBody TaskCreateUpdateDto taskCreateUpdateDto){
        TaskEntity taskEntity = taskService.createTask(taskCreateUpdateDto);
        return new ResponseEntity(taskEntity, HttpStatus.CREATED);
    }
    @PutMapping(value = {"/update/{id}"})
    public ResponseEntity update (@PathVariable String id, @RequestBody TaskCreateUpdateDto taskCreateUpdateDto){
        TaskEntity taskEntity= taskService.updateTask(id, taskCreateUpdateDto);
        return new ResponseEntity(taskEntity, HttpStatus.OK);
    }


    @GetMapping(value = {"/get/{id}"})
    public @ResponseBody ResponseEntity get(@PathVariable String id){
        TaskDto taskDto = taskService.getTaskById(id);
        return new ResponseEntity(taskDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) throws Exception{
        try {
            taskService.deleteTask(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity getAll(){
        return new ResponseEntity(taskService.findAllTask(), HttpStatus.OK);
    }
}