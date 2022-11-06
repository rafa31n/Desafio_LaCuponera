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
				<h1 class="text-center">Olvide mi contraseña</h1>
				<p class="text-center">Ingresa tu dirección de correo
					electrónico y te enviaremos una contraseña nueva provisional.</p>

				<form
					action="${pageContext.request.contextPath}/login.do?op=contraOlvidada"
					method="POST">

					<div class="form-group">
						<label for="correo">Correo de tu cuenta</label>
						<div class="input-group">
							<input type="text" class="form-control" name="correo" id="correo"
								placeholder="Ingresa el correo del usuario" required>
						</div>
					</div>

					<c:if test="${not empty listaErrores}">
						<div class="alert alert-danger">
							<ul>
								<c:forEach var="errores" items="${requestScope.listaErrores}">
									<li>${errores}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<div class="text-center">
						<button name="enviar" type="submit" class="btn btn-primary col-5">
							Enviar</button>
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