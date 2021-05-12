package com.oo.ui;

import com.oo.domain.User;

import javax.swing.*;

/**
 * 作者：刘时明
 * 日期：2018/9/30 0030
 * 时间：23:51
 * 说明：
 */
public class CharUI extends JFrame
{
    private JLabel title;
    private JTextPane textReceive, txtSend;
    private JButton btnSend, btnClose, btnShake, btnFile, btnColor, btnFace;
    private JComboBox<String> cbFont, cbSize;
    private User myInfo, friendInfo;

    public CharUI(User myInfo, User friendInfo)
    {
        super("你正在和" + friendInfo.getNickName() + "(" + friendInfo.getAccNumber() + ")聊天");
        this.myInfo = myInfo;
        this.friendInfo = friendInfo;
        title=new JLabel("",new ImageIcon(friendInfo.getHead_img()),JLabel.LEFT);
    }
}
