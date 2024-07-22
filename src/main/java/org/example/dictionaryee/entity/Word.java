package org.example.dictionaryee.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import org.example.dictionaryee.adapter.LocalDateAdapter;

import java.time.LocalDate;

@Entity(name = "word")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(namespace = "Words.xsd")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(name = "id", namespace = "Words.xsd")
    private Long id;

    @Column(name = "value", nullable = false)
    @XmlElement(name = "value", namespace = "Words.xsd")
    private String value;

    @Column(name = "translation", nullable = false)
    @XmlElement(name = "translation", namespace = "Words.xsd")
    private String translation;

    @Enumerated(EnumType.STRING)
    @Column(name = "dictionaryType", nullable = false)
    @XmlElement(name = "dictionary-type", namespace = "Words.xsd")
    private DictionaryType dictionaryType;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "creationDate", nullable = false)
    @XmlElement(name = "creation-date", namespace = "Words.xsd")
    private LocalDate creationDate;

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
