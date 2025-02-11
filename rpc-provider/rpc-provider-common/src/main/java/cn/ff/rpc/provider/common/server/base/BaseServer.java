package cn.ff.rpc.provider.common.server.base;

import cn.ff.rpc.protocol.codec.RpcDecoder;
import cn.ff.rpc.protocol.codec.RpcEncoder;
import cn.ff.rpc.provider.common.handler.RpcProviderHandler;
import cn.ff.rpc.provider.common.server.api.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * 启动rpc框架服务提供者的核心接口的实现类
 * 本类中封装了：rpc框架服务提供者启动时的通用功能
 */
public class BaseServer implements Server {
    private final Logger logger = LoggerFactory.getLogger(BaseServer.class);

    private String reflectType;

    //主机域名或者IP地址
    protected String host = "127.0.0.1";
    //端口号
    protected int port = 27110;
    //存储的是实体类关系
    protected Map<String, Object> handlerMap = new HashMap<>();


    public BaseServer(String serverAddress, String reflectType){
        if (!StringUtils.isEmpty(serverAddress)){
            String[] serverArray = serverAddress.split(":");
            this.host = serverArray[0];
            this.port = Integer.parseInt(serverArray[1]);
        }
        this.reflectType = reflectType;
    }

    @Override
    public void startNettyServer() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    //未实现具体的网络传输协议和数据的编解码时：使用的编解码类为Netty自带的StringEncoder类和StringDecoder类
                                    // 后来使用自定义的编码解码
                                    .addLast(new RpcDecoder())
                                    .addLast(new RpcEncoder())
                                    // 将RpcProviderHandler对象添加到Netty的数据传递链中
                                    // 这样，自定义的RpcProviderHandler类就能够收到外界传递的数据
                                    .addLast(new RpcProviderHandler(reflectType,handlerMap));
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = bootstrap.bind(host, port).sync();
            logger.info("Server started on {}:{}", host, port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("RPC Server start error", e);
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}
