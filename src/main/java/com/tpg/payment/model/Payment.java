package com.tpg.payment.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Models the payment entity class.
 *
 * @author romina.milea@threepillarglobal.com
 */
@Entity
@Table(name = "PAYMENTS")
public class Payment implements Serializable {

    private Long id;

    /**
     * Amount of money.
     */
    private Integer amount;

    /**
     * Payment description.
     */
    private String description;

    /**
     * Payment date.
     */
    private Date date;

    public Payment() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull(message = "Amount can not be empty.")
    @Range(min = 1, message = "Amount value should be greater than 1.")
    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @NotEmpty(message = "Description can not be empty.")
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "PAYMENT_DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;
        if (description != null ? !description.equals(payment.description) : payment.description != null) return false;
        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public String toString() {
        StringBuilder paymentString = new StringBuilder(description);
        paymentString.append(" - ");
        paymentString.append(amount);
        paymentString.append(" RON on ");
        paymentString.append(date);
        return paymentString.toString();
    }
}