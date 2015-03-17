package com.xgd.jms.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.xgd.jms.service.ProducerService;

@Component  
public class ProducerServiceImpl implements ProducerService {   
    @Autowired  
    private JmsTemplate jmsTemplate;  
    @Autowired  
    @Qualifier("responseQueue")
    private Destination responseDestination;
    public void sendMessage(Destination destination, final String message) {  
        System.out.println("---------------生产者发送消息-----------------");  
        System.out.println("---------------生产者发了一个消息：" + message);  
        jmsTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                TextMessage textMessage = session.createTextMessage(message);  
                //指定该消息对应的回复消息的目的地
//                textMessage.setJMSReplyTo(responseDestination);
                return textMessage;
            }
        });
    }
}