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
        <button> <a href="${contextPath}/client-form"></a></button>

    </c:when>


    <c:when test="${not empty clients}">
        <table class="clients">
            <caption>Таблица пользователей</caption>
                <tr>
                    <th>№</th>
                    <th>Имя</th>
                    <th>Номер телефона</th>
                    <th></th>
                    <th></th>

                </tr>
            <c:forEach var="client" items="${clients}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value ="${client.name}" /></td>
                    <td><c:out value ="${client.phone}" /></td>
                    <td>
                        <a class="change__button" href="${contextPath}/clients?id=${client.id}">Изменить</a>
                    </td>
                    <td>
                        <form action="${contextPath}/clients" method="post"
                              onsubmit="return confirm('Точно удалить клиента?');">
                            <input type="hidden" name="id" value="${client.id}">
                            <button class="delete__button" type="submit">Удалить</button>
                        </form>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:when>

</c:choose>


</body>


</html>