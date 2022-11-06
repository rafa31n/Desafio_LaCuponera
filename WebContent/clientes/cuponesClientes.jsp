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
					<h2>Lista de cupones disponibles</h2>

					<a class="btn btn-secondary"
						href="${pageContext.request.contextPath}/usuarios.do?op=listarClientes">Regresar</a><br>
					<br>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>Código</th>
								<th>Oferta</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaCuponesDisponibles}"
								var="cupones">
								<tr>
									<td>${cupones.codigoCupon}</td>
									<td>${cupones.tituloOferta}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<hr>

					<h2>Lista de cupones canjeados</h2>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>Código</th>
								<th>Oferta</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaCuponesCanjeados}"
								var="cupones">
								<tr>
									<td>${cupones.codigoCupon}</td>
									<td>${cupones.tituloOferta}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<hr>

					<h2>Lista de cupones vencidos</h2>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>Código</th>
								<th>Oferta</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaCuponesVencidos}"
								var="cupones">
								<tr>
									<td>${cupones.codigoCupon}</td>
									<td>${cupones.tituloOferta}</td>
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