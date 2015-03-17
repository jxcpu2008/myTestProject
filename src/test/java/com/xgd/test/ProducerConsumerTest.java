package com.xgd.test;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xgd.jms.service.ProducerService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("/applicationContext_mq.xml")  
public class ProducerConsumerTest {
	@Autowired
	private ProducerService producerService;
	@Autowired
    @Qualifier("adapterQueue")  
    private Destination adapterQueue;  
    @Test
    public void testMessageListenerAdapter() {  
        producerService.sendMessage(adapterQueue, "测试MessageListenerAdapter");  
    }
}