package org.example.dictionaryee.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    private UUID uuid;
    private String value;
    private String translation;
    private DictionaryType dictionaryType;
}
