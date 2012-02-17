<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<select id="monthsList" name="monthsList">
    <c:forEach items="${monthsList}" var="month">
        <option value="${month}">${month}</option>
    </c:forEach>
</select>