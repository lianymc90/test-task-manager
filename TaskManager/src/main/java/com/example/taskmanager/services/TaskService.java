package com.example.taskmanager.services;

import com.example.taskmanager.dto.TaskCreateUpdateDto;
import com.example.taskmanager.dto.TaskDto;
import com.example.taskmanager.entity.TaskEntity;
import com.example.taskmanager.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskEntity> findAllTask(){
        return taskRepository.findAll();
    }

    public TaskDto getTaskById(String id) {
        if (id.toString().isEmpty() || id.toString().isBlank()) {
            throw new EntityNotFoundException();
        }
        TaskEntity taskEntity = getTaskByIdRepository(id);

        TaskDto taskDto = mapperToTaskDto(taskEntity);
        return taskDto;
    }

    @Transactional
    public TaskEntity createTask(TaskCreateUpdateDto taskCreateDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity = mapperToTaskEntity(taskEntity, taskCreateDto);

        return taskRepository.save(taskEntity);
    }

    @Transactional
    public TaskEntity updateTask(String id, TaskCreateUpdateDto taskCreateUpdateDto) {
        TaskEntity taskEntity = getTaskByIdRepository(id);

        taskEntity = mapperToTaskEntity(taskEntity, taskCreateUpdateDto);

        return taskRepository.save(taskEntity);
    }

    public void deleteTask(String id) {
        TaskEntity taskEntity = getTaskByIdRepository(id);
        taskRepository.delete(taskEntity);
    }


    private TaskEntity getTaskByIdRepository(String id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Entity not found with id: " + id.toString()));
    }

    /* Lo ideal es usar un mapper pero por lo pequeña que es la app decidi hacerlo manual*/
    private TaskDto mapperToTaskDto(TaskEntity taskEntity) {
        TaskDto taskDto = new TaskDto(taskEntity.getId(), taskEntity.getTitle(),
                taskEntity.getDescription(), taskEntity.getStatus(), taskEntity.getCreatedAt(),
                taskEntity.getUpdatedAt());
        return taskDto;
    }

    private TaskEntity mapperToTaskEntity(TaskEntity taskEntity, TaskCreateUpdateDto taskCreateUpdateDto) {
        taskCreateUpdateDto.setTitle(taskCreateUpdateDto.getTitle().trim());

        if (taskCreateUpdateDto.getDescription() != null) {
            taskCreateUpdateDto.setDescription(taskCreateUpdateDto.getDescription().trim());
            if (!taskCreateUpdateDto.getDescription().isEmpty() & !taskCreateUpdateDto.getDescription().isBlank()) {
                taskEntity.setDescription(taskCreateUpdateDto.getDescription());
            }
        }

        if (!taskCreateUpdateDto.getTitle().isEmpty() & !taskCreateUpdateDto.getTitle().isBlank() & taskCreateUpdateDto.getTitle() != null) {
            taskEntity.setTitle(taskCreateUpdateDto.getTitle());
        }
        if (taskCreateUpdateDto.getStatus() != null) {
            taskEntity.setStatus(taskCreateUpdateDto.getStatus());
        }

        return taskEntity;
    }

}