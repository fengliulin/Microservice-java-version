package cc.chengheng.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {

    // 原子整型
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    // 用的自旋锁
    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;

            // 第一个参数就是，AtomicInteger获取的value，和预期值对比，如果一样，就把数据更新值， 把第二个参数的值写到AtomicInteger的字段value里
        } while (!atomicInteger.compareAndSet(current, next));
        System.out.println("第几次访问,次数****next:" + next);

        return next;
    }

    /**
     *
     * @param serviceInstance 收集注册服务，服务器集群上有多少台服务，
     *                        每一台服务封装ServiceInstance对象，放入ArrayList中。
     * @return 返回一个ServiceInstance实例
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstance) {
        int index = getAndIncrement() % serviceInstance.size();
        return serviceInstance.get(index);
    }
}
