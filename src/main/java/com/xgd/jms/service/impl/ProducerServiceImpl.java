package com.xgd.jms.service.impl;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.xgd.jms.service.ProducerService;

@Component  
public class ProducerServiceImpl implements ProducerService {   
	//why environment.getProperty("redis.port") can not get the config value from properties file?
//	@Autowired
//	private Environment environment;
	
	//get the config value from properties file through annotation @Value by ${}
	//the same as get the config value from properties file in XML config file by ${}
	@Value("${redis.port}")
	private String port;
	
    @Autowired  
    private JmsTemplate jmsTemplate;

    @Autowired  
    @Qualifier("responseQueue")
    private Destination responseDestination;
    
    public void sendMessage(Destination destination, final String message) { 
//    	System.out.println("redis.port=" + environment.getProperty("redis.port"));
    	System.out.println("redis.port=" + this.port);
    	
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