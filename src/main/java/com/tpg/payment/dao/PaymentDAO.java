package com.tpg.payment.dao;

import com.tpg.payment.model.Payment;

import java.util.List;

/**
 * Represents a data access object that handles payment operations.
 *
 * @author romina.milea@threepillarglobal.com
 */
public interface PaymentDAO {

    /**
     * Saves a new payment to database.
     *
     * @param payment the payment to be saved
     */
    void save(Payment payment);

    /**
     * Deletes a specified payment from database.
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
     * Returns a list of payments saved into database in the specified year and month.
     *
     * @param year      the specified year
     * @param monthName the specified month
     * @return a list of payments
     */
    List<Payment> getByYearAndMonth(Integer year, String monthName);

    /**
     * Returns a list of years for which was saved at least one payment into database.
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
     * Calculates the amount spent during a month from the specified year.
     *
     * @param year      the specified year
     * @param monthName the month for which is calculated the amount
     * @return the amount spent during a month from the specified year
     */
    Long getTotalAmountByYearAndMonth(Integer year, String monthName);
}
