<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ofertas</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Ofertas activas</h2>

					<a type="button" class="btn btn-primary btn-md"
						href="${pageContext.request.contextPath}/ofertas.do?op=nuevo">
						Nueva oferta</a><br> <br>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<tr>
							<th>#</th>
							<th>Empresa</th>
							<th>Titulo</th>
							<th>Fecha inicio/fin</th>
							<th>Descripción</th>
							<th>Cupones vendidos</th>
							<th>Cupones disponibles</th>
							<th>Ingresos totales</th>
							<th>Cargo por servicio</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaOfertasActivas}"
								var="ofertas">
								<tr>
									<td>${ofertas.idOferta}</td>
									<td>${ofertas.empresa}</td>
									<td>${ofertas.titulo}</td>
									<td>${ofertas.fecha_inicio}<br>${ofertas.fecha_fin}</td>
									<td>${ofertas.descripcion}</td>
									<td>${ofertas.cupones_vendidos}</td>
									<td>${ofertas.cupones_disponibles}</td>
									<td>${ofertas.ingresos_totales}</td>
									<td>${ofertas.cargo_servicio}</td>
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