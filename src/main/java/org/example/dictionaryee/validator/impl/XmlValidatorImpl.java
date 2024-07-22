package org.example.dictionaryee.validator.impl;

import org.example.dictionaryee.validator.api.XmlValidator;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Stateless
public class XmlValidatorImpl implements XmlValidator {

    private Validator validator;
    private final Logger logger = Logger.getLogger(XmlValidatorImpl.class.getName());

    @PostConstruct
    private void initValidator() throws SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source schemaFile = new StreamSource(getFile("C:/Users/vagin.my.kst/IdeaProjects/DictionaryEE/src/main/java/org/example/dictionaryee/validator/impl/Words.xsd"));
        Schema schema = factory.newSchema(schemaFile);
        validator = schema.newValidator();
    }

    private File getFile(String location) {
        return new File(location);
    }

    @Override
    public void validate() throws IOException, SAXException {
        validator.validate(new StreamSource(getFile("C:\\Users\\vagin.my.kst\\IdeaProjects\\DictionaryEE\\src\\main\\resources\\xml\\Words.xml")));
    }
}
