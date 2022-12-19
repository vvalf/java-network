package lessons.six;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance(ServerHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) {
        LOGGER.info("Message received: {}", msg);
        String[] split = msg.split(":");

        if (split.length != 2 || Integer.parseInt(split[0]) != split[1].length()) {
            ctx.channel()
                    .writeAndFlush("3:err")
                    .addListener(ChannelFutureListener.CLOSE);
            throw new IllegalArgumentException("Некорректный формат сообщения: " + msg);
        }

        ctx.channel().writeAndFlush("2:ok");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}