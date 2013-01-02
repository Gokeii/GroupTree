<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>
<div id="content">
	<div style="padding-top:30px;">
		There's an error occurred while processing your request.
	</div>
	<div>
		<h4>Exception Name:<br> ${exception } </h4>

   		<h4>Exception Details:<br> ${exceptionStack }</h4> 
	</div>
</div>
</body>
</html>