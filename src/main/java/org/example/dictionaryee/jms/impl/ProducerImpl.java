package org.example.dictionaryee.jms.impl;

import org.example.dictionaryee.jms.api.Producer;
import org.example.dictionaryee.service.api.XmlService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ProducerImpl implements Producer {

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "java:/jms/queue/ExpiryQueue")
    private Destination destination;
    @EJB
    private XmlService xmlService;
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void produceMessage() {
        try (Connection connection = connectionFactory.createConnection();) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(destination);
            Message message = session.createObjectMessage(xmlService.getXmlDoc());
            producer.send(message);
            session.close();
        } catch (JMSException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
