<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Empresas</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Lista de Empresas</h2>

					<a type="button" class="btn btn-primary btn-md"
						href="${pageContext.request.contextPath}/empresas.do?op=nuevo">
						Nueva Empresa</a><br> <br>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>#</th>
								<th>Rubro</th>
								<th>Nombre Empresa</th>
								<th>Código Empresa</th>
								<th>Dirección</th>
								<th>Nombre Contacto</th>
								<th>telefono</th>
								<th>Correo</th>
								<th>Porcentaje Comision</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaEmpresas}" var="empresa">
								<tr>
									<td>${empresa.idEmpresa}</td>
									<td>${empresa.nombreRubro}</td>
									<td>${empresa.nombreEmpresa}</td>
									<td>${empresa.codigoEmpresa}</td>
									<td>${empresa.direccion}</td>
									<td>${empresa.nombreContacto}</td>
									<td>${empresa.telefono}</td>
									<td>${empresa.correo}</td>
									<td>${empresa.porcentajeComision}%</td>
									<td><a class="btn btn-primary"
										href="${pageContext.request.contextPath}/empresas.do?op=obtener&id=${empresa.idEmpresa}">
											Editar</a> <a class="btn btn-danger"
										href="javascript:eliminar('${empresa.idEmpresa}')"></span>
											Eliminar</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>


				</div>
				<jsp:include page="/footer.jsp" />
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

		function eliminar(id) {
			if (confirm("¿Realmente decea eliminar esta empresa?")) {
				location.href = "empresas.do?op=eliminar&id=" + id;
			} else {
				return;
			}
		}
	</script>
	<jsp:include page="/scripts.jsp" />
</body>
</html>