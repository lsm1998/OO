package com.lsm1998.oo.common.net.tcp;

import com.lsm1998.oo.common.net.NetConnect;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpNetConnect implements NetConnect
{
    protected SocketChannel socketChannel;

    protected TcpNetConnect(SocketChannel socketChannel)
    {
        this.socketChannel = socketChannel;
    }

    public void close() throws IOException
    {
        this.socketChannel.close();
    }

    public void write(byte[] bytes) throws IOException
    {
        this.socketChannel.write(ByteBuffer.wrap(bytes));
    }

    public SocketAddress getRemoteAddress() throws IOException
    {
        return this.socketChannel.getRemoteAddress();
    }

    public SocketAddress getLocalAddress() throws IOException
    {
        return this.socketChannel.getLocalAddress();
    }
}