<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Personal expenses management</title>
    <script src="<c:url value="/resources/js/functions.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery/jquery-1.7.7.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/jquery/jquery-ui-1.8.17.custom.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#datePicker').datepicker({
                maxDate:new Date(),
                onSelect:function () {
                    var selectedDate = $("#datePicker").datepicker("getDate");
                    $("#dateInput").attr("value", selectedDate);
                }
            });
            var selectedDate = $("#datePicker").datepicker("getDate");
            $("#dateInput").attr("value", selectedDate);
        });
    </script>
    <link href="<c:url value="/resources/css/ui-styles.css"/>" type="text/css" rel="stylesheet"/>
</head>

<body>
<div id="addDiv">
    <form:form id="addForm" action="add" method="POST" commandName="payment">
        <fieldset>
            <legend for="name">Add new payment</legend>
            <table id="addTable">
                <tr>
                    <td>Amount of money:
                        <form:input path="amount" id="amount" size="7"/>
                        <br/>
                        <form:errors path="amount" class="error"/>
                    </td>
                </tr>
                <tr>
                    <td>Description:</td>
                </tr>
                <tr>
                    <td>
                        <form:textarea path="description" id="description" rows="4" cols="31"></form:textarea>
                        <form:errors path="description" class="error"/>
                    </td>
                </tr>
                <tr>
                    <td>Payment date:
                        <form:input path="date" id="dateInput" onclick="fillDateInput('dateInput')"/>
                        <form:errors path="date" class="error"/>
                    </td>
                </tr>
                <tr>
                    <td class="tdRight">
                        <div id="datePickerParent">
                            <div id="datePicker">
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td id="tdAddButton">
                        <input type="submit" id="addButton" value="Add payment"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:set var="addSuccessMessage" value="${param['addSuccessMessage']}"/>
                        <c:if test="${addSuccessMessage != null}">
                            <h4 class="successMsg"><fmt:message key="${addSuccessMessage}"/></h4>
                        </c:if>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form:form>

    <div class="manageP">
        <a class="no_underline" href="currentPayments"> >> View current month payments</a>
    </div>
</div>

</body>
</html>