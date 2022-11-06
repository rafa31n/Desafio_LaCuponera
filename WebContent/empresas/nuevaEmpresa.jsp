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
					<h2>Nueva Empresa</h2>

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
							<input type="hidden" name="op" value="insertar">

							<div class="form-group">
								<label for="nombreE">Nombre de la Empresa</label>
								<div class="input-group">
									<input type="text" class="form-control" name="nombreE"
										id="nombreE" placeholder="Ingresa el nombre del usuario"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="codigoE">Código de la Empresa</label>
								<div class="input-group">
									<input type="text" class="form-control" name="codigoE"
										id="codigoE"
										placeholder="Ingresa el código de tu empresa (EMP000)"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="telefono">Teléfono</label>
								<div class="input-group">
									<input type="text" class="form-control" name="telefono"
										id="telefono" placeholder="Ingresa el teléfono de tu empresa"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="direccion">Dirección:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="direccion"
										id="direccion" placeholder="Ingresa la dirección" required>
								</div>
							</div>
							<div class="form-group">
								<label for="correo">Correo de la empresa:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="correo"
										id="correo" placeholder="Ingresa el correo de la empresa"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="nombreContacto">Nombre Contacto:</label>
								<div class="input-group">
									<input type="text" class="form-control" name="nombreContacto"
										id="nombreContacto"
										placeholder="Ingresa el nombre de contacto de tu empresa."
										required>
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
										id="porcentaje"
										placeholder="Ingresa el porcentaje de comisión" required>
								</div>
							</div>

							<input type="submit" class="btn btn-info" value="Guardar"
								name="Guardar"> <a class="btn btn-danger"
								href="${pageContext.request.contextPath}/empresas.do?op=listarEmpresas">Cancelar</a>
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