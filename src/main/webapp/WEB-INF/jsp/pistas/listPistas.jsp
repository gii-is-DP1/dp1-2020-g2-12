<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="pistas">
    <h2>Pistas</h2>

    <table id="pistasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th>Ciudad</th>
            <th>Aforo</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pistas}" var="pista">
            <tr>
                
                <td>
                    <c:out value="${pista.nombre}"/>
                </td>
                <td>
                    <c:out value="${pista.ciudad}"/>
                </td>
                <td>
                    <c:out value="${pista.aforo}"/>
                </td>
                
                

                
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>