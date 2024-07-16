package org.example.dictionaryee.entity;

import jakarta.persistence.Entity;

public enum TaskStatus {

    TO_PROCESS,
    PROCESSING,
    COMPLETED,
    ERROR
}
