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

<div class="menu">
    <a class="menu__logo" href="${contextPath}/">FixCar</a>
    <a href="${contextPath}/clients">Клиенты</a>
    <a href="${contextPath}/cars">Машины</a>
    <a href="${contextPath}/mechanics">Механики</a>
    <a href="${contextPath}/orders">Заказы</a>
</div>

<div class="container">
    <h1>Автосервис FixCar</h1>
    <p>Учёт клиентов, автомобилей, механиков и заказов.</p>

    <div class="container__tiles">
        <a class="container__tile" href="${contextPath}/clients">Клиенты</a>
        <a class="container__tile" href="${contextPath}/cars">Машины</a>
        <a class="container__tile" href="${contextPath}/mechanics">Механики</a>
        <a class="container__tile" href="${contextPath}/orders">Заказы</a>
    </div>
</div>

</body>
</html>