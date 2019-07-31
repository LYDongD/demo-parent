package com.liam.demo.disruptor;


import com.lmax.disruptor.EventHandler;

/**
 * @author Liam(003046)
 * @date 2019/7/1 上午11:10
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) {
        System.out.println("Event: " + longEvent);
    }
}
