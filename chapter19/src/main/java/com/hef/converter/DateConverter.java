package com.hef.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author lifei
 * @since 2020/7/27
 */
public class DateConverter implements Converter {

    private Locale locale;

    public DateConverter(Locale locale){
        super();
        this.locale = locale;
    }
    /** 判断要转换的类型 */
    @Override
    public boolean canConvert(Class aClass) {
        return Date.class.isAssignableFrom(aClass);
    }

    /** 实现该方法，编写Java对象道XML的转换逻辑 */
    @Override
    public void marshal(Object value,
                        HierarchicalStreamWriter hierarchicalStreamWriter,
                        MarshallingContext marshallingContext) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, this.locale);
        hierarchicalStreamWriter.setValue(formatter.format(value));
    }

    /** 实现该方法，编写Java对象到XML的转换逻辑 */
    @Override
    public Object unmarshal(HierarchicalStreamReader hierarchicalStreamReader, UnmarshallingContext unmarshallingContext) {
        GregorianCalendar calendar = new GregorianCalendar();
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, this.locale);
        try {
            calendar.setTime(formatter.parse(hierarchicalStreamReader.getValue()));
        } catch (ParseException e) {
            throw new ConversionException(e);
        }
        return calendar.getGregorianChange();
    }


}
