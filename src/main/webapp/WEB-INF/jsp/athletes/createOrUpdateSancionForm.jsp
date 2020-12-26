<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="athletes">
	
	<jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaFin").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
      </jsp:attribute>
      
     <jsp:body> 
     <h2><c:if test="${sancion['new']}">Nueva </c:if>Sancion</h2>
     
    <form:form modelAttribute="sancion" class="form-horizontal" >
    	 
        <div class="form-group has-feedback">
        	 <div class="form-group">
                    <label class="col-sm-2 control-label">Athlete</label>
                    <div class="col-sm-10">
                        <c:out value="${sancion.athlete.firstName} ${sancion.athlete.lastName}"/>
                    </div>
            </div>
            <petclinic:inputField label="Fecha de fin de sancion" name="fechaFin"/>
            <petclinic:inputField label="Descripcion de la sancion" name="descripcion"/>
            
        </div>
       	
        <div class="form-group">
        		<input type="hidden" name="id" value="${sancion.id}"/>
                <c:choose>
                    <c:when test="${sancion['new']}">
                        <button class="btn btn-default" type="submit">Add Sanción</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Owner</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>
