<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
기관 계정
</rapid:override>

<rapid:override name="style">
<link href="<%=cp%>/static/css/contents.css" rel="stylesheet" type="text/css">
</rapid:override>

<rapid:override name="content">
	<section id="body" class="sub"><!-- ★ 클래스 값 변경 : 로그인 전-main / 로그인 후-login_main / 검색-search / 서브-sub -->
		<div id="side">
			<h2 class="account">계정관리</h2><!-- ★ 대카테고리별 클래스 값 변경 -->
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
				<p class="path"><strong>HOME</strong> <span>&gt;</span> <strong>계정관리</strong> <span>&gt;</span> <strong class="current">기관계정</strong></p>
				<h1 class="stitle">기관계정</h1>
			</div>
			<div id="detail_content">
				<h2 class="depth2_title">담당자 정보</h2>
				
			<form id="f_orgClient" action="" method="post">
			<input type="hidden" name="type" value="orgClient">
			<input type="hidden" name="clientLevel" value="60">
			<input type="hidden" name="orgCode" id="orgCode" value="${write.orgCode }">
			<input type="hidden" name="clientStatus" id="clientStatus" value="${write.clientStatus }">			
			<input type="hidden" name="orgTitle" id="orgTitle" value="${write.orgTitle }" placeholder="담당부서전체이름">
			<input type="hidden" name="useMt" id="useMt" value="${write.useMt }">
			<input type="hidden" name="changeStatus" id="changeStatus" value="${write.changeStatus }">
			<input type="hidden" name="groupName" id="groupName" value="${write.groupName }">
			<input type="hidden" name="upOrgId" id="upOrgId" value="${write.upOrgId }">
			<input type="hidden" name="exClientCel" id="exClientCel" value="${write.clientCel }">
			<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
			
				<table class="basic_table left_head">
					<caption>
						담당자정보 - 기간,ID,담당자,비밀번호,기관유형,기관설정,기간설정,빌링여부,사용서비스,대표자명,고유번호,전화번호,FAX,주소
					</caption>
					<colgroup>
						<col style="width:15%">
						<col style="width:35%">
						<col style="width:15%">
						<col>
					</colgroup>
					<tbody>
					<tr>
						<th scope="col"><label for="orgName">기관</label></th>
						<td><input type="text" name="orgName" readonly="readonly" value="${write.orgName }" id="orgName" style="background-color:#dcdcdc;"/><%/* <a href="#" class="basic_btn">찾기</a>*/%></td>
						<th scope="col"><label for="orgDept">상세부서명</label></th>
						<td><input type="text" name="orgDept" value="${write.orgDept }" id="orgDept" style="background-color:#dcdcdc;"/></td>
					</tr>
					<!-- 20160929 그룹명 displsy 하지 않아도 됨 -->
					<!-- <tr>
						<th scope="col"><label for="groupName">그룹명</label></th>
						<td colspan="3"><input type="text" name="groupName" value="${write.groupName }" id="groupName" /></td>
					</tr>
					 -->

					<tr>
						<th><label for="clientId">ID</label></th>
						<td><input type="text" name="clientId" readonly="readonly" id="clientId" value="${write.clientId }"  style="background-color:#dcdcdc;"/></td>
						<th><span class="required">*</span><label for="clientName">담당자</label></th>
						<td><input type="text" name="clientName" id="clientName" value="${write.clientName }" /></td>
					</tr>
					<tr>
						<th><span class="required">*</span><label for="clientPwd">비밀번호</label></th>
						<td><input type="password" name="clientPwd"  id="clientPwd" value="${write1}"  /></td>
						<th><span class="required">*</span><label for="clientName">비밀번호확인</label></th>
						<td><input type="password" name="clientPwdRe" id="clientPwdRe" value="${write1}" /></td>
					</tr>  
					<tr>
						<th><span class="required">*</span><label for="uniqueNo">고유번호</label></th>
						<td colspan="3"><input type="text" name="uniqueNo" id="uniqueNo" value="${write.uniqueNo }" /></td>
					</tr>
					<tr>
						<th><span class="required">*</span><label for="clientCel">핸드폰번호</label></th>
						<td><input type="text" name="clientCel" id="clientCel" value="${write.clientCel }" /></td>
						<th><label for="clientTel">전화번호</label></th>
						<td><input type="text" name="clientTel" id="clientTel" value="${write.clientTel }" /></td>
					</tr>
					</tbody>
				</table>
				<div class="btn_group txt_center">
					<%-- <a href="<%=cp %>/user/account/account.do" class="basic_btn bg_black">목록</a> --%>
					<a href='javascript:listPage();' class="basic_btn bg_black">목록</a>
					<c:if test="${sessionScope.w eq '1'}">
						<a onclick="saveAccount($('#f_orgClient'));" class="basic_btn bg_green">저장</a>
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

	  alert("8자리 ~ 15자리 이내로 입력해주세요.");
	  return false;
	 }else if(pw.search(/\s/) != -1){
	  alert("비밀번호는 공백 없이 입력해주세요.");
	  return false;
	 }else if(num < 0 || eng < 0 || spe < 0 ){
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
	  return false;
	 }else {
		console.log("통과"); 
	    return true;
	 }

	}

	function saveAccount(f){
		if($("#exClientCel").val() != $("#clientCel").val()){
			$("#changeClientCel").val("Y");
		}
		if($("#clientName").val() == null || $("#clientName").val() == ''){
			alert('담당자명을 입력하세요.');
			return false;
		}		
		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
			alert('비밀번호를 입력하세요.');
			return false;
		}
		if($("#clientPwdRe").val() == null || $("#clientPwdRe").val() == ''){
			alert('비밀번호 확인란을 입력하세요.');
			return false;
		}
		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
			alert('고유번호를 입력하세요.');
			return false;
		}
		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
			alert('휴대폰번호를 입력하세요.');
			return false;
		}
		var pass = document.getElementById('clientPwd').value;
		var passChk = document.getElementById('clientPwdRe').value;

		if(pass != passChk){
			alert('비밀번호와 비밀번호확인란이 일치하지않습니다.');
			return false;
		}
		
		if (!chkPW(pass)) {
			return false;
		}
		$(f).attr('action','<%=cp %>/user/account/accountUpdate.do');
		$(f).submit();
	}
	//목록버튼 검색조건유지
	function listPage(){
		$('#f_list').attr('action','<%=cp%>/user/account/account.do');	
		$('#f_list').submit();	
	}
</script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/main.base.jsp"%>
