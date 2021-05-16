package week3.problem2.router;

import java.util.List;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public interface HttpRouter {

    /**
     * 实现路由
     *
     * @param endPoints url集合
     * @return 需要请求的路径
     */
    String router(List<String> endPoints);
}
