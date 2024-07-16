package org.example.dictionaryee.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.dictionaryee.entity.Word;

public interface DictionaryService {

    byte[] getWord() throws JsonProcessingException;
}
