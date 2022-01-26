package com.wry.dao;

import com.wry.domain.Orders;
import com.wry.domain.OrdersExample;

import java.util.Date;
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

public interface OrdersDao {
    //多条件查询
    List<Orders> getOrders(@Param("orderno") String orderno,@Param("startTime") Date startTime,
                           @Param("endTime") Date endTime,@Param("status") Integer status);

    //根据id查询 关联的用户
    Orders getOrderById(@Param("id") Integer id);

    @SelectProvider(type=OrdersSqlProvider.class, method="countByExample")
    long countByExample(OrdersExample example);

    @DeleteProvider(type=OrdersSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrdersExample example);

    @Delete({
        "delete from mall_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mall_order (orderno, userid, ",
        "ordertime, accept, ",
        "telphone, address, ",
        "money, paytype, ",
        "paytime, delivertime, ",
        "receivetime, status, ",
        "isdel)",
        "values (#{orderno,jdbcType=VARCHAR}, #{userid,jdbcType=INTEGER}, ",
        "#{ordertime,jdbcType=TIMESTAMP}, #{accept,jdbcType=VARCHAR}, ",
        "#{telphone,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{money,jdbcType=DECIMAL}, #{paytype,jdbcType=INTEGER}, ",
        "#{paytime,jdbcType=TIMESTAMP}, #{delivertime,jdbcType=TIMESTAMP}, ",
        "#{receivetime,jdbcType=TIMESTAMP}, #{status,jdbcType=INTEGER}, ",
        "#{isdel,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Orders record);

    @InsertProvider(type=OrdersSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Orders record);

    @SelectProvider(type=OrdersSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderno", property="orderno", jdbcType=JdbcType.VARCHAR),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="ordertime", property="ordertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="accept", property="accept", jdbcType=JdbcType.VARCHAR),
        @Result(column="telphone", property="telphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="paytype", property="paytype", jdbcType=JdbcType.INTEGER),
        @Result(column="paytime", property="paytime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delivertime", property="delivertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receivetime", property="receivetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="isdel", property="isdel", jdbcType=JdbcType.CHAR)
    })
    List<Orders> selectByExample(OrdersExample example);

    @Select({
        "select",
        "id, orderno, userid, ordertime, accept, telphone, address, money, paytype, paytime, ",
        "delivertime, receivetime, status, isdel",
        "from mall_order",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderno", property="orderno", jdbcType=JdbcType.VARCHAR),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="ordertime", property="ordertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="accept", property="accept", jdbcType=JdbcType.VARCHAR),
        @Result(column="telphone", property="telphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="money", property="money", jdbcType=JdbcType.DECIMAL),
        @Result(column="paytype", property="paytype", jdbcType=JdbcType.INTEGER),
        @Result(column="paytime", property="paytime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="delivertime", property="delivertime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="receivetime", property="receivetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="isdel", property="isdel", jdbcType=JdbcType.CHAR)
    })
    Orders selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Orders record);

    @Update({
        "update mall_order",
        "set orderno = #{orderno,jdbcType=VARCHAR},",
          "userid = #{userid,jdbcType=INTEGER},",
          "ordertime = #{ordertime,jdbcType=TIMESTAMP},",
          "accept = #{accept,jdbcType=VARCHAR},",
          "telphone = #{telphone,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "money = #{money,jdbcType=DECIMAL},",
          "paytype = #{paytype,jdbcType=INTEGER},",
          "paytime = #{paytime,jdbcType=TIMESTAMP},",
          "delivertime = #{delivertime,jdbcType=TIMESTAMP},",
          "receivetime = #{receivetime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "isdel = #{isdel,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Orders record);
}