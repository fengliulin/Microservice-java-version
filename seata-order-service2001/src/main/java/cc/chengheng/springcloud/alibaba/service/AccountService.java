package cc.chengheng.springcloud.alibaba.service;

import cc.chengheng.springcloud.alibaba.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {
    @PostMapping(value = "/account/decrease")
    CommonResult decrease(
                            @RequestParam("userId") Integer userId,
                            @RequestParam("money") BigDecimal money
    );
}
