package com.lsm1998.oo.common.net;

import java.io.IOException;

public interface NetEventHandler
{
    void accept(NetConnect conn) throws IOException;

    void read(byte[] data, NetConnect conn) throws IOException;

    void write(byte[] data, NetConnect conn) throws IOException;

    void connect(NetConnect conn) throws IOException;

    void valid(NetConnect conn) throws IOException;

    void close(NetConnect conn) throws IOException;
}
