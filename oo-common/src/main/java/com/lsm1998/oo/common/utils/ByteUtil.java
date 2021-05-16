package com.lsm1998.oo.common.utils;

import java.util.Arrays;
import java.util.List;

public class ByteUtil
{
    public static byte[] mergeBytes(List<byte[]> data)
    {
        byte[][] bytes = new byte[data.size()][];
        for (int i = 0; i < data.size(); i++)
        {
            bytes[i] = data.get(i);
        }
        return mergeBytes(bytes);
    }

    public static byte[] mergeBytes(byte[]... data)
    {
        int length = 0;
        for (byte[] b : data)
        {
            length += b.length;
        }
        byte[] resultArray = new byte[length];
        int lengthCount = 0;
        for (int i = 0; i < data.length; i++)
        {
            System.arraycopy(data[i], 0, resultArray, lengthCount, data[i].length);
            lengthCount += data[i].length;
        }
        return resultArray;
    }

    public static void main(String[] args)
    {
        byte[] b1 = new byte[]{1};
        byte[] b2 = new byte[]{2};
        byte[] b3 = new byte[]{3};
        List<byte[]> list = List.of(b1, b2, b3);
        System.out.println(Arrays.toString(mergeBytes(list)));
    }
}
