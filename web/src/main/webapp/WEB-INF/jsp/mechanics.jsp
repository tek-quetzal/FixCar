<%@ page contentType="text/html;charset=UTF-8"%>
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

<%@ include file="/WEB-INF/jsp/validation-error.jsp" %>


<c:choose>
    <c:when test="${empty mechanics}">
        <p>Механиков не найдено</p>
        <a class="create__button" href="${contextPath}/mechanics?action=create">Добавить механика</a>
    </c:when>


    <c:otherwise>
        <table class="mechanics">
            <caption>Таблица механиков</caption>
            <tr>
                <th>№</th>
                <th>Имя</th>
                <th>Специализация</th>
                <th></th>
                <th></th>

            </tr>
            <c:forEach var="mechanic" items="${mechanics}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td><c:out value ="${mechanic.name}" /></td>
                    <td><c:out value ="${mechanic.specialization}" /></td>
                    <td>
                        <a class="change__button" href="${contextPath}/mechanics?mechanicId=${mechanic.id}&action=change">Изменить</a>
                    </td>
                    <td>
                        <form class="delete" action="${contextPath}/mechanics" method="post"
                              onsubmit="return confirm('Точно удалить механика?');">
                            <input type="hidden" name="mechanicId" value="${mechanic.id}">
                            <input type="hidden" name="action" value="delete">
                            <button class="delete__button" type="submit">Удалить</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a class="create__button" href="${contextPath}/mechanics?action=create">Добавить механика</a>
    </c:otherwise>

</c:choose>


</body>


</html>