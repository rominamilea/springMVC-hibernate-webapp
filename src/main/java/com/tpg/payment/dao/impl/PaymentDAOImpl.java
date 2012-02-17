package com.tpg.payment.dao.impl;

import com.tpg.payment.dao.PaymentDAO;
import com.tpg.payment.model.Payment;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Represents a data access object that handles payment operations.
 *
 * @author romina.milea@threepillarglobal.com
 */
public class PaymentDAOImpl implements PaymentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void save(Payment payment) {
        sessionFactory.getCurrentSession().saveOrUpdate(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void delete(Payment payment) {
        sessionFactory.getCurrentSession().delete(payment);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Payment getById(long id) {
        Criteria getByIdCriteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        getByIdCriteria.add(Restrictions.idEq(id));

        return (Payment) getByIdCriteria.uniqueResult();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<Payment> getByYearAndMonth(Integer year, String monthName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.add(Restrictions.and(Restrictions.sqlRestriction("year(payment_date)=?", year, IntegerType.INSTANCE),
                Restrictions.sqlRestriction("monthname(payment_date)=?", monthName, StringType.INSTANCE)));
        criteria.addOrder(Order.desc("date"));
        return (List<Payment>) criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<Integer> getPaymentYears() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.setProjection(Projections.distinct(
                Projections.sqlProjection("year(payment_date) as year",
                        new String[]{"year"}, new IntegerType[]{IntegerType.INSTANCE})));
        criteria.addOrder(Order.desc("date"));
        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public List<String> getPaymentMonthsByYear(Integer year) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.setProjection(Projections.distinct(
                Projections.sqlProjection("monthname(payment_date) as monthname",
                        new String[]{"monthname"}, new StringType[]{StringType.INSTANCE})));
        criteria.add(Restrictions.sqlRestriction("year(payment_date)=?", year, IntegerType.INSTANCE));
        criteria.addOrder(Order.desc("date"));
        return criteria.list();
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Long getTotalAmountByYearAndMonth(Integer year, String monthName) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Payment.class);
        criteria.setProjection(Projections.sum("amount"));
        criteria.add(Restrictions.and(Restrictions.sqlRestriction("year(payment_date)=?", year, IntegerType.INSTANCE),
                Restrictions.sqlRestriction("monthname(payment_date)=?", monthName, StringType.INSTANCE)));
        return (Long) criteria.uniqueResult();
    }

}