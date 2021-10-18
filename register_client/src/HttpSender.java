public class HttpSender {

    /**
     * 这个组件一般是工具类，用来发送http请求调用别人接口的。
     * 发送注册
     */
    public RegisterResponse register(RegisterRequest request){
//        System.out.println("正在为服务【服务名："+request.getServiceName()+"；服务ID："+request.getServiceInstanceId()+"】进行注册");
        System.out.println("服务实例【"+request+"】，发送请求进行注册......");
        RegisterResponse response = new RegisterResponse();
        response.setCode(200);
        response.setStatus(RegisterResponse.SUCCESS);
        return response;
    }

    /**
     * 发送心跳
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        System.out.println("服务实例【"+request+"】，发送请求进行心跳......");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(RegisterResponse.SUCCESS);
        response.setCode(200);

        return response;

    }


}
