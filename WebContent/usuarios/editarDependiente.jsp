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
					<h2>Modificar usuario dependiente de sucursal</h2>

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
							<input type="hidden" name="op" value="modificarDependiente">

							<div class="form-group">
								<label for="id">Id del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="id" id="id"
										value="${usuario.idUsuario}" readonly>
								</div>
							</div>
							<div class="form-group">

								<label for="nombres">Nombre del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="nombres"
										id="nombres" value="${usuario.nombre}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="apellidos">Apellidos del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="apellidos"
										id="apellidos" value="${usuario.apellidos}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="telefono">Teléfono del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="telefono"
										id="telefono" value="${usuario.telefono}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="correo">Correo del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="correo"
										id="correo" value="${usuario.correo}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="password">Contraseña del usuario</label>
								<div class="input-group">
									<input type="password" class="form-control" name="password"
										id="password" value="${usuario.password}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="direccion">Dirección del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="direccion"
										id="direccion" value="${usuario.direccion}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="dui">DUI del usuario</label>
								<div class="input-group">
									<input type="text" class="form-control" name="dui" id="dui"
										value="${usuario.dui}" required>
								</div>
							</div>
							<%
								HttpSession sesion = request.getSession(false);
								int empresa = (Integer) sesion.getAttribute("id_empresa");
							%>
							<input type="submit" class="btn btn-info" value="Guardar"
								name="Guardar"> <a class="btn btn-danger"
								href="${pageContext.request.contextPath}/usuarios.do?op=listarDependientes&empresaId=
								<%out.print(empresa);%>">Cancelar</a>
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