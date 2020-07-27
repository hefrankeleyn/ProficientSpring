package com.hef.converter;

import com.hef.utils.MyInstantUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.time.Instant;

/**
 * @author lifei
 * @since 2020/7/27
 */
public class InstanceConverter implements Converter {


    /** 判断是否为 Instant 类型 */
    @Override
    public boolean canConvert(Class aClass) {
        return Instant.class.isAssignableFrom(aClass);
    }
    @Override
    public void marshal(Object o, HierarchicalStreamWriter hierarchicalStreamWriter, MarshallingContext marshallingContext) {
        hierarchicalStreamWriter.setValue(MyInstantUtils.instantToFormatStr(MyInstantUtils.FORMAT01, (Instant)o));

    }

    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        return MyInstantUtils.strToInstant(MyInstantUtils.FORMAT01,hierarchicalStreamReader.getValue());
    }


}
