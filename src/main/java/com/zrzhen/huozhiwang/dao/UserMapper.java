package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select({
            "select",
            "count(*)",
            "from `user`",
            "where phone = #{phone,jdbcType=VARCHAR}",
            "and `password` = #{password,jdbcType=VARCHAR}",
            "limit 1"
    })
    int checkByPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);
    @Select({
            "select",
            "id, name, email",
            "from `user`",
            "where phone = #{phone,jdbcType=VARCHAR}",
            "and `password` = #{password,jdbcType=VARCHAR}",
            "limit 1"
    })
    User selectByPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

    @Select({
            "select",
            "count(1)",
            "from `user`",
            "where phone = #{phone,jdbcType=VARCHAR}"
    })
    int oneByPhone(@Param("phone") String phone);

    @Select({
            "select",
            "id, name, email",
            "from `user`",
            "where id = #{id,jdbcType=BIGINT}",
            "limit 1"
    })
    User oneById(@Param("id") long id);


    @Delete({
        "delete from `user`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into `user` (id,phone, ",
        " `password`)",
        "values (#{id,jdbcType=BIGINT},#{phone,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    Integer insert(User record);

   /* @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=true, resultType=Long.class)
    int insertSelective(User record);
*/
    /*
    @Select({
        "select",
        "id, email, `name`, `password`, `status`, ctime, utime",
        "from `user`",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.SMALLINT),
        @Result(column="ctime", property="ctime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="utime", property="utime", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update `user`",
        "set email = #{email,jdbcType=VARCHAR},",
          "`name` = #{name,jdbcType=VARCHAR},",
          "`password` = #{password,jdbcType=VARCHAR},",
          "`status` = #{status,jdbcType=SMALLINT},",
          "ctime = #{ctime,jdbcType=TIMESTAMP},",
          "utime = #{utime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

    @Select({
            "select",
            "id",
            "from `user`",
            "where  email= #{email,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true)
    })
    User selectIdByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    @Select({
            "select",
            "email",
            "from `user`",
            "where  id = #{id,jdbcType=BIGINT}"
    })
    String selectEmailById(long id);

    @Select({
            "select",
            "name",
            "from `user`",
            "where  email= #{email,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
    })
    String selectNameByEmailAndPassword(String email,String password);
    *//**
     * 查询指定Email的数量，查询其是否存在
     *
     * @param email
     * @return
     *//*
    @Select({
            "select",
            "count(1)",
            "from `user`",
            "where email = #{email,jdbcType=VARCHAR}"
    })
    int emailExist(String email);*/
}