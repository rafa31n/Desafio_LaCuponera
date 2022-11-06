<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La Cuponera</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body>
	<div class="container mt-5">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-12 col-md-8 shadow-lg rounded px-3 py-4">
				<h1 class="text-center">Inicio de sesión</h1>
				<h2 class="text-center">La Cuponera</h2>

				<div class="text-center my-2">
					<i style="font-size: 7em;" class="fas fa-solid fa-store"></i>
				</div>

				<form
					action="${pageContext.request.contextPath}/login.do?op=iniciarSesion"
					method="POST">

					<label for="email" class="form-label">Correo electronico:</label> <input
						type="email" class="form-control" id="email" name="email"
						autocomplete="email" required> <br> <label
						for="password" class="form-label">Contraseña:</label> <input
						type="password" class="form-control" name="password" id="password"
						autocomplete="current-password" required> <br>
					<c:if test="${not empty listaErrores}">
						<div class="alert alert-danger">
							<ul>
								<c:forEach var="errores" items="${requestScope.listaErrores}">
									<li>${errores}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<a href="${pageContext.request.contextPath}/registrarse.jsp">¿Aún
						no tienes cuenta? Regístrate</a> <br> <a id="contraOlvidada"
						href="${pageContext.request.contextPath}/contraOlvidada.jsp">Olvide
						mi contraseña</a> <br> <br>

					<div class="text-center">
						<button name="enviar" type="submit" class="btn btn-primary col-5">Iniciar
							sesion</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		<c:if test="${not empty exito}">
		alertify.success('${exito}');
		<c:set var="exito" value="" scope="session" />
		</c:if>
		<c:if test="${not empty fracaso}">
		alertify.error('${fracaso}');
		<c:set var="fracaso" value="" scope="session" />
		</c:if>
	</script>
	<jsp:include page="/footer.jsp" />
	<jsp:include page="/scripts.jsp" />
</body>
</html>