package com.lsm1998.oo.common.net.core;

import com.lsm1998.oo.common.net.NetConnect;
import com.lsm1998.oo.common.net.NetEventHandler;

import java.io.IOException;

public class DefaultNetEventHandler implements NetEventHandler
{
    @Override
    public void accept(NetConnect conn) throws IOException
    {

    }

    @Override
    public void read(byte[] data, NetConnect conn) throws IOException
    {

    }

    @Override
    public void write(byte[] data, NetConnect conn) throws IOException
    {

    }

    @Override
    public void connect(NetConnect conn) throws IOException
    {

    }

    @Override
    public void valid(NetConnect conn) throws IOException
    {

    }

    @Override
    public void close(NetConnect conn) throws IOException
    {

    }
}
