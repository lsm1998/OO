package com.oo.mapper;

import com.oo.domain.Friends;
import com.oo.domain.Group;
import com.oo.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 作者：刘时明
 * 日期：2018/9/26
 * 时间：14:02
 * 说明：
 */
@Transactional
public interface FriendsMapper
{
    @Insert("Insert into t_friends(myId,FriendsId,groupId) values(#{myId},#{FriendsId},#{groupId})")
    int saveFriends(Friends friends);

    @Select("SELECT * FROM t_friends WHERE myid=#{myid} and Groupid =(SELECT id FROM `t_group` WHERE groupname = #{group} and accNumber=#{myid})")
    List<Friends> getFriendsByMyAccAndGroupName(@Param("myid") int myid, @Param("group") String group);

    @Select("select * from t_user where accnumber in(SELECT FriendsId FROM t_friends WHERE myid=#{accnumber})")
    List<User> getFriendsByMyAcc(long accnumber);
}
