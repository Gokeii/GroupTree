<%@ page language="java" import="javax.servlet.http.*;" contentType="text/html;  charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>GroupTree Sign In</title>
	<link rel="stylesheet" href="style.css" />
	<script type="text/javascript" src="js/jquery.min.js"></script>
</head>
<%
	//UserHandlers.clearSessionAndCookies(request, response);
%>
<script type="text/javascript">
	/**
	* Albus Shin
	* Up background bar
	*/
	function loadbackgroundname(){
		var strallbg = "url(images/sgbg";
		var ran = parseInt(Math.random()*2 + 1);
		strallbg += ran;
		strallbg += ".jpg) left top no-repeat";
		document.body.style.background=strallbg;
	}

	/**
	*	Albus Shin 
	*	User Animation Bar
	*/

		  var status = "In";
		  var animating = false;
		  
	$(document).ready(function(){
		loadbackgroundname();
	  $("#userbaravatar").click(function(){
		  if (status == "Out"){
			  $("#userbar").animate({width:100},"slow");
			  document.getElementById("userbarelse").style.display="none";
			  status = "In";
		  }
		  else{
	 		  $("#userbar").animate({width:780},"slow");
			  document.getElementById("userbarelse").style.display="block";
			  status = "Out";
		  }
	  });
	  
	});

</script>

<body>
 	<%
 		String stat = (String)request.getAttribute("stat");
 		if (stat != null)
	 		if (stat.equals("wrong")){
	 			out.println("<div id=\"wrongmessage\" class=\"alert-messages\" style=\"display:block\">");
	 			out.println("<div class=\"message\">");
	 			out.println("<div class=\"message-inside\">");
	 			out.println("<div class=\"message-text\">");
	%>
		<div>
	 			${message }
	 	</div>
	<%
	 			out.println("</div>");
	 			out.println("<a class=\"dismiss\" href=\"javascript:dismiss();\">×</a>");
	 			out.println("</div>");
	 			out.println("</div>");
	 			out.println("</div>");
	 		}
 	%>
             	<script type="text/javascript">
             		function dismiss(){
             			document.getElementById("wrongmessage").setAttribute("style", "display:none");
             		}
             	</script>

	<div id="signinicontipsy" class="tipsy tipsy-n" style="top: 210px; right: 385px; visibility:visible; display:none; opacity:0.8; ">
		<div class="tipsy-arrow tipsy-arrow-n"></div>
		<div class="tipsy-inner">Sign In</div>
	</div>

	<div align="right">
            <div id="menu2" class="menu">
                <ul>
                	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </li>
                    <li><a href="javascript:;">HOME</a></li>
                    <div class="searchinput" align="right">
                    	<form>
							<input type="search" placeholder="Search">
						</form>
                    </div>
                </ul>
            </div>
            <div id="userbar">
                     <img id="userbaravatar" src="http://www.gravatar.com/avatar/http://www.gravatar.com/avatar/<HASH>?s=45" align="left"/>
                     <div id="userbarelse">
                        <div id="username">Tourist</div>
                        <img class="seperator" src="images/seperator.png" align="middle"/>
                        <img class="seperator" src="images/seperator.png" align="middle"/>
                        <img id="signinicon" src="images/signinicon.png" onmouseover="this.src='images/signiniconpressed.png';document.getElementById('signinicontipsy').style.display='block'" onmouseout="this.src='images/signinicon.png';document.getElementById('signinicontipsy').style.display='none'" onclick="window.location.href='index.jsp'"/>
                     </div>
            </div>
</div>    
    		<div id="container">
			<form action="login" class="loginform" method="post">
				<div class="login">SIGN IN</div>
				<div class="username-text">Username:</div>
				<div class="password-text">Password:</div>
				<div class="username-field">
					<input type="text" name="username"/>
				</div>
				<div class="password-field">
					<input type="password" name="password"/>
				</div>
				<input type="submit" id="signingo" name="submit" value="GO" />
			</form>
		</div>
		<style type="text/css"></style>
    	<div id="footer" align="center">
            <p align="center" style="color:#000;font-size:15px">Copyright © NBGroup</p>
    	</div>
</body>
</html>
