package com.liam.demo.classloader;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class MyClassLoader extends ClassLoader {

    public MyClassLoader(){}

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name)
    {
        super(parent);
        this.name = name;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        //如果类名和类加载器指定的类名相同，则由自定义加载器进行加载，不走parent
        if (this.getName().equals(name)) {
            Class<?> classz = findLoadedClass(name);
            if (classz == null) {
                classz = this.findClass(name);
            }

            if (resolve) {
                resolveClass(classz);
            }
        }

        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] bytes = f2b("/Users/lee/demo-parent/demo-jdk/src/main/java/com/liam/demo/classloader/People.class");
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }


    /**
     * 读取文件byte数组
     * @param fileName
     * @return
     */
    private byte[] f2b(String fileName){
        RandomAccessFile file = null;
        FileChannel channel = null;
        byte[] bytes = null;
        try {
            /**随机存取文件对象，只读取模式**/
            file = new RandomAccessFile(fileName , "r");
            /**NIO文件通道**/
            channel = file.getChannel();
            /**NIO字节缓冲**/
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int size = (int) channel.size();
            bytes = new byte[size];
            int index = 0;
            /**从NIO文件通道读取数据**/
            while (channel.read(buffer) > 0){
                /**字节缓冲从写模式转为读取模式**/
                buffer.flip();
                while (buffer.hasRemaining()){
                    bytes[index] = buffer.get();
                    ++index;
                }
                /**字节缓冲的readerIndex、writerIndex置零**/
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    public static void main(String args[]) throws Exception{
        MyClassLoader mcl = new MyClassLoader(Thread.currentThread().getContextClassLoader(), "com.liam.demo.classloader.People");
        Class<?> clazz = Class.forName("com.liam.demo.classloader.People", true, mcl);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }

}
