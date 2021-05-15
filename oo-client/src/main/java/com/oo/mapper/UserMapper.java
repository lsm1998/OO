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
    @Select("select * from t_user where accNumber=#{accNumber} and passWord=#{passWord}")
    User login(User user);

    @Insert("Insert into t_user(nickName,accNumber,passWord,sex,age,birthDay,autoGraph,head_img) values(#{nickName},#{accNumber},#{passWord},#{sex},#{age},#{birthDay},#{autoGraph},#{head_img})")
    int saveUser(User user);

    @Select("select * from t_user")
    List<User> getAll();

    @Select("select * from t_user where accNumber=#{accNumber}")
    User getUserByAccNumber(int accNumber);

    @Select("select * from t_user")
    Vector<User> getMyFriend(int accNumber);

    @Insert("")
    int addFriend(long acc1, long acc2);

    @Update("update t_user set head_img=#{head_img} where accNumber=#{accNumber}")
    int updateImg(User user);

    @Select("SELECT COUNT(id) FROM `t_user`")
    long getAccNumber();

    @Select("SELECT * FROM `t_user` WHERE accNumber in (SELECT Friendsid FROM t_friends WHERE myid=#{acc} and Groupid in (SELECT id FROM `t_group` WHERE Groupname=#{groupName}))")
    List<User> getFriendByAccAndGroupName(@Param("acc") long acc, @Param("groupName") String groupName);

    @Update("update t_user set head_img=#{head_img},nickName=#{nickName},sex=#{sex},passWord=#{passWord},birthDay=#{birthDay},autoGraph=#{autoGraph} where accNumber=#{accNumber}")
    int updateUser(User user);

    @Update("update t_user set flag=#{flag} where accNumber=#{acc}")
    int changeFlag(@Param("acc") long acc, @Param("flag") byte flag);
}
