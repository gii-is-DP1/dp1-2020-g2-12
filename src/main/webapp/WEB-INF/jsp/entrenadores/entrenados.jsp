<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="athletes">

    <h2>Atletas sin entrenador <c:out value="${entrenador.firstName}" /></h2>

    <table id="athletesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Name</th>
            <th style="width: 150px;">Height</th>
            <th style="width: 150px;">Weight</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${atletas}" var="atletas">
            <tr>
                <td>
                    <spring:url value="/athletes/{athleteId}" var="athleteUrl">
                        <spring:param name="athleteId" value="${atletas.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(athleteUrl)}"><c:out value="${atletas.firstName} ${atletas.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${atletas.height}"/>
                </td>
                <td>
                    <c:out value="${atletas.weight}"/>
                </td>    
                <td>
                    <spring:url value="/entrenadores/{entrenadorId}/add/{athleteId}" var="athletedelUrl">
                        <spring:param name="athleteId" value="${atletas.id}"/>
                        <spring:param name="entrenadorId" value="${entrenador.id}"/>
                        
                    </spring:url>
                    <a href="${fn:escapeXml(athletedelUrl)}">Añadir</a>
                </td>             
            </tr>
        </c:forEach>
        </tbody>
         <sec:authorize access="hasAuthority('admin')">
	</sec:authorize>
    </table>
</petclinic:layout>