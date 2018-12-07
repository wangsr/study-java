package org.wsr.stu.ioc.xml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangshengren on 16/10/28.
 */
@Service
public class DemoService {
    @Autowired
    private DemoDao demoDao;

    public DemoDao getDemoDao() {
        return demoDao;
    }

    public void setDemoDao(DemoDao demoDao) {
        this.demoDao = demoDao;
    }
}
