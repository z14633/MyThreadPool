import java.util.Map;

/**
 * 启动一个线程类，每隔30秒，扫描一下内存中的服务，判断其是否还存活
 *
 * 老师给出的解决方法： ServiceMonitor类本身不去做实现Thread类，而是内包了Thread类
 *
 */

public class ServiceMonitor  {

    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    Deamon deamon = new Deamon();


    public void start(){
        this.deamon.start();
    }


    private class Deamon extends Thread{

        private Registry registry  = Registry.getInstance();

        @Override
        public void run() {
//            Map<String, Map<String, ServiceInstance>> registryMap = this.registry.getRegistry();  这里register如果初始化后，
            Map<String, Map<String, ServiceInstance>> registryMap = null;
            while(true) {
                try {
                    registryMap = registry.getRegistry();
                    for (String serviceName : registryMap.keySet()) {

                        for (ServiceInstance serviceInstance : registryMap.get(serviceName).values()) {
                            if (!serviceInstance.isAlive()) {
                                //这说明这个服务没有存活
                                System.out.println("服务实例【" + serviceInstance + "】，心跳时间已经过期，需要从注册表中删除......");
                                registry.remove(serviceName, serviceInstance.getServiceInstanceId());
                            }
                        }
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }
}
