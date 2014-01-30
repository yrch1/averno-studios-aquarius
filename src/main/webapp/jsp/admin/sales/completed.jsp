<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="sessionBean" scope="session" type="com.gruporon2005.aquarius.bean.SessionBean" />

<c:url var="url1" value="/servlet/exportOrder.view"/>
<div class="contenido">
    <div class="content">
        <h1>PEDIDOS COMPLETADOS</h1>
        <div class="top">
            <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />

            <jsp:include page="/jsp/helper/storeSelector.jsp" />
            <c:if test="${not empty orderList}">
                <jsp:include page="/jsp/helper/pageSize.jsp" />
            </c:if>


            <div class="link">
                <span id="selectAll" >Seleccionar Todo</span> |
                <span id="deselectAll">Deseleccionar</span> |
                <span id="inverseAll">Invertir Seleccion</span>
            </div>
        </div>
        <br class="clear" />

        <c:if test="${sessionBean.storeId != -1 && not empty orderList}">
            <form action='<c:out value="${url1}" />' name="exportOrder" id="exportOrder">
                <div class="table">
                    <div class="row header">
                        <span class="check"></span>
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
                            <span class="check"><input type="checkbox" class="checkbox1" value='<c:out value="${order.increment_id}" />' name="order_ids" /></span>
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
                <input type="submit" class="button"  value="Tramitar pedidos"/>
            </form>

            <c:if test="${not empty orderList}">
                <jsp:include page="/jsp/helper/pagination.jsp" >
                    <jsp:param name="url" value="/actions/admin/sales/completed.do" />
                    <jsp:param name="numPages" value="${numPages}" />
                    <jsp:param name="storeId" value="${sessionBean.storeId}" />
                    <jsp:param name="pageSize" value="${pageSize}" />                    
                    <jsp:param name="dateFrom" value="${dateFrom}" />
                </jsp:include>
            </c:if>

        </c:if>
        <c:if test="${empty  orderList}">
            No hay ventas que tramitar
        </c:if>
        <c:if test="${sessionBean.storeId == -1}">
            Selecciona una tienda para continuar
        </c:if>
    </div>

</div>

<script type="text/javascript">

    (function(){

        function selectAll(value){
            jQuery('.checkbox1').each(function(index){
                this.checked = value;
            });
        }

        function inverseSelection(){
            jQuery('.checkbox1').each(function(index){
                this.checked = !this.checked ;
            });
        }

        jQuery('#selectAll').click(function(){
            selectAll(true);
        });

        jQuery('#deselectAll').click(function(){
            selectAll(false);
        });

        jQuery('#inverseAll').click(function(){
            inverseSelection();
        });

    })();
</script>
<script type="text/javascript">
    jQuery('#menuPedidos').addClass('current');
</script>