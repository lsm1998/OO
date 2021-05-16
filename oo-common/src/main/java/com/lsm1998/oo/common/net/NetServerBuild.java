package com.lsm1998.oo.common.net;

import com.lsm1998.oo.common.net.config.NetConfig;
import com.lsm1998.oo.common.net.core.DefaultNetEventHandler;
import com.lsm1998.oo.common.net.tcp.DefaultNetServer;

import java.io.IOException;

public class NetServerBuild
{
    private NetConfig config;

    private NetEventHandler eventHandler;

    public NetServerBuild()
    {
        this.config = new NetConfig();
    }

    public NetServerBuild host(String host)
    {
        this.config.setHost(host);
        return this;
    }

    public NetServerBuild port(Integer port)
    {
        this.config.setPort(port);
        return this;
    }

    public NetServerBuild type(NetType type)
    {
        this.config.setType(type);
        return this;
    }

    public NetServerBuild type(Boolean block)
    {
        this.config.setBlock(block);
        return this;
    }

    public NetServerBuild eventHandler(NetEventHandler eventHandler)
    {
        this.eventHandler = eventHandler;
        return this;
    }

    public NetServer build() throws IOException
    {
        if (this.eventHandler == null)
        {
            this.eventHandler = new DefaultNetEventHandler();
        }
        if (this.config.getType() == NetType.TCP)
        {
            return new DefaultNetServer(config, eventHandler);
        } else if (this.config.getType() == NetType.UDP)
        {
            return null;
        }
        return null;
    }
}
