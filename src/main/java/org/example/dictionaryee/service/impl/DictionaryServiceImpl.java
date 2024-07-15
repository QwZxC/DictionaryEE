package org.example.dictionaryee.service.impl;

import jakarta.ejb.Stateless;
import lombok.NoArgsConstructor;
import org.example.dictionaryee.entity.DictionaryType;
import org.example.dictionaryee.entity.Word;
import org.example.dictionaryee.service.api.DictionaryService;

import java.util.UUID;


@Stateless
@NoArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    @Override
    public Word getWord() {
        return new Word(UUID.randomUUID(), "name", "11111", DictionaryType.LETTERS);
    }
}
