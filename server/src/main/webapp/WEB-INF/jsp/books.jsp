<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>List Books Page</title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-3.3.1.min.js" ></script>
        <script type="text/javascript" src="js/myScript.js" ></script>
    </head>
    <body>
        <table class="noborder">
            <tr>
                <td class="noborder"><h1>List of books</h1></td>
                <td class="noborder" style="text-align:right">
                    <form action="switchUsers.do" method="POST"><button>Switch users</button></form>
                </td>
            </tr>
        </table>

        <table class="list">
            <tr>
                <th style="width:50px">book #</th>
                <th>Title</th>
                <th>Authors</th>
                <th style="width:100px"></th>
            </tr>
            <c:forEach var="aBook" items="${booksList}">
                <tr>
                    <td>${aBook.bookId}</td>
                    <td>${aBook.bookTitle}</td>
                    <td>${aBook.bookAuthors}</td>
                    <td  class="centered">
                        <form action="#" method="POST">
                            <input type="hidden" name="id" value="${aBook.bookId}" />
                            <button name="edit" formaction="editBook.do"><img src="img/edit.png" alt="edit" class="icon" /></button>
                            <button name="delete" formaction="deleteBook.do"><img src="img/delete.png" alt="delete" class="icon" /></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr id="addNew">
                <td colspan="3"></td>
                <td  class="centered">
                    <form action="createBook.do" method="POST">
                        <button><img src="img/plus.png" alt="add" class="icon" /></button>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>

