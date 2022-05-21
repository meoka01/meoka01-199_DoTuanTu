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
				<a href="" class="navbar-brand"> Quản lý kho Vinmart</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Danh sách các loại sản phẩm</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Thêm sản phẩm mới</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						
						<th>Tên sản phẩm</th>
						<th>Giá</th>
						<th>Số lượng</th>
						<th>Ngày nhập kho</th>
						<th>Thao tác</th>
						
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
						
					<c:forEach var="product" items="${listProduct}">

						<tr>
							<td><c:out value="${product.nameProduct}" /></td>
							<td><c:out value="${product.price}" /></td>
							<td><c:out value="${product.quantity}" /></td>
							<td><c:out value="${product.dateInput}" /></td>
							<td><a href="edit?id=<c:out value='${product.id}' />">Sửa</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${product.id}' />">Xóa</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
