package com.example.todolist.service;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.TaskStatus;
import com.example.todolist.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    TaskRepository taskRepository;

    public String createTask(TaskDto taskDto) {
        TaskEntity taskEntity = TaskEntity.builder()
                .description(taskDto.getDescription())
                .status(taskDto.getStatus())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        taskRepository.save(taskEntity);
        return "Task Created!";
    }

    public String updateTask(String id, TaskDto taskDto) {
        TaskEntity taskEntity = taskRepository.findById(id).orElse(null);
        if(taskEntity == null){
            return "Task not Found!";
        }
        TaskEntity updateEntity = TaskEntity.builder()
                .id(taskEntity.getId())
                .description(taskDto.getDescription())
                .status(taskEntity.getStatus())
                .createdAt(taskEntity.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
        taskRepository.save(updateEntity);
        return "Task Updated!";
    }

    public String deleteTask(String id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElse(null);
        if(taskEntity == null){
            return "Task not Found!";
        }
        taskRepository.deleteById(id);
        return "Task Deleted!";
    }

    public List<TaskEntity> getAllTask() {
        return taskRepository.findAll();
    }

    public String updateTaskStatusById(String id, TaskStatus status){
        taskRepository.updateStatusById(id,status );
        return "Task Status Updated!";
    }

    public List<TaskEntity> getAllStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
