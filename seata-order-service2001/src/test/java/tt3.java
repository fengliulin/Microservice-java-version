import cc.chengheng.springcloud.alibaba.dao.OrderDao;
import cc.chengheng.springcloud.alibaba.domain.Order;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class tt3 {

    @Test
    public void abc() throws NoSuchMethodException {
        Method orderDaoMethod = OrderDao.class.getDeclaredMethod("update", Long.class, Integer.class);
        String name = orderDaoMethod.getParameterTypes().getClass().getName();
        System.out.println(name);
        Annotation[][] parameterAnnotations = orderDaoMethod.getParameterAnnotations();
        Arrays.stream(parameterAnnotations).forEach(System.out::println);
        System.out.println();
    }
}
