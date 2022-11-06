<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuarios</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Lista de Clientes</h2>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>#</th>
								<th>Cargo</th>
								<th>Nombres</th>
								<th>Apellidos</th>
								<th>Teléfono</th>
								<th>Correo</th>
								<th>Dirección</th>
								<th>DUI</th>
								<th>Cupones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaClientes}" var="usuarios">
								<tr>
									<td>${usuarios.idUsuario}</td>
									<td>${usuarios.cargoUsuario}</td>
									<td>${usuarios.nombre}</td>
									<td>${usuarios.apellidos}</td>
									<td>${usuarios.telefono}</td>
									<td>${usuarios.correo}</td>
									<td>${usuarios.direccion}</td>
									<td>${usuarios.dui}</td>
									<td><a class="btn btn-primary"
										href="${pageContext.request.contextPath}/usuarios.do?op=mostrarCupones&id=${usuarios.idUsuario}">
											Ver cupones</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="/scripts.jsp" />
</body>
</html>