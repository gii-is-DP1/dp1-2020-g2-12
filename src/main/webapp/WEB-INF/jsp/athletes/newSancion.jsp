<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <h3>Ahtlete: <c:out value="${sancion.athlete.firstName} ${sancion.athlete.lastName}"/></h3>
        
        <form:form modelAttribute="sancion" class="form-horizontal">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Fecha de fin de sancion" name="fechaFin"/>
                <petclinic:inputField label="Descripcion de la sancion" name="descripción"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="athleteId" value="${sancion.athlete.id}"/>
                    <button class="btn btn-default" type="submit">Sancionar</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
