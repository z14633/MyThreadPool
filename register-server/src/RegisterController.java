/**
 *  这个类用来接收register-client发送的http请求
 *
 *     spring cloud用的开源组件jersey，它是一个restful框架，可以接收http请求。
 */
public class RegisterController {
    private Registry registry;

    public RegisterController(){
        this.registry = Registry.getInstance();
    }

    public RegisterResponse register(RegisterRequest registerRequest){
        RegisterResponse response = new RegisterResponse();
        try {
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setHostName(registerRequest.getHostName());
            serviceInstance.setIp(registerRequest.getIp());
            serviceInstance.setPort(registerRequest.getPort());
            serviceInstance.setServiceName(registerRequest.getServiceName());
            serviceInstance.setServiceInstanceId(registerRequest.getServiceInstanceId());

            registry.register(serviceInstance);

            response.setCode(200);
            response.setStatus(RegisterResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(400);
            response.setStatus(RegisterResponse.FAILURE);
        }

        return response;

    }

    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        HeartbeatResponse response = new HeartbeatResponse();
        try {
            ServiceInstance serviceInstance =
                    registry.getServiceInstance(request.getServiceName(), request.getServiceInstanceId());
            serviceInstance.renew();

            response.setCode(200);
            response.setStatus(HeartbeatResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            response.setCode(400);
            response.setStatus(HeartbeatResponse.FAILURE);
        }

        return response;
    }


}
