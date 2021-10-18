import java.util.UUID;

/**
 * 这是注册的客户端组件，不是独立部署的，需要被服务启动，它是负责与Regist-Server进行交互通信的。
 *
 * register-client启动后，它会将启动一个线程去做注册的工作，注册完成后，就会再启动一个线程去完成发送心跳工作
 *
 * 这里为了简化，让只启动一个工作线程，让他去先去注册，注册成功后，再写个死循环去发送心跳
 *
 *
 */
public class RegisterClient {

    private String serviceInstanceId;

    public RegisterClient(){
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-","");
    }

    public void start(){
        new RegisterWorker(serviceInstanceId).start();
    }

}
