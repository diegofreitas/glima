<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Starting page</title>
<script src="js/jquery.js">
	
</script>

<script type="text/javascript">
	$( function() {
		$("input").addClass("celulaNaoEditavel");
		
		$("input").focus(function() {
			$(this).removeClass("celulaNaoEditavel");
			$(this).addClass("celulaEditavel");
		});
		
		$("input").blur(function() {
			$(this).removeClass("celulaEditavel");
			$(this).addClass("celulaNaoEditavel");
		});	
	});
</script>
<style type="text/css">
.celulaNaoEditavel {
	border: none;
	cursor: pointer;
	outline-style: none;
}

.celulaEditavel {
	border: solid 2px gray;
	cursor: text;
}

td {
	border-right: solid black 1px;
	border-bottom:  solid black 1px;
	padding: 0px;
}

table{
	border-top: solid black 1px;
    border-left: solid black 1px;
    border-spacing: 0px;
}

</style>
</head>

<body>
<c:redirect context="${pageContext.request.contextPath}" url="/estoque/categoriaMain.jsf"/>
<!-- form action="">
<table border="0">
<tbody>


	<tr>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
	
	</tr>
	<tr>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		
	</tr>
	<tr>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		
	</tr>
	<tr>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		
	</tr>
	<tr>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		<td><input  type='text' /></td>
		
	</tr>
</tbody>
</table>
</form-->
</body>
</html>
