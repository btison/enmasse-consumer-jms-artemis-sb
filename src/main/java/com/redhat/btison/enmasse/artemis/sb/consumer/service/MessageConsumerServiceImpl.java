package com.redhat.btison.enmasse.artemis.sb.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerServiceImpl implements MessageConsumerService {

    @Autowired
    private MessageCounterService messageCounterService;

    @Override
    @JmsListener(destination = "${consumer.destination}", subscription= "${consumer.subscription}")
    public void processMessage(String msg) {
        messageCounterService.countMessage(msg);
    }
}
