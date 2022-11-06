<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rubros</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body id="page-top">

	<div id="wrapper">
		<jsp:include page="/dashboard.jsp" />

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<jsp:include page="/top_navbar.jsp" />

				<div class="container-fluid">
					<h2>Lista de Rubros</h2>

					<a type="button" class="btn btn-primary btn-md"
						href="${pageContext.request.contextPath}/rubros.do?op=nuevo">
						Nuevo rubro</a><br> <br>

					<table class="table table-striped table-bordered table-hover"
						id="tabla">
						<thead>
							<tr>
								<th>#</th>
								<th>Rubro</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.listaRubros}" var="rubros">
								<tr>
									<td>${rubros.idRubro}</td>
									<td>${rubros.nombreRubro}</td>
									<td><a class="btn btn-primary"
										href="${pageContext.request.contextPath}/rubros.do?op=obtener&id=${rubros.idRubro}">
											Editar</a> <a class="btn btn-danger"
										href="javascript:eliminar('${rubros.idRubro}')"></span> Eliminar</a></td>
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
			if (confirm("¿Realmente decea eliminar este rubro?")) {
				location.href = "rubros.do?op=eliminar&id=" + id;
			} else {
				return;
			}
		}
	</script>
	<jsp:include page="/scripts.jsp" />
</body>
</html>