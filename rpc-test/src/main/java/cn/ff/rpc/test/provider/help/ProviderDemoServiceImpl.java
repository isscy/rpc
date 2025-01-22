package cn.ff.rpc.test.provider.help;


import cn.ff.rpc.annotation.RpcService;
import cn.ff.rpc.test.scanner.help.DemoService;

@RpcService(interfaceClass = DemoService.class, interfaceClassName = "cn.ff.rpc.test.provider.help.DemoService", version = "1.0.0", group = "demo")
public class ProviderDemoServiceImpl implements DemoService {


}
