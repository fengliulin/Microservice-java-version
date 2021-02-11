package cc.chengheng.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    // 编码
    private Integer code;

    // 信息
    private String message;


    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
