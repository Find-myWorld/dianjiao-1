package com.dj.dianjiao.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2016/7/29.
 */
public class DispacherService {
    private static Map<String, ChannelHandlerContext> map = new ConcurrentHashMap<>();

    public static void addChannel(String id, ChannelHandlerContext gateway_channel){
        map.put(id, gateway_channel);
    }

    public static Map<String, ChannelHandlerContext> getChannels(){
        return map;
    }

    public static ChannelHandlerContext getSingeleChannel(String id){
        return map.get(id);
    }

    public static void removeSingleChannel(String id){
        map.remove(id);
    }
}

