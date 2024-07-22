package org.example.dictionaryee.service.impl;

import lombok.SneakyThrows;
import org.example.dictionaryee.dto.XmlWords;
import org.example.dictionaryee.entity.Task;
import org.example.dictionaryee.repository.api.DictionaryRepository;
import org.example.dictionaryee.service.api.XmlService;
import org.example.dictionaryee.validator.api.XmlValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.logging.Logger;

@Stateless
public class XmlServiceImpl implements XmlService {

    @EJB
    private DictionaryRepository dictionaryRepository;
    @EJB
    private XmlValidator validator;
    private static final Logger logger = Logger.getLogger(XmlServiceImpl.class.getName());

    @Override
    @SneakyThrows
    public void createXmlDoc(Task task) {
        XmlWords words = new XmlWords(dictionaryRepository.findAllWordsByCreationDate(task.getCreationDate()));
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlWords.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File file = new File("C:\\Users\\vagin.my.kst\\IdeaProjects\\DictionaryEE\\src\\main\\resources\\xml\\Words.xml");
        marshaller.marshal(words, file);
        marshaller.marshal(words, System.out);
        validator.validate();
        logger.info("XML-файл создан");
    }

    @Override
    public File getXmlDoc() {
        return  new File("C:\\Users\\vagin.my.kst\\IdeaProjects\\DictionaryEE\\src\\main\\resources\\xml\\Words.xml");
    }
}
