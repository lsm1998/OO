package com.lsm1998.oo.handler;

import com.lsm1998.oo.common.net.NetConnect;
import com.lsm1998.oo.common.net.NetEventHandlerAdapter;

import java.io.IOException;

public class NetEventHandlerImpl extends NetEventHandlerAdapter
{
    @Override
    public void accept(NetConnect conn) throws IOException
    {
        System.out.println("NetConnect join!");
    }

    @Override
    public void read(byte[] data, NetConnect conn) throws IOException
    {
        System.out.println("收到数据:" + new String(data));
    }

    @Override
    public void close(NetConnect conn) throws IOException
    {
        System.out.println("NetConnect close!");
    }
}
