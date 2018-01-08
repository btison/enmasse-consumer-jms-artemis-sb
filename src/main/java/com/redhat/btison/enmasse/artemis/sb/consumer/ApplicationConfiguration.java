package com.redhat.btison.enmasse.artemis.sb.consumer;

import javax.jms.ConnectionFactory;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ConfigurationProperties(prefix = "spring.artemis")
public class ApplicationConfiguration {

    private String url;

    private String user;

    private String password;

    private boolean subscriptionShared;

    @Bean
    public JacksonJsonProvider jsonProvider(ObjectMapper objectMapper) {
        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(objectMapper);
        return provider;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactory cf = new ActiveMQConnectionFactory(url, user, password);
        return cf;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            DefaultJmsListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setSubscriptionShared(subscriptionShared);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSubscriptionShared(boolean subscriptionShared) {
        this.subscriptionShared = subscriptionShared;
    }
}
