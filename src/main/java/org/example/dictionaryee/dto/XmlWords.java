package org.example.dictionaryee.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.example.dictionaryee.entity.Word;

import java.util.List;

@JacksonXmlRootElement(localName = "words")
public class XmlWords {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "word")
    private List<Word> words;

    public XmlWords() {}

    public XmlWords(List<Word> words) {
        this.words = words;
    }
}
