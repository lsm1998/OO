package com.lsm1998.oo;

import com.lsm1998.oo.common.net.NetServer;
import com.lsm1998.oo.common.net.NetServerBuild;
import com.lsm1998.oo.handler.NetEventHandlerImpl;

import java.io.IOException;

public class ServerApplication
{
    // https://www.cnblogs.com/f1194361820/p/4019575.html
    public static void main(String[] args) throws IOException
    {
        NetServer netServer = new NetServerBuild().
                host("127.0.0.1").
                port(8000).
                eventHandler(new NetEventHandlerImpl()).
                build();
        netServer.start();
    }
}
