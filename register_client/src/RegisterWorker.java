public class RegisterWorker extends Thread {
    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP = "192.168.31.204";
    public static final String HOSTNAME = "inventory01";
    public static final int PORT = 9000;

    /**
     * 判断是否注册成功
     */
    private Boolean finishedRegister = false;
    /**
     * 服务实例ID
     */
    private String serviceInstanceId;
    /**
     * http通信组件
     */
    private HttpSender httpSender;

    public RegisterWorker(String serviceInstanceId){
        this.serviceInstanceId = serviceInstanceId;
        this.httpSender = new HttpSender();
    }

    @Override
    public void run() {
        if(!finishedRegister){
            // 去做注册的业务
            RegisterRequest request = new RegisterRequest();
            request.setHostName(HOSTNAME);
            request.setPort(PORT);
            request.setIp(IP);
            request.setServiceName(SERVICE_NAME);
            request.setServiceInstanceId(serviceInstanceId);

            RegisterResponse response = httpSender.register(request);
            if(response.getStatus().equals(RegisterResponse.SUCCESS)){
                finishedRegister = true;
            } else {
                return;
            }
        }
        if(finishedRegister) {
            // 去做发送心跳的业务
            HeartbeatRequest request = new HeartbeatRequest();
            request.setServiceInstanceId(serviceInstanceId);
            request.setServiceName(SERVICE_NAME);
            HeartbeatResponse heartbeatResponse = null; // 这里老师就特别注意，防止在循环内创建对象。以免给jvm回收带来压力。
            while (true) {
                try {
                    heartbeatResponse = httpSender.heartbeat(request);
                    System.out.println("心跳的结果为： "+heartbeatResponse.getStatus()+"......");
                    Thread.sleep(30 * 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
