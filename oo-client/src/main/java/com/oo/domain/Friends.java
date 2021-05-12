package com.oo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 作者：刘时明
 * 日期：2018/9/24 0024
 * 时间：20:38
 * 说明：好友实体
 */
@Data
public class Friends implements Serializable
{
    private int id;
    private int myId;
    private int FriendsId;
    private int groupId;

    @Override
    public String toString()
    {
        return "Friends{" +
                "id=" + id +
                ", myId=" + myId +
                ", FriendsId=" + FriendsId +
                ", groupId=" + groupId +
                '}';
    }
}
