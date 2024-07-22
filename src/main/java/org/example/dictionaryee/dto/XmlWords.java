package org.example.dictionaryee.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import org.example.dictionaryee.entity.Word;

import java.util.List;

@XmlRootElement(name = "words", namespace = "Words.xsd")
@Getter
public class XmlWords {

    @XmlElement(name = "word",namespace = "Words.xsd")
    private List<Word> words;

    public XmlWords() {}

    public XmlWords(List<Word> words) {
        this.words = words;
    }
}
