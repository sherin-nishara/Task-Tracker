package com.example.todolist.repository;

import com.example.todolist.entity.TaskEntity;
import com.example.todolist.entity.TaskStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TaskRepository extends MongoRepository<TaskEntity, String> {

    @Query("{ 'id' : '?0' }")
    @Update("{ '$set' : { 'status' : ?1 } }")
    void updateStatusById(String id, TaskStatus status);

    @Query("{ 'id' : '?0' }")
    List<TaskEntity> findByStatus(TaskStatus status);
}
