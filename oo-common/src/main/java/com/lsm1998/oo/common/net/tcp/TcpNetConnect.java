package com.lsm1998.oo.common.net.tcp;

import com.lsm1998.oo.common.net.NetConnect;

import java.nio.channels.SocketChannel;

public class TcpNetConnect implements NetConnect
{
    protected SocketChannel socketChannel;

    protected TcpNetConnect(SocketChannel socketChannel)
    {
        this.socketChannel = socketChannel;
    }
}