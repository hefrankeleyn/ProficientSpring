# Spring JDBC 访问数据库

[toc]

## 一、返回数据库的表自增主键值

```java
    /**
     * 添加一条记录，并返回绑定一个主键值
     * @param forum
     */
    public void addForumAndFetchId(Forum forum){
        // 创建一个主键持有者
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                /**
                 * Statement.RETURN_GENERATED_KEYS 绑定主键值
                 * Statement.NO_GENERATED_KEYS  不绑定主键值
                 */
                PreparedStatement ps = connection.prepareStatement(ADD_FORUM_SQL, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, forum.getForumName());
                ps.setString(2, forum.getForumDesc());
                return ps;
            }
        }, keyHolder);
        forum.setForumId(keyHolder.getKey().intValue());
    }
```

在实际开发中，我们并不太建议使用表自增键，因为这种方式会让开发更加复杂且降低程序移植性，在应用层中创建主键才是主流的方式，可以使用UUID或者通过一个编码引擎获取主键值。