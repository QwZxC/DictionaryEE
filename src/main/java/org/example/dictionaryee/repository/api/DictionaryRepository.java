package org.example.dictionaryee.repository.api;

import org.example.dictionaryee.entity.Word;

import java.util.List;

public interface DictionaryRepository {

    List<Word> getWords();

}
