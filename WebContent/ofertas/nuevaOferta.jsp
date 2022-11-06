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
					<h2>Nueva oferta</h2>

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
						<%
							HttpSession sesion = request.getSession(false);
							int empresa = (Integer) sesion.getAttribute("id_empresa");
						%>
						<form role="form"
							action="${pageContext.request.contextPath}/ofertas.do"
							method="POST">
							<input type="hidden" name="op" value="insertar"> <input
								type="hidden" name="idEmpresa" value="<%out.print(empresa);%>">

							<div class="form-group">
								<label for="titulo">Titulo de la oferta</label>
								<div class="input-group">
									<input type="text" class="form-control" name="titulo"
										id="titulo" placeholder="Ingresa el titulo de la oferta"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="precio_regular">Precio regular de la oferta</label>
								<div class="input-group">
									<input type="number" class="form-control" name="precio_regular"
										id="precio_regular" step="any"
										placeholder="Ingresa el precio regular de la oferta" required>
								</div>
							</div>
							<div class="form-group">
								<label for="precio_oferta">Precio de oferta</label>
								<div class="input-group">
									<input type="number" class="form-control" name="precio_oferta"
										id="precio_oferta" step="any"
										placeholder="Ingresa el precio de oferta" required>
								</div>
							</div>
							<div class="form-group">
								<label for="fecha_inicio">Fecha inicio</label>
								<div class="input-group">
									<input type="date" class="form-control" name="fecha_inicio"
										id="fecha_inicio" placeholder="Ingresa la fecha de inicio"
										required>
								</div>
							</div>
							<div class="form-group">
								<label for="fecha_fin">Fecha fin</label>
								<div class="input-group">
									<input type="date" class="form-control" name="fecha_fin"
										id="fecha_fin" placeholder="Ingresa la fecha final" required>
								</div>
							</div>
							<div class="form-group">
								<label for="fecha_limite">Fecha limite</label>
								<div class="input-group">
									<input type="date" class="form-control" name="fecha_limite"
										id="fecha_limite"
										placeholder="Ingresa la fecha limite de compra" required>
								</div>
							</div>
							<div class="form-group">
								<label for="limite">Limite cupones</label>
								<div class="input-group">
									<input type="number" class="form-control" name="limite"
										id="limite" placeholder="Ingresa el limite" required>
								</div>
							</div>
							<div class="form-group">
								<label for="descripcion">Descripción de la oferta</label>
								<div class="input-group">
									<input type="text" class="form-control" name="descripcion"
										id="descripcion"
										placeholder="Ingresa la descripción de la oferta" required>
								</div>
							</div>
							<div class="form-group">
								<label for="cupones_disponibles">Cupones disponibles
									(Opcional)</label>
								<div class="input-group">
									<input type="number" class="form-control"
										name="cupones_disponibles" id="cupones_disponibles"
										placeholder="Ingresa la cantidad de cupones disponibles" value="0">
								</div>
							</div>


							<input type="submit" class="btn btn-info" value="Guardar"
								name="Guardar"> <a class="btn btn-danger"
								href="${pageContext.request.contextPath}/ofertas.do?op=listarAprobadas">Cancelar</a>
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