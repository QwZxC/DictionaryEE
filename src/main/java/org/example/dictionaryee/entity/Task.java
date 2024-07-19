package org.example.dictionaryee.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private TaskStatus status;

    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @Column(name = "attempts", nullable = false)
    private Integer attempts;

    @Column(name = "errorMessage", length = 300)
    private String errorMessage;

    public Task(String name, TaskStatus status, LocalDate creationDate, Integer attempts) {
        this.name = name;
        this.status = status;
        this.creationDate = creationDate;
        this.attempts = attempts;
    }
}