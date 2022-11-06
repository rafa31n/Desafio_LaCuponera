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
					<h2>Modificar usuario</h2>

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
							action="${pageContext.request.contextPath}/empresas.do"
							method="POST">
							<input type="hidden" name="op" value="modificar"> <input
								type="hidden" name="id" value="${empresa.idEmpresa}">

							<div class="form-group">
								<label for="nombreE">Nombre de la Empresa</label>
								<div class="input-group">
									<input type="text" class="form-control" name="nombreE"
										id="nombreE" value="${empresa.nombreEmpresa}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="codigoE">Código de la Empresa</label>
								<div class="input-group">
									<input type="text" class="form-control" name="codigoE"
										id="codigoE" value="${empresa.codigoEmpresa}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="telefono">Teléfono</label>
								<div class="input-group">
									<input type="text" class="form-control" name="telefono"
										id="telefono" value="${empresa.telefono}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="direccion">Dirección:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="direccion"
										id="direccion" value="${empresa.direccion}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="correo">Correo de la empresa:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="correo"
										id="correo" value="${empresa.correo}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="nombreContacto">Nombre Contacto:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="nombreContacto"
										id="nombreContacto" value="${empresa.nombreContacto}" required>
								</div>
							</div>
							<div class="form-group">
								<label for="rubro">Rubro:</label> <select name="rubro"
									id="rubro" class="form-control">
									<c:forEach items="${requestScope.listaRubros}" var="rubros">
										<option value="${rubros.idRubro}">${rubros.nombreRubro}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="porcentaje">Porcentaje de comisión:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="porcentaje"
										id="porcentaje" value="${empresa.porcentajeComision}" required>
								</div>
							</div>
							<input type="hidden" class="form-control"
								value="${empresa.password}" name="password"> <input
								type="submit" class="btn btn-info" value="Guardar"
								name="Guardar"> <a class="btn btn-danger"
								href="${pageContext.request.contextPath}/empresas.do?op=listar">Cancelar</a>
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