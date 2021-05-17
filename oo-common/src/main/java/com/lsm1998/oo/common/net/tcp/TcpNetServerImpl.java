package com.lsm1998.oo.common.net.tcp;

import com.lsm1998.oo.common.net.NetEventHandler;
import com.lsm1998.oo.common.net.NetServer;
import com.lsm1998.oo.common.net.config.NetConfig;
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

public class TcpNetServerImpl implements NetServer
{
    private static final int DEFAULT_BUFF_SIZE = 4 * 1024;

    private Selector selector;

    private final NetEventHandler handler;
    private final ServerSocketChannel server;

    public TcpNetServerImpl(NetConfig config, NetEventHandler handler) throws IOException
    {
        InetSocketAddress isa = new InetSocketAddress(config.getHost(), config.getPort());
        server = ServerSocketChannel.open();
        server.bind(isa);
        server.configureBlocking(config.getBlock());
        this.handler = handler;
    }

    @Override
    public void start() throws IOException
    {
        selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0)
        {
            for (SelectionKey key : selector.selectedKeys())
            {
                selector.selectedKeys().remove(key);
                SocketChannel socketChannel;
                // 是否包含客户端请求
                if (key.isAcceptable())
                {
                    socketChannel = server.accept();
                    handler.accept(new TcpNetConnect(socketChannel));
                }
                // 是否存在读取的数据
                if (key.isReadable())
                {
                    socketChannel = (SocketChannel) key.channel();
                    TcpNetConnect netConnect = new TcpNetConnect(socketChannel);
                    ByteBuffer buffer = ByteBuffer.allocate(DEFAULT_BUFF_SIZE);
                    int len;
                    List<byte[]> bytesList = new ArrayList<>();
                    while ((len = socketChannel.read(buffer)) > 0)
                    {
                        buffer.flip();
                        bytesList.add(buffer.array());
                    }
                    if (len == -1)
                    {
                        handler.close(netConnect);
                        return;
                    }
                    handler.read(ByteUtil.mergeBytes(bytesList), netConnect);
                }
//                if (key.isConnectable())
//                {
//                    handler.close(connect);
//                }
//                if (key.isValid())
//                {
//                    handler.valid(connect);
//                }
//                if (key.isConnectable())
//                {
//                    handler.connect(connect);
//                }
            }
        }
    }

    @Override
    public void stop() throws IOException
    {
        this.selector.close();
    }
}
