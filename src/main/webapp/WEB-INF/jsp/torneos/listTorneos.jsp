<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="torneos">
    <h2>Torneos</h2>

    <table id="torneosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th>Fecha Incicio</th>
            <th>Fecha Fin</th>
            <th> Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${torneos}" var="torneo">
            <tr>
                
                <td>
                    <c:out value="${torneo.name}"/>
                </td>
                  <td><petclinic:localDate date="${torneo.fechaInicio}" pattern="yyyy/MM/dd"/></td>
                
                  <td><petclinic:localDate date="${torneo.fechaFin}" pattern="yyyy/MM/dd"/></td>
                <td>
                 <td>
                                    <spring:url value="/torneos/delete/{torneoId}" var="torneoUrl">
                        <spring:param name="torneoId" value="${torneo.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(torneoUrl)}">Delete</a>
                </td>
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/torneos/new" htmlEscape="true"/>'>Añadir torneo</a>
	</sec:authorize>
</petclinic:layout>