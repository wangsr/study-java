import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.wsr.stu.ioc.xml.DemoService;

/**
 * Created by wangshengren on 16/10/28.
 */
public class SpringXML {
    public static void main(String[] args) {
        //classpath
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"service.xml", "dao.xml"});

        DemoService service = applicationContext.getBean(DemoService.class);
        System.out.println(service.getDemoDao());

        //filesystem
        //        applicationContext = new FileSystemXmlApplicationContext(new String[] {"service.xml", "dao.xml"});
        //        service = applicationContext.getBean(DemoService.class);
        //        System.out.println(service.getDemoDao());

    }
}
