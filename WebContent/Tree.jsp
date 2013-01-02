<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- ZTree树形插件 -->  
<link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css"> 
<script type="text/javascript" src="js/jquery.min.js"></script>  
<script type="text/javascript" src="js/jquery.ztree.all-3.5.js"></script> 
<script type="text/javascript">
	//-----------ajax及显示区域------------------
	//function定义,通过ajax从服务器读取信息，并在infoDiv中显示
	var readGroupInfo = function(name, isGroup){
		var infoUrl;
		var formData = new FormData();
		formData.append('name', name);
		if(isGroup){
			infoUrl = "getGroupOrUserInfo_getGroupInfo";
		}
		else {
			infoUrl = "getGroupOrUserInfo_getUserInfo";
		}	
		$.ajax({
			    url : infoUrl,
			   type : 'POST',
			   data : formData,
			  cache : false,
	        enctype : 'multipart/form-data',
	    contentType : false,    //must declare
	     processData: false,    //must declare
			success : function(data){
				//获取信息,调用显示方法
				displayInfo(data);
			}
		});
	};
	//function定义,在infoDiv中显示
	var displayInfo = function(data){
		$('#infoDiv').html(data);
	};
	//----------ztree区域------------
	//function定义,节点点击触发
	var onClick = function(event, treeId, treeNode, clickFlag) {
		readGroupInfo(treeNode.name, true);
	};
	//ztree的setting
	var setting = {
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			onClick: onClick
		}
	};
	//ztree节点, 注意 下面的result注释不能删,十分重要,属于EL表达式传递信息
	var zNodes =[//${result}
	]; 
	//ready后,生成树
	$(document).ready(function(){ 
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
<div id="infoDiv"></div>
</body>
</html>