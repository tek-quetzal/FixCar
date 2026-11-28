<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Не найдено</title>
    <link rel="stylesheet" href="${contextPath}/css/style.css">
</head>

<c:set var="uri" value="${pageContext.request.requestURI}" />
<c:set var="lastSegment" value="${uri.substring(uri.lastIndexOf('/')+1)}"/>


<body>
    <%@ include file="/WEB-INF/jsp/header.jsp" %>
    <div class="error-box">
        <h1 class="error-box__code">404</h1>
        <h2 class="error-box__title">Страница не найдена</h2>
        <p class="error-box__text">
            ${errorMessage}
        </p>
        <c:choose>
            <c:when test="${lastSegment == 'mechanics' || lastSegment == 'clients' || lastSegment == 'cars' || lastSegment == 'orders'}">
                <a class="error-box__btn" href="${contextPath}/${lastSegment}">Вернуться назад</a>
            </c:when>
            <c:otherwise>
                <a class="error-box__btn" href="${contextPath}/home">Вернуться на главную</a>
            </c:otherwise>
        </c:choose>
    </div>
</body>


</html>