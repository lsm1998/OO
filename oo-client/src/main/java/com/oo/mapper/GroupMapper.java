package com.oo.mapper;

import com.oo.domain.Group;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作者：刘时明
 * 日期：2018/9/26
 * 时间：13:48
 * 说明：
 */
@Transactional
public interface GroupMapper
{
    @Insert("Insert into `t_group`(groupName,accNumber) values(#{groupName},#{accNumber})")
    int saveGroup(Group group);

    @Update("update `t_group` set groupName=#{groupName} where id=#{id}")
    int updateGroup(Group group);

    @Update("delete from `t_group` where id=#{id}")
    int deleteGroup(int id);

    @Select("select * from `t_group` where id=#{id}")
    Group getgroupById(int id);

    @Select("select * from `t_group` where accNumber = #{acc}")
    List<Group> getGroupsByAcc(long acc);

    @Select("select count(1) from `t_friends` where myid=#{acc} and Groupid=#{Groupid}")
    int getCountByByGroupId(@Param("acc") long acc, @Param("Groupid") int Groupid);

    @Select("select * from `t_group` where accNumber=#{acc} and groupName=#{groupName}")
    Group getgroupByNameAndAcc(@Param("acc") long acc, @Param("groupName") String groupName);
}
