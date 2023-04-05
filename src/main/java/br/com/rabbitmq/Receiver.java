package br.com.rabbitmq;

import br.com.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receiver {

    private static final String NAME_QUEUE = "HELLO";

    private static final Logger logger = Logger.getLogger(Receiver.class.getName());

    public static void main(String[] args) throws IOException, TimeoutException {

        // Criar conexao
        // Setar informacoes
        ConnectionFactory connectionFactory = RabbitmqUtil.getConnectionFactory();

        Connection connection = connectionFactory.newConnection();
        // Criar canal
        Channel channel = connection.createChannel();

        // Declarar fila utilizada
        // Parametros: fila, exclusive, autodelete, durable, map(args)
        channel.queueDeclare(NAME_QUEUE, false, false, false, null);

        // Definir Callback
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            var message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            logger.log(Level.INFO, "[*] - Received message: '" + message + "'");
        };

        // Receber mensagem
        channel.basicConsume(NAME_QUEUE, true, deliverCallback, consumerTag -> {});

    }

}
