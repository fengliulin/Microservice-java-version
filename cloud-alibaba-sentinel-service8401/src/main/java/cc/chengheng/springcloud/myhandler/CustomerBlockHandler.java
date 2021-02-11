package cc.chengheng.springcloud.myhandler;

import cc.chengheng.springcloud.entities.CommonResult;
import cc.chengheng.springcloud.entities.Payment;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult<>(
                4444,
                "1-自定义处理CustomerBlockHandler",
                new Payment(2020l,"serial001")
        );
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult<>(
                4444,
                "2-自定义处理CustomerBlockHandler",
                new Payment(2020l,"serial002")
        );
    }
}
