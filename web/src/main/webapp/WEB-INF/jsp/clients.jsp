<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>FixCar</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css">
</head>
<body>

    <%@ include file="/WEB-INF/jsp/header.jsp" %>
<c:choose>
    <c:when test="${empty clients}">
        <p>Клиентов не найдено</p>
        <button> <a href="${contextPath}/client"></a></button>
    </c:when>


    <c:when test="${not empty clients}">
        <table class="clients">
            <caption>Таблица пользователей</caption>
                <tr>
                    <th>№</th>
                    <th>Имя</th>
                    <th>Номер телефона</th>
                </tr>
            <c:forEach var="client" items="${clients}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value ="${client.name}" /></td>
                    <td><c:out value ="${client.phone}" /></td>
                </tr>
            </c:forEach>

    </c:when>
        </table>
</c:choose>
</body>


</html>