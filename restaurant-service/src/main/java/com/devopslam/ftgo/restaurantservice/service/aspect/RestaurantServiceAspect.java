package com.devopslam.ftgo.restaurantservice.service.aspect;

import com.devopslam.ftgo.restaurantservice.enums.RestaurantMessageAction;
import com.devopslam.ftgo.restaurantservice.events.RestaurantChangedMessage;
import com.devopslam.ftgo.restaurantservice.events.UpdateRestaurantRequest;
import com.devopslam.ftgo.restaurantservice.events.handler.RestaurantSender;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestaurantServiceAspect {

    private final Logger logger = LoggerFactory.getLogger(RestaurantServiceAspect.class);

    @Autowired
    private RestaurantSender senderOut;

    @Autowired
    private TaskExecutor taskExecutor;

    @AfterReturning("execution(* com.devopslam.ftgo.restaurantservice.service.RestaurantService.update(..)) && args(request)")
    public void afterUpdateRestaurant(final UpdateRestaurantRequest request) {
        taskExecutor.execute(() -> {
            RestaurantChangedMessage sendOutMsg = new RestaurantChangedMessage(
                    RestaurantServiceAspect.class.getName(),
                    RestaurantMessageAction.UPDATE,
                    request.getId());
            logger.info("After update executors send out restaurant with restaurant Id: {}", request.getId());
            senderOut.sendChangedMessage(sendOutMsg);
        });
    }

    @AfterReturning("execution(* com.devopslam.ftgo.restaurantservice.service.RestaurantService.delete(..)) && args(restaurantId)")
    public void afterDeleteRestaurant(final String restaurantId) {
        taskExecutor.execute(() -> {
            RestaurantChangedMessage sendOutMsg = new RestaurantChangedMessage(
                    RestaurantServiceAspect.class.getName(),
                    RestaurantMessageAction.DELETE,
                    restaurantId);
            logger.info("After delete executors send out restaurant with restaurant Id: {}", restaurantId);
            senderOut.sendChangedMessage(sendOutMsg);
        });
    }

}
