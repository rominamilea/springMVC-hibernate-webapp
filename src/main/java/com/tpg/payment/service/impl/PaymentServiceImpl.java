package com.tpg.payment.service.impl;

import com.tpg.payment.dao.PaymentDAO;
import com.tpg.payment.model.Payment;
import com.tpg.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Represents a service for handling payment related business operations.
 *
 * @author romina.milea@threepillarglobal.com
 */
//@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    /**
     * {@inheritDoc}
     */
    public void save(Payment payment) {
        paymentDAO.save(payment);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Payment payment) {
        paymentDAO.delete(payment);
    }

    /**
     * {@inheritDoc}
     */
    public Payment getById(long id) {
        return paymentDAO.getById(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Payment> getByYearAndMonth(Integer year, String monthName) {
        return paymentDAO.getByYearAndMonth(year, monthName);
    }

    /**
     * {@inheritDoc}
     */
    public List<Integer> getPaymentYears() {
        return paymentDAO.getPaymentYears();
    }

    public List<String> getPaymentMonthsByYear(Integer year) {
        return paymentDAO.getPaymentMonthsByYear(year);
    }

    public Long getTotalAmountByYearAndMonth(Integer year, String monthName) {
        return paymentDAO.getTotalAmountByYearAndMonth(year, monthName);
    }

}
