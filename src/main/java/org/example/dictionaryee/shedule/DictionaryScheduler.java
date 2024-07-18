package org.example.dictionaryee.shedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.TaskStatus;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.repository.api.TaskRepository;

import jakarta.annotation.Resource;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Singleton
@Startup
public class DictionaryScheduler {

    private static final Logger logger = Logger.getLogger(DictionaryScheduler.class.getName());
    @EJB
    private TaskRepository taskRepository;
    @EJB
    private DictionaryRepository dictionaryRepository;
    @Resource
    private ManagedScheduledExecutorService scheduledExecutorService;
    private final XmlMapper xmlMapper = new XmlMapper();


    @PostConstruct
    public void scheduleTask() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            logger.info("Создание задачи");
            Task task = new Task("Создать отчёт на" + LocalDate.now(), TaskStatus.TO_PROCESS, LocalDate.now(), 0);
            taskRepository.createTask(task);
        }, 0, 1, TimeUnit.MINUTES);

        startTasks(TaskStatus.TO_PROCESS);
        startTasks(TaskStatus.ERROR);
    }

    private void startTasks(TaskStatus status) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            logger.info("Выполнение всех задач со статусом ERROR");
            completeTasks(taskRepository.findAllTasksByStatus(status));
        }, 0, 5, TimeUnit.MINUTES);
    }

    private void completeTasks(List<Task> tasks) {
        for (Task task : tasks) {
            try {
                task.setStatus(TaskStatus.TO_PROCESS);
                taskRepository.updateTask(task);
                List<Word> words = dictionaryRepository.findAllWordsByCreationDate(task.getCreationDate());
                logger.info(xmlMapper.writeValueAsString(words));
                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.updateTask(task);
            } catch (JsonProcessingException e) {
                task.setStatus(TaskStatus.ERROR);
                task.setErrorMessage(e.getMessage());
                task.setAttempts(task.getAttempts() + 1);
                taskRepository.updateTask(task);
            }
        }
    }
}
