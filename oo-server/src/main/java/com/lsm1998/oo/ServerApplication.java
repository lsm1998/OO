package com.lsm1998.oo;

import com.lsm1998.oo.common.net.NetServer;
import com.lsm1998.oo.common.net.NetServerBuild;

import java.io.IOException;

public class ServerApplication
{
    public static void main(String[] args) throws IOException
    {
        NetServer netServer = new NetServerBuild().port(8000).build();
        netServer.start();
    }
}
