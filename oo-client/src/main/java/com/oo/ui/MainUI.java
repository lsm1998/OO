package com.oo.ui;

import com.oo.domain.Friends;
import com.oo.domain.Group;
import com.oo.domain.User;
import com.oo.mapper.FriendsMapper;
import com.oo.mapper.GroupMapper;
import com.oo.mapper.UserMapper;
import com.oo.server.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.List;

/**
 * 作者：刘时明
 * 日期：2018/9/26
 * 时间：11:06
 * 说明：
 */
public class MainUI extends JFrame implements MouseListener
{
    private Socket socket;
    private UserMapper userMapper = App.context.getBean(UserMapper.class);
    private FriendsMapper friendsMapper = App.context.getBean(FriendsMapper.class);
    private GroupMapper groupMapper = App.context.getBean(GroupMapper.class);
    JLabel bgImg, lblmyInfo;
    private JTabbedPane tabbedPane;

    private JButton btnFind, btnInfo,btnGroup;
    private User myInfo, friendInfo;
    private List<Group> groupList;
    private JList[] jLists;
    private List<User>[] friendsList;

    public MainUI(User myInfo, Socket socket)
    {
        this.myInfo = myInfo;
        this.socket = socket;
        groupList = groupMapper.getGroupsByAcc(myInfo.getAccNumber());
        jLists = new JList[groupList.size()];
        friendsList = new List[groupList.size()];
        this.setResizable(false);
        this.setIconImage(new ImageIcon("src\\main\\resources\\img\\logo.png").getImage());
        this.setTitle("oo");
        bgImg = new JLabel(new ImageIcon("src\\main\\resources\\img\\MainBg.jpg"));
        bgImg.setLayout(new BorderLayout());
        // 背景透明
        bgImg.setOpaque(false);
        this.add(bgImg);

        UIManager.put("TabbedPane.contentOpaque", false);
        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        for (int i = 0; i < groupList.size(); i++)
        {
            List<Friends> l = friendsMapper.getFriendsByMyAccAndGroupName((int) myInfo.getAccNumber(), groupList.get(i).getGroupName());
            String[] strs = new String[l.size()];
            for (int j = 0; j < l.size(); j++)
            {
                strs[j] = userMapper.getUserByAccNumber(l.get(j).getFriendsId()).getNickName();
                System.out.println("nickname=" + strs[j]);
            }
            jLists[i] = new JList(strs);
            List<User> temp = userMapper.getFriendByAccAndGroupName(this.myInfo.getAccNumber(), groupList.get(i).getGroupName());
            friendsList[i] = temp;
            jLists[i].setModel(new DataModel(temp));
            jLists[i].setCellRenderer(new MyHeadImg(temp));
            jLists[i].setOpaque(false);
            jLists[i].addMouseListener(this);
            tabbedPane.addTab(groupList.get(i).getGroupName(), jLists[i]);
        }
        bgImg.add(tabbedPane);
        // 设置个人信息
        JPanel panelN = new JPanel(new GridLayout(1, 2));

        lblmyInfo = new JLabel(myInfo.getNickName() + "[" + myInfo.getAutoGraph() + "]", new ImageIcon(myInfo.getHead_img()), JLabel.LEFT);
        panelN.setLayout(new GridLayout(2, 2));
        panelN.add(lblmyInfo);
        lblmyInfo.addMouseListener(this);
        JPanel panelS = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JTextField findText = new JTextField(8);
        findText.setOpaque(false);
        JButton findBtn = new JButton(new ImageIcon("src\\main\\resources\\img\\CheckBut.png"));
        findBtn.setFocusPainted(false);
        findBtn.setBorderPainted(false);
        findBtn.setOpaque(false);
        findBtn.setContentAreaFilled(false);

        JButton btn1 = new JButton(new ImageIcon("src\\main\\resources\\img\\starBut.png"));
        btn1.setFocusPainted(false);
        btn1.setBorderPainted(false);
        btn1.setOpaque(false);
        btn1.setContentAreaFilled(false);

        panelS.add(findBtn);
        panelS.add(btn1);
        panelS.setOpaque(false);
        panelN.add(panelS);
        panelN.setOpaque(false);
        bgImg.add(panelN, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        btnFind = new JButton("查找好友");
        btnFind.addActionListener(e -> System.out.println("查找好友"));
        btnInfo = new JButton("关于我们");
        btnInfo.addActionListener(e -> System.out.println("关于我们"));

        btnGroup=new JButton("分组管理");
        btnGroup.addActionListener(e -> group());

        panel.setOpaque(false);
        panel.add(btnGroup);
        panel.add(btnFind);
        panel.add(btnInfo);
        bgImg.add(panel, BorderLayout.SOUTH);
        this.setSize(300, 700);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
    }

    class DataModel extends AbstractListModel<Object>
    {
        List<User> data;

        public DataModel(List<User> data)
        {
            this.data = data;
        }


        public Object getElementAt(int index)
        {
            User info = data.get(index);
            String str;
            if (info.getFlag() == 1)
            {
                str = "";
            } else
            {
                str = "[离线]";
            }
            return str + info.getNickName() + "(" + info.getAccNumber() + ")     " + info.getAutoGraph();
        }

        public int getSize()
        {
            return data.size();
        }
    }

    class MyHeadImg extends DefaultListCellRenderer
    {
        List<User> datas;

        public MyHeadImg(List<User> datas)
        {
            this.datas = datas;
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (index >= 0 && index < datas.size())
            {
                User user = datas.get(index);
                setIcon(new ImageIcon(user.getHead_img()));
                String str;
                if (user.getFlag() == 1)
                {
                    str = "";
                } else
                {
                    str = "[离线]";
                }
                setText(str + user.getNickName() + "(" + user.getAccNumber() + ")       [" + user.getAutoGraph() + "]");
            }

            if (isSelected)
            {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else
            {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(false);
            return this;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == lblmyInfo)
        {
            if (e.getClickCount() == 2)
            {
                // 编辑资料
                new MyInfoUI(myInfo, this);
            }
        }
        for (int i = 0; i < jLists.length; i++)
        {
            if (e.getSource() == jLists[i])
            {
                if (jLists[i].getSelectedIndex() >= 0)
                {
                    friendInfo = friendsList[i].get(jLists[i].getSelectedIndex());
                }
                if (e.getButton() == 3)
                {
                    if (jLists[i].getSelectedIndex() >= 0)
                    {
                        System.out.println("右击");
                        //popMenu.show(lstFriend, e.getX(), e.getY());
                    }
                } else if (e.getClickCount() == 2)
                {
                    if (jLists[i].getSelectedIndex() >= 0)
                    {
                        System.out.println("开始聊天，好友：" + friendInfo.getNickName());
                        //openChat();
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    private void group()
    {
        new GroupUI(myInfo,this).setVisible(true);
    }

    public void flashList(List<Group> groupList)
    {
        //groupList = groupMapper.getGroupsByAcc(myInfo.getAccNumber());
        tabbedPane.removeAll();
        jLists = new JList[groupList.size()];
        friendsList = new List[groupList.size()];
        for (int i = 0; i < groupList.size(); i++)
        {
            List<Friends> l = friendsMapper.getFriendsByMyAccAndGroupName((int) myInfo.getAccNumber(), groupList.get(i).getGroupName());
            String[] strs = new String[l.size()];
            for (int j = 0; j < l.size(); j++)
            {
                strs[j] = userMapper.getUserByAccNumber(l.get(j).getFriendsId()).getNickName();
                System.out.println("nickname=" + strs[j]);
            }
            jLists[i] = new JList(strs);
            List<User> temp = userMapper.getFriendByAccAndGroupName(this.myInfo.getAccNumber(), groupList.get(i).getGroupName());
            friendsList[i] = temp;
            jLists[i].setModel(new DataModel(temp));
            jLists[i].setCellRenderer(new MyHeadImg(temp));
            jLists[i].setOpaque(false);
            jLists[i].addMouseListener(this);
            tabbedPane.addTab(groupList.get(i).getGroupName(), jLists[i]);
        }
        tabbedPane.repaint();
    }
}
