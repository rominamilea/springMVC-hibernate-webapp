package com.tpg.payment.service;

import com.tpg.payment.model.Payment;

import java.util.List;

/**
 * Represents a service for handling payment related business operations.
 *
 * @author romina.milea@threepillarglobal.com
 */
public interface PaymentService {

    /**
     * Saves a new payment.
     *
     * @param payment the payment to be saved
     */
    void save(Payment payment);

    /**
     * Deletes a specified payment.
     *
     * @param payment the payment to be deleted
     */
    void delete(Payment payment);

    /**
     * Returns the payment object with the specified id.
     *
     * @param id
     * @return a payment object with the specified id
     */
    Payment getById(long id);

    /**
     * Returns a list of payments for the specified year and month.
     *
     * @param year      the specified year
     * @param monthName the specified month
     * @return a list of payments for the specified year and month
     */
    List<Payment> getByYearAndMonth(Integer year, String monthName);

    /**
     * Returns a list of years for which was registered at least one payment into database.
     *
     * @return a list of years for which was registered at least one payment
     */
    List<Integer> getPaymentYears();

    /**
     * Returns a list of months from a specified year for which was registered at least one payment into database.
     *
     * @param year the specified year
     * @return a list of months from a specified year for which was registered at least one payment
     */
    List<String> getPaymentMonthsByYear(Integer year);

    /**
     * Calculates amount spent during the specified month from one year.
     *
     * @param year      the year
     * @param monthName the month for which is calculated the amount
     * @return
     */
    Long getTotalAmountByYearAndMonth(Integer year, String monthName);

}
