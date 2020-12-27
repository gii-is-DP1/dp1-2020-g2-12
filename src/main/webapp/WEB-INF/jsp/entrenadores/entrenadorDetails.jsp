<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="entrenadores">
    <h2>Entrenador</h2>
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
       <h2><b> 
       		<spring:url value="/athletes/{athleteId}" var="athleteUrl">
            <spring:param name="athleteId" value="${athlete.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(athleteUrl)}"><c:out value="${athlete.firstName} ${athlete.lastName}"/></a></b></h2>


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
    <spring:url value="/entrenadores/{entrenadorId}/delete/{athleteId}" var="athletedelUrl">
                        <spring:param name="athleteId" value="${athlete.id}"/>
                        <spring:param name="entrenadorId" value="${athlete.entrenador.id}"/>

                    </spring:url>
                    <a href="${fn:escapeXml(athletedelUrl)}" class="btn btn-default">Eliminar</a><br/>
    <br/>
        </c:forEach>
    </table>
   
    <spring:url value="{entrenadorId}/add" var="addAthleteUrl">
        <spring:param name="entrenadorId" value="${entrenador.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(addAthleteUrl)}" class="btn btn-default">Add Athlete</a>


</petclinic:layout>