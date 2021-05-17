package com.lsm1998.oo.bean;

import com.lsm1998.oo.util.PathUtil;
import javazoom.jl.player.Player;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 作者：刘时明
 * 日期：2018/10/1
 * 时间：0:46
 * 说明：音频播放组件类
 */
@Component
public class Sound
{
    @Async
    public void play(String filePath)
    {
        filePath = PathUtil.RESOURCES + "\\sound\\" + filePath + ".mp3";
        Player player = null;
        try
        {
            player = new Player(new BufferedInputStream(new FileInputStream(filePath)));
            player.play();
        } catch (Exception e)
        {
            if (e instanceof FileNotFoundException)
            {
                System.err.println("语音资源找不到!" + filePath);
            } else
            {
                e.printStackTrace();
            }
        } finally
        {
            if (player != null)
                player.close();
        }
    }
}
