<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- ZTree树形插件 -->  
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css"> 
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>  
<script type="text/javascript" src="js/jquery.ztree.all-3.5.js"></script> 
<script type="text/javascript">
	var setting = {
			data: {
				simpleData: {
					enable: true
			}
		}
	};

	var zNodes =[//${result}
	    
	]; 

	$(document).ready(function(){ 
		    //zTree = $("#treeDemo").zTree(setting, treeNodes);  
		 $.fn.zTree.init($('#treeDemo'), setting, zNodes); 
	});
</script> 
<title>Insert title here</title>
</head>
<body>
<div id="ztreeBackground">
	<ul id="treeDemo" class="ztree">
	</ul>
</div>
</body>
</html>