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
@XmlRootElement
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    private Long id;

    @Column(name = "value", nullable = false)
    @XmlElement
    private String value;

    @Column(name = "translation", nullable = false)
    @XmlElement
    private String translation;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @Column(name = "creationDate", nullable = false)
    @XmlElement(name = "creation-date")
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dictionaryType", nullable = false)
    @XmlElement(name = "dictionary-type")
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
