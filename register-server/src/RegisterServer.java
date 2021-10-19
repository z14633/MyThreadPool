import java.util.UUID;

/**
 * 这是一个Register启动类，它支持独立部署，含有main函数。
 *
 */

public class RegisterServer {
    public static void main(String[] args) throws Exception {
        RegisterController registerController = new RegisterController();
        // 构造一个注册请求、心跳请求
        String serviceInstanceId = UUID.randomUUID().toString().replace("-", "");

        // 模拟发起一个服务注册的请求
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setHostName("inventory-service-01");
        registerRequest.setIp("192.168.31.208");
        registerRequest.setPort(9000);
        registerRequest.setServiceInstanceId(serviceInstanceId);
        registerRequest.setServiceName("inventory-service");

        registerController.register(registerRequest);

        System.out.println("=============================");

        // 模拟进行一次心跳，完成续约
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceName("inventory-service");
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);

        registerController.heartbeat(heartbeatRequest);

        ServiceMonitor serviceMonitor = new ServiceMonitor();
        serviceMonitor.start();

        while(true){
            Thread.sleep(30*1000);
        }


    }
}
