package com.oo.bean;

import javazoom.jl.player.Player;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

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
        filePath = "src\\main\\resources\\sound\\" + filePath + ".mp3";
        try
        {
            Player player=new Player(new BufferedInputStream(new FileInputStream(new File(filePath))));
            player.play();
            player.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
