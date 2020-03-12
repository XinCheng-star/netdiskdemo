package com.chengxin.netdiskdemo.dao;

import com.chengxin.netdiskdemo.dataobject.UserDiskCapacityTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserDiskCapacityTableMapper {


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_disk_capacity_table
     *
     * @mbg.generated Thu Mar 12 16:29:34 GMT+08:00 2020
     */
    @Delete({
            "delete from user_disk_capacity_table",
            "where id = #{id,jdbcType=CHAR}"
    })
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_disk_capacity_table
     *
     * @mbg.generated Thu Mar 12 16:29:34 GMT+08:00 2020
     */
    @Insert({
            "insert into user_disk_capacity_table (id, account, ",
            "is_vip, used_disk, capacity, ",
            "create_time, modify_time)",
            "values (#{id,jdbcType=CHAR}, #{account,jdbcType=VARCHAR}, ",
            "#{isVip,jdbcType=CHAR}, #{usedDisk,jdbcType=DOUBLE}, #{capacity,jdbcType=DOUBLE}, ",
            "now(),now())"
    })
    int insert(UserDiskCapacityTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_disk_capacity_table
     *
     * @mbg.generated Thu Mar 12 16:29:34 GMT+08:00 2020
     */
    @Select({
            "select",
            "id, account, is_vip, used_disk, capacity, create_time, modify_time",
            "from user_disk_capacity_table",
            "where id = #{id,jdbcType=CHAR}"
    })
    @Results(id = "userDiskMap", value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.CHAR, id = true),
            @Result(column = "account", property = "account", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_vip", property = "isVip", jdbcType = JdbcType.CHAR),
            @Result(column = "used_disk", property = "usedDisk", jdbcType = JdbcType.DOUBLE),
            @Result(column = "capacity", property = "capacity", jdbcType = JdbcType.DOUBLE),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP)
    })
    UserDiskCapacityTable selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_disk_capacity_table
     *
     * @mbg.generated Thu Mar 12 16:29:34 GMT+08:00 2020
     */
    @Select({
            "select",
            "id, account, is_vip, used_disk, capacity, create_time, modify_time",
            "from user_disk_capacity_table"
    })
    @ResultMap(value = {"userDiskMap"})
    List<UserDiskCapacityTable> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_disk_capacity_table
     *
     * @mbg.generated Thu Mar 12 16:29:34 GMT+08:00 2020
     */
    @Update({
            "update user_disk_capacity_table",
            "set account = #{account,jdbcType=VARCHAR},",
            "is_vip = #{isVip,jdbcType=CHAR},",
            "used_disk = #{usedDisk,jdbcType=DOUBLE},",
            "capacity = #{capacity,jdbcType=DOUBLE},",
            "modify_time = now()",
            "where id = #{id,jdbcType=CHAR}"
    })
    int updateByPrimaryKey(UserDiskCapacityTable record);
}