package com.liam.demo.ioc;

import com.liam.demo.ioc.bean.Computer;
import com.liam.demo.ioc.container.ApplicationContext;

;

/**
 * @author Liam
 * @date 2019/4/30 下午5:21
 */
public class Bootstrap {

    public static void main(String args[]) {
        ApplicationContext applicationContext = new ApplicationContext("/Users/lee/demo-parent/demo-jdk/src/main/java/com/liam/demo/ioc/applicationContext.xml");
        Computer computer = (Computer) applicationContext.getBean("computer");
        computer.run();
    }
}
