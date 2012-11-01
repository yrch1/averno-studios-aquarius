<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/actions/admin/sales.do" var="url0"></c:url>
<c:url value="/actions/admin/products.do" var="url1"></c:url>
<c:url value="/actions/admin/prueba.do" var="url2"></c:url>
<div class="contenido">
    <div class="content">
        <h1>Bienvenidos a la herramienta de gesti&oacute;n de Grupo Ron</h1>
    </div>
    <div class="menu_home">
        <ul>
            <li class="usuario"><a href="${url2}" ><span>usuario</span></a></li>
            <li class="pedido"><a href="${url0}"><span>pedido</span></a></li>
            <li class="producto"><a href="${url1}" ><span>producto</span></a></li>
        </ul>
    </div>
</div>
<script type="text/javascript">
    jQuery('#menuHome').addClass('current');
</script>