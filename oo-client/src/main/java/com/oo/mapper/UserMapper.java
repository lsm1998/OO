package com.oo.mapper;

import com.oo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Vector;

/**
 * 作者：刘时明
 * 日期：2018/9/25
 * 时间：18:18
 * 说明：User数据库操作类
 */
@Transactional
public interface UserMapper
{
    @Select("select * from user where accNumber=#{accNumber} and passWord=#{passWord}")
    User login(User user);

    @Insert("Insert into user(nickName,accNumber,passWord,sex,age,birthDay,autoGraph,head_img) values(#{nickName},#{accNumber},#{passWord},#{sex},#{age},#{birthDay},#{autoGraph},#{head_img})")
    int saveUser(User user);

    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user where accNumber=#{accNumber}")
    User getUserByAccNumber(int accNumber);

    @Select("select * from user")
    Vector<User> getMyFriend(int accNumber);

    @Insert("")
    int addFriend(long acc1, long acc2);

    @Update("update user set head_img=#{head_img} where accNumber=#{accNumber}")
    int updateImg(User user);

    @Select("SELECT COUNT(id) FROM `user`")
    long getAccNumber();

    @Select("SELECT * FROM `user` WHERE accNumber in (SELECT Friendsid FROM friends WHERE myid=#{acc} and Groupid in (SELECT id FROM `group` WHERE Groupname=#{groupName}))")
    List<User> getFriendByAccAndGroupName(@Param("acc") long acc, @Param("groupName") String groupName);

    @Update("update user set head_img=#{head_img},nickName=#{nickName},sex=#{sex},passWord=#{passWord},birthDay=#{birthDay},autoGraph=#{autoGraph} where accNumber=#{accNumber}")
    int updateUser(User user);

    @Update("update user set flag=#{flag} where accNumber=#{acc}")
    int changeFlag(@Param("acc") long acc, @Param("flag") byte flag);
}
