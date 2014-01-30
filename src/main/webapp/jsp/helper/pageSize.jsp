<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="pageSize">
    <form action="" id="pageSizeForm">
        <label>Mostrar </label>
        <select name="pageSize" id="pageSizeSelector">
            <option value="10" <c:if test="${pageSize == 10}">selected="selected"</c:if> >10</option>
            <option value="20" <c:if test="${pageSize == 20}">selected="selected"</c:if>>20</option>
            <option value="30" <c:if test="${pageSize == 30}">selected="selected"</c:if>>30</option>
            <option value="100" <c:if test="${pageSize == 100}">selected="selected"</c:if>>100</option>
        </select>
        <label>por página</label>
        <input type="hidden" name="sortField" value="${param.sortField}" />
        <input type="hidden" name="ascDesc" value="${param.ascDesc}" />
        
        <label>Realizados </label>
        <select name="dateFrom" id="dateFromeSelector">
            <option value="1" rel="${dateFrom}" <c:if test="${dateFrom == 1}">selected="selected"</c:if>>Día anterior</option>
            <option value="2" <c:if test="${dateFrom == 2}">selected="selected"</c:if>>Última semana</option>
            <option value="3" <c:if test="${dateFrom == 3}">selected="selected"</c:if>>Último mes</option>
            <option value="4" <c:if test="${dateFrom == 4}">selected="selected"</c:if>>Último año</option>
        </select>
    </form>
    <script type="text/javascript">
        jQuery('#pageSizeSelector').change(function(){
            try{
                var value = jQuery('#pageSizeSelector')[0].options[jQuery('#pageSizeSelector')[0].selectedIndex].value;
                if(value != ''){
                    document.forms["pageSizeForm"].submit();
                }
            }
            catch(exception){
            }

        });
        jQuery('#dateFromeSelector').change(function(){
            try{
                var value = jQuery('#dateFromeSelector')[0].options[jQuery('#dateFromeSelector')[0].selectedIndex].value;
                if(value != ''){
                    document.forms["pageSizeForm"].submit();
                }
            }
            catch(exception){
            }

        });

    </script>
</div>