package org.example.dictionaryee.repository.api;

import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.TaskStatus;

import java.util.List;

public interface TaskRepository {

    void createTask(Task task);
    void updateTask(Task task);

    List<Task> findAllTasksByStatus(TaskStatus status);
}
