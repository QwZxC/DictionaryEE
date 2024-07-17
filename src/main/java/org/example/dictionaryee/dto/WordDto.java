package org.example.dictionaryee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dictionaryee.entity.DictionaryType;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordDto implements Serializable {

    private String value;
    private String translation;
    private DictionaryType dictionaryType;
}
