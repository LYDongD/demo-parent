package com.liam.demo.gc;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCInfo {

    public static void main(String args[]) {

        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : l) {
            System.out.println(garbageCollectorMXBean.getName());
        }

        while (true){

        }
    }

}
