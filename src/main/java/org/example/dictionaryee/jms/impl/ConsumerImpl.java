package org.example.dictionaryee.jms.impl;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(name = "Consumer",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/ExpiryQueue")
        })
public class ConsumerImpl implements MessageListener {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            logger.info("Received message: " + objectMessage.getObject());
        } catch (JMSException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }
}
