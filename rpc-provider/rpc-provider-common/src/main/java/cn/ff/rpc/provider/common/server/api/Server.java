package cn.ff.rpc.provider.common.server.api;

/**
 * 启动rpc框架服务提供者的核心接口
 */
public interface Server {


    /**
     * 启动Netty服务
     */
    void startNettyServer();


}
