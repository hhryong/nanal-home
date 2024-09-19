<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
��� ����
</rapid:override>

<rapid:override name="style">
<link href="<%=cp%>/static/css/contents.css" rel="stylesheet" type="text/css">
</rapid:override>

<rapid:override name="content">
	<section id="body" class="sub"><!-- �� Ŭ���� �� ���� : �α��� ��-main / �α��� ��-login_main / �˻�-search / ����-sub -->
		<div id="side">
			<h2 class="account">��������</h2><!-- �� ��ī�װ��� Ŭ���� �� ���� -->
			<ul id="#snavigation">
			<%@ include file="/WEB-INF/view/site/menuSub.jsp"%>
			</ul>
		</div>
		<div id="content">
		<form role="form" id="f_list" action="<%=cp%>/user/account/account.do" method="post">
		<input type="hidden" id="clientId" name="clientId" value="${search.clientId}">
			<input type="hidden" name="pageNum" id="pageNum" value="${search.pageNum }"> 
			<!-- <input type="hidden" id="clientName" name="clientName" value="${search.clientName}"> -->
			<input type="hidden" name="startDate" id="startDate" value="${search.startDate}">
			<input type="hidden" name="endDate" id="endDate" value="${search.endDate}">	
			<input type="hidden" name="useMt" id="useMt" value="${search.useMt}">	
			<input type="hidden" name="useMms" id="useMms" value="${search.useMms}">	
			<input type="hidden" name="useMo" id="useMo" value="${search.useMo}">	
			<input type="hidden" name="useWeb" id="useWeb" value="${search.useWeb}">
			<input type="hidden" name="searchKey" id="searchKey" value="${search.searchKey}">	
			<input type="hidden" name="searchVal" id="searchVal" value="${search.searchVal}">	
				
			</form>
			<div id="content_title">
				<p class="path"><strong>HOME</strong> <span>&gt;</span> <strong>��������</strong> <span>&gt;</span> <strong class="current">�������</strong></p>
				<h1 class="stitle">�������</h1>
			</div>
			<div id="detail_content">
				<h2 class="depth2_title">����� ����</h2>
				
			<form id="f_orgClient" action="" method="post">
			<input type="hidden" name="type" value="orgClient">
			<input type="hidden" name="clientLevel" value="60">
			<input type="hidden" name="orgCode" id="orgCode" value="${write.orgCode }">
			<input type="hidden" name="clientStatus" id="clientStatus" value="${write.clientStatus }">			
			<input type="hidden" name="orgTitle" id="orgTitle" value="${write.orgTitle }" placeholder="���μ���ü�̸�">
			<input type="hidden" name="useMt" id="useMt" value="${write.useMt }">
			<input type="hidden" name="changeStatus" id="changeStatus" value="${write.changeStatus }">
			<input type="hidden" name="groupName" id="groupName" value="${write.groupName }">
			<input type="hidden" name="upOrgId" id="upOrgId" value="${write.upOrgId }">
			<input type="hidden" name="exClientCel" id="exClientCel" value="${write.clientCel }">
			<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
			
				<table class="basic_table left_head">
					<caption>
						��������� - �Ⱓ,ID,�����,��й�ȣ,�������,�������,�Ⱓ����,��������,��뼭��,��ǥ�ڸ�,������ȣ,��ȭ��ȣ,FAX,�ּ�
					</caption>
					<colgroup>
						<col style="width:15%">
						<col style="width:35%">
						<col style="width:15%">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th scope="col"><label for="orgName">���</label></th>
						<td><input type="text" name="orgName" readonly="readonly" value="${write.orgName }" id="orgName" style="background-color:#dcdcdc;"/><%/* <a href="#" class="basic_btn">ã��</a>*/%></td>
						<th scope="col"><label for="orgDept">�󼼺μ���</label></th>
						<td><input type="text" name="orgDept" value="${write.orgDept }" id="orgDept" style="background-color:#dcdcdc;"/></td>
					</tr>
					<!-- 20160929 �׷�� displsy ���� �ʾƵ� �� -->
					<!-- <tr>
						<th scope="col"><label for="groupName">�׷��</label></th>
						<td colspan="3"><input type="text" name="groupName" value="${write.groupName }" id="groupName" /></td>
					</tr>
					 -->

					<tr>
						<th><label for="clientId">ID</label></th>
						<td><input type="text" name="clientId" readonly="readonly" id="clientId" value="${write.clientId }"  style="background-color:#dcdcdc;"/></td>
						<th><span class="required">*</span><label for="clientName">�����</label></th>
						<td><input type="text" name="clientName" id="clientName" value="${write.clientName }" /></td>
					</tr>
					<tr>
						<th><span class="required">*</span><label for="clientPwd">��й�ȣ</label></th>
						<td><input type="password" name="clientPwd"  id="clientPwd" value="${write1}"  /></td>
						<th><span class="required">*</span><label for="clientName">��й�ȣȮ��</label></th>
						<td><input type="password" name="clientPwdRe" id="clientPwdRe" value="${write1}" /></td>
					</tr>  
					<tr>
						<th><span class="required">*</span><label for="uniqueNo">������ȣ</label></th>
						<td colspan="3"><input type="text" name="uniqueNo" id="uniqueNo" value="${write.uniqueNo }" /></td>
					</tr>
					<tr>
						<th><span class="required">*</span><label for="clientCel">�ڵ�����ȣ</label></th>
						<td><input type="text" name="clientCel" id="clientCel" value="${write.clientCel }" /></td>
						<th><label for="clientTel">��ȭ��ȣ</label></th>
						<td><input type="text" name="clientTel" id="clientTel" value="${write.clientTel }" /></td>
					</tr>
					</tbody>
				</table>
				<div class="btn_group txt_center">
					<%-- <a href="<%=cp %>/user/account/account.do" class="basic_btn bg_black">���</a> --%>
					<a href='javascript:listPage();' class="basic_btn bg_black">���</a>
					<c:if test="${sessionScope.w eq '1'}">
						<a onclick="saveAccount($('#f_orgClient'));" class="basic_btn bg_green">����</a>
					</c:if>
				</div>
			
			</form>
			
		</div>
	</section>
</rapid:override>  


<rapid:override name="javascript">
<script>
function chkPW(pw){
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[!@#$%^\*+=-_\\\\(\\\\)\\\\~]/g);

	 if(pw.length < 8 || pw.length > 15){

	  alert("8�ڸ� ~ 15�ڸ� �̳��� �Է����ּ���.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
	  alert("��й�ȣ�� ���� ���� �Է����ּ���.");
	  return false;
	 }else if(num < 0 || eng < 0 || spe < 0 ){
	  alert("����,����, Ư�����ڸ� ȥ���Ͽ� �Է����ּ���.");
	  return false;
	 }else {
		console.log("���"); 
	    return true;
	 }

	}

	function saveAccount(f){
		if($("#exClientCel").val() != $("#clientCel").val()){
			$("#changeClientCel").val("Y");
		}
		if($("#clientName").val() == null || $("#clientName").val() == ''){
			alert('����ڸ��� �Է��ϼ���.');
			return false;
		}		
		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
			alert('��й�ȣ�� �Է��ϼ���.');
			return false;
		}
		if($("#clientPwdRe").val() == null || $("#clientPwdRe").val() == ''){
			alert('��й�ȣ Ȯ�ζ��� �Է��ϼ���.');
			return false;
		}
		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
			alert('������ȣ�� �Է��ϼ���.');
			return false;
		}
		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
			alert('�޴�����ȣ�� �Է��ϼ���.');
			return false;
		}
		var pass = document.getElementById('clientPwd').value;
		var passChk = document.getElementById('clientPwdRe').value;

		if(pass != passChk){
			alert('��й�ȣ�� ��й�ȣȮ�ζ��� ��ġ�����ʽ��ϴ�.');
			return false;
		}
		
		if (!chkPW(pass)) {
			return false;
		}
		$(f).attr('action','<%=cp %>/user/account/accountUpdate.do');
		$(f).submit();
	}
	//��Ϲ�ư �˻���������
	function listPage(){
		$('#f_list').attr('action','<%=cp%>/user/account/account.do');	
		$('#f_list').submit();	
	}
</script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/main.base.jsp"%>
