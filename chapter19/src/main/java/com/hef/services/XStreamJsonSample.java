package com.hef.services;

import com.hef.beans.User02;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * @author lifei
 * @since 2020/7/28
 */
public class XStreamJsonSample {

    private static XStream xStream;

    /**
     * 连续的没有分隔的Json串
     * @throws FileNotFoundException
     */
    public static void toJsonByJettisonMappedXmlDriver() throws FileNotFoundException {
        User02 user02 = XStreamUser02.getUser02();
        FileOutputStream outputStream = new FileOutputStream("out/JetisonMappedSample.json");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
        xStream = new XStream(new JettisonMappedXmlDriver());
        xStream.autodetectAnnotations(true);
        xStream.setMode(XStream.NO_REFERENCES);
        xStream.alias("user", User02.class);
        xStream.toXML(user02, writer);

    }

    /**
     * 格式良好的Json
     * @throws FileNotFoundException
     */
    public static void toJsonByJsonHierarchicalStreamDriver() throws FileNotFoundException {
        User02 user02 = XStreamUser02.getUser02();

        FileOutputStream outputStream = new FileOutputStream("out/JetisonMappedSample02.json");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
        xStream = new XStream(new JsonHierarchicalStreamDriver());
        xStream.autodetectAnnotations(true);
        xStream.alias("user", User02.class);
        xStream.toXML(user02, writer);
    }

    public static void main(String[] args) {
        try {
            toJsonByJettisonMappedXmlDriver();
            toJsonByJsonHierarchicalStreamDriver();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
