import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by wangshengren on 16/11/29.
 */
public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MqConfig.class);
        RabbitTemplate t = context.getBean(RabbitTemplate.class);
        //t.convertAndSend("cms-top-test", "abc", "msg04");
        t.convertAndSend("cms-direct-test", "abc", "msg09");
        System.out.println("=======");
    }
}
