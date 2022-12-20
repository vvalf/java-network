package finalProject;

import java.nio.charset.StandardCharsets;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8));
        socketChannel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
        socketChannel.pipeline().addLast(new ServerHandler());
    }
}

