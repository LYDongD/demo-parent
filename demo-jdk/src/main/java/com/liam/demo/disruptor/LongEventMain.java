package com.liam.demo.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * @author Liam(003046)
 * @date 2019/7/1 上午11:22
 */
public class LongEventMain {

    public static void main(String args[]) throws Exception{
        LongEventFactory longEventFactory = new LongEventFactory();
        int buffSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(longEventFactory, buffSize, DaemonThreadFactory.INSTANCE,ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            byteBuffer.putLong(0, l);
            longEventProducer.onData(byteBuffer);
            Thread.sleep(1000);
        }

    }
}
