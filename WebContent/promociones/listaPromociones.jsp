<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cupones</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Promociones</h2>
					<c:forEach items="${requestScope.listaPromociones}"
						var="promociones">
						<br />

						<div class="card shadow mb-4" style="width: 900px">
							<!-- Card Header - Dropdown -->
							<div
								class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
								<h6 class="m-0 font-weight-bold text-primary">Oferta</h6>
							</div>

							<div class="card-body" style="width: 600px">
								<h5>
									<b>Titulo: </b> ${promociones.titulo_oferta}
								</h5>
								<p>
									<b>Descripción: </b>${promociones.descripcion_oferta}</p>
								<p>
									<b>Precio regular: $</b> ${promociones.precio_regular}
								</p>
								<p>
									<b>Precio Oferta: $</b> ${promociones.precio_oferta}
								</p>
								<p>
									<b>Cupones disponibles: </b> ${promociones.cupones_disponibles}
								</p>
								<a class="btn btn-success"
									href="${pageContext.request.contextPath}/promociones.do?op=nuevo&id=${promociones.id_oferta}&codigoEmpresa=${promociones.codigoEmpresa}">Comprar
									Cupones</a>
							</div>
						</div>
					</c:forEach>

				</div>
				<jsp:include page="/footer.jsp" />
			</div>
		</div>
	</div>
	<jsp:include page="/scripts.jsp" />
</body>

</html>