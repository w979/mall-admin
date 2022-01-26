package com.wry.dao;

import com.wry.domain.AdminInfo;
import com.wry.domain.AdminInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface AdminInfoDao {

    //根据id修改密码
    @Update("update mall_admin set password=#{pwd} where id=#{id}")
    int updatePwd(@Param("id") Integer id,@Param("pwd") String pwd);

    @SelectProvider(type=AdminInfoSqlProvider.class, method="countByExample")
    long countByExample(AdminInfoExample example);

    @DeleteProvider(type=AdminInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(AdminInfoExample example);

    @Delete({
        "delete from mall_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mall_admin (account, password, ",
        "role, lastlogintime, ",
        "lastloginip, status)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{role,jdbcType=INTEGER}, #{lastlogintime,jdbcType=TIMESTAMP}, ",
        "#{lastloginip,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AdminInfo record);

    @InsertProvider(type=AdminInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AdminInfo record);

    @SelectProvider(type=AdminInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="lastlogintime", property="lastlogintime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastloginip", property="lastloginip", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    List<AdminInfo> selectByExample(AdminInfoExample example);

    @Select({
        "select",
        "id, account, password, role, lastlogintime, lastloginip, status",
        "from mall_admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="role", property="role", jdbcType=JdbcType.INTEGER),
        @Result(column="lastlogintime", property="lastlogintime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lastloginip", property="lastloginip", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER)
    })
    AdminInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AdminInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AdminInfo record, @Param("example") AdminInfoExample example);

    @UpdateProvider(type=AdminInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AdminInfo record, @Param("example") AdminInfoExample example);

    @UpdateProvider(type=AdminInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AdminInfo record);

    @Update({
        "update mall_admin",
        "set account = #{account,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "role = #{role,jdbcType=INTEGER},",
          "lastlogintime = #{lastlogintime,jdbcType=TIMESTAMP},",
          "lastloginip = #{lastloginip,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AdminInfo record);
}