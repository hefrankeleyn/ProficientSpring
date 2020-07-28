package com.hef.services;

import com.hef.beans.User02;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lifei
 * @since 2020/7/28
 */
public class PersistenceSample {

    public void persist(){
        List<User02> user02s = new ArrayList<>();
        User02 user02 = new User02();
        user02.setUserId(0);
        user02.setBirthday(Instant.now());
        user02.setUserName("aaa");
        user02s.add(user02);
        User02 user02_02 = new User02();
        user02_02.setUserId(0);
        user02_02.setBirthday(Instant.now());
        user02_02.setUserName("aaa");
        user02s.add(user02_02);

        File file = new File("out");
        PersistenceStrategy strategy = new FilePersistenceStrategy(file);
        List list = new XmlArrayList(strategy);
        list.addAll(user02s);
    }

    public static void main(String[] args) {
        PersistenceSample persistenceSample = new PersistenceSample();
        persistenceSample.persist();
    }
}
