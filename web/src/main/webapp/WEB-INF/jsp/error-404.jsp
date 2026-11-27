<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Не найдено</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
    <div class="error-box">
        <h1 class="error-box__code">404</h1>
        <h2 class="error-box__title">Страница или клиент не найдены</h2>
        <p class="error-box__text">
            ${errorMessage}
        </p>
        <a class="error-box__btn" href="${contextPath}/clients">Вернуться к клиентам</a>
    </div>
</body>


</html>