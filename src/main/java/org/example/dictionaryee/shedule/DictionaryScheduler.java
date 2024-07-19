package org.example.dictionaryee.shedule;

import com.thoughtworks.xstream.XStream;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.enterprise.concurrent.ManagedScheduledExecutorService;
import org.example.dictionaryee.dto.XmlWords;
import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.entity.TaskStatus;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.repository.api.TaskRepository;

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
    private XStream xStream;

    @PostConstruct
    public void scheduleTask() {
        xStream = new XStream();
        xStream.alias("word", Word.class);
        xStream.alias("words", XmlWords.class);
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
                XmlWords words = new XmlWords(dictionaryRepository.findAllWordsByCreationDate(task.getCreationDate()));
                String xml = xStream.toXML(words);
                logger.info(xml);
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
