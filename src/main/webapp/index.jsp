<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>App Reparacion Electrodomesticos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>       
  </head>
  <style>
	th {
	  cursor: pointer;
	}
	</style>
<body  style="background: beige;" >
<%@include file="/WEB-INF/jsp/vista/includes/navbar.jsp" %>
 <section class="midBot bgc1 p-5">
		<div class="container">
			<div class="row">
				<div class="col text-center bl">
					<h1>Bienvenido</h1><br>
					<p>Las averías en los electrodomésticos del hogar siempre suceden en el peor momento y se pueden convertir en auténticos quebraderos de cabeza si no tienes un servicio de reparación de confianza. Aca te ofreceremos una gran alternativa.</p>
				</div>
			</div>
		</div>
	</section>
  <footer class="fixed-bottom light bg-dark">
    <div class="m-3">
           <p class="text text-center fs-6 text-light text-white">Desarrollado por FRF - curso FullStack 2022. Osorno - Chile - 
           <a class="text text-light text-white text-decoration-none" href="https://educacioncontinua-otec.aiep.cl/" target="_blank">Aiep</a> </p>
        </div>
</footer>
</body>
</html>