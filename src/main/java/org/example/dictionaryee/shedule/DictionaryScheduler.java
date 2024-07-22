package org.example.dictionaryee.shedule;

import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.TaskStatus;
import org.example.dictionaryee.jms.api.Producer;
import org.example.dictionaryee.repository.api.TaskRepository;
import org.example.dictionaryee.service.api.XmlService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
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
    @Resource
    private ManagedScheduledExecutorService scheduledExecutorService;
    @EJB
    private XmlService xmlService;

    @EJB
    private Producer producer;

    @PostConstruct
    public void scheduleTask() {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            logger.info("Создание задачи");
            Task task = new Task("Создать отчёт на" + LocalDate.now(), TaskStatus.TO_PROCESS, LocalDate.now(), 0);
            taskRepository.createTask(task);
        }, 0, 30, TimeUnit.SECONDS);
        startTasks(TaskStatus.TO_PROCESS);
        startTasks(TaskStatus.ERROR);
    }

    private void startTasks(TaskStatus status) {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            logger.info("Выполнение всех задач со статусом " + status);
            completeTasks(taskRepository.findAllTasksByStatus(status));
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void completeTasks(List<Task> tasks) {
        for (Task task : tasks) {
            try {
                task.setStatus(TaskStatus.TO_PROCESS);
                taskRepository.updateTask(task);
                xmlService.createXmlDoc(task);
                producer.produceMessage();
                task.setStatus(TaskStatus.COMPLETED);
                taskRepository.updateTask(task);
            } catch (Exception e) {
                task.setStatus(TaskStatus.ERROR);
                task.setErrorMessage(e.getMessage());
                task.setAttempts(task.getAttempts() + 1);
                taskRepository.updateTask(task);
            }
        }
    }
}
