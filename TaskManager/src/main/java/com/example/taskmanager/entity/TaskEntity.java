package com.example.taskmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotBlank
    @Column(length = 200, nullable = false)
    @Size(min=1,max = 200)
    private String title;

    @Column(length = 1000)
    @Size(max = 1000)
    @Nullable
    private String description;

    @Pattern(regexp = "TO DO|IN PROGRESS|COMPLETED|CANCELED", message = "The status can be: TO DO, IN PROGRESS, COMPLETED o CANCELED")
    @NotNull
    @Column(nullable = false)
    private String status;

    @Nullable
    @Column()
    private LocalDateTime createdAt;

    @Nullable
    @Column(nullable = true)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
