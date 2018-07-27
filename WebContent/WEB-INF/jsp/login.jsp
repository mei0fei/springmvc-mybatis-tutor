<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>学生登录</h1>
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<br/>


	<form action="/hello/student/dologin" method="post">
		账号: <input type="text" name="username" value="${username }"><br>
		密码: <input type="password" name="password"><br>
		记住账号?: <input type="checkbox"  name="remember" value="1"><br>
		<input type="submit" value="登录">
	</form>

</body>
</html>