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

## 二、批量更新数据

```java
    /**
     * 批量更新数据
     * @param forumList
     */
    public void addForums(List<Forum> forumList){
        jdbcTemplate.batchUpdate(ADD_FORUM_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Forum forum = forumList.get(i);
                preparedStatement.setString(1, forum.getForumName());
                preparedStatement.setString(2, forum.getForumDesc());
            }
            /**
             * 指定该批次的记录数
             * @return
             */
            @Override
            public int getBatchSize() {
                return forumList.size();
            }
        });
    }
```

## 三、查询数据

处理结果集有两种方案：

采用`RowMapper`的操作方式是先获取数据，然后再处理数据；而RowCallbackHandler的操作方式是一边获取数据一边处理，处理完就丢弃。因此，可以将RowMapper看作采用批量化数据处理逻辑，而RowCallbackHandler则采用流化处理策略。

> 如果使用RowMapper，那么采用的方式是将结果集中的所有数据都放到一个List<T>对象中，这样将会占用大量的JVM内存。

方案一： 使用`RowCallbackHandler`

```
    /**
     * 使用 RowCallbackHandler 查询多个
     * @param forumId
     * @param forumId02
     * @return
     */
    public List<Forum> getForumByForumIds(int forumId, int forumId02){
        String sql = "select forum_id, forum_name, forum_desc from t_forum where forum_id in (?,?) ";
        List<Forum> forumList = new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{forumId, forumId02}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                forumList.add(forum);
            }
        });
        return forumList;
    }
```

方案二： 使用`RowMapper`

```
    /**
     * 通过 RollMapper 查询多个
     * @param forumId
     * @param forumId02
     * @return
     */
    public List<Forum> getForumByForumIdsAndRollMapper(int forumId, int forumId02){
        String sql = "select forum_id, forum_name, forum_desc from t_forum where forum_id in (?,?) ";
        return jdbcTemplate.query(sql, new Object[]{forumId, forumId02}, new RowMapper<Forum>() {
            @Override
            public Forum mapRow(ResultSet resultSet, int i) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                return forum;
            }
        });
    }
```

## 四、BLOB/CLOB类型数据的操作

LOB代表大对象数据，包括BLOB和CLOB两种类型，前者用于存储大块的二进制数据，如图片数据、视频数据等（一般不宜将文件存储到数据库中，而应存储到文件服务器中）；而后者用于存储长文本数据；

| 数据库     | BLOB  | CLOB     |
| ---------- | ----- | -------- |
| Oracle     | BLOB  | CLOB     |
| MySQL      | BLOB  | LONGTEXT |
| SQL Server | IMAGE | TEXT     |

- 有些数据库到大对象类型可以像简单类型一样访问，如MySQL的LONGTEXT的操作方式和VARCHAR类型一样。

- 一般情况下，LOB类型数据的访问方式不同于其他简单类型的数据，用户可能会以流的方式操作LOB类型的数据；

>   MySQL中BLOB是个类型系列，包括：TinyBlob、Blob、MediumBlob、LongBlob，这几个类型之间的唯一区别是在存储文件的最大大小上不同。
> 　　类型     大小(单位：字节)
> 　　TinyBlob   最大255
> 　　Blob     最大65K
> 　　MediumBlob  最大16M
> 　　LongBlob   最大4G
>
> 在BLOB中存储大型文件，MYSQL提供了很强的灵活性！允许的最大文件大小，可以在配置文件中设置。
> 1）Windows中在文件my.ini中配置(在系统盘)
>   [mysqld]
>   set-variable = max_allowed_packet=10M
>
>
> 2）linux通过etc/my.cnf
>   [mysqld]
>   max_allowed_packet = 10M 

> alter  table t_post modify post_attach  MediumBlob COMMENT '二进制流，图片，视频'; 

### 4.1 插入LOB类型的数据

以mysql为例：

如果不是Oracle 9i 的数据库，只需要简单地配置一个`DefaultLobHandler`就可以了：[《完整的配置》](https://github.com/hefrankeleyn/ProficientSpring/blob/master/chapter13/src/main/resources/spring-db.xml)

Oracle 9i还需要配置 `NativeJdbcExtractor`。

```xml
<!--  只要不是Oracle 9i 的数据库，即Oracle 10g 或其他数据库，则只要简单地配置一个DefaultLobHandler就可以了 -->
    <bean id="lobHandler" class="org.springframework.jdbc.support.lob.DefaultLobHandler" lazy-init="true"/>
```

###  4.2 读取LOB类型的数据

#### （1） 以块数据方式读取LOB数据

由于Lob数据的体积可能很大（如100MB），如果直接以块的方式操作LOB数据，则需要消耗大量的内存，直接影响程序的整体运行。对于体积很大的LOB数据，可以使用流的方式进行访问。

```java
    /**
     * 以块数据方式读取LOB数据
     * 由于Lob数据的体积可能很大（如100MB），如果直接以块的方式操作LOB数据，则需要消耗大量的内存，直接影响程序的整体运行。对于体积很大的LOB数据，可以使用流的方式进行访问。
     * @param userId
     * @return
     */
    public List<Post> getAttachs(final int userId){
        String sql = "select post_id, post_attach from t_post where user_id=? and post_attach is not null";
        return jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Post>() {
            @Override
            public Post mapRow(ResultSet resultSet, int i) throws SQLException {
                int postId = resultSet.getInt(1);
                byte[] attach = lobHandler.getBlobAsBytes(resultSet, 2);

                Post post = new Post();
                post.setPostId(postId);
                post.setPostAttach(attach);
                return post;
            }
        });
    }
```

#### （2）以流数据方式读取LOB数据

由于Lob数据的体积可能很大（如100MB），如果直接以块的方式操作LOB数据，则需要消耗大量的内存，直接影响程序的整体运行。对于体积很大的LOB数据，可以使用流的方式进行访问。

```java
    /**
     * 以流的形式读取LOB数据
     * @param postId
     * @param outputStream
     */
    public void getAttachsByStream(int postId, OutputStream outputStream){
        String sql = "select post_attach from t_post where post_id=? and post_attach is not null";
        jdbcTemplate.query(sql, new Object[]{postId}, new AbstractLobStreamingResultSetExtractor<Object>() {

            @Override
            protected void handleNoRowFound() throws DataAccessException {
                System.out.println("Not Found result!");
            }
            @Override
            protected void streamData(ResultSet resultSet) throws SQLException, IOException, DataAccessException {
                InputStream is = lobHandler.getBlobAsBinaryStream(resultSet, 1);
                if (is !=null){
                    FileCopyUtils.copy(is, outputStream);
                }
            }
        });
    }
```

```java
    /**
     * 以流数据方式读取LOB数据
     * @throws FileNotFoundException
     */
    @Test
    public void getAttachsByStreamTest() throws FileNotFoundException {
        OutputStream outputStream = new FileOutputStream("out/temp.png");
        postDao.getAttachsByStream(2, outputStream);
    }
```

## 五、自增键和行集

Spring JDBC 提供了对自增键及行集的支持

### 5.1 自增键的使用

**Spring允许用户在应用层产生主键值。**为此，定义了：

`org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer`， 提供了两种产生主键的方案：**第一，通过序列产生主键**；**第二，通过表产生主键**。根据主键产生方式及数据库类型的不同，Spring提供了若干实现类。

#### （1）以表方式产生主键值

第一步： 在MySQL数据库中创建一张用于维护t_post主键的t_post_id表。需要为该表提供初始值，以便后续主键值可以在此基础之上进行递增。

```sql
create table t_post_id(sequence_id int(11)) ENGINE=MyISAM;
insert into t_post_id(sequence_id) values(0);
```

第二步：配置`MySQLMaxValueIncrementer`

```xml
<!--    配置一个用于产生主键的递增器 
      incrementerName: 设置维护主键的表名
      columnName： 用于生成主键值的别名
      cacheSize： 缓存大小，（一次返回主键的个数。先从缓存中取值，取完了，才会再递增查询）
-->
    <bean id="incrementer"
          class="org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer"
          p:incrementerName="t_post_id"  
          p:columnName="sequence_id"
          p:cacheSize="10"
          p:dataSource-ref="dataSource"/>
```

第三步：使用

```java
    /**
     * 使用递增迭代器 进行自增主键
     * @param post
     */
    public void addPostWithIncrementer(Post post){
        String sql = "insert into t_post(post_id, topic_id,forum_id, user_id, post_text, post_attach) " +
                " values(?,?,?,?,?,?)";
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                // 使用递增迭代器
                preparedStatement.setInt(1, incrementer.nextIntValue());
                preparedStatement.setInt(2, post.getTopicId());
                preparedStatement.setInt(3, post.getForumId());
                preparedStatement.setInt( 4, post.getUserId());
                lobCreator.setClobAsString(preparedStatement, 5, post.getPostText());
                lobCreator.setBlobAsBytes(preparedStatement, 6, post.getPostAttach());
            }
        });
    }
```

#### （2）`DataFieldMaxValueIncrementer`存在的问题

基于序列表的方式创建主键值，应该考虑两个层面的并发问题：

- 其一，应用层获取主键的并发问题，

  Spring的DataFieldMaxValueIncrementer实现类已经对获取主键值的代码进行了同步，因此保证了同一JVM内应用不会产生并发问题；

- 其二，全局的并发问题

  如果应用是集群部署的，所有集群节点都通过同一张序列表获取主键值，那么就必须对这张序列表进行乐观锁定（序列表必须添加一个版本或时间戳字段），以防止集群节点的并发问题。

  **Spring的DataFieldMaxValueIncrementer实现类并没有对序列表进行乐观锁定，因此，如果应用需要集群部署，那么Spring对DataFieldMaxValueIncrementer实现类是有全局并发问题的，必须自己实现DataFieldMaxValueIncrementer接口，以解决全局并发的问题。**

### 5.2 规划主键的方案

#### （1）“应用层主键“方案，新数据的主键分配由应用层负责

采用UUID或者使用DataFieldMaxValueIncrementer生成主键都属于这一类型。

#### （2）”数据库主键方案“，新数据的主键分配由数据库负责

在定义表结构时将主键列设置为auto increment， 或者通过表的触发器分配主键

## 六、行集`SQLRowSet`

- 连接行集：绑定一个数据连接并在其整个生命周期中维护该连接；
- 非连接行集：先绑定一个数据连接，获取数据后就关闭它。非连接行集可以在断开连接时更改数据，然后重新绑定数据连接，并将对数据的更改同步到数据库中。

**对于大结果集的数据，使用SqlRowSet会造成很大的内存消耗，这点要铭记。**

```java
    /**
     * 返回行集数据
     * 对于大结果集的数据，使用SqlRowSet会造成很大的内存消耗，这点要铭记
     * @param userId
     * @return
     */
    public SqlRowSet getTopicRowSet(int userId){
        String sql = "select topic_id, topic_title from t_topic where user_id=?";
        return jdbcTemplate.queryForRowSet(sql, userId);
    }
```

## 七、`NamedParameterJdbcTemplate`模版类

`NamedParameterJdbcTemplate`支持命名参数变量的SQL。在SQL语句中声明命名参数的格式是：“:paramName”。

第一步： 配置`NamedParameterJdbcTemplate`

```xml
<!--    配置一个可以使用 命名参数的 JdbcTemplate -->
    <bean id="namedParameterJdbcTemplate" class="org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate">
        <constructor-arg ref="dataSource"/>
    </bean>
```

第二步：使用

```java
    /**
     * 使用 NamedParameterJdbcTemplate
     * @param topic
     * @return
     */
    public List<Topic> findTopicList(Topic topic){
        String sql = "select topic_title from t_topic where user_id=:userId";
        Map<String, Integer> paramMap = new HashMap<>();
        paramMap.put("userId", topic.getUserId());
        return namedParameterJdbcTemplate.query(sql, paramMap, new RowMapper<Topic>() {
            @Override
            public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
                Topic oneTopic = new Topic();
                oneTopic.setTopicTitle(resultSet.getString("topic_title"));
                return oneTopic;
            }
        });
    }
```

