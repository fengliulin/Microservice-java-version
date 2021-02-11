package cc.chengheng.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {

    /**
     *
     * @param serviceInstance 收集注册服务，服务器集群上有多少台服务，
     *                        每一台服务封装ServiceInstance对象，放入ArrayList中。
     * @return 轮询算法返回的存放在ArrayList里面的实例。
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstance);
}
