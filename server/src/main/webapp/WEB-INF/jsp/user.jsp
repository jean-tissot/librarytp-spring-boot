<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>Create / Edit User Page</title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-3.3.1.min.js" > </script>
        <script type="text/javascript" src="js/myScript.js" > </script>
    </head>
    <body>
        <h1>Create / Edit User page</h1>
        <form action="saveUser.do" method="POST">
            <table>
                <tr>
                    <th>user #</th>
                    <td>
                        <c:choose>
                            <c:when test="${empty user.personId}">NEW<input type="hidden" name="id" value="-1" /></c:when>
                            <c:otherwise>${user.personId}<input type="hidden" name="id" value="${user.personId}" /></c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <th>FirstName</th>
                    <td><input type="text" name="FirstName" value="${user.personFirstname}" size="60" /></td>
                </tr>
                <tr>
                    <th>LastName</th>
                    <td><input type="text" name="LastName" value="${user.personLastname}" size="60" /></td>
                </tr>
                <tr>
                    <th>Birthdate</th>
                    <td><input type="date" name="Birthdate" 
                               value="<fmt:formatDate value="${user.personBirthdate}" pattern="yyyy-MM-dd" />" size="12" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><button type="submit" class="large">Save</button></td>
                </tr>
            </table>
        </form>


        <h2>Borrow list:</h2>
        <table>
            <tr>
                <th style="width:100px">Date</th>
                <th>Title</th>
                <th style="width:100px">Return</th>
            </tr>

            <c:forEach var="borrow" items="${user.borrowCollection}">
                <tr>
                    <td><fmt:formatDate value="${borrow.borrowDate}" pattern="yyyy-MM-dd" /></td>
                    <td>${ borrow.bookId.bookTitle }</td>
                    <td class="centered">
                        <c:choose> 
                            <c:when test="${not empty borrow.borrowReturn}">
                                <fmt:formatDate value="${borrow.borrowReturn}" pattern="yyyy-MM-dd" />
                            </c:when>
                            <c:otherwise>
                                <button class="icon" name="return" 
                                        onclick="returnBorrow(this, ${ borrow.borrowId }); return false;">
                                    <img src="img/return.png" alt="return" class="icon" />
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>

            <form action="addBorrow.do" method="POST">
                <tr>
                    <td colspan="2">
                        <input type="hidden" name="userID" value="${ user.personId }" />
                        <select name="bookID" class="large">
                            <option value="-1" selected="selected">-</option>
                            <c:forEach var="book" items="${booksList}">
                                <option value="${ book.bookId}">${ book.bookTitle }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td  class="centered">
                        <button><img src="img/plus.png" alt="add" class="icon" /></button>
                    </td>
                </tr>
            </form>
        </table>
    </body>
</html>

