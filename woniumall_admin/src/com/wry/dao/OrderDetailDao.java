package com.wry.dao;

import com.wry.domain.OrderDetail;
import com.wry.domain.OrderDetailExample;
import java.util.List;

import com.wry.domain.Orders;
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

public interface OrderDetailDao {
    //查询订单明细
    List<OrderDetail> findOrderDetailVById(@Param("orderid") Integer orderid);


    @SelectProvider(type=OrderDetailSqlProvider.class, method="countByExample")
    long countByExample(OrderDetailExample example);

    @DeleteProvider(type=OrderDetailSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrderDetailExample example);

    @Delete({
        "delete from mall_order_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mall_order_item (orderid, goodsid, ",
        "nums, price)",
        "values (#{orderid,jdbcType=INTEGER}, #{goodsid,jdbcType=INTEGER}, ",
        "#{nums,jdbcType=INTEGER}, #{price,jdbcType=DECIMAL})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(OrderDetail record);

    @InsertProvider(type=OrderDetailSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(OrderDetail record);

    @SelectProvider(type=OrderDetailSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER),
        @Result(column="goodsid", property="goodsid", jdbcType=JdbcType.INTEGER),
        @Result(column="nums", property="nums", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
    })
    List<OrderDetail> selectByExample(OrderDetailExample example);

    @Select({
        "select",
        "id, orderid, goodsid, nums, price",
        "from mall_order_item",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="orderid", property="orderid", jdbcType=JdbcType.INTEGER),
        @Result(column="goodsid", property="goodsid", jdbcType=JdbcType.INTEGER),
        @Result(column="nums", property="nums", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DECIMAL)
    })
    OrderDetail selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    @UpdateProvider(type=OrderDetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OrderDetail record);

    @Update({
        "update mall_order_item",
        "set orderid = #{orderid,jdbcType=INTEGER},",
          "goodsid = #{goodsid,jdbcType=INTEGER},",
          "nums = #{nums,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderDetail record);
}