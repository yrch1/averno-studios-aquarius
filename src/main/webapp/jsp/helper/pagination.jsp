<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination">
    <c:forEach begin="1" end="${param.numPages}" var="pos">
        <c:url value="${param.url}" var="url1">
            <c:param name="offset" value="${(pos-1)*param.pageSize}" />
            <c:param name="pageSize" value="${param.pageSize}" />
            <c:param name="dateFrom" value="${param.dateFrom}" />
        </c:url>
        <a class='<c:if test="${pos == currentPage}"><c:out value="current" /></c:if>' href='<c:out value="${url1}" />'><c:out value="${pos}" /></a>
    </c:forEach>
</div>