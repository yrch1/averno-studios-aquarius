<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="sessionBean" scope="session" type="com.gruporon2005.aquarius.bean.SessionBean" />

<script type="text/javascript" src="/Aquarius/js/jquery-validate/1.7/jquery.validate.min.js"></script>
<script type="text/javascript" src="/Aquarius/js/jquery-validate/1.7/messages_es.js"></script>
<div class="contenido">
    <div class="content">
        <div class="top">
            <input type="button" value="Volver atrás" onclick="window.history.back();" />
        </div>
        <br/><br/>
        <c:if test="${not empty product}">
            <form action="/Aquarius/actions/admin/products/update.do" name="updateProduct" id="updateProduct">

                <label>Producto: </label><input class="required" type="text" readonly="readonly" name="sku" value="${product.sku}">
                <label>Propietario: </label><input class="required" type="text" name="owner" value="${product.owner}">
                <label>Adicional: </label><input class="required" type="text" name="additional" value="${product.additional}" ><br/><br/><br/>
                <input type="submit" value="Modificar" class="button float"  />

            </form>
            <form action="/Aquarius/actions/admin/products/delete.do" name="deleteProduct" class="float">
                <input type="submit" value="Eliminar" class="button"  />
                <input type="hidden" name="sku" value="${product.sku}">
            </form>

        </c:if>
        <c:if test="${empty product}">
            <form action="/Aquarius/actions/admin/products/add.do" name="addProduct" id="addProduct">

                <label>Producto: </label><input class="required" type="text" name="sku" value="sku">
                <label>Propietario: </label><input class="required" type="text" name="owner" value="owner">
                <label>Adicional: </label><input class="required" type="text" name="additional" value="additional">
                <input type="submit" value="Añadir" class="button"  />

            </form>
        </c:if>


    </div>
</div>
<script type="text/javascript">
    jQuery('#menuProductos').addClass('current');
    jQuery("#addProduct").validate();
    jQuery('#updateProduct').validate();
</script>