package com.lsm1998.oo.common.net;

import java.io.IOException;
import java.net.SocketAddress;

public interface NetConnect
{
    void close() throws IOException;

    void write(byte[] bytes) throws IOException;

    SocketAddress getRemoteAddress() throws IOException;

    SocketAddress getLocalAddress() throws IOException;
}
