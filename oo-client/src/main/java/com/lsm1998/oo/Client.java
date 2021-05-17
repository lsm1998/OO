package com.lsm1998.oo;

import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        Socket socket = new Socket("127.0.0.1",8000);

        for (int i = 0; i < 1; i++)
        {
            socket.getOutputStream().write("hello".getBytes());
            System.out.println("send");
        }

        socket.close();
    }
}
