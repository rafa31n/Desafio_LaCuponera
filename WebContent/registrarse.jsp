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
				<h1 class="text-center">Crear cuenta</h1>

				<form
					action="${pageContext.request.contextPath}/login.do?op=registrarse"
					method="POST">

					<div class="form-group">
						<label for="nombres">Nombre del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="nombres"
								id="nombres" placeholder="Ingresa el nombre del usuario"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="apellidos">Apellidos del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="apellidos"
								id="apellidos" placeholder="Ingresa el apellido del usuario"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="telefono">Teléfono del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="telefono"
								id="telefono" placeholder="Ingresa el teléfono del usuario"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="correo">Correo del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="correo" id="correo"
								placeholder="Ingresa el correo del usuario" required>
						</div>
					</div>
					<div class="form-group">
						<label for="password">Contraseña del usuario</label>
						<div class="input-group">
							<input type="password" class="form-control" name="password"
								id="password" placeholder="Ingresa el contraseña del usuario"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="direccion">Dirección del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="direccion"
								id="direccion" placeholder="Ingresa la dirección del usuario"
								required>
						</div>
					</div>
					<div class="form-group">
						<label for="dui">DUI del usuario</label>
						<div class="input-group">
							<input type="text" class="form-control" name="dui" id="dui"
								placeholder="Ingresa el dui del usuario" required>
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
						<button name="enviar" type="submit" class="btn btn-primary col-5">Crear
							cuenta</button>
						<a class="btn btn-dark"
							href="${pageContext.request.contextPath}/index.jsp">Cancelar</a>
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