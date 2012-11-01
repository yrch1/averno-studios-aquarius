<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang='es-ES' lang="es-ES">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <jsp:include page="/jsp/defecto/css.jsp" />
        <jsp:include page="/jsp/defecto/js.jsp" />
    </head>
    <body>
        <jsp:include page="/jsp/defecto/header.jsp" />
        <form method="POST" action="j_security_check">
            User Name : <input type="text" name="j_username" />
            Password  : <input type="password" name="j_password" />
            <input type="submit" />
        </form>
        <jsp:include page="/jsp/defecto/footer.jsp" />
    </body>
</html>

