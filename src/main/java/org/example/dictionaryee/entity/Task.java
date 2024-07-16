package org.example.dictionaryee.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "status", nullable = false)
    private TaskStatus status;
    @Column(name = "creationDate", nullable = false)
    private Date creationDate;
    @Column(name = "attempts", nullable = false)
    private Integer attempts;
    @Column(name = "errorMessage", length = 300)
    private String errorMessage;
}
