package cc.chengheng.springcloud.controller;

import cc.chengheng.springcloud.entities.CommonResult;
import cc.chengheng.springcloud.entities.Payment;
import cc.chengheng.springcloud.myhandler.CustomerBlockHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult<Payment> byResource() {
        return new CommonResult<>(200, "按资源名称限流测试ok", new Payment(2020l,"serial001"));
    }

    // 兜底
    public CommonResult<Payment> handleException(BlockException exception) {
        return new CommonResult<>(444, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }


    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult<Payment> byUrl() {
        return new CommonResult<>(200, "按URL限流测试ok", new Payment(2020l,"serial002"));
    }


    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(
            value = "customerBlockHandler",
            blockHandlerClass = CustomerBlockHandler.class, // 哪个类处理
            blockHandler = "handlerException2"              // 哪个方法处理
    )
    public CommonResult<Payment> customerBlockHandler() {
        return new CommonResult<>(200, "自定义处理", new Payment(2020l,"serial003"));
    }

}
