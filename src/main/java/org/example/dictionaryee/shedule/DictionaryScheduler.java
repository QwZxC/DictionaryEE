package org.example.dictionaryee.shedule;

import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.TaskStatus;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.repository.api.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Singleton
@Startup
public class DictionaryScheduler {

    private static final Logger logger = Logger.getLogger(DictionaryScheduler.class.getName());
    @EJB
    private TaskRepository taskRepository;
    @EJB
    private DictionaryRepository dictionaryRepository;

    @Schedule(
            minute = "*/1",
            hour = "*",
            info = "1MinScheduler",
            persistent = false)
    public void createNewTask() {
        logger.info("Создание задачи");
        Task task = new Task("Создать отчёт на" + LocalDate.now(), TaskStatus.TO_PROCESS, LocalDate.now(), 0);
        taskRepository.createTask(task);
    }

    @Schedule(
            minute = "*/2",
            hour = "*",
            persistent = false)
    public void completeNewTasks() {
        logger.info("Выполнение всех задач со статусом TO_PROCESS");
        completeTasks(taskRepository.findAllTasksByStatus(TaskStatus.TO_PROCESS));
    }

    @Schedule(
            minute = "*/2",
            hour = "*",
            persistent = false
    )
    public void completeErrorTasks() {
        logger.info("Выполнение всех задач со статусом ERROR");
        completeTasks(taskRepository.findAllTasksByStatus(TaskStatus.ERROR));
    }

    private void completeTasks(List<Task> tasks) {
        for (Task task : tasks) {
            try {
                task.setStatus(TaskStatus.TO_PROCESS);
                taskRepository.updateTask(task);
                List<Word> words = dictionaryRepository.findAllWordsByCreationDate(task.getCreationDate());
                words.forEach(word -> logger.info(word.toString()));
                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.updateTask(task);
            } catch (Exception ex) {
                task.setStatus(TaskStatus.ERROR);
                task.setErrorMessage(ex.getMessage());
                task.setAttempts(task.getAttempts() + 1);
                taskRepository.updateTask(task);
            }
        }
    }
}
