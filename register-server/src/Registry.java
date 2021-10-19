import java.util.HashMap;
import java.util.Map;

/**
 * 注册表类，所有的服务注册信息都放在内存中，
 * 内存的数据结构是MAP，本类提供了注册（将注册数据放入MAP当中）、心跳（更新最近一次的心跳时间）等功能
 * 这是一个单例模式
 */
public class Registry {
    private static Registry instance = new Registry(); // 这里的单例模式，把构造方法置为private（外面调用不了），同时，提供一个getInstance方法，将该单例对象返回。

    private Registry(){

    }

    private Map<String, Map<String,ServiceInstance>> registry =
            new HashMap<String, Map<String, ServiceInstance>>() ;
    /**
     * 服务注册
     * 这里有个关于Map的疑问： serviceInstance创建了一个new Hashmap();
     * 然后serviceInstance又put入一个更大的map，但是后续操作小map，会影响到大map吗？
     * @param serviceInstance 参数为服务实例信息
     */
    public void register(ServiceInstance serviceInstance){
        Map<String,ServiceInstance> serviceInstanceMap =
                registry.get(serviceInstance.getServiceName());
        if(serviceInstanceMap==null){
            serviceInstanceMap = new HashMap<>();
            registry.put(serviceInstance.getServiceName(),serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),
                serviceInstance);

        System.out.println("服务实例【"+serviceInstance+"】，完成注册......");
        System.out.println("注册表： "+registry);
    }


    public void remove(String serviceName,String serviceInstanceId){
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        serviceInstanceMap.remove(serviceInstanceId);
    }

    public ServiceInstance getServiceInstance(String serviceName,String serviceInstanceId){
        return this.registry.get(serviceName).get(serviceInstanceId);
    }

    public Map<String,Map<String,ServiceInstance>> getRegistry(){
        return registry;
    }


    public static Registry getInstance(){
        return instance;
    }

}
