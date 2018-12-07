import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangshengren on 16/11/29.
 */
@Configuration
public class MqConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername("36krplus");
        connectionFactory.setPassword("tmurQHV1BGH9lXIP");
        connectionFactory.setAddresses("rmq-rong-01.dns.36kr.com:5672");
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public TopicExchange msgPushExchange() {
        return new TopicExchange("cms-top-test");
    }

    @Bean
    public DirectExchange msgPushExchangeDirect() {
        return new DirectExchange("cms-direct-test");
    }

    @Bean
    public Queue xgPushQueue() {
        return new Queue("cms-queue-test");
    }

    @Bean
    public Binding xgBind1() {
        return BindingBuilder.bind(xgPushQueue()).to(msgPushExchange()).with("abc");
    }

    @Bean
    public Binding xgBind2() {
        return BindingBuilder.bind(xgPushQueue()).to(msgPushExchangeDirect()).with("abc");
    }

}
