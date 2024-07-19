package org.example.dictionaryee.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.dictionaryee.entity.Word;

import java.util.List;

@XmlRootElement(name = "words")
public class XmlWords {

    @XmlElement(name = "word")
    private List<Word> words;

    public XmlWords() {}

    public XmlWords(List<Word> words) {
        this.words = words;
    }
}
