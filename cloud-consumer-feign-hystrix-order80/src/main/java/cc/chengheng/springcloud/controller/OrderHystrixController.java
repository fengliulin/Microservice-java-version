package cc.chengheng.springcloud.controller;
;
import cc.chengheng.springcloud.entities.Payment;
import cc.chengheng.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }



    /*// 方法出现超时或异常， cglib根据这个类生成一个继承它的类，反射调用目标方法之前，先判断
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
                    // 大概原理，就是超过这个时间毫秒，中断这个方法的线程，执行fallbackMethod 方法
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})*/

    @HystrixCommand //全局的话要与这个，指定一个服务降级, 请求的方法上添加
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "我是消费者80，对方支付系统繁忙";
    }

    // 全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "全局异常处理信息，请稍后再试";
    }
}
