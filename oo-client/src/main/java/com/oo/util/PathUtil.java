package com.oo.util;

import java.net.URL;

public class PathUtil
{
    public static String getPathByResources(String path)
    {
        URL url = PathUtil.class.getClassLoader().getResource("");
        return url.getPath() + path;
    }

    public static String imgPath(String imgName)
    {
        URL url = PathUtil.class.getClassLoader().getResource("");
        return url.getPath() + "/img/" + imgName;
    }

    public static String resourcesPath(String path)
    {
        return getPathByResources(path);
    }
}
