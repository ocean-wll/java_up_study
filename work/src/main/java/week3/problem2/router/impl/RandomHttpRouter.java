package week3.problem2.router.impl;

import week3.problem2.router.HttpRouter;

import java.util.List;
import java.util.Random;

/**
 * @author : ocean_wll
 * @since 2021/5/16
 */
public class RandomHttpRouter implements HttpRouter {

    @Override
    public String router(List<String> endPoints) {
        if (endPoints == null || endPoints.size() < 1) {
            return null;
        }
        Random random = new Random();
        return endPoints.get(random.nextInt(endPoints.size()));
    }
}
