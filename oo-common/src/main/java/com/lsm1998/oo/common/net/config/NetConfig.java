package com.lsm1998.oo.common.net.config;

import com.lsm1998.oo.common.net.NetType;
import lombok.Data;

@Data
public class NetConfig
{
    private String host = "localhost";

    private Integer port = 8000;

    private Boolean block = true;

    private NetType type = NetType.TCP;
}
