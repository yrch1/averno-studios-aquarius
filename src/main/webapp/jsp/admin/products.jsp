<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="sessionBean" scope="session" type="com.gruporon2005.aquarius.bean.SessionBean" />

<c:set var="ascDesc" value="ASC" />
<c:if test="${param.ascDesc == 'ASC'}">
    <c:set var="ascDesc" value="DESC" />
</c:if>
<div class="contenido">
    <div class="content">

        <h1>Productos</h1>
        <div class="top">
            <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />

            <c:url var="url0" value="/actions/admin/products/view.do" />
            <input type="submit" class="button" value="Añadir un nuevo producto" onClick="location.href='${url0}';">
            <c:if test="${not empty productsInfoList}">
                <jsp:include page="/jsp/helper/pageSize.jsp">
                    <jsp:param name="sortField" value="sku" />
                    <jsp:param name="ascDesc" value="ASC" />
                </jsp:include>
            </c:if>
        </div>
        <div class="top">
            <form action="">
                <input type="text" name="q" value="sku" />
                <input type="submit" name="buscarSKU" value="buscar" />
            </form>
        </div>
        <div class="table">
            <div class="row header">
                <span class="cell"><a href="?sortField=sku&ascDesc=${ascDesc}&pageSize=${pageSize}">Producto:</a></span>
                <span class="cell"><a href="?sortField=owner&ascDesc=${ascDesc}&pageSize=${pageSize}">Propietario:</a></span>
                <span class="cell">Adicional:</span>
            </div>

            <c:forEach var="product" items="${productsInfoList}">
                <c:url var="url0" value="/actions/admin/products/view.do">
                    <c:param name="sku" value="${product.sku}" />
                </c:url>
                <div class="row body">
                    <span class="cell"><c:out value="${product.sku}"/></span>
                    <span class="cell"><c:out value="${product.owner}"/></span>
                    <span class="cell"><c:out value="${product.additional}"/></span>
                    <span class="cell3"><a href='<c:out value="${url0}" />'>Ver <c:out value="${product.sku}"/></a></span>
                </div>
            </c:forEach>
        </div>

        <c:if test="${not empty productsInfoList}">
            <jsp:include page="/jsp/helper/pagination.jsp" >
                <jsp:param name="url" value="/actions/admin/products.do" />
                <jsp:param name="numPages" value="${numPages}" />
                <jsp:param name="storeId" value="${sessionBean.storeId}" />
                <jsp:param name="pageSize" value="${pageSize}" />
            </jsp:include>
        </c:if>

        <div class="top">
            <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />
            <c:url var="url0" value="/actions/admin/products/view.do" />
            <input type="submit" class="button" value="Añadir un nuevo producto" onClick="location.href='${url0}';">
            <c:if test="${not empty productsInfoList}">
                <jsp:include page="/jsp/helper/pageSize.jsp">
                    <jsp:param name="sortField" value="sku" />
                    <jsp:param name="ascDesc" value="ASC" />
                </jsp:include>
            </c:if>
        </div>

    </div>

</div>
<script type="text/javascript">
    jQuery('#menuProductos').addClass('current');
</script>

