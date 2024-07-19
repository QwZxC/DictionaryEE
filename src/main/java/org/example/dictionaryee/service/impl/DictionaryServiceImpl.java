package org.example.dictionaryee.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.example.dictionaryee.dto.WordDto;
import org.example.dictionaryee.dto.XmlWords;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.mapper.WordMapper;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.service.api.DictionaryService;

@Stateless
public class DictionaryServiceImpl implements DictionaryService {

    @EJB
    private DictionaryRepository dictionaryRepository;

    @Override
    public XmlWords findWords(DictionaryType type) {
        return new XmlWords(dictionaryRepository.findWords(type));
    }

    @Override
    public Word findTranslation(String value) {
        Word word = dictionaryRepository.findWordByValue(value);
        return dictionaryRepository.findTranslation(word);
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
