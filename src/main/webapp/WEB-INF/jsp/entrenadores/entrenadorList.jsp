<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="entrenadores">
    <h2>Entrenadores</h2>

    <table id="entrenadoresTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 250px;">Nombre</th>
            <th>Athletes</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${entrenadores}" var="entrenador">
      
            <tr>
                
                <td>
                     <spring:url value="/entrenadores/{entrenadorId}" var="entrenadorUrl">
                        <spring:param name="entrenadorId" value="${entrenador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(entrenadorUrl)}"><c:out value="${entrenador.firstName} ${entrenador.lastName}"/></a>
                </td>
               
                <td>
               <c:forEach var="athlete" items="${entrenador.athletes}">
               <c:out value="${athlete.firstName} ${athlete.lastName}"/>
               </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
    <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/entrenador/new" htmlEscape="true"/>'>Add entrenador</a>
	</sec:authorize>
	
	 
</petclinic:layout>