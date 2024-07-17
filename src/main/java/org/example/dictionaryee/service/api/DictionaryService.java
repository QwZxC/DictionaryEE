package org.example.dictionaryee.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dictionaryee.dto.WordDto;
import org.example.dictionaryee.entity.DictionaryType;

public interface DictionaryService {

    String findWords(DictionaryType type) throws JsonProcessingException;
    String findTranslation(String value) throws JsonProcessingException;
    void createWord(WordDto word);
    void updateWord(WordDto word);
    void deleteWord(WordDto word);
}
