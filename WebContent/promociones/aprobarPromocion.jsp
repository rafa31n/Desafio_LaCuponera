<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cupones</title>
<%@ include file='/cabecera.jsp'%>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"
	integrity="sha384-Xe+8cL9oJa6tN/veChSP7q+mnSPaj5Bcu9mPX5F5xIGE0DVittaqT5lorf0EI7Vk"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"
	integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK"
	crossorigin="anonymous"></script>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Promociones en espera de respuesta</h2>
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
								<a class="btn btn-success"
									href="${pageContext.request.contextPath}/promociones.do?op=aprobar&id=${promociones.id_oferta}">Aceptar
									Promoción</a>

								<button type="button" class="btn btn-danger"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Rechazar oferta</button>



							</div>

						</div>



						<!-- Modal -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Justificación</h5>
										<div class="form-floating"></div>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<form
										action="${pageContext.request.contextPath}/promociones.do"
										method="POST">
										<input type="hidden" name="op" value="rechazar">
										<div class="modal-body">
											<input type="hidden" id="id" name="id"
												value="${promociones.id_oferta}">
											<textarea class="form-control" placeholder="Ingrese motivo"
												id="rechazo" name="rechazo" style="height: 100px"></textarea>

										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>

											<input type="submit" class="btn btn-danger"
												value="Rechazar oferta">
										</div>
									</form>

								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
			<jsp:include page="/footer.jsp" />
		</div>
	</div>



	<jsp:include page="/scripts.jsp" />
</body>

</html>