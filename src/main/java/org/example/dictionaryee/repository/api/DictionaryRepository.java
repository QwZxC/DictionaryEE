package org.example.dictionaryee.repository.api;

import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;

import java.util.List;

public interface DictionaryRepository {

    List<Word> findWords(DictionaryType type);
    List<Word> findTranslation(Word word);
    Word findWordByValue(String value);
    void createWord(Word word);
    void updateWord(Word word);
    void deleteWord(Word word);
}
