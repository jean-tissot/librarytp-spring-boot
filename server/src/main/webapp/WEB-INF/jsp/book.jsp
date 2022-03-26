<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>Create / Edit book Page</title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <h1>Create / Edit Book page</h1>
        <form action="saveBook.do" method="POST">
            <table>
                <tr>
                    <th>book #</th>
                    <td>
                        <c:choose>
                            <c:when test="${empty book.bookId}">NEW<input type="hidden" name="id" value="-1" /></c:when>
                            <c:otherwise>${book.bookId}<input type="hidden" name="id" value="${book.bookId}" /></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>Title</th>
                    <td><input type="text" name="Title" value="${book.bookTitle}" size="60" /></td>
                </tr>
                <tr>
                    <th>Authors</th>
                    <td><input type="text" name="Authors" value="${book.bookAuthors}" size="60" /></td>
                </tr>
                <tr>
                    <td colspan="2"><button type="submit" class="large">Save</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>

