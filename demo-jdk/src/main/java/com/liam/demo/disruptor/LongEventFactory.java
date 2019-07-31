package com.liam.demo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author Liam(003046)
 * @date 2019/7/1 上午11:08
 */
public class LongEventFactory implements EventFactory<LongEvent> {


    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
