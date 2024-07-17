package org.example.dictionaryee.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import org.example.dictionaryee.dto.WordDto;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.mapper.WordMapper;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.service.api.DictionaryService;

import java.util.List;

@Stateless
public class DictionaryServiceImpl implements DictionaryService {

    @EJB
    private DictionaryRepository dictionaryRepository;
    private final XmlMapper xmlMapper;


    public DictionaryServiceImpl() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public String findWords(DictionaryType type) throws JsonProcessingException {
        List<Word> words = dictionaryRepository.findWords(type);
        return xmlMapper.writeValueAsString(words);
    }

    @Override
    public String findTranslation(String value) throws JsonProcessingException {
        Word word = dictionaryRepository.findWordByValue(value);
        return xmlMapper.writeValueAsString(dictionaryRepository.findTranslation(word));
    }

    @Override
    public void createWord(WordDto word) {
        dictionaryRepository.createWord(WordMapper.toEntity(word));
    }

    @Override
    public void updateWord(WordDto word) {
        dictionaryRepository.updateWord(WordMapper.toEntity(word));
    }

    @Override
    public void deleteWord(WordDto word) {
        dictionaryRepository.deleteWord(WordMapper.toEntity(word));
    }
}
