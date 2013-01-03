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
<link rel="stylesheet" href="style.css" type="text/css"> 
<script type="text/javascript">

	//Albus Shin style
	function loadbarname() {
		var strupbar = "url(images/upbar";
		var ran = parseInt(Math.random() * 5 + 1);
		strupbar += ran;
		strupbar += ".png) left top no-repeat";
		document.getElementById("upbar").style.background = strupbar;
	}



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
				//alert(bean.id);
				if(isGroup){
					//alert('g'+bean.id);
					displayGroupInfo(bean);
				}
				else{
					//alert('u'+bean.id);
					displayUserInfo(bean);
				}
					
			}
		});
	};
	//function定义,在infoDiv中显示
	var connectedUsers;
	var displayGroupInfo = function(bean){
		$('#groupInfo').show();
		
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
			$('#connectedUsersId').append('<label style="font-family:Consolas,Arial,Serif; font-size:16px; font-weight:bold"> '+connectedUsers[i].id+' </label>' +
										'<a href="javascript:void(0);">more...</a>');
	
		}
		$('#connectedUsers label').click(function(){
			displayConnectedUsers(connectedUsers[$(this).index()/2]);
		});
		$('#connectedUsers a').click(function(){
			$('#groupInfo').hide();
			//$('#userInfo').show();
			readGroupInfo(connectedUsers[($(this).index()-1)/2].id, false);
			//displayUserInfo(connectedUsers[$(this).index()]);
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
	//display users
	var connectedGroups;
	var displayUserInfo = function(bean){
		$('#userInfo').show();
		
		$('#userId').text(bean.id);
		$('#userName').text(bean.name);
		$('#userCreated').text(bean.created);
		$('#userDefaultGroup').text(bean.defaultGroup);
		$('#userPassDate').text(bean.passDate);
		$('#userPassInterval').text(bean.passInterval);
		$('#userPhraseDate').text(bean.phraseDate);
		$('#userAttributes').text(bean.attributes);
		$('#userRevokeDate').text(bean.revokeDate);
		$('#userResumeDate').text(bean.resumeDate);
		$('#userLastAccess').text(bean.lastAccess);
		$('#userClassAuthorization').text(bean.classAuthorization);
		$('#userInstallationData').text(bean.installationData);
		$('#userModelName').text(bean.modelName);
		$('#userLogonAllowedDays').text(bean.logonAllowedDays);
		$('#userLogonAllowedTime').text(bean.logonAllowedTime);
		$('#userSecurityLevel').text(bean.securityLevel);
		$('#userCategoryAuthorization').text(bean.categoryAuthorization);
		$('#userSecurityLabel').text(bean.securityLabel);
		connectedGroups = bean.connectedGroups;
		$('#connectedGroupsId').empty();
		for(var i=0;i < connectedGroups.length ; i++){
			$('#connectedGroupsId').append('<label style="font-family:Consolas,Arial,Serif; font-size:16px; font-weight:bold> '+connectedGroups[i].id+' </label>' +
										'<a href="javascript:void(0);">more...</a>');
	
		}
		$('#connectedGroups label').click(function(){
			displayConnectedGroups(connectedGroups[$(this).index()/2]);
		});
		$('#connectedGroups a').click(function(){
			$('#userInfo').hide();
			//$('#groupInfo').show();
			var i = ($(this).index() - 1)/2;
			readGroupInfo(connectedGroups[i].id, true);
			//displayConnectedUsers(connectedGroups[$(this).index()]);
		});
	};
	var displayConnectedGroups = function(bean){
		$('#connGroupId').text(bean.id);
		$('#connGroupAuth').text(bean.auth);
		$('#connGroupConnectOwner').text(bean.connectOwner);
		$('#connGroupConnectDate').text(bean.connectDate);
		$('#connGroupConnects').text(bean.connects);
		$('#connGroupUAcc').text(bean.uAcc);
		$('#connGroupLastConnect').text(bean.lastConnect);
		$('#connGroupConnectAttributes').text(bean.connectAttributes);
		$('#connGroupRevokeDate').text(bean.revokeDate);
		$('#connGroupResumeDate').text(bean.resumeDate);
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
		loadbarname();
		generateTree();
		$('#refreshTree').click(function(){
			generateTree();
		});
		$('#groupInfo').hide();
		$('#userInfo').hide();
	});
</script> 
<title>Tree Display - GroupTree</title>
</head>
<body>
<%
		out.println("<div align='right'>"
				+ "<div id='menu2' class='menu'>"
				+ "<ul>"
				+ "<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </li>"
				+ "<li><a href='#'>HOME</a></li>"
				+ "</div>"
				+ "</ul>"
				+ "</div>"
				+ "<div id='upbar'>"
				+ "    <div id='userbar'>"
				+ "<img id='userbaravatar' src='http://www.gravatar.com/avatar/http://www.gravatar.com/avatar/<HASH>?s=45' align='left'/>"
				+ "<div id='userbarelse'>"
				+ "   <div id='username'>Tourist</div>"
				+ "<img class='seperator' src='images/seperator.png' align='middle'/>"
				+ "<div id='userbarcredit' onMouseOver='document.getElementById('credittipsy').style.display='block'' onmouseout='document.getElementById('credittipsy').style.display='none''>0</div>"
				+ "<img class='seperator' src='images/seperator.png' align='middle'/>"
				+ "<img id='signinicon' src='images/signinicon.png' onmouseover=\"this.src='images/signiniconpressed.png';document.getElementById('signinicontipsy').style.display='block'\" onmouseout=\"this.src='images/signinicon.png';document.getElementById('signinicontipsy').style.display='none'\" onclick=\"window.location.href='signin.jsp'\"/>"
				+ "<img id='signupicon' src='images/signupicon.png' onmouseover=\"this.src='images/signupiconpressed.png';document.getElementById('signupicontipsy').style.display='block'\" onmouseout=\"this.src='images/signupicon.png';document.getElementById('signupicontipsy').style.display='none'\" onclick=\"window.location.href='signup.jsp'\"/>"
				+ "</div>" + "</div>" + "</div>" + "</div>");
%>
<div class="leftBar">
	<input type="image" id="refreshTree" src="images/refreshbutton.png" 
	onmouseover="this.src='images/refreshbuttonpressed.png'" 
	onmouseout="this.src='images/refreshbutton.png'" width="300px" align="center"/>
	<div id="ztreeBackground">
		<ul id="treeDisplay" class="ztree">
		</ul>
	</div>
</div>
<div id="content">
	<div id="infoDiv"	class="rightBar">
		<!-- group info -->
		<div id="groupInfo">
			<div class="commentstitle">
				<p >Group information:</p>
			</div>
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
					<td>Connected users:</td> 
					<td id="connectedUsersId"></td>
				</tr>
			</table>
			<hr/>
			<div class="commentstitle">
				<p >Connected users information:</p>
			</div>
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
		<!-- user info -->
		<div id="userInfo">
		
			<div class="commentstitle">
				<p >User information:</p>
			</div>
			<table>
				<tr>
					<td>ID:</td>
					<td id="userId"></td>
				</tr>
				<tr>
					<td>User name:</td>
					<td id="userName"></td>
				</tr>	
				<tr>
					<td>Created:</td>
					<td id="userCreated"></td>
				</tr>
				<tr>
					<td>Default group:</td>
					<td id="userDefaultGroup"></td>
				</tr>
				<tr>
					<td>Pass date:</td>
					<td id="userPassDate"></td>
				</tr>
				<tr>
					<td>Pass interval:</td>
					<td id="userPassInterval"></td>
				</tr>
				<tr>
					<td>Phrase date:</td>
					<td id="userPhraseDate"></td>
				</tr>
				<tr>
					<td>Attributes:</td>
					<td id="userAttributes"></td>
				</tr>
				<tr>
					<td>Revoke date:</td>
					<td id="userRevokeDate"></td>
				</tr>
				<tr>
					<td>Resume date:</td>
					<td id="userResumeDate"></td>
				</tr>
				<tr>
					<td>Last access:</td>
					<td id="userLastAccess"></td>
				</tr>
				<tr>
					<td>Class authorization:</td>
					<td id="userClassAuthorization"></td>
				</tr>
				<tr>
					<td>Installation data:</td>
					<td id="userInstallationData"></td>
				</tr>
				<tr>
					<td>Model name:</td>
					<td id="userModelName"></td>
				</tr>
				<tr>
					<td>Logon allowed days:</td>
					<td id="userLogonAllowedDays"></td>
				</tr>
				<tr>
					<td>Logon allowed time:</td>
					<td id="userLogonAllowedTime"></td>
				</tr>
				<tr>
					<td>Security level:</td>
					<td id="userSecurityLevel"></td>
				</tr>
				<tr>
					<td>Category authorization:</td>
					<td id="userCategoryAuthorization"></td>
				</tr>
				<tr>
					<td>Security label:</td>
					<td id="userSecurityLabel"></td>
				</tr>
				<tr id="connectedGroups">
					<td>Connected groups:</td> 
					<td id="connectedGroupsId"></td>
				</tr>
			</table>
			<hr>
			<div class="commentstitle">
				<p >Connected users information:</p>
			</div>
			<table id="connGroupsAttr">
				<tr>
					<td>ID:</td>
					<td id="connGroupId"></td>
				</tr>
				<tr>
					<td>Auth:</td>
					<td id="connGroupAuth"></td>
				</tr>
				<tr>
					<td>Connect owner:</td>
					<td id="connGroupConnectOwner"></td>
				</tr>
				<tr>
					<td>Connect date:</td>
					<td id="connGroupConnectDate"></td>
				</tr>
				<tr>
					<td>Connects:</td>
					<td id="connGroupConnects"></td>
				</tr>
				<tr>
					<td>UAcc:</td>
					<td id="connGroupUAcc"></td>
				</tr>
				<tr>
					<td>Last connect:</td>
					<td id="connGroupLastConnect"></td>
				</tr>
				<tr>
					<td>Connect attributes:</td>
					<td id="connGroupConnectAttributes"></td>
				</tr>
				<tr>
					<td>Revoke date:</td>
					<td id="connGroupRevokeDate"></td>
				</tr>
				<tr>
					<td>Resume date:</td>
					<td id="connGroupResumeDate"></td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>