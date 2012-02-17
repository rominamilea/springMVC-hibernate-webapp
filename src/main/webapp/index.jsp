<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Personal expenses management</title>
    <link href="<c:url value="/resources/css/ui-styles.css"/>" type="text/css" rel="stylesheet"/>
</head>

<body>

<h4 id="pemTitle">Personal expenses management</h4>

<div class="managePayments">
    <ul>
        <li>
            <a class="no_underline" href="currentPayments"> >> View current month payments</a>
        </li>
        <li>
            <a class="no_underline" href="viewAddPayment"> >> Add new payment</a>
        </li>
    </ul>
</div>

</body>
</html>