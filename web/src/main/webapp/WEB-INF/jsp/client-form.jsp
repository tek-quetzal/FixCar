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

<c:set var="curId" value="${not empty client.id ? client.id : param.id}" />

<c:choose>
    <c:when test="${not empty curId}">
        <c:set var="pageTitle" value="Изменение клиента с ID ${curId}" />
        <c:set var="formAction" value="change" />
        <c:set var="buttonText" value="Изменить" />
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle" value="Создание клиента" />
        <c:set var="formAction" value="create" />
        <c:set var="buttonText" value="Создать" />
    </c:otherwise>
</c:choose>


<c:if test="${not empty errorMessage}">
    <div class="validation-error">
        <h1 class="validation-error__h1">Ошибка</h1>
        <p class="validation-error__p">${errorMessage}</p>
    </div>
</c:if>




<h1>${pageTitle}</h1>
<form action="${contextPath}/clients" method="post">
    <c:set var="clientName" value="${param.clientName}"/>
    <c:set var="clientPhone" value="${param.clientPhone}"/>

    <c:if test="${not empty curId}">
        <input type="hidden" name="id" value="${curId}">
    </c:if>

    <input type="hidden" name="action" value="${formAction}">

    <label for="client-name">Имя</label>
    <input type="text" id="client-name" name="clientName" value="<c:out value="${empty clientName ? client.name : clientName}"/>">

    <label for="client-phone">Телефон</label>
    <input type="text" id="client-phone" name="clientPhone" value = "<c:out value="${empty clientPhone ? client.phone : clientPhone}"/>">

    <button type="submit">${buttonText}</button>
    <a href="${contextPath}/clients">Отмена</a>
</form>






</body>


</html>