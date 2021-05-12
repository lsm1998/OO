package com.oo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 作者：刘时明
 * 日期：2018/9/24
 * 时间：20:23
 * 说明：用户实体
 */
@Data
public class User implements Serializable
{
    private int id;
    private String nickName;
    private long accNumber;
    private String passWord;
    private char sex;
    private byte age;
    private String birthDay;
    private String autoGraph;
    private byte[] head_img;
    private String groupName;
    private int port;
    private String ipAddr;
    private byte flag;

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", accNumber=" + accNumber +
                ", passWord='" + passWord + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", birthDay='" + birthDay + '\'' +
                ", autoGraph='" + autoGraph + '\'' +
                ", head_img=" + Arrays.toString(head_img) +
                ", groupName='" + groupName + '\'' +
                ", port=" + port +
                ", ipAddr='" + ipAddr + '\'' +
                ", flag=" + flag +
                '}';
    }
}
