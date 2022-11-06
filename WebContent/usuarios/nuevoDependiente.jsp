<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La Cuponera</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Nuevo usuario dependiente de sucursal</h2>

					<div class=" col-md-7">

						<c:if test="${not empty listaErrores}">
							<div class="alert alert-danger">
								<ul>
									<c:forEach var="errores" items="${requestScope.listaErrores}">
										<li>${errores}</li>
									</c:forEach>
								</ul>
							</div>
						</c:if>
						<form role="form"
							action="${pageContext.request.contextPath}/usuarios.do"
							method="POST">
							<input type="hidden" name="op" value="insertarDependiente">

							<%
								HttpSession sesion = request.getSession(false);
							%>
							<input type="hidden" class="form-control" name=id_empresa
								id="id_empresa"
								value="<%out.print(session.getAttribute("id_empresa").toString());%>"
								required>

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
									<input type="text" class="form-control" name="correo"
										id="correo" placeholder="Ingresa el correo del usuario"
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

							<input type="submit" class="btn btn-info" value="Guardar"
								name="Guardar"> <a class="btn btn-danger"
								href="${pageContext.request.contextPath}/usuarios.do?op=listarDependientes">Cancelar</a>
						</form>
					</div>
				</div>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>

	<jsp:include page="/scripts.jsp" />
</body>
</html>