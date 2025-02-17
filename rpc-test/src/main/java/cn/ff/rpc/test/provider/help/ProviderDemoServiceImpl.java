package cn.ff.rpc.test.provider.help;


import cn.ff.rpc.annotation.RpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RpcService(interfaceClass = DemoService.class, interfaceClassName = "cn.ff.rpc.test.provider.help.DemoService", version = "1.0.0", group = "demo")
public class ProviderDemoServiceImpl implements DemoService {
    private final Logger logger = LoggerFactory.getLogger(ProviderDemoServiceImpl.class);

    @Override
    public String hello(String name) {
        logger.info("调用hello方法传入的参数为===>>>{}", name);
        return "hello " + name;
    }
}
