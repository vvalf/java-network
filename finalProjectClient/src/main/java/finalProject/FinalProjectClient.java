package finalProject;

import java.util.Arrays;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


public class FinalProjectClient {

    public static void main(String[] args) throws InterruptedException {
        var eventLoopGroup = new NioEventLoopGroup(4);
        var bootstrap = new Bootstrap()
                .group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientChannelInitializer());

        try {
            for (var line : Arrays.asList("5\nhello", "4\ncool", "6\nhaha")) {
                var channel = bootstrap.connect("localhost", 8080).sync().channel();
                channel.writeAndFlush(line);
                channel.closeFuture().sync();
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}