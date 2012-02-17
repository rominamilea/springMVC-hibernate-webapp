package com.tpg.payment.controller;

import com.tpg.payment.model.Payment;
import com.tpg.payment.service.PaymentService;
import com.tpg.payment.util.MonthName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Represents a payment controller for handling payment operations.
 *
 * @author romina.milea@threepillarglobal.com
 */
@Controller
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/viewAddPayment", method = RequestMethod.GET)
    public String viewAddPayment(Map<String, Object> modelMap) {
        Payment payment = new Payment();
        modelMap.put("payment", payment);
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addPayment(@ModelAttribute("payment") @Valid Payment payment, BindingResult result) {

        if (result.hasErrors()) {
            logger.warn("Payment {} is not valid: {}", payment, Arrays.toString(result.getAllErrors().toArray()));
            return new ModelAndView("add");
        }

        paymentService.save(payment);
        payment = new Payment();
        ModelAndView modelView = new ModelAndView("redirect:/viewAddPayment");
        modelView.addObject("addSuccessMessage", "Added.payment.success");
        return modelView;
    }

    @RequestMapping(value = "/currentPayments", method = RequestMethod.GET)
    public String viewCurrentMonthPayments(Map<String, Object> modelMap) {

        setModelAndViewForPayments(modelMap, getCurrentYear(), getCurrentMonthName());
        return "payments";
    }


    @RequestMapping(value = "/getPayments", method = RequestMethod.POST)
    public String showPayments(HttpServletRequest request, Map<String, Object> modelMap) {

        Integer selectedYear = Integer.parseInt(request.getParameter("yearsList"));
        String selectedMonth = request.getParameter("monthsList");
        setModelAndViewForPayments(modelMap, selectedYear, selectedMonth);

        return "payments_by_month";
    }

    @RequestMapping(value = "/months", method = RequestMethod.POST)
    public ModelAndView getMonthsByYear(HttpServletRequest request) {

        Integer year = Integer.parseInt(request.getParameter("yearsList"));
        ModelAndView modelAndView = new ModelAndView("months");
        modelAndView.addObject("monthsList", paymentService.getPaymentMonthsByYear(year));

        return modelAndView;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ModelAndView deletePayment(HttpServletRequest request) {

        long paymentId = Integer.parseInt(request.getParameter("paymentId"));
        Payment payment = paymentService.getById(paymentId);
        paymentService.delete(payment);
        if (logger.isDebugEnabled()) {
            logger.debug("Payment {} deleted successfully.", payment);
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/currentPayments");
        return modelAndView;
    }

    private void setModelAndViewForPayments(Map<String, Object> modelMap, Integer year, String month) {
        modelMap.put("yearsList", paymentService.getPaymentYears());
        modelMap.put("monthsList", paymentService.getPaymentMonthsByYear(year));
        modelMap.put("paymentsList", paymentService.getByYearAndMonth(year, month));
        modelMap.put("totalAmount", paymentService.getTotalAmountByYearAndMonth(year, month));
    }

    private String getCurrentMonthName() {
        Calendar calendar = Calendar.getInstance();
        return MonthName.values()[calendar.get(Calendar.MONTH)].toString();
    }

    private Integer getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

}
