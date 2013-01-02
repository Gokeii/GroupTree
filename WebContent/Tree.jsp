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
			infoUrl = "groupAndUser_getGroupInfo";
		}
		else {
			infoUrl = "groupAndUser_getUserInfo";
		}	
		$.ajax({
			    url : infoUrl,
			   type : 'POST',
			   data : formData,
			  cache : false,
	        enctype : 'multipart/form-data',
	    contentType : false,    //must declare
	    processData : false,    //must declare
			success : function(bean){
				//获取信息,调用显示方法
				displayInfo(bean);
			}
		});
	};
	//function定义,在infoDiv中显示
	var connectedUsers;
	var displayInfo = function(bean){
		$('#groupId').text(bean.id);
		$('#created').text(bean.created);
		$('#installationData').text(bean.installationData);
		$('#modelDataset').text(bean.modelDataset);
		$('#owner').text(bean.owner);
		$('#superiorGroup').text(bean.superiorGroup);
		$('#termUAcc').text(bean.termUAcc);
		connectedUsers = bean.users;
		$('#connectedUsersId').empty();
		for(var i=0;i < connectedUsers.length ; i++){
			$('#connectedUsersId').append('<label> '+connectedUsers[i].id+' </label>');
			//$('#connectedUsers laber:eq('+i+')').click(function(){
			//	var j = i.clone;
			//	alert(j);
			//});
		}
		$('#connectedUsers label').click(function(){
			displayConnectedUsers(connectedUsers[$(this).index()]);
		});
	};
	var displayConnectedUsers = function(bean){
		$('#connectedUserId').text(bean.id);
		$('#access').text(bean.access);
		$('#accessCount').text(bean.accessCount);
		$('#connectAttributes').text(bean.connectAttributes);
		$('#resumeDate').text(bean.resumeDate);
		$('#revokeDate').text(bean.revokeDate);
		$('#universalAccess').text(bean.universalAccess);
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
	var generateTree = function(){
		$('#treeDisplay').html('');
		$.ajax({
			    url : "groupAndUser_getTreeInfo",
				success : function(zNodes){
				//获取信息,调用显示方法
				$.fn.zTree.init($('#treeDisplay'), setting, zNodes);
			}
		});
	};
	//ready后,生成树
	$(document).ready(function(){ 
		generateTree();
		$('#refreshTree').click(function(){
			generateTree();
		});
	});
</script> 
<title>Insert title here</title>
</head>
<body>
<button id="refreshTree">Refresh</button>
<div id="ztreeBackground">
	<ul id="treeDisplay" class="ztree">
	</ul>
</div>
<div id="infoDiv">
	<table>
		<tr>
			<td>ID:</td> 
			<td id="groupId"></td>
		</tr>
		<tr>
			<td>Created:</td> 
			<td id="created"></td>
		</tr>
		<tr>
			<td>Installation data:</td> 
			<td id="installationData"></td>
		</tr>
		<tr>
			<td>Model data set:</td> 
			<td id="modelDataset"></td>
		</tr>
		<tr>
			<td>Owner:</td> 
			<td id="owner"></td>
		</tr>
		<tr>
			<td>Superior group:</td> 
			<td id="superiorGroup"></td>
		</tr>
		<tr>
			<td>Term UAcc:</td> 
			<td id="termUAcc"></td>
		</tr>
		<tr id="connectedUsers">
			<td id="connectedUsersId">Connected users:</td> 
		</tr>
	</table>
	<table id="connectedUsersAttr">
		<tr>
			<td>ID:</td> 
			<td id="connectedUserId"></td>
		</tr>
		<tr>
			<td>Access:</td> 
			<td id="access"></td>
		</tr>
		<tr>
			<td>Access count:</td> 
			<td id="accessCount"></td>
		</tr>
		<tr>
			<td>Connect attributes:</td> 
			<td id="connectAttributes"></td>
		</tr>
		<tr>
			<td>Resume date:</td> 
			<td id="resumeDate"></td>
		</tr>
		<tr>
			<td>Revoke date:</td> 
			<td id="revokeDate"></td>
		</tr>
		<tr>
			<td>Universal access:</td> 
			<td id="universalAccess"></td>
		</tr>
	</table>
</div>
</body>
</html>