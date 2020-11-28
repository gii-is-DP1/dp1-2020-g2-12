<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="entrenadores">

    <h2>Owner Information</h2>


    <table class="table table-striped">
        <tr>
            <th>Name</th>
            <td><b><c:out value="${entrenador.firstName} ${entrenador.lastName}"/></b></td>
        </tr>
    </table>

    <spring:url value="{entrenadorId}/edit" var="editUrl">
        <spring:param name="entrenadorId" value="${entrenador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Entrenador</a>

    <spring:url value="{entrenadorId}/athletes/new" var="addUrl">
        <spring:param name="entrenadorId" value="${entrenador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addUrl)}" class="btn btn-default">Add New Athlete</a>

    <br/>
    <br/>
    <br/>
    <h2>Athletes</h2>

    <table class="table table-striped">
        <c:forEach var="athlete" items="${entrenador.athletes}">
            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Name</dt>
                        <dd><c:out value="${athlete.firstName}"/></dd>                        
                    </dl>
                </td>
			</tr>
            <tr>
				<td>
				 	<spring:url value="/entrenadores/{entrenadorId}/athletes/{athleteId}/edit" var="petUrl">
                    	<spring:param name="entrenadorId" value="${entrenador.id}"/>
                        <spring:param name="athleteId" value="${athlete.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(petUrl)}">Edit Athlete</a>
				</td>            
            </tr>
        </c:forEach>
    </table>

</petclinic:layout>
