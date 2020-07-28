package com.hef.services;

import com.hef.beans.User;
import com.hef.beans.User02;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

import java.io.*;

/**
 * @author lifei
 * @since 2020/7/28
 */
public class ObjectStreamSample {

    private static XStream xStream;

    static {
        xStream = new XStream();

        /** 自动加载注解 */
        xStream.autodetectAnnotations(true);
    }

    public void objectToXml() throws IOException {
        User02 user02 = XStreamUser02.getUser02();

        PrintWriter pw = new PrintWriter("ObjectStreamSample02.xml");

        PrettyPrintWriter ppw = new PrettyPrintWriter(pw);

        ObjectOutputStream out = xStream.createObjectOutputStream(ppw);
        out.writeObject(user02);
        out.close();
    }

    public void xmlToObject() throws IOException, ClassNotFoundException {
        FileReader reader = new FileReader("ObjectStreamSample02.xml");
        BufferedReader bufferedReader = new BufferedReader(reader);

        ObjectInputStream input = xStream.createObjectInputStream(bufferedReader);
        User02 user02= (User02) input.readObject();
        System.out.println(user02);


    }

    public static void main(String[] args) {
        try {
            ObjectStreamSample objectStreamSample = new ObjectStreamSample();
            objectStreamSample.objectToXml();
            objectStreamSample.xmlToObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
