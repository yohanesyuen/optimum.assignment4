<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%><header>
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<c:url value="/" var="indexUrl" />
		<div class="container">
			<a class="navbar-brand mx-auto d-block text-center w-100"
				style="padding: 0px;" href="${indexUrl}home"> <img
				src="${indexUrl}images/yohanes-logo.png"
				style="max-height: 100%; height: 40px; display: inline-block;">
				<span>Hello World Pte Ltd</span>
			</a>
			<div class="collapse navbar-collapse" id="#navbarCollapse">
				<ul class="navbar-nav mr-auto">
					<%
						if (session.getAttribute("UID") != null) {
					%>
					<li class="nav-item"><a class="nav-link"
						href="${indexUrl}logout">Logout</a></li>
					<%
						}
					%>
				</ul>
			</div>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarCollapse" aria-controls="navbarCollapse"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>
</header>
