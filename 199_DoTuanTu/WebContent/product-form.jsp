<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Quan ly kho Vinmart</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">San pham</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${product != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${product == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Chỉnh sửa sản phẩm
            		</c:if>
						<c:if test="${product == null}">
            			Thêm sản phẩm mới
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">
					<input type="hidden" name="id" value="<c:out value='${product.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Tên Sản phẩm</label> <input type="text"
						value="<c:out value='${product.nameProduct}' />" class="form-control"
						name="nameProduct" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Giá sản phẩm</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>

				<fieldset class="form-group">
					<label>Số lượng sản phẩm</label> <input type="text"
						value="<c:out value='${product.quantity}' />" class="form-control"
						name="quantity">
				</fieldset>
				<fieldset class="form-group">
					<label>Ngày nhập kho</label> <input type="text"
						value="<c:out value='${product.dateInput}' />" class="form-control"
						name="dateInput">
				</fieldset>

				<button type="submit" class="btn btn-success">Lưu</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
