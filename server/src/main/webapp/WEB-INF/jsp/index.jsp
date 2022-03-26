<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr-fr">
    <head>
        <title>Library Login</title>
        <meta charset="UTF-8"/> 
        <link rel="stylesheet" type="text/css" media="screen" href="css/main.css"/>
    </head>
    <body>
        <form action="index.do" method="POST" class="login">
            <h1>Library Login</h1>
            <p><input type="text" name="user" placeholder="Login" /></p>
            <p><input type="password" name="passwd" placeholder="Password" /></p>
            <p><button type="submit" class="large">Login</button></p>
        </form>
    </body>
</html>

