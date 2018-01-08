package com.redhat.btison.enmasse.artemis.sb.consumer.service;


public interface MessageConsumerService {

    void processMessage(String msg);

}
