package com.dj.dianjiao.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2016/7/12.
 */
public class NettyClient {
    static final String HOST = System.getProperty("host", "192.168.0.199");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8992"));

    public static void sendMsg2Server(String msg)throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            // Start the connection attempt.开始连接
            Channel ch = b.connect(HOST, PORT).sync().channel();


            ChannelFuture cf = ch.writeAndFlush(msg + "\r\n");
            ch.closeFuture().sync();
            if(cf.isSuccess()) {
                System.out.println("已发送");
            }else{
                System.out.println("发送失败");
            }
        } finally {
            // The connection is closed automatically on shutdown.连接自动关闭
            group.shutdownGracefully();
        }
    }

}
