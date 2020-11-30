<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="athletes">

    <h2><b><c:out value="${athlete.firstName} ${athlete.lastName}"/></b></h2>


     <table class="table table-striped">
        <tr>
            <th>Height</th>
            <td><c:out value="${athlete.height}"/></td>
        </tr>
        <tr>
            <th>Weight</th>
            <td><c:out value="${athlete.weight}"/></td>
        </tr>
        <tr>
            <th>Genero</th>
            <td><c:out value="${athlete.genero}"/></td>
        </tr>
    </table>

    <spring:url value="{athleteId}/edit" var="editUrl">
        <spring:param name="athleteId" value="${athlete.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default">Edit Athlete</a>
    
    <br/>
    <br/>
    <br/>
    
    <h2>Sanciones</h2>

    <table class="table table-striped">
        <c:forEach var="sancion" items="${athlete.sanciones}">

            <tr>
                <td valign="top">
                    <dl class="dl-horizontal">
                        <dt>Fecha de finalizacion</dt>
                        <dd><petclinic:localDate date="${sancion.fechaFin}" pattern="yyyy-MM-dd"/></dd>
                        <dt>Descripcion</dt>
                        <dd><c:out value="${sancion.descripción}"/></dd>
                        
                    </dl>
                </td>
		</c:forEach>
    </table>
     <spring:url value="/entrenadores/{entrenadorId}/athletes/{athleteId}/newSancion" var="newSancionUrl">
        <spring:param name="entrenadorId" value="${athlete.entrenador.id}"/>
        <spring:param name="athleteId" value="${athlete.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(newSancionUrl)}" class="btn btn-default">Sancionar</a>
</petclinic:layout>
