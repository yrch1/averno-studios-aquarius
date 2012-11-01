<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="contenido">
    <div class="content">
        <a href="../sales.do" title="Volver al listado de los pedidos">&laquo; Volver al listado de los pedidos</a><br/>
        <br/>
        <h1>ORDEN</h1>
        <input type="button" value="Volver atrás" class="button2" onclick="window.history.back();" />
        <div class="clear"></div>
        <br/>
        <div class="top2">

            <div id="account_info">
                <h2>Account info</h2>
                <ul>
                    <li><strong>Nombre del cliente:</strong> <c:out value="${orderInfo.customer_firstname} ${orderInfo.customer_lastname}"/></li>
                    <li><strong>Correo electronico:</strong> <c:out value="${orderInfo.customer_email}"/></li>
                    <li><strong>Grupo de clientes:</strong> <c:out value="${orderInfo.customer_group_id}"/></li>
                    <li><strong>DNI:</strong> <c:out value="${customerInfo.taxvat}"/></li>
                </ul>
            </div>

            <div id="billing_address">
                <h2>Datos de facturación</h2>
                <ul>
                    <li><c:out value="${orderInfo.billing_address.firstname} ${orderInfo.billing_address.lastname}"/></li>
                    <li><c:out value="${orderInfo.billing_address.street}"/></li>
                    <li><c:out value="${orderInfo.billing_address.city}"/> <c:out value="${orderInfo.billing_address.region}"/> <c:out value="${orderInfo.billing_address.postcode}"/> -
                        <c:out value="${orderInfo.billing_address.country_id}"/></li>
                </ul>

            </div>

            <div id="shipping_address">
                <h2>Datos de envío</h2>
                <ul>
                    <li><c:out value="${orderInfo.shipping_address.firstname} ${orderInfo.shipping_address.lastname}"/></li>
                    <li><c:out value="${orderInfo.shipping_address.street}"/></li>
                    <li><c:out value="${orderInfo.shipping_address.city}"/> <c:out value="${orderInfo.shipping_address.region}"/> <c:out value="${orderInfo.shipping_address.postcode}"/> -
                        <c:out value="${orderInfo.shipping_address.country_id}"/></li>
                </ul>
            </div>
        </div>
        <br/>
        <br class="clear" />


        <div class="table" id="items_ordered">
            <div class="row header">
                <span class="cell2">Producto</span>
                <span class="cell2">Estado del artículo</span>
                <span class="cell2">Precio original</span>
                <span class="cell2">Precio</span>
                <span class="cell2">Cantidad</span>
                <span class="cell2">Subtotal</span>
                <span class="cell2">Importe del impuesto</span>
                <span class="cell2">Porcentaje de impuestos</span>
                <span class="cell2">Importe del descuento</span>
                <span class="cell2">Total de fila</span>

            </div>
            <c:forEach var="item" items="${orderInfo.items}">
                <div class="row body">
                    <span class="cell2"><c:out value="${item.sku}" /></span>
                    <span class="cell2"><fmt:formatNumber type="number" maxIntegerDigits="3" value="${item.qty_ordered}" /></span>
                    <span class="cell2"><c:out value="${orderInfo.billing_address.firstname} ${orderInfo.billing_address.lastname}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.shipping_address.firstname} ${orderInfo.shipping_address.lastname}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.base_grand_total}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.grand_total}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.status}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.status}"/></span>
                    <span class="cell2"><c:out value="${orderInfo.status}"/></span>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
<script type="text/javascript">
    jQuery('#menuPedidos').addClass('current');
</script>