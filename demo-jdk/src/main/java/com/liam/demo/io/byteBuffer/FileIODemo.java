package com.liam.demo.io.byteBuffer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 文件IO的两种方式
 */
public class FileIODemo {

    private static final String FILE_NAME = "/Users/lee/Desktop/test.txt";

    /**
     * 采用IO面向流的方式读取文件数据
     * @throws IOException
     */
    public void readDataIO() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(FILE_NAME), "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String data;
        while ((data = bufferedReader.readLine()) != null) {
            System.out.println(data);
        }

        inputStreamReader.close();
        bufferedReader.close();
    }

    /**
     * 采用NIO，面向channle和buffer的方式读取文件数据
     */
    public void readDataNIO() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024); //堆外内存
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        RandomAccessFile randomAccessFile = new RandomAccessFile(FILE_NAME, "rw");
        try(FileChannel fileChannel = randomAccessFile.getChannel()){
            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    decoder.decode(byteBuffer, charBuffer, false);
                    charBuffer.flip();
                    System.out.print(charBuffer);
                }
                charBuffer.clear();
                byteBuffer.clear();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws IOException {
        new FileIODemo().readDataIO();
        System.out.println("----------");
        new FileIODemo().readDataNIO();
    }
}
