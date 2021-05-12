package com.oo.server;

import com.google.gson.Gson;
import com.oo.domain.User;
import com.oo.mapper.UserMapper;

import java.io.*;
import java.net.Socket;

/**
 * 作者：刘时明
 * 日期：2018/9/26
 * 时间：16:28
 * 说明：服务器线程
 */
public class ServerThread extends Thread
{
    private Socket socket;
    private BufferedReader br;
    private UserMapper userMapper = App.context.getBean(UserMapper.class);

    public ServerThread(Socket socket)
    {
        this.socket = socket;
        try
        {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try
        {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            String str = "ml_ip=" + socket.getInetAddress().toString() + "-" + socket.getPort();
            System.out.println("服务端发送数据：" + str);
            ps.println(str);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            String str;
            while ((str = getMsg()) != null)
            {
                System.out.println("接收消息：" + str);
                if (str.startsWith("ml_ob="))
                {
                    StringBuilder sb = new StringBuilder(str);
                    sb.delete(0, 6);
                    Gson gson = new Gson();
                    User user = gson.fromJson(sb.toString(), User.class);
                    System.out.println("接收对象：" + user);
                    ServerUI.userMap.put(socket, user);
                    userMapper.changeFlag(ServerUI.userMap.get(socket).getAccNumber(), (byte) 1);
                    System.out.println("一个用户连接完成，当前人数：" + ServerUI.userMap.size());
                    ServerUI.flash();
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private String getMsg()
    {
        try
        {
            return br.readLine();
        } catch (IOException e)
        {
            userMapper.changeFlag(ServerUI.userMap.get(socket).getAccNumber(), (byte) 0);
            ServerUI.userMap.remove(socket);
            System.out.println("一个用户退出，当前人数：" + ServerUI.userMap.size());
            ServerUI.flash();
        }
        return null;
    }
}
