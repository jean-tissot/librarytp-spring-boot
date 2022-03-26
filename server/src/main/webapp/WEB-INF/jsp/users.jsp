<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>List Users Page</title>
        <meta charset="UTF-8"/> 
        <link href="css/main.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <table class="noborder">
            <tr>
                <td class="noborder"><h1>List of users</h1></td>
                <td class="noborder" style="text-align:right">
                    <form action="switchBooks.do" method="POST"><button>Switch books</button></form>
                </td>
            </tr>
        </table>

        <table class="list">
            <tr>
                <th style="width:50px">user #</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th style="width:100px">Birthdate</th>
                <th style="width:100px"></th>
            </tr>
            <c:forEach var="anUser" items="${usersList}">
                <tr>
                    <td>${anUser.personId}</td>
                    <td>${anUser.personFirstname}</td>
                    <td>${anUser.personLastname}</td>
                    <td><fmt:formatDate value="${anUser.personBirthdate}" pattern="yyyy-MM-dd" /></td>
                    <td  class="centered">
                        <form action="#" method="POST">
                            <input type="hidden" name="id" value="${anUser.personId}" />
                            <button name="edit" formaction="editUser.do"><img src="img/edit.png" alt="edit" class="icon" /></button>
                            <button name="delete" formaction="deleteUser.do"><img src="img/delete.png" alt="delete" class="icon" /></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            <tr id="addNew">
                <td colspan="4"></td>
                <td  class="centered">
                    <form action="createUser.do" method="POST">
                        <button><img src="img/plus.png" alt="add" class="icon" /></button>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>



