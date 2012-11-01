<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="salesForm" name="salesForm" action="">
    <div class="storeSelect">
        Selecci&oacute;r una una tienda
        <select name="storeId">
            <option value="0" <c:if test="${sessionBean.storeId == -1}">selected='selected'</c:if> >Todas las tiendas</option>
            <c:forEach items="${sessionBean.storeInfoHash}" var="store">
                <option value="${store.key}" <c:if test="${sessionBean.storeId == store.key}">selected='selected'</c:if> ><c:out value="${store.value.requester}"/></option>
            </c:forEach>
        </select>
        <input type="submit" class="button"  />
    </div>
</form>