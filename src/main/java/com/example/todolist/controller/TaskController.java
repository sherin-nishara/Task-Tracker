package com.example.todolist.controller;

import com.example.todolist.dto.TaskDto;
import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.TaskStatus;
import com.example.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {
    TaskService taskService;

    @PostMapping("/add")
    public String add(@RequestBody TaskDto taskDto){
        return taskService.createTask(taskDto);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable String id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable String id ,@RequestBody TaskDto taskDto){
        return taskService.updateTask(id, taskDto);
    }

    @PutMapping("/update/{id}/{status}")
    public String updateStatus(@PathVariable String id, @PathVariable TaskStatus status){
        return taskService.updateTaskStatusById(id,status);
    }

    @GetMapping("/list")
    public List<TaskEntity> getAll(){
        return taskService.getAllTask();
    }

    @GetMapping("/list/{status}")
    public List<TaskEntity> getAllDone(TaskStatus status){
        return taskService.getAllStatus(status);
    }
}
