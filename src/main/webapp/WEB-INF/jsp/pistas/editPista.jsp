<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="pistas">
    
    <jsp:body>
        <h2>Pista</h2>

        <form:form modelAttribute="pista" class="form-horizontal" action="/pistas/save">
            <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="name"/>
                <petclinic:inputField label="Ciudad" name="ciudad"/>
                <petclinic:inputField label="Aforo" name="aforo"/>
                <petclinic:inputField label="Deporte" name="deporte"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${pista.id}"/>
                    <button class="btn btn-default" type="submit">Guardar pista</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
