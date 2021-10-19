
/**
 * 服务实例类，相当于接收服务实例信息类，pojo
 */
public class ServiceInstance {

    private final Long  NOT_ALIVE_PEROID = 90*1000L;

    private String hostName;
    private String serviceName;
    private String ip;
    private String serviceInstanceId;
    private Integer port;

    /**
     * 为什么我的内部类实现Lease(契约)的时候，它会显示出内部定义的private class lease从来没用过。 : 我用的是private Class Lease{} 去定义内部类；这明显是不合理的。
     *
     */
    private  Lease lease;

    public ServiceInstance(){
        this.lease = new Lease();
    }

    public void renew(){
        this.lease.renew();
    }
    public Boolean isAlive(){
        return this.lease.isAlive();
    }


    private  class Lease {
        private Long lastHeartbeatTime =System.currentTimeMillis();

        public void renew(){
            this.lastHeartbeatTime =  System.currentTimeMillis();
            System.out.println("服务实例【"+serviceInstanceId+"】，进行续约： "+lastHeartbeatTime);
        }

        public Boolean isAlive(){
            Long currentTime = System.currentTimeMillis();
            if(currentTime - lastHeartbeatTime > NOT_ALIVE_PEROID){
                System.out.println("服务实例：【"+serviceInstanceId+"】已经过期，死掉了.......");
                return false;
            } else {
                System.out.println("服务实例：【"+serviceInstanceId+"】还活着.......");
                return true;
            }
        }
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "NOT_ALIVE_PEROID=" + NOT_ALIVE_PEROID +
                ", hostName='" + hostName + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                ", port=" + port +
                ", lease=" + lease +
                '}';
    }
}

