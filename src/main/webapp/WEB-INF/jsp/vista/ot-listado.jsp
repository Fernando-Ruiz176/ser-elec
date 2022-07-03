<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="includes/navbar.jsp" %>		
	<meta charset="UTF-8">
	<title>Listado</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">
<%@ include file="includes/navbar.jsp" %>
<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Ordenes de trabajo</h1>	
	<table class="table table-striped">
		<thead>
			<tr>
				<th class="col">ID</th>
				<th class="col">Objeto</th>
				<th class="col">Estado</th>
				<th class="col">Cliente asociado</th>
				<th class="col">Fecha Solicitud</th>
				<th class="col">Fecha Entrega</th>
				<th class="col">Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="odt" items="${ot}">
				<tr>
					<td><c:out value="${ot.id}"></c:out></td>
					<td><c:out value="${ot.electrodomestico.nombre}"></c:out></td>
					<td><c:out value="${ot.estado}"></c:out></td>
					<td><c:out value="${ot.electrodomestico.cliente.nombre}"></c:out></td>
					<td><c:out value="${ot.fechaSolicitud}"></c:out></td>
					<td><c:out value="${ot.fechaEntreganOrden}"></c:out></td>
					<td>
						<a href="OtController?accion=editar&amp;id=${ot.id}">Modificar</a>
						<a href="OtController?accion=verODT&amp;id=${ot.id}">Ver OT</a>
					</td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
</div>		
	<%@ include file="includes/navbar.jsp" %>
</body>
</html>