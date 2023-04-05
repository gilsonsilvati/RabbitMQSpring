package br.com.rabbitmq.util;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtil {

    private static final String HOST = "172.22.0.2";
    private static final int PORT = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "pass123";

    private RabbitmqUtil() {
        throw new UnsupportedOperationException();
    }

    public static ConnectionFactory getConnectionFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);

        return connectionFactory;
    }

}
