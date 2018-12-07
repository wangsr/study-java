import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wsr.stu.ioc.xml.DemoService;

/**
 * Created by wangshengren on 16/10/28.
 */
public class SpringAnnotation {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        DemoService service = applicationContext.getBean(DemoService.class);
        System.out.println(service.getDemoDao());
    }
}
