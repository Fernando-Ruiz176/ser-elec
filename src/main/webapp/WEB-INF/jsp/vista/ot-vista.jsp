<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Orden de Trabajo</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">
<%@include file="includes/navbar.jsp" %>
<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Orden de Trabajo Nº ${odt.id}</h1>
	<div class="container">	
		<div class="col mb-6">
			  <h3>Detalles del producto</h3>
			  <div class="mb-3">
			    <label class="form-label">Nombre del producto: </label>
			    <input type="text" class="form-control" id="nombreProducto" name="nombreProducto" disabled value="${ot.electrodomestico.nombre}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Falla del producto</label>
			    <input type="text" class="form-control" id="fallaProducto" name="fallaProducto" disabled value="${ot.electrodomestico.falla}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Estado de la orden</label>
			    <input type="text" class="form-control" id="estadoOrden" name="estadoOrden" disabled value="${ot.estado}">
			  </div>
			  
			  <h3>Detalles del cliente</h3>
			  <div class="mb-3">
			    <label class="form-label">Nombre del cliente</label>
			    <input type="text" class="form-control" id="nombreCliente" name="nombreCliente" disabled value="${ot.electrodomestico.cliente.nombre}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Telefono del cliente</label>
			    <input type="text" class="form-control" id="telefonoCliente" name="telefonoCliente" disabled value="${ot.electrodomestico.cliente.telefono}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Direccion del cliente</label>
			    <input type="text" class="form-control" id="direccionCliente" name="direccionCliente" disabled value="${ot.electrodomestico.cliente.direccion}">
			  </div>
			  <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
		</div>
	</div>
</div>

</body>
</html>