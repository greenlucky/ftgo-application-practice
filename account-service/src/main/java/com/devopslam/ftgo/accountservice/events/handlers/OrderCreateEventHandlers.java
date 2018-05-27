package com.devopslam.ftgo.accountservice.events.handlers;

import com.devopslam.common.events.IMessageEvent;
import com.devopslam.common.events.MessageEvent;
import com.devopslam.common.events.enums.MessageStatusEvent;
import com.devopslam.common.events.enums.ServiceEvent;
import com.devopslam.ftgo.accountservice.domain.ChargeRequest;
import com.devopslam.ftgo.accountservice.events.channels.CustomEventChannels;
import com.devopslam.ftgo.accountservice.service.StripeService;
import com.devopslam.ftgo.orderservice.events.OrderCreateEvent;
import com.devopslam.ftgo.orderservice.events.PaymentInformation;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderCreateEventHandlers {

    private final Logger logger = LoggerFactory.getLogger(OrderCreateEventHandlers.class);

    @Autowired
    private StripeService stripeService;

    @StreamListener(CustomEventChannels.ORDER_CREATE_ORDER_IN_CHANEl)
    @SendTo(CustomEventChannels.ORDER_CREATE_ORDER_OUT_CHANEl)
    public IMessageEvent createOrderInSink(IMessageEvent msg) {
        logger.info("Receives event from {} with order Id: {}", msg.getServiceEvent(), msg.getOrderId());
        String orderId = msg.getOrderId();
        try {
            MessageStatusEvent statusEvent = MessageStatusEvent.FAILURE;
            if (msg.getDataEvent() == null || !(msg.getDataEvent() instanceof OrderCreateEvent)) return null;

            OrderCreateEvent event = (OrderCreateEvent) msg.getDataEvent();
            Optional<PaymentInformation> paymentInfo = Optional.ofNullable(event.getPaymentInformation());
            if (!paymentInfo.isPresent())
                return new MessageEvent(orderId, ServiceEvent.ACCOUNT_SERVICE, MessageStatusEvent.FAILURE);

            String desc = "FTGO valid order Id: " + orderId;
            int amount = event.getTotal().getAmount().intValue();
            ChargeRequest request = new ChargeRequest(desc, amount, ChargeRequest.Currency.USD, paymentInfo.get().getPaymentToken());
            Charge charge = stripeService.charge(request);
            if (charge != null && charge.getFailureCode() == null)
                statusEvent = MessageStatusEvent.SUCCESS;
            logger.info("Charged {} for order Id: {} with amount: {}", statusEvent, orderId, amount);
            return new MessageEvent(orderId, ServiceEvent.ACCOUNT_SERVICE, statusEvent);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new MessageEvent(orderId, ServiceEvent.ACCOUNT_SERVICE, MessageStatusEvent.FAILURE);
        }
    }

}
