<%@ page contentType="text/html" %>
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
<c:set var="currentId" value="${not empty mechanic.id ? mechanic.id : param.mechanicId}"/>

<c:choose>
    <c:when test="${not empty currentId}">
        <c:set var="pageTitle" value="Изменение механика с именем ${mechanic.name}" />
        <c:set var="formAction" value="change"/>
        <c:set var="buttonText" value="Изменить"/>
    </c:when>
    <c:otherwise>
        <c:set var="pageTitle" value="Создание механика" />
        <c:set var="formAction" value="create"/>
        <c:set var="buttonText" value="Создать"/>
    </c:otherwise>
</c:choose>


<%@ include file="/WEB-INF/jsp/validationError.jsp" %>

<h1><c:out value="${pageTitle}"/></h1>
<form action="${contextPath}/mechanics" method="post">
    <c:set var="mechanicName" value="${param.mechanicName}"/>
    <c:set var="mechanicSpecialization" value="${param.mechanicSpecialization}"/>

    <c:if test="${not empty currentId}">
        <input type="hidden" name="mechanicId" value="${currentId}">
    </c:if>

    <input type="hidden" name="action" value="${formAction}">

    <label for="mechanic-name">Имя</label>
    <input type="text" id="mechanic-name" name="mechanicName"
           value="<c:out value='${empty mechanic.name ? mechanicName : mechanic.name}'/>">

    <label for="mechanic-specialization">Имя</label>
    <input type="text" id="mechanic-specialization" name="mechanicSpecialization"
           value="<c:out value='${empty mechanic.specialization ? mechanicSpecialization : mechanic.specialization}'/>">

    <button type="submit">${buttonText}</button>
    <a href="${contextPath}/mechanics">Отмена</a>

</form>

</body>
</html>