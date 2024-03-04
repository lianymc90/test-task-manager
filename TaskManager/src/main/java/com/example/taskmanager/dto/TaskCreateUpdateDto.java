package com.example.taskmanager.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class TaskCreateUpdateDto {
    @NotBlank(message = "Task name cannot be blank")
    @Size(min=1,max = 200, message = "Task name cannot be longer than 200 characters")
    private String title;

    @Null
    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;

    @Pattern(regexp = "TO DO|IN PROGRESS|COMPLETED|CANCELED", message = "The status can be: TO DO, IN PROGRESS, COMPLETED o CANCELED")
    @NotNull(message = "Task status cannot be null")
    private String status;

}