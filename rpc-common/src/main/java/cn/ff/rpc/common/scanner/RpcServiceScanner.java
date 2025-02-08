package cn.ff.rpc.common.scanner;

import cn.ff.rpc.annotation.RpcService;
import cn.ff.rpc.common.helper.RpcServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务提供者启动时：扫描@RpcService注解扫描器
 * 通过解析@RpcService注解的属性，将服务提供者的元数据信息注册到注册中心，并且会将@RpcService注解标注的实现类放入一个Map缓存中
 */
public class RpcServiceScanner extends ClassScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServiceScanner.class);


    /**
     * 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     */
    public static Map<String, Object> doScannerWithRpcServiceAnnotationFilterAndRegistryService(String scanPackage) throws Exception {
        Map<String, Object> handlerMap = new HashMap<>();
        List<String> classNameList = getClassNameList(scanPackage, true);
        if (classNameList.isEmpty()) {
            return handlerMap;
        }
        classNameList.forEach((className) -> {
            try {
                Class<?> clazz = Class.forName(className);
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                if (rpcService != null) { //优先使用interfaceClass, interfaceClass的name为空，再使用interfaceClassName
                    String serviceName = getServiceName(rpcService);
                    String key = RpcServiceHelper.buildServiceKey(serviceName, rpcService.version(), rpcService.group());
                    handlerMap.put(key, clazz.getDeclaredConstructor().newInstance());
                }
            } catch (Exception e) {
                LOGGER.error("scan classes throws exception: ", e);
            }
        });
        return handlerMap;
    }








    /**
     * 获取serviceName： 优先使用interfaceClass
     */
    private static String getServiceName(RpcService rpcService){
        Class<?> clazz = rpcService.interfaceClass();
        if (clazz == void.class){
            return rpcService.interfaceClassName();
        }
        String serviceName = clazz.getName();
        if (serviceName.trim().isEmpty()){
            serviceName = rpcService.interfaceClassName();
        }
        return serviceName;
    }

}


















