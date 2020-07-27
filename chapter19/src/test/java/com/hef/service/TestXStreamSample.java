package com.hef.service;

import com.hef.services.XStreamSample;
import com.hef.services.XStreamSampleAlias;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * @author lifei
 * @since 2020/7/26
 */
public class TestXStreamSample {

    @Test
    public void xstreamTest(){
        try {
            XStreamSample.objectToXML();
            XStreamSample.XMLToObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void xstreamAliasTest(){
        try {
            XStreamSampleAlias.objectToXML();
            XStreamSampleAlias.XMLToObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
