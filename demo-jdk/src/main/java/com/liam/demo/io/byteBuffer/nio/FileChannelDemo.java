package com.liam.demo.io.byteBuffer.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 通过NIO中的FileChannel + buffer模式实现文件读写
 *
 * @author Liam
 * @date 2019/4/30 上午10:48
 */
public class FileChannelDemo {


    public void write() throws Exception {

        FileChannel fileChannel = new RandomAccessFile("/Users/lee/Desktop/test.txt", "rw").getChannel();

        //构造4kb的数据
        byte[] datas = new byte[4 * 1024];
        byte[] tmps = new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', '1', '2', '3', '4'};
        for (int i = 0; i < datas.length; i++) {
            datas[i] = tmps[i % tmps.length];
        }

        //从指定位置写入
        long position = 1024L;
        fileChannel.write(ByteBuffer.wrap(datas), position);

        //从当前文件指针写入, 会覆盖前面写入的3k数据，最终文件大小应该是5k
        fileChannel.write(ByteBuffer.wrap(datas));

        //关闭通道
        fileChannel.close();
    }

    public void read() throws Exception {
        FileChannel fileChannel = new RandomAccessFile("/Users/lee/Desktop/test.txt", "rw").getChannel();

        //使用对内内存缓冲数据, buffer 大小为1k
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //从当前文件指针开始读取，一次读取1k, 需要读取5次
        int bytes = fileChannel.read(byteBuffer);
        //读取buffer数据并打印
        while (bytes != -1) {
            //每次读取让buffer position回归0
            byteBuffer.flip();
            //逐个字符读取
            while (byteBuffer.hasRemaining()) {
                System.out.println((char) byteBuffer.get());
            }
            //清空buffer
            byteBuffer.clear();
            bytes = fileChannel.read(byteBuffer);
        }

        //关闭
        fileChannel.close();
    }

    /**
     *  通过内存映射方式写
     */
    public void mmapWrite() throws Exception{

        FileChannel fileChannel = new RandomAccessFile("/Users/lee/Desktop/test.txt", "rw").getChannel();


        //构造4kb数据
        byte[] datas = new byte[4 * 1024];
        byte[] tmps = new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', '1', '2', '3', '4'};
        for (int i = 0; i < datas.length; i++) {
            datas[i] = tmps[i % tmps.length];
        }

        //通过mmap写入
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, datas.length);

        //从当前文件指针写入
        mappedByteBuffer.put(datas);

    }

    /**
     * 通过文件映射方式读
     */
    public void mmapRead() throws Exception {

        FileChannel fileChannel = new RandomAccessFile("/Users/lee/Desktop/test.txt", "rw").getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileChannel.size());
        while (mappedByteBuffer.hasRemaining()) {
            System.out.println((char) mappedByteBuffer.get());
        }
    }

    public static void main(String args[]) throws Exception{
        FileChannelDemo fileChannelDemo = new FileChannelDemo();
        fileChannelDemo.mmapRead();
    }

}
