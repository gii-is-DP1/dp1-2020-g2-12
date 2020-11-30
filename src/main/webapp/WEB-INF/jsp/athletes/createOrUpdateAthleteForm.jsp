<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="athlete">

	<jsp:body>
   		<h2><c:if test="${athlete['new']}">New Athlete </c:if></h2>
		<h2><c:if test="${!athlete['new']}">Update Athlete </c:if></h2>
		
		<b>Athlete</b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Height</th>
                <th>Weight</th>
                <th>Genero</th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${athlete.firstName}"/></td>
                <td><c:out value="${athlete.lastName}"></c:out></td>
                <td><c:out value="${athlete.height}"/></td>
                <td><c:out value="${athlete.weight}"/></td>
                <td><c:out value="${athlete.genero}"></c:out></td>              
            </tr>
        </table>
        
         <form:form modelAttribute="athlete" class="form-horizontal">
            <div class="form-group has-feedback">
            	<petclinic:inputField label="First Name" name="firstName"/>
                <petclinic:inputField label="Last Name" name="lastName"/>
                <petclinic:inputField label="Height" name="height"/>                
              	<petclinic:inputField label="Weight" name="weight"/>        
                <petclinic:selectField label="Genero" name="genero" size="2" names="${genero}"/>
				          
                <c:if test="${edit == true}">
                </c:if>
            	
            </div>
            
            <c:if test="${athlete['new']}">
			<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="athleteId" value="${athlete.id}"/>
                    <button class="btn btn-default" type="submit">Add Athlete</button>
                </div>
            </div>
			</c:if>
			<c:if test="${!athlete['new']}">
			<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${athlete.id}"/>
                    <button class="btn btn-default" type="submit">Update Athlete</button>
                </div>
            </div>
			</c:if>
			
			
			
        </form:form>
    </jsp:body>
    
</petclinic:layout>

