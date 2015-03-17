package com.xgd.jms.service;

import javax.jms.Destination;

public interface ProducerService {
	public void sendMessage(Destination destination, String message);
}