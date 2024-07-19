package org.example.dictionaryee.entity;


import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlElement
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "dictionaryType", nullable = false)
    @XmlElement
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
