package com.hef.beans;

import com.hef.converter.InstanceConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.time.Instant;
import java.util.List;

/**
 * @author lifei
 * @since 2020/7/27
 */
@XStreamAlias("user")
public class User02 {

    @XStreamAsAttribute
    @XStreamAlias("id")
    private int userId;

    @XStreamAlias("username")
    private String userName;

    @XStreamConverter(InstanceConverter.class)
    private Instant birthday;

    /** 隐式集合 */
    @XStreamImplicit
    private List<Book> bookList;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "User02{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", birthday=" + birthday +
                ", bookList=" + bookList +
                '}';
    }
}
