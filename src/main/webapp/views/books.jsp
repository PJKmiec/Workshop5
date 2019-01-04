<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 03.01.19
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="true" %>
<html>
<head>
    <title>Books</title>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="/resources/js/main.js" />" type="text/javascript"></script>
</head>
<body>
<h1>BOOKS</h1>

<a href="add-book" class="add"><span></span> Dodaj nową książkę</a>
<div class="clear"></div>
<div class="newbook">
    <b>Dodawanie nowej książki:</b><br><br>
    <div class="align-r">
        Tytuł:<br>
        Autor:<br>
        Rodzaj:<br>
        ISBN:<br>
        Wydawca:<br>
    </div>
    <div>
        <form id="addBookForm" method="post">
        <input type="text" name="title"><br>
        <input type="text" name="author"><br>
        <input type="text" name="type"><br>
        <input type="text" name="isbn"><br>
        <input type="text" name="publisher"><br>
        <button id="addBookSubmit">Dodaj książkę &raquo;</button>
        <button id="addBookCancel">Anuluj</button>
        </form>
    </div>
</div>
<div class="clear"></div>

<table>
    <thead>
    <th>Tytuł:</th>
    <th>Autor:</th>
    <th>Rodzaj:</th>
    <th></th>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="item">
        <tr>
            <td>${item.title}</td>
            <td>${item.author}</td>
            <td>${item.type}</td>
            <td>
                <a href="book-view/${item.id}" class="view"><span></span></a>
                <a href="book-edit/${item.id}" class="edit"><span></span></a>
                <a href="book-delete/${item.id}" class="delete"><span></span></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

