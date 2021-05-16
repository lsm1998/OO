package com.lsm1998.oo.common.net.udp;

import com.lsm1998.oo.common.net.NetConnect;

import java.io.IOException;
import java.net.SocketAddress;

public class UdpNetConnect implements NetConnect
{
    @Override
    public void close() throws IOException
    {

    }

    @Override
    public void write(byte[] bytes) throws IOException
    {

    }

    @Override
    public SocketAddress getRemoteAddress() throws IOException
    {
        return null;
    }

    @Override
    public SocketAddress getLocalAddress() throws IOException
    {
        return null;
    }
}