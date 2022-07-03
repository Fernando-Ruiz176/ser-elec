<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Formulario editar orden</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">
<%@include file="includes/navbar.jsp" %>
<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Editar Orden de Trabajo</h1>
	<div class="container">
	
		<div class="col mb-6">
			<form method="POST" action="Controller?accion=finalizarEdit">
			  <input type=hidden class="form-control" id="otId" name="idOdt" value="${ot.id}">
			  <input type=hidden class="form-control" id="clienteId" name="clienteId" value="${ot.electrodomestico.cliente.id}">
			  <input type=hidden class="form-control" id="electrodomesticoId" name="electrodomesticoId" value="${ot.electrodomestico.id}">
			  
			  <h3>Detalles del producto</h3>
			  <div class="mb-3">
			    <label class="form-label">Nombre del producto: </label>
			    <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" value="${ot.electrodomestico.nombre}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Falla del producto</label>
			    <input type="text" class="form-control" id="fallaProducto" name="fallaProducto" value="${ot.electrodomestico.falla}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Estado de la orden</label>
			    <select class="form-select" name="estadoOrden" id="estadoOrden">
		    		<c:choose>
						<c:when test="${ot.estado eq 'Pendiente'}">
							<option selected="selected" value="Pendiente">Pendiente</option>
							<option value="En reparacion">En reparacion</option>
							<option value="Reparado">Reparado</option>
							<option value="Orden cerrada">Orden cerrada</option>
							<option value="Sin arreglo">Sin arreglo</option>
						</c:when>		
					</c:choose>
			    </select>
			  </div>
			  
			  <h3>Detalles del cliente</h3>
			  <div class="form-text">Cambios para actualizacion.</div>
			  <div class="mb-3">
			    <label class="form-label">Nombre del cliente</label>
			    <input type="text" class="form-control" id="nombreCliente" name="nombreCliente" value="${ot.electrodomestico.cliente.nombre}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Telefono del cliente</label>
			    <input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" value="${ot.electrodomestico.cliente.telefono}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Direccion del cliente</label>
			    <input type="text" class="form-control" id="direccionCliente" name="direccionCliente" value="${ot.electrodomestico.cliente.direccion}">
			  </div>
			  
			  <button type="submit" class="btn btn-success">Finalizar edicion</button> 
			  <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
			</form>

		</div>
	</div>
</div>

</body>
</html>