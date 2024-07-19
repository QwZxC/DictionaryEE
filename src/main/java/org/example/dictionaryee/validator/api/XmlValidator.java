package org.example.dictionaryee.validator.api;

import org.xml.sax.SAXException;

import java.io.IOException;

public interface XmlValidator {
    void validate() throws IOException, SAXException;
}
