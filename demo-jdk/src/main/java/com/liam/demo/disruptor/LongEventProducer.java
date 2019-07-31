package com.liam.demo.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author Liam(003046)
 * @date 2019/7/1 上午11:17
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            longEvent.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
