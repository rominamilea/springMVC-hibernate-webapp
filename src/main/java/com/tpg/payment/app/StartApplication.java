package com.tpg.payment.app;

import com.tpg.payment.model.Payment;
import com.tpg.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * @author romina.milea@threepillarglobal.com
 */
public class StartApplication {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(StartApplication.class);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/app-context.xml");

        PaymentService paymentService = (PaymentService) applicationContext.getBean("paymentService");
        Payment payment = new Payment();
        payment.setAmount(100);
        payment.setDate(new Date());
        payment.setDescription("Food");
        paymentService.save(payment);

        if (logger.isDebugEnabled()) {
            logger.debug("Payment list size is {}", paymentService.getByYearAndMonth(2012, "January").size());
        }

    }

}
