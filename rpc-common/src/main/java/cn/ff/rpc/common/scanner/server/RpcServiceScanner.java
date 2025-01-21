package cn.ff.rpc.common.scanner.server;

import cn.ff.rpc.common.scanner.ClassScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务提供者启动时：扫描@RpcService注解扫描器
 * 通过解析@RpcService注解的属性，将服务提供者的元数据信息注册到注册中心，并且会将@RpcService注解标注的实现类放入一个Map缓存中
 */
public class RpcServiceScanner extends ClassScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServiceScanner.class);
}
