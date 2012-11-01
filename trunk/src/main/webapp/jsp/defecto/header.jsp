<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Inicio header -->
<c:url value="/actions/admin/sales.do" var="url0"></c:url>
<c:url value="/actions/admin/sales/completed.do" var="url1"></c:url>
<c:url value="/actions/admin/sales/fullyCompleted.do" var="url2"></c:url>
<c:url value="/inicio.do" var="url3"></c:url>
<c:url value="/actions/admin/products.do" var="url4"></c:url>
<div class="head">
    <div class="logo">&nbsp;</div>

    <div class="nav">
        <div class="menu">
            <ul>
                <li><a id="menuHome" href='<c:out value="${url3}"/>'><b>HOME</b></a></li>
                <li><a id="menuPedidos" href="<c:out value="${url0}"/>"><b>PEDIDOS</b></a>
                    <ul>
                        <li><a href='<c:out value="${url1}"/>'><b>COMPLETOS</b></a></li>
                        <li><a href='<c:out value="${url2}"/>'><b>TRAMITADOS</b></a></li>
                    </ul>
                </li>
                <li>
                    <a id="menuProductos" href="<c:out value="${url4}"/>"><b>PRODUCTOS</b></a>
                </li>
            </ul>
        </div>
    </div>

</div>

<!-- Fin header -->