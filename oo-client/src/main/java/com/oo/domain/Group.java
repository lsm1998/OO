package com.oo.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 作者：刘时明
 * 日期：2018/9/24
 * 时间：20:36
 * 说明：分组实体
 */
@Data
public class Group implements Serializable
{
    private int id;
    private long accNumber;
    private String groupName;

    @Override
    public String toString()
    {
        return "Group{" +
                "id=" + id +
                ", accNumber=" + accNumber +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
