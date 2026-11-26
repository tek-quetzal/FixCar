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


<c:if test="${not empty user.id}" >
    <h1>Изменение пользователя с ${user.id}</h1>
    <form action="${contextPath}/clients" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <label for="client-name">Имя</label>
        <input type="text" id="client-name" name="clientName" value="${user.name}">

        <label for="client-phone">Телефон</label>
        <input type="text" id="client-phone" name="clientPhone" value="${user.phone}">
        <input type="hidden" name="action" value="change">
        <button type="submit">Изменить</button>
        <a href="${contextPath}/clients">Отмена</a>
    </form>
</c:if>


<c:if test="${empty user.id}" >
    <h1>Создание пользователя</h1>
    <form action="${contextPath}/clients" method="post">
        <label for="client-name">Имя</label>
        <input type="text" id="client-name" name="clientName">

        <label for="client-phone">Телефон</label>
        <input type="text" id="client-phone" name="clientPhone">
        <input type="hidden" name="action" value="create">

        <button type="submit">Создать</button>
        <a href="${contextPath}/clients">Отмена</a>
    </form>
</c:if>

</body>


</html>