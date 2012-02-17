<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
    $(document).ready(function () {
                $("#paymentsTable").tablesorter();
                $("#paymentsTable tr:even").css("background-color", "#E0E0E0");
                setSelectedDate();
            }
    );
</script>

<div>
    <h4> Payments list for <label class="selectedMonth"></label>, <label class="selectedYear"></label>: </h4>
    <c:choose>
        <c:when test="${not empty paymentsList}">
            <table id="paymentsTable" class="tableSorter">
                <thead>
                <tr>
                    <th id="tableDate">Date</th>
                    <th id="tableAmount">Amount
                        <img class="orderImage" src="resources/images/order.gif"/>
                    </th>
                    <th id="tableDesc">Description
                        <img class="orderImage" src="resources/images/order.gif"/></th>
                    <th id="tableAction">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${paymentsList}" var="payment">
                    <tr>
                        <td>${payment.date} </td>
                        <td>${payment.amount} </td>
                        <td class="descriptionTd">${payment.description} </td>
                        <td><a href="#" onclick="deletePayment('${payment.id}')">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div id="totalAmountDiv">
                <h4>Total amount: <span class="totalAmount"><c:out value="${totalAmount}"/></span> RON </h4>
            </div>
        </c:when>
        <c:otherwise>
            <p>There are no payments for the selected year and month.</p>
        </c:otherwise>
    </c:choose>
</div>
