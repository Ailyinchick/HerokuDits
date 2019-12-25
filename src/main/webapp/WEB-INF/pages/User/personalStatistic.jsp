<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="container">


    <table border="2">
        <tr>
            <th>Ф.И.О.</th>
            <th>Название теста</th>
            <th>Формулировка вопроса</th>
            <th>Пройдено всего</th>
            <th>Процент правильно пройденных вопросов</th>
        </tr>

        <c:forEach items="${statistic}" var="item">
            <tr>
                <td> ${item.FIO} </td>
                <td> ${item.testName} </td>
                <td> ${item.questionDescription} </td>
                <td> ${item.countAnswers} </td>
                <td> ${item.questionRate} </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <form action="/goUserHome">
        <input type="submit" value="Home page">
    </form>

</div>
</body>
</html>