package org.example.dictionaryee.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.ejb.Stateless;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.service.api.DictionaryService;


@Stateless
public class DictionaryServiceImpl implements DictionaryService {

    private DictionaryRepository dictionaryRepository;
    private final XmlMapper xmlMapper;


    public DictionaryServiceImpl() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public byte[] getWord() throws JsonProcessingException {
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return xmlMapper.writeValueAsBytes(dictionaryRepository.getWords());
    }
}
