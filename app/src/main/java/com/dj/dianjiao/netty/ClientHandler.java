package com.dj.dianjiao.netty;

import com.dj.dianjiao.domain.LoginResultEvent;

import org.greenrobot.eventbus.EventBus;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.err.println("返回的消息"+ msg);
        EventBus.getDefault().post(new LoginResultEvent(Integer.parseInt(msg)));//返回server的登陆结果
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("连接成功");
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
