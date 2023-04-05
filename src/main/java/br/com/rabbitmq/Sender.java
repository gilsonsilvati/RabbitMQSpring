package br.com.rabbitmq;

import br.com.rabbitmq.util.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender {

    private static final String NAME_QUEUE = "HELLO";

    private static final Logger logger = Logger.getLogger(Sender.class.getName());

    public static void main(String[] args) throws IOException, TimeoutException {

        // Criar conexao
        // Setar informacoes
        ConnectionFactory connectionFactory = RabbitmqUtil.getConnectionFactory();

        try (Connection connection = connectionFactory.newConnection()) {
            // Criar canal
            Channel channel = connection.createChannel();

            // Declarar fila utilizada
            // Parametros: fila, exclusive, autodelete, durable, map(args)
            channel.queueDeclare(NAME_QUEUE, false, false, false, null);

            // Criar mensagem
            var message = "Hello world, this is my first created spring program.";

            // Enviar mensagem
            channel.basicPublish("", NAME_QUEUE, null, message.getBytes(StandardCharsets.UTF_8));

            logger.log(Level.INFO, "[x] - Sent: '" + message + "'");

        }

    }

}
