package org.example.dictionaryee.service.api;

import org.example.dictionaryee.entity.Task;

import java.io.File;

public interface XmlService {

    void createXmlDoc(Task task);

    File getXmlDoc();
}
