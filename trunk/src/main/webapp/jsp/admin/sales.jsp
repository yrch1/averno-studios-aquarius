<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="sessionBean" scope="session" type="com.gruporon2005.aquarius.bean.SessionBean" />
<div class="contenido">
    <div class="content">

        <h1>PEDIDOS</h1>
        <div class="top">
            <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />

            <jsp:include page="/jsp/helper/storeSelector.jsp" />

            <c:if test="${not empty orderList}">
                <jsp:include page="/jsp/helper/pageSize.jsp" />
            </c:if>
        </div>
        <br class="clear" />
        <c:if test="${sessionBean.storeId != -1}">
            <div class="table">
                <div class="row header">
                    <span class="cell">Pedido #</span>
                    <span class="cell">Comprado el</span>
                    <span class="cell">Factura a nombre</span>
                    <span class="cell">Envio a nombre</span>
                    <span class="cell">Total (Base)</span>
                    <span class="cell">Total (Comprado)</span>
                    <span class="cell">Estado</span>
                    <span class="cell">Ver</span>
                </div>

                <c:forEach var="order" items="${orderList}">
                    <c:url var="url0" value="/actions/admin/sales/order.do">
                        <c:param name="id" value="${order.increment_id}" />
                    </c:url>
                    <div class="row body">
                        <span class="cell"><c:out value="${order.increment_id}"/></span>
                        <span class="cell"><c:out value="${order.created_at}"/></span>
                        <span class="cell"><c:out value="${order.billing_name}"/></span>
                        <span class="cell"><c:out value="${order.shipping_name}"/></span>
                        <span class="cell"><fmt:formatNumber type="number" maxIntegerDigits="3" value="${order.base_grand_total}" /></span>
                        <span class="cell"><fmt:formatNumber type="number" maxIntegerDigits="3" value="${order.grand_total}" /></span>
                        <span class="cell"><c:out value="${order.status}"/></span>
                        <span class="cell"><a href='<c:out value="${url0}" />'>Ver <c:out value="${order.increment_id}"/></a></span>
                    </div>
                </c:forEach>
            </div>


            <c:if test="${not empty orderList}">
                <jsp:include page="/jsp/helper/pagination.jsp" >
                    <jsp:param name="url" value="/actions/admin/sales.do" />
                    <jsp:param name="numPages" value="${numPages}" />
                    <jsp:param name="storeId" value="${sessionBean.storeId}" />
                    <jsp:param name="pageSize" value="${pageSize}" />
                </jsp:include>
            </c:if>

            <div class="top">
                <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />

                <jsp:include page="/jsp/helper/storeSelector.jsp" />

                <c:if test="${not empty orderList}">
                    <jsp:include page="/jsp/helper/pageSize.jsp" />
                </c:if>
            </div>

        </c:if>
        <c:if test="${sessionBean.storeId == -1}">
            Selecciona una tienda para continuar
        </c:if>
    </div>

</div>
<script type="text/javascript">
    jQuery('#menuPedidos').addClass('current');
</script>

