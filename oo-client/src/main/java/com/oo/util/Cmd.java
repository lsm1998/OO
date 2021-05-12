package com.oo.util;

public interface Cmd
{
    // 上线通知
    int CMD_ONLINE = 1000;
    // 离线
    int CMD_OFFLINE = 1001;
    // 离开
    int CMD_LEVEL = 1002;
    // 忙碌
    int CMD_BUYS = 1003;
    // 发送信息
    int CMD_SEND = 1004;
    // 发送文件
    int CMD_FILE = 1005;
    // 抖动
    int CMD_SHAKE = 1006;
    // 添加好友
    int CMD_ADDFRI = 1007;
    // 同意添加
    int CMD_ARGEE = 1008;
    // 拒绝添加
    int CMD_REFUSE = 1009;
    // 更改状态
    int CMD_CHANGESTATE = 1010;
    // 删除好友
    int CMD_DELFRIEND = 1011;
}
