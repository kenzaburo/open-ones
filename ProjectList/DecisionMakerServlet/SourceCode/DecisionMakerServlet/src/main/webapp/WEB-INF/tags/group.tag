<%@ attribute name="list" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="grouptag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!empty list}">
<option value="${list.getDn()}">${list.getName()}</option>
<optgroup label="${list.getName()}">
  <c:forEach var="group" items="${list.getChild()}">
    <option value="${group.getDn()}">${group.getName()}</option>
    <grouptag:group list="${group}"></grouptag:group>
  </c:forEach>
</optgroup>
</c:if>