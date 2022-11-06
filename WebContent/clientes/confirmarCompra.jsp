<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>La Cuponera</title>
<%@ include file='/cabecera.jsp'%>
</head>
<body>
	<div class="container mt-5">
		<div class="row align-items-center justify-content-center">
			<div class="col-sm-12 col-md-8 shadow-lg rounded px-3 py-4">
				<h1 class="text-center">Confirmar compra</h1>
				<p class="text-center">Ingresa el código del cupon de tu compra
					que quieras validar.</p>

				<form
					action="${pageContext.request.contextPath}/promociones.do?op=validarCompra"
					method="POST">

					<div class="form-group">
						<label for="codigoCupon">Código de cupon:</label>
						<div class="input-group">
							<input type="text" class="form-control" name="codigoCupon"
								id="codigoCupon" placeholder="Ingresa el codigo del cupon"
								required>
						</div>
					</div>

					<c:if test="${not empty listaErrores}">
						<div class="alert alert-danger">
							<ul>
								<c:forEach var="errores" items="${requestScope.listaErrores}">
									<li>${errores}</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
					<div class="text-center">
						<button name="enviar" type="submit" class="btn btn-success col-5">
							Confirmar compra</button>
					</div>
				</form>
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
	</script>
	<jsp:include page="/footer.jsp" />
	<jsp:include page="/scripts.jsp" />
</body>
</html>