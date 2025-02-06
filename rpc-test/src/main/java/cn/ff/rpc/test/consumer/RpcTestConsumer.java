package cn.ff.rpc.test.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 模拟的服务消费者的启动类：启动服务消费者并连接到服务提供者
 * 后续会自动触发RpcTestConsumerHandler类中的channelActive()方法，向服务提供者发送数据，
 * 并在RpcTestConsumerHandler类中的channelRead0()方法中接收服务提供者响应的数据。
 */
@Slf4j
public class RpcTestConsumer {

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
        try {
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new RpcTestConsumerInitializer());
            bootstrap.connect("127.0.0.1", 27880).sync();
        } catch (Exception e) {
            log.error("Error：",e);
        } finally {
            Thread.sleep(2000);
            eventLoopGroup.shutdownGracefully();
        }
    }
}
