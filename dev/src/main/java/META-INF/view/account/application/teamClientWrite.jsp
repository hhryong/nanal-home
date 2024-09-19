<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
팀 계정
</rapid:override>

<rapid:override name="style">
	<link href="<%=cp%>/static/css/contents.css" rel="stylesheet"
		type="text/css">
</rapid:override>

<rapid:override name="content">
	<section id="body" class="sub">
		<div id="side">
			<h2 class="account">계정관리</h2>
			<ul id="#snavigation">
				<%@ include file="/WEB-INF/view/site/menuSub.jsp"%>
			</ul>
		</div>
		<div id="content">
			<div id="content_title">
				<p class="path">
					<strong>HOME</strong> <span>&gt;</span> <strong>계정관리</strong> <span>&gt;</span>
					<strong class="current">팀계정</strong>
				</p>
				<h1 class="stitle">팀계정</h1>
			</div>
			<div id="detail_content">
				<h2 class="depth2_title">담당자 정보</h2>

				<form id="f_teamClient" action="" method="post">
					<input type="hidden" name="type" value="teamClient">
					<input type="hidden" name="smsSpeedLimit" id="smsSpeedLimit" value="30" placeholder="SMS발송속도"> 
					<input type="hidden" name="lmsSpeedLimit" id="lmsSpeedLimit" value="5" placeholder="LMS발송속도"> 
					<input type="hidden" name="mmsSpeedLimit" id="mmsSpeedLimit" value="2" placeholder="MMS발송속도">
					<input type="hidden" name="useBilling" id="useBilling" value="${write.useBilling }">
					<input type="hidden" name="useMt" id="useMt" value="${write.useMt }">
					<input type="hidden" name="useApp" id="useApp" value="${write.useApp }">
					<input type="hidden" name="useMms" id="useMms" value="${write.useMms }">
					<input type="hidden" name="useMo" id="useMo" value="${write.useMo }">
					<input type="hidden" name="useWeb" id="useWeb" value="${write.useWeb }">
					<input type="hidden" name="orgCode" id="orgCode" value="${write.orgCode }">
					<input type="hidden" name="clientStatus" id="clientStatus" value="${write.clientStatus }">
					<input type="hidden" name="isOld" id="isOld" value="${write.isOld }">
					<input type="hidden" name="useOrg" id="useOrg" value="${write.useOrg }">
					<input type="hidden" name="useSmsOld" id="useSmsOld" value="${write.useSmsOld }">
					<input type="hidden" name="useSms" id="useSms" value="${write.useSms }">
					<input type="hidden" name="serviceStatus" id="serviceStatus" value="${write.serviceStatus }">
					<input type="hidden" name="nStatus" id="nStatus" value="${write.nStatus }">
					<input type="hidden" name="changeStatus" id="changeStatus" value="${write.changeStatus }">
					<input type="hidden" name="systemInfo" id="systemInfo" value="${write.systemInfo }">
					<input type="hidden" name="auth" id="auth" value="${write.auth }">
					<input type="hidden" name="ipNo1" id="ipNo1" value="${write.ipNo1 }">
					<input type="hidden" name="ipNo2" id="ipNo2" value="${write.ipNo2 }">
					<input type="hidden" name="ipNo3" id="ipNo3" value="${write.ipNo3 }">
					<input type="hidden" name="moSmsNoti" id="moSmsNoti" value="${write.moSmsNoti }">
					<input type="hidden" name="payerCel" id="payerCel" value="${billing.payerCel }">
					<input type="hidden" name="exClientCel" id="exClientCel" value="${write.clientCel }">
					<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
					
					<table class="basic_table left_head">
						<caption>담당자정보 - 기관계정, 기관,그룹명, 부서명, 아이디, 담당자, 대표자명,
							고유번호, 핸드폰번호, 전화번호, FAX, 주소, 월 이용건수제한</caption>
						<colgroup>
							<col style="width: 15%">
							<col style="width: 35%">
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<input type="hidden" name="upOrgId" id="upOrgId" value="${write.upOrgId }" />
								<th><label for="orgName">기관</label></th>
								<td><input type="text" name="orgName" id="orgName" value="${write.orgName }" /></td>
								<th><label for="orgDept">부서명</label></th>
								<td><input type="text" name="orgDept" id="orgDept" value="${write.orgDept }" /></td>
							</tr>
							<tr>
								<th style="line-height: 15px;"><label for="orgTitle">
										&nbsp;담당부서전체이름1<br>(서비스시스템명칭)
								</label></th>
								<td colspan="3"><input type="text" name="orgTitle"
									id="orgTitle" value="${write.orgTitle }" style="width: 635px;" /></td>
							</tr>
							<tr>
								<input type="hidden" name="masterId" id="masterId"
									value="${write.masterId }">
								<th><label for="clientId">ID</label></th>
								<td><input type="text" name="clientId" id="clientId"
									readonly="readonly" value="${write.clientId }"
									style="background-color: #dcdcdc;" /></td>
								<th><span class="required">*</span><label for="clientName">담당자</label></th>
								<td><input type="text" name="clientName" id="clientName"
									value="${write.clientName }" /></td>
							</tr>

							<!-- 20160928 비밀번호 수정 관련 -->
							<tr>
								<th><span class="required">*</span><label for="clientPwd">비밀번호</label></th>
								<td><input type="password" name="clientPwd" id="clientPwd"
									value="${write1}" /></td>
								<th><span class="required">*</span><label for="clientPwdRe">비밀번호확인</label></th>
								<td><input type="password" name="clientPwdRe"
									id="clientPwdRe" value="${write1}" /></td>
							</tr>

							<tr>
								<th><span class="required">*</span><label for="mngOrgCeo">대표자명</label></th>
								<td><input type="text" name="mngOrgCeo" id="mngOrgCeo"
									value="${write.mngOrgCeo }" /></td>
								<th><span class="required">*</span><label for="uniqueNo">고유번호</label></th>
								<td><input type="text" name="uniqueNo" id="uniqueNo"
									value="${write.uniqueNo }" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span><label for="clientCel">핸드폰번호</label></th>
								<td><input type="text" name="clientCel" id="clientCel"
									value="${write.clientCel }" /></td>
								<th><span class="required">*</span><label for="clientTel">전화번호</label></th>
								<td><input type="text" name="clientTel" id="clientTel"
									value="${write.clientTel }" /></td>
							</tr>
							<tr>
								<th><label for="mngFax">FAX</label></th>
								<td><input type="text" name="mngFax" id="mngFax"
									value="${write.mngFax }" /></td>
								<th><span class="required">*</span><label
									for="monthSendLimite">월 이용건수 제한</label></th>
								<td><input type="text" name="monthSendLimite"
									id="monthSendLimite" value="${write.monthSendLimite }" /></td>
							</tr>
							<tr>
								<th><label for="clientMail">E-MAIL</label></th>
								<td colspan="3"><input type="text" name="clientMail"
									id="clientMail" value="${write.clientMail }" /></td>
							</tr>

							<tr>
								<th><span class="required">*</span><label for="mngPost">주소</label></th>
								<td colspan="3">우편번호 : <input type="text" name="mngPost"
									id="mngPost" value="${write.mngPost }" /><br /> 주
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소*: <input type="text"
									name="mngAddr1" id="mngAddr1" value="${write.mngAddr1 }"
									style="width: 300px; margin-top: 5px;" /><br /> 상세주소*: <input
									type="text" name="mngAddr2" id="mngAddr2"
									value="${write.mngAddr2 }"
									style="width: 300px; margin-top: 5px;" />
								</td>
							</tr>
							
						</tbody>
					</table>
					<h2 class="depth2_title">요금납부방법</h2>
					<table class="basic_table left_head">
						<caption>요금납부방법 - 납부방법, 은행/카드사명, 계좌/카드번호, 예금/소유주,
							주민/사업자번호, 납부번호</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="billMethod">납부방법</label></th>
								<td><select name="billMethod" id="billMethod"
									class="billing">
										<option value="GIRO"
											${ billing.billMethod eq 'GIRO' ? 'selected="selected"' : '' }>지로출금</option>
										<option value="AUTO"
											${ billing.billMethod eq 'AUTO' ? 'selected="selected"' : '' }>계좌이체</option>
										<option value="CARD"
											${ billing.billMethod eq 'CARD' ? 'selected="selected""' : '' }>카드결제</option>
								</select></td>
								<th scope="col"><label for="bankName">은행/카드사명</label></th>
								<td><input type="text" name="bankName"
									value="${billing.bankName }" id="bankName" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="bankNo">계좌/카드번호</label></th>
								<td><input type="text" name="bankNo"
									value="${billing.bankNo }" id="bankNo" /></td>
								<th scope="col"><label for="bankOwner">예금/소유주</label></th>
								<td><input type="text" name="bankOwner"
									value="${billing.bankOwner }" id="bankOwner" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="bankRegNo">고유번호</label></th>
								<td><input type="text" name="bankRegNo"
									value="${billing.bankRegNo }" id="bankRegNo" /></td>
								<th scope="col"><label for="payerNo">납부번호</label></th>
								<td><input type="text" name="payerNo"
									value="${billing.payerNo }" id="payerNo" readonly="readonly"
									style="background-color: #dcdcdc;" /></td>
							</tr>
						</tbody>
					</table>

					<h2 class="depth2_title">요금납부담당자 정보</h2>
					<table class="basic_table left_head">
						<caption>요금담당자 정보 - 기관, 담당자, 전화번호, FAX, 납부주소</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><span class="required">*</span><label
									for="payerName">담당자</label></th>
								<td><input type="text" name="payerName"
									value="${billing.payerName }" id="payerName" /></td>
								<th scope="col"><span class="required">*</span><label
									for="payerOrg">부서</label></th>
								<td><input type="text" name="payerOrg"
									value="${billing.payerOrg }" id="payerOrg" /></td>
							</tr>
							<tr>
								<th scope="col"><span class="required">*</span><label
									for="payerTel">전화번호</label></th>
								<td><input type="text" name="payerTel"
									value="${billing.payerTel }" id="payerTel" /></td>
								<th scope="col"><label for="payerFax">FAX</label></th>
								<td><input type="text" name="payerFax"
									value="${billing.payerFax }" id="payerFax" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="payerMail">담당자Mail</label>
								<td colspan="3"><input type="text" name="payerMail"
									id="payerMail" value="${billing.payerMail}" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span><label
									for="charge_address">납부주소</label></th>
								<td colspan="3">우편번호 : <input type="text" name="payerPost"
									id="payerPost" value="${billing.payerPost }" /><br /> 주
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소*: <input type="text"
									name="payerAddr1" id="payerAddr1"
									value="${billing.payerAddr1 }"
									style="width: 300px; margin-top: 5px;" /><br /> 상세주소*: <input
									type="text" name="payerAddr2" id="payerAddr2"
									value="${billing.payerAddr2 }"
									style="width: 300px; margin-top: 5px;" />
								</td>
							</tr>
						</tbody>
					</table>

					<!-- 20160927 팀계정(서비스담당자정보추가) -->
					<h2 class="depth2_title">서비스담당자 정보</h2>
					<table class="basic_table left_head">
						<caption>팀계정 로그인시 서비스담당자 추가(로그인시 필요)</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="serviceName2">서비스담당자이름2</label></th>
								<td><input type="text" name="serviceName2"
									id="serviceName2" value="${write.serviceName2}" /></td>
								<th scope="col"><label for="serviceTel2">서비스담당자연락처2</label></th>
								<td><input type="text" name="serviceTel2" id="serviceTel2"
									value="${write.serviceTel2}" /></td>
							</tr>
						</tbody>
					</table>


					<!-- 앱메시지관련 -->
					<h2 class="depth2_title">앱메시지 정보</h2>
					<table class="basic_table left_head">
						<caption>앱메시지 정보</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="kakaoChannelId">카카오 채널
										ID</label></th>
								<td><input type="text" name="kakaoChannelId"
									id="kakaoChannelId" value="${senderKey.kakaoChannelId}" 
									<c:if test="${ senderKey.kakaoKey != null && senderKey.kakaoKey ne '' }">readonly</c:if>
									/></td>
								<th scope="col"><label for="kakaoKey">카카오 KEY </label></th>
								<td><input type="text" name="kakaoKey" id="kakaoKey"
									value="${senderKey.kakaoKey}" readonly /></td>
							</tr>
							<tr>
								<th scope="col"><label for="naverChannelId">네이버 채널
										ID</label></th>
								<td><input type="text" name="naverChannelId"
									id="naverChannelId" value="${senderKey.naverChannelId}" 
									<c:if test="${ senderKey.naverKey != null && senderKey.naverKey ne '' }">readonly</c:if>
									/></td>
								<th scope="col"><label for="naverKey">네이버 KEY</label></th>
								<td><input type="text" name="naverKey" id="naverKey"
									value="${senderKey.naverKey}" readonly/></td>
							</tr>
						</tbody>
					</table>

					<div class="btn_group txt_center">
						<a href="<%=cp%>/user/account/account.do"
							class="basic_btn bg_black">목록</a>
						<c:if test="${sessionScope.w eq '1'}">
							<a onclick="saveAccount($('#f_teamClient'));"
								class="basic_btn bg_green">저장</a>
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
		if($("#mngOrgCeo").val() == null || $("#mngOrgCeo").val() == ''){
			alert('대표자명을 입력하세요.');
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
		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
			alert('전화번호를 입력하세요.');
			return false;
		}
		if($("#mngAddr1").val() == null || $("#mngAddr1").val() == ''){
			alert('주소를 입력하세요.');
			return false;
		}
		if($("#mngAddr2").val() == null || $("#mngAddr2").val() == ''){
			alert('상세주소를 입력하세요.');
			return false;
		}
		if($("#monthSendLimite").val() == null || $("#monthSendLimite").val() == ''){
			alert('월발송 제한건수를 입력하세요.');
			return false;
		}
		if($("#payerName").val() == null || $("#payerName").val() == ''){
			alert('요금납부담당자명을 입력하세요.');
			return false;
		}
		if($("#payerOrg").val() == null || $("#payerOrg").val() == ''){
			alert('요금납부담당부서를 입력하세요.');
			return false;
		}
		if($("#payerTel").val() == null || $("#payerTel").val() == ''){
			alert('요금납부담당 전화번호를 입력하세요.');
			return false;
		}
		if($("#payerAddr1").val() == null || $("#payerAddr1").val() == ''){
			alert('요금납부담당 주소를 입력하세요.');
			return false;
		}
		if($("#payerAddr2").val() == null || $("#payerAddr2").val() == ''){
			alert('요금납부담당 상세주소를 입력하세요.');
			return false;
		}
		
		if($("#billMethod").val() != 'GIRO'){
	 		if($("#bankName").val() == null || $("#bankName").val() == ''){
	 			alert("은행/카드사명을 입력해주세요.");
	 			return false;
	 		}

	 		if($("#bankNo").val() == null || $("#bankNo").val() == ''){
	 			alert("계좌/카드번호를 입력해주세요.");
	 			return false;
	 		}

	 		if($("#bankOwner").val() == null || $("#bankOwner").val() == ''){
	 			alert("예금/소유주를 입력해주세요.");
	 			return false;
	 		}

	 		if($("#bankRegNo").val() == null || $("#bankRegNo").val() == ''){
	 			alert("요금납부정보의 고유번호를 입력해주세요.");
	 			return false;
	 		}
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
		
		
		$(f).attr('action','<%=cp%>/user/account/accountUpdate.do');
		$(f).submit();
	}
</script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/main.base.jsp"%>
