package org.example.dictionaryee.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "translation", nullable = false)
    private String translation;

    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dictionaryType", nullable = false)
    private DictionaryType dictionaryType;

    public Word() {

    }

    public Word(String value) {
        this.value = value;
    }

    public Word(String value, String translation, DictionaryType dictionaryType) {
        this.value = value;
        this.translation = translation;
        this.dictionaryType = dictionaryType;
        creationDate = LocalDate.now();
    }
}
