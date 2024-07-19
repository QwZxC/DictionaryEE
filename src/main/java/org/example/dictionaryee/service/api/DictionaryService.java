package org.example.dictionaryee.service.api;

import org.example.dictionaryee.dto.WordDto;
import org.example.dictionaryee.dto.XmlWords;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;

public interface DictionaryService {

    XmlWords findWords(DictionaryType type);
    Word findTranslation(String value);
    void createWord(WordDto word);
    void updateWord(WordDto word);
    void deleteWord(WordDto word);
}
