<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
�� ����
</rapid:override>

<rapid:override name="style">
	<link href="<%=cp%>/static/css/contents.css" rel="stylesheet"
		type="text/css">
</rapid:override>

<rapid:override name="content">
	<section id="body" class="sub">
		<div id="side">
			<h2 class="account">��������</h2>
			<ul id="#snavigation">
				<%@ include file="/WEB-INF/view/site/menuSub.jsp"%>
			</ul>
		</div>
		<div id="content">
			<div id="content_title">
				<p class="path">
					<strong>HOME</strong> <span>&gt;</span> <strong>��������</strong> <span>&gt;</span>
					<strong class="current">������</strong>
				</p>
				<h1 class="stitle">������</h1>
			</div>
			<div id="detail_content">
				<h2 class="depth2_title">����� ����</h2>

				<form id="f_teamClient" action="" method="post">
					<input type="hidden" name="type" value="teamClient">
					<input type="hidden" name="smsSpeedLimit" id="smsSpeedLimit" value="30" placeholder="SMS�߼ۼӵ�"> 
					<input type="hidden" name="lmsSpeedLimit" id="lmsSpeedLimit" value="5" placeholder="LMS�߼ۼӵ�"> 
					<input type="hidden" name="mmsSpeedLimit" id="mmsSpeedLimit" value="2" placeholder="MMS�߼ۼӵ�">
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
						<caption>��������� - �������, ���,�׷��, �μ���, ���̵�, �����, ��ǥ�ڸ�,
							������ȣ, �ڵ�����ȣ, ��ȭ��ȣ, FAX, �ּ�, �� �̿�Ǽ�����</caption>
						<colgroup>
							<col style="width: 15%">
							<col style="width: 35%">
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<input type="hidden" name="upOrgId" id="upOrgId" value="${write.upOrgId }" />
								<th><label for="orgName">���</label></th>
								<td><input type="text" name="orgName" id="orgName" value="${write.orgName }" /></td>
								<th><label for="orgDept">�μ���</label></th>
								<td><input type="text" name="orgDept" id="orgDept" value="${write.orgDept }" /></td>
							</tr>
							<tr>
								<th style="line-height: 15px;"><label for="orgTitle">
										&nbsp;���μ���ü�̸�1<br>(���񽺽ý��۸�Ī)
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
								<th><span class="required">*</span><label for="clientName">�����</label></th>
								<td><input type="text" name="clientName" id="clientName"
									value="${write.clientName }" /></td>
							</tr>

							<!-- 20160928 ��й�ȣ ���� ���� -->
							<tr>
								<th><span class="required">*</span><label for="clientPwd">��й�ȣ</label></th>
								<td><input type="password" name="clientPwd" id="clientPwd"
									value="${write1}" /></td>
								<th><span class="required">*</span><label for="clientPwdRe">��й�ȣȮ��</label></th>
								<td><input type="password" name="clientPwdRe"
									id="clientPwdRe" value="${write1}" /></td>
							</tr>

							<tr>
								<th><span class="required">*</span><label for="mngOrgCeo">��ǥ�ڸ�</label></th>
								<td><input type="text" name="mngOrgCeo" id="mngOrgCeo"
									value="${write.mngOrgCeo }" /></td>
								<th><span class="required">*</span><label for="uniqueNo">������ȣ</label></th>
								<td><input type="text" name="uniqueNo" id="uniqueNo"
									value="${write.uniqueNo }" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span><label for="clientCel">�ڵ�����ȣ</label></th>
								<td><input type="text" name="clientCel" id="clientCel"
									value="${write.clientCel }" /></td>
								<th><span class="required">*</span><label for="clientTel">��ȭ��ȣ</label></th>
								<td><input type="text" name="clientTel" id="clientTel"
									value="${write.clientTel }" /></td>
							</tr>
							<tr>
								<th><label for="mngFax">FAX</label></th>
								<td><input type="text" name="mngFax" id="mngFax"
									value="${write.mngFax }" /></td>
								<th><span class="required">*</span><label
									for="monthSendLimite">�� �̿�Ǽ� ����</label></th>
								<td><input type="text" name="monthSendLimite"
									id="monthSendLimite" value="${write.monthSendLimite }" /></td>
							</tr>
							<tr>
								<th><label for="clientMail">E-MAIL</label></th>
								<td colspan="3"><input type="text" name="clientMail"
									id="clientMail" value="${write.clientMail }" /></td>
							</tr>

							<tr>
								<th><span class="required">*</span><label for="mngPost">�ּ�</label></th>
								<td colspan="3">�����ȣ : <input type="text" name="mngPost"
									id="mngPost" value="${write.mngPost }" /><br /> ��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��*: <input type="text"
									name="mngAddr1" id="mngAddr1" value="${write.mngAddr1 }"
									style="width: 300px; margin-top: 5px;" /><br /> ���ּ�*: <input
									type="text" name="mngAddr2" id="mngAddr2"
									value="${write.mngAddr2 }"
									style="width: 300px; margin-top: 5px;" />
								</td>
							</tr>
							
						</tbody>
					</table>
					<h2 class="depth2_title">��ݳ��ι��</h2>
					<table class="basic_table left_head">
						<caption>��ݳ��ι�� - ���ι��, ����/ī����, ����/ī���ȣ, ����/������,
							�ֹ�/����ڹ�ȣ, ���ι�ȣ</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="billMethod">���ι��</label></th>
								<td><select name="billMethod" id="billMethod"
									class="billing">
										<option value="GIRO"
											${ billing.billMethod eq 'GIRO' ? 'selected="selected"' : '' }>�������</option>
										<option value="AUTO"
											${ billing.billMethod eq 'AUTO' ? 'selected="selected"' : '' }>������ü</option>
										<option value="CARD"
											${ billing.billMethod eq 'CARD' ? 'selected="selected""' : '' }>ī�����</option>
								</select></td>
								<th scope="col"><label for="bankName">����/ī����</label></th>
								<td><input type="text" name="bankName"
									value="${billing.bankName }" id="bankName" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="bankNo">����/ī���ȣ</label></th>
								<td><input type="text" name="bankNo"
									value="${billing.bankNo }" id="bankNo" /></td>
								<th scope="col"><label for="bankOwner">����/������</label></th>
								<td><input type="text" name="bankOwner"
									value="${billing.bankOwner }" id="bankOwner" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="bankRegNo">������ȣ</label></th>
								<td><input type="text" name="bankRegNo"
									value="${billing.bankRegNo }" id="bankRegNo" /></td>
								<th scope="col"><label for="payerNo">���ι�ȣ</label></th>
								<td><input type="text" name="payerNo"
									value="${billing.payerNo }" id="payerNo" readonly="readonly"
									style="background-color: #dcdcdc;" /></td>
							</tr>
						</tbody>
					</table>

					<h2 class="depth2_title">��ݳ��δ���� ����</h2>
					<table class="basic_table left_head">
						<caption>��ݴ���� ���� - ���, �����, ��ȭ��ȣ, FAX, �����ּ�</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><span class="required">*</span><label
									for="payerName">�����</label></th>
								<td><input type="text" name="payerName"
									value="${billing.payerName }" id="payerName" /></td>
								<th scope="col"><span class="required">*</span><label
									for="payerOrg">�μ�</label></th>
								<td><input type="text" name="payerOrg"
									value="${billing.payerOrg }" id="payerOrg" /></td>
							</tr>
							<tr>
								<th scope="col"><span class="required">*</span><label
									for="payerTel">��ȭ��ȣ</label></th>
								<td><input type="text" name="payerTel"
									value="${billing.payerTel }" id="payerTel" /></td>
								<th scope="col"><label for="payerFax">FAX</label></th>
								<td><input type="text" name="payerFax"
									value="${billing.payerFax }" id="payerFax" /></td>
							</tr>
							<tr>
								<th scope="col"><label for="payerMail">�����Mail</label>
								<td colspan="3"><input type="text" name="payerMail"
									id="payerMail" value="${billing.payerMail}" /></td>
							</tr>
							<tr>
								<th><span class="required">*</span><label
									for="charge_address">�����ּ�</label></th>
								<td colspan="3">�����ȣ : <input type="text" name="payerPost"
									id="payerPost" value="${billing.payerPost }" /><br /> ��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��*: <input type="text"
									name="payerAddr1" id="payerAddr1"
									value="${billing.payerAddr1 }"
									style="width: 300px; margin-top: 5px;" /><br /> ���ּ�*: <input
									type="text" name="payerAddr2" id="payerAddr2"
									value="${billing.payerAddr2 }"
									style="width: 300px; margin-top: 5px;" />
								</td>
							</tr>
						</tbody>
					</table>

					<!-- 20160927 ������(���񽺴���������߰�) -->
					<h2 class="depth2_title">���񽺴���� ����</h2>
					<table class="basic_table left_head">
						<caption>������ �α��ν� ���񽺴���� �߰�(�α��ν� �ʿ�)</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="serviceName2">���񽺴�����̸�2</label></th>
								<td><input type="text" name="serviceName2"
									id="serviceName2" value="${write.serviceName2}" /></td>
								<th scope="col"><label for="serviceTel2">���񽺴���ڿ���ó2</label></th>
								<td><input type="text" name="serviceTel2" id="serviceTel2"
									value="${write.serviceTel2}" /></td>
							</tr>
						</tbody>
					</table>


					<!-- �۸޽������� -->
					<h2 class="depth2_title">�۸޽��� ����</h2>
					<table class="basic_table left_head">
						<caption>�۸޽��� ����</caption>
						<colgroup>
							<col style="width: 15%">
							<col>
							<col style="width: 15%">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"><label for="kakaoChannelId">īī�� ä��
										ID</label></th>
								<td><input type="text" name="kakaoChannelId"
									id="kakaoChannelId" value="${senderKey.kakaoChannelId}" 
									<c:if test="${ senderKey.kakaoKey != null && senderKey.kakaoKey ne '' }">readonly</c:if>
									/></td>
								<th scope="col"><label for="kakaoKey">īī�� KEY </label></th>
								<td><input type="text" name="kakaoKey" id="kakaoKey"
									value="${senderKey.kakaoKey}" readonly /></td>
							</tr>
							<tr>
								<th scope="col"><label for="naverChannelId">���̹� ä��
										ID</label></th>
								<td><input type="text" name="naverChannelId"
									id="naverChannelId" value="${senderKey.naverChannelId}" 
									<c:if test="${ senderKey.naverKey != null && senderKey.naverKey ne '' }">readonly</c:if>
									/></td>
								<th scope="col"><label for="naverKey">���̹� KEY</label></th>
								<td><input type="text" name="naverKey" id="naverKey"
									value="${senderKey.naverKey}" readonly/></td>
							</tr>
						</tbody>
					</table>

					<div class="btn_group txt_center">
						<a href="<%=cp%>/user/account/account.do"
							class="basic_btn bg_black">���</a>
						<c:if test="${sessionScope.w eq '1'}">
							<a onclick="saveAccount($('#f_teamClient'));"
								class="basic_btn bg_green">����</a>
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
		if($("#mngOrgCeo").val() == null || $("#mngOrgCeo").val() == ''){
			alert('��ǥ�ڸ��� �Է��ϼ���.');
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
		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
			alert('��ȭ��ȣ�� �Է��ϼ���.');
			return false;
		}
		if($("#mngAddr1").val() == null || $("#mngAddr1").val() == ''){
			alert('�ּҸ� �Է��ϼ���.');
			return false;
		}
		if($("#mngAddr2").val() == null || $("#mngAddr2").val() == ''){
			alert('���ּҸ� �Է��ϼ���.');
			return false;
		}
		if($("#monthSendLimite").val() == null || $("#monthSendLimite").val() == ''){
			alert('���߼� ���ѰǼ��� �Է��ϼ���.');
			return false;
		}
		if($("#payerName").val() == null || $("#payerName").val() == ''){
			alert('��ݳ��δ���ڸ��� �Է��ϼ���.');
			return false;
		}
		if($("#payerOrg").val() == null || $("#payerOrg").val() == ''){
			alert('��ݳ��δ��μ��� �Է��ϼ���.');
			return false;
		}
		if($("#payerTel").val() == null || $("#payerTel").val() == ''){
			alert('��ݳ��δ�� ��ȭ��ȣ�� �Է��ϼ���.');
			return false;
		}
		if($("#payerAddr1").val() == null || $("#payerAddr1").val() == ''){
			alert('��ݳ��δ�� �ּҸ� �Է��ϼ���.');
			return false;
		}
		if($("#payerAddr2").val() == null || $("#payerAddr2").val() == ''){
			alert('��ݳ��δ�� ���ּҸ� �Է��ϼ���.');
			return false;
		}
		
		if($("#billMethod").val() != 'GIRO'){
	 		if($("#bankName").val() == null || $("#bankName").val() == ''){
	 			alert("����/ī������ �Է����ּ���.");
	 			return false;
	 		}

	 		if($("#bankNo").val() == null || $("#bankNo").val() == ''){
	 			alert("����/ī���ȣ�� �Է����ּ���.");
	 			return false;
	 		}

	 		if($("#bankOwner").val() == null || $("#bankOwner").val() == ''){
	 			alert("����/�����ָ� �Է����ּ���.");
	 			return false;
	 		}

	 		if($("#bankRegNo").val() == null || $("#bankRegNo").val() == ''){
	 			alert("��ݳ��������� ������ȣ�� �Է����ּ���.");
	 			return false;
	 		}
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
		
		
		$(f).attr('action','<%=cp%>/user/account/accountUpdate.do');
		$(f).submit();
	}
</script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/main.base.jsp"%>
