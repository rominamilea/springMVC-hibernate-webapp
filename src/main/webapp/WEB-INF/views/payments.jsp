<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Personal expenses management</title>
    <script src="<c:url value="/resources/js/functions.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery/jquery-1.7.7.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery/jquery-ui-1.8.17.custom.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery/jquery.tablesorter.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
                    $("#paymentsTable").tablesorter();
                    $("#paymentsTable tr:even").css("background-color", "#E0E0E0");
                    setSelectedDate();
                }
        );
    </script>
    <link href="<c:url value="/resources/css/ui-styles.css"/>" type="text/css" rel="stylesheet"/>
</head>

<body>

<div id="content">
    <div id="paymentsDiv">
        <form:form id="deletePayment" method="POST">
            <input type="hidden" id="delPaymentId" name="paymentId"/>
        </form:form>

        <div id="dateDiv">
            <form:form id="dateForm" method="POST">
                <table cellspacing="5">
                    <tr>
                        <td class="align_right">Select year:</td>
                        <td>
                            <select id="yearsList" name="yearsList"
                                    onchange="fillMonthsList(this.options[this.selectedIndex].value)">
                                <c:forEach items="${yearsList}" var="year">
                                    <option value="${year}">${year}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="align_right">
                            Select month:
                        </td>
                        <td id="monthsTd">
                            <jsp:include page="months.jsp"></jsp:include>
                        </td>
                    </tr>
                </table>
            </form:form>
            <div id="showButtonDiv">
                <input id="showPaymentsButton" type="submit" onclick="showPayments()"
                       value="Show payments">
            </div>
        </div>

        <div id="paymentsByMonthDiv">
            <jsp:include page="payments_by_month.jsp"></jsp:include>
        </div>

        <div>
            <c:set var="deleteSuccessMessage" value="${param['deleteSuccessMessage']}"/>
            <c:if test="${deleteSuccessMessage != null}">
                <h4 class="successMsg"><fmt:message key="${deleteSuccessMessage}"/></h4>
            </c:if>
        </div>

        <div class="manageP">
            <a class="no_underline" href="viewAddPayment"> >> Add new payment</a>
        </div>
    </div>
</div>
</body>
</html>