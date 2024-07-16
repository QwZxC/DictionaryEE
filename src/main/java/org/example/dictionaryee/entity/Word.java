package org.example.dictionaryee.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;


@Data
@Entity(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "creationDate", nullable = false)
    private Date creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dictionaryType", nullable = false)
    private DictionaryType dictionaryType;

    public Word() {

    }

    public Word(UUID uuid, String value, String translation) {
        this.uuid = uuid;
        this.value = value;
        this.translation = translation;
    }
}
