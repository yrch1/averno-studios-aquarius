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

    </script>
</div>