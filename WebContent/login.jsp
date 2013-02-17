<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>autenticação do sistema</title>

<style type="text/css">
@IMPORT url("/css/style.css");
body,p,a,span,div,input,legend,h1,h2,h3,h4,h5,h6,li,dd,dt,th,td {
	font-family: Arial, Helvetica, sans-serif;
}

body,p,a,span,div,input,legend,li,dd,dt,th,td {
	font-size: 10pt;
}

body{
	padding: 10% 20% 10% ;
}
#loginform {
	width: 300px;
	margin: auto;
}

#loginform fieldset {
	padding: 10px;
}

#loginform legend {
	font-weight: bold;
	font-size: 9pt;
}

#loginform label {
	display: block;
	height: 2em;
	background-color: #E7E7E7;
	padding: 10px 10px 0;
}

#loginform input {
	margin-right: 20px;
	border: 1px solid #999999;
	float: right;
	clear: right;
	background: #CCCCCC;
}

#loginform input:focus,#loginform input:hover {
	border: 1px solid #333333;
}

.error {
	color: red;
	font-weight: bold;
}

#wellcome{
	float: left;
	width: 50%;
	height: 100%
}

#login{
	float: right;
	width: 50%;
	height: 100%
}

#login p{
	width: 300px;
	margin: auto;
}
</style>

</head>
<body>
<div id="wellcome">
	<p> Bem vindo ao Sistema Comercial <p>
</div>
<div id="login">

<c:if test="${not empty param.invalid}">
	<p class="error">Falha na autenticação, Verifique se seu nome de usuário e senha estao corretos!</p>
</c:if>

<form id="loginform" method="post" action="<%=response.encodeURL("j_security_check")%>">
	<fieldset><legend>Log in</legend>

		<p>
			Porfavor, digite nome de usuário e senha para acessar o sistema
		</p>
	<label for="j_username"><input type="text" name="j_username"
	tabindex="1" id="username" />username: </label> <label for="j_password"><input
	type="password" name="j_password" tabindex="2" id="password" />password:
	</label> <label for="remember_me"><input type="checkbox"
	name="remember_me" value="1" tabindex="3" id="remember_me" />Remember
me ? </label> <label for="submit"> <input name="Submit" type="submit"
	id="submit" tabindex="4" value="Log in" /> </label></fieldset>
	</form>
</div>

</body>
</html>