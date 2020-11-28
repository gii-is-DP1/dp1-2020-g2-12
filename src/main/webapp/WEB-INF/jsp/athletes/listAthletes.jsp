<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="athletes">
    <h2>Athletes</h2>

    <table id="athletesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 200px;">Name</th>
            <th style="width: 150px;">Height</th>
            <th style="width: 150px;">Weight</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${athletes}" var="athlete">
            <tr>
                <td>
                    <spring:url value="/athlete/{athleteId}" var="athleteUrl">
                        <spring:param name="athleteId" value="${athlete.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(athleteUrl)}"><c:out value="${athlete.firstName} ${athlete.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${athlete.height}"/>
                </td>
                <td>
                    <c:out value="${athlete.weight}"/>
                </td>                
            </tr>
        </c:forEach>
        </tbody>
         <sec:authorize access="hasAuthority('admin')">
		<a class="btn btn-default" href='<spring:url value="/athlete/new" htmlEscape="true"/>'>Add Athlete</a>
	</sec:authorize>
    </table>
</petclinic:layout>
