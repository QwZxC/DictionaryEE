package org.example.dictionaryee.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity(name = "word")
public class Word {

    @Id
    @JacksonXmlProperty(localName ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JacksonXmlProperty(localName ="value")
    @Column(name = "value", nullable = false)
    private String value;


    @JacksonXmlProperty(localName ="translation")
    @Column(name = "translation", nullable = false)
    private String translation;


    @JacksonXmlProperty
    @JacksonXmlText
    @Column(name = "creationDate", nullable = false)
    private LocalDate creationDate;

    @JacksonXmlProperty(localName ="dictionaryType")
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
