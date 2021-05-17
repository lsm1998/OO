package com.lsm1998.oo.common.net.udp;

import com.lsm1998.oo.common.net.NetEventHandler;
import com.lsm1998.oo.common.net.NetServer;
import com.lsm1998.oo.common.net.config.NetConfig;
import com.lsm1998.oo.common.net.tcp.TcpNetConnect;
import com.lsm1998.oo.common.utils.ByteUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class UdpNetServerImpl implements NetServer
{
    private static final int DEFAULT_BUFF_SIZE = 4 * 1024;

    private final NetEventHandler handler;

    public UdpNetServerImpl(NetConfig config, NetEventHandler handler) throws IOException
    {

        this.handler = handler;
    }

    @Override
    public void start() throws IOException
    {

    }

    @Override
    public void stop() throws IOException
    {

    }
}
