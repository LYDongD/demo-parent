package com.liam.demo.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Liam
 * @date 2019/5/1 上午11:38
 */
public class Bootstrap {

    private static final String FILE_PATH ="/Users/lee/Desktop/test.txt";

    public static void main(String args[]) throws Exception{
        User user = new User();
        user.setId(1);
        user.setName("liam");

        //序列化并写入文件
        FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(user);

        //反序列化,修改序列化版本id后，会导致反序列失败
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
        User userDeserialization = (User)objectInputStream.readObject();
        System.out.println(userDeserialization.toString());

    }
}
