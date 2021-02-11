package cc.chengheng.springcloud.controller;

import cc.chengheng.springcloud.entities.CommonResult;
import cc.chengheng.springcloud.entities.Payment;
import cc.chengheng.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OrderFeignController {

    /**
     * 注入的接口，会通过返回把生成的代理对象赋值过来
     */
    @Resource
    private PaymentFeignService paymentFeignService;

    /**
     * 调用
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        return paymentFeignService.paymentFeignTimeout();
    }
}
