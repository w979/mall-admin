package com.wry.dao;

import com.wry.domain.AddressInfo;
import com.wry.domain.AddressInfoExample;
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

public interface AddressInfoDao {
    @SelectProvider(type=AddressInfoSqlProvider.class, method="countByExample")
    long countByExample(AddressInfoExample example);

    @DeleteProvider(type=AddressInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(AddressInfoExample example);

    @Delete({
        "delete from mall_address",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into mall_address (userid, accept, ",
        "telphone, province, ",
        "city, area, address, ",
        "type)",
        "values (#{userid,jdbcType=INTEGER}, #{accept,jdbcType=VARCHAR}, ",
        "#{telphone,jdbcType=VARCHAR}, #{province,jdbcType=VARCHAR}, ",
        "#{city,jdbcType=VARCHAR}, #{area,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=CHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AddressInfo record);

    @InsertProvider(type=AddressInfoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AddressInfo record);

    @SelectProvider(type=AddressInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="accept", property="accept", jdbcType=JdbcType.VARCHAR),
        @Result(column="telphone", property="telphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.CHAR)
    })
    List<AddressInfo> selectByExample(AddressInfoExample example);

    @Select({
        "select",
        "id, userid, accept, telphone, province, city, area, address, type",
        "from mall_address",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="accept", property="accept", jdbcType=JdbcType.VARCHAR),
        @Result(column="telphone", property="telphone", jdbcType=JdbcType.VARCHAR),
        @Result(column="province", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="area", property="area", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.CHAR)
    })
    AddressInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AddressInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AddressInfo record, @Param("example") AddressInfoExample example);

    @UpdateProvider(type=AddressInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AddressInfo record, @Param("example") AddressInfoExample example);

    @UpdateProvider(type=AddressInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AddressInfo record);

    @Update({
        "update mall_address",
        "set userid = #{userid,jdbcType=INTEGER},",
          "accept = #{accept,jdbcType=VARCHAR},",
          "telphone = #{telphone,jdbcType=VARCHAR},",
          "province = #{province,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "area = #{area,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=CHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AddressInfo record);
}