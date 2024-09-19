<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");	
%>

<rapid:override name="title">
����������
</rapid:override>

<rapid:override name="style">
</rapid:override>

<rapid:override name="content">
	<div id="page-wrapper">
		<%@ include file="/WEB-INF/view/site/admin_breadCrumb.inc.jsp"%>
		<c:if test="${orgList.clientId != null}">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-green">
					<div class="panel-heading">���������Է�</div>
					<div class="panel-body">
						<div class="row"> 
							<div class="col-md-12">
								<form role="form" action="" method="post" id="f_accountAdd" onsubmit="return submitFunction(this);">
									<input type="hidden" name="clientLevel" value="60">
									<input type="hidden" name="clientSeq" value="${orgList.clientSeq }">
									<input type="hidden" id="idCheck" > <label for="idCheck"></label>
									<input type="hidden" name="type" value="orgClient"> 
									<input type="hidden" name="exClientCel" id="exClientCel" value="${view.clientCel }">
									<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
									<div class="col-md-12">
									<div class="row">
									<div class="col-md-6">
										<label for="orgName">���</label>
										<div class="input-group">
											<input type="text" class="form-control" name="orgName" id="orgName" value="${orgList.orgName }" placeholder="���">
											<input type="hidden" name="orgCode" id="orgCode" value="${orgList.orgCode }">
											<div class="input-group-btn">
												<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">�˻�</a>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
										<label for="orgDept">�󼼺μ���</label>  
										<input type="text" class="form-control" name="orgDept" id="orgDept" value="${orgList.orgDept }"  placeholder="orgDept">
										</div>
									</div>
										</div>
									</div>																	
									
									<div class="col-md-12">
						 				<div class="form-group">
											<label for="orgTitle">���μ� ��ü�̸�</label> 
											<input type="text" class="form-control" name="orgTitle" id="orgTitle" value="${orgList.orgTitle }" placeholder="���μ���ü�̸�">
										</div>
									</div> 
																																				
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="groupName">�׷��</label>  
													<input type="text" class="form-control" name="groupName" id="groupName" value="${orgList.groupName }" placeholder="groupName">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">���̵�</label> 
											<!-- <div class="input-group"> -->
												<input type="text" class="form-control" name="clientId" onkeydown="$('#idCheck').val('');" id="clientId" maxlength="20" value="${orgList.clientId }" ${orgList.clientId != null ? 'readonly="readonly"' : '' } placeholder="���̵�">
												<!-- 
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">�˻�</button>
												</div>
												 -->
											<!-- </div> -->
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">�����</label> 
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20"  value="${orgList.clientName }" placeholder="�����">
										</div>
									</div>
									
									 
									<div class="col-md-12">
										<div class="form-group">
											<label for="clientPwd">��й�ȣ</label>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<input type="password" class="form-control" name="clientPwd" id="clientPwd" maxlength="20"  value="${write1}"  placeholder="��й�ȣ">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group" style="margin-top:-14px;"><label for="clientPwdRe"></label>
														<input type="password" class="form-control" name="clientPwdRe" id="clientPwdRe" maxlength="20"  value="${write1}"  placeholder="��й�ȣȮ��">
													</div>
												</div>
											</div>
										</div>
									</div>
									
								
								
									<div class="col-md-12">
										<div class="form-group">
											<label for="uniqueNo">������ȣ</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" value="${orgList.uniqueNo }"  placeholder="������ȣ">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">�ڵ�����ȣ</label> 
											<input type="text" class="form-control" name="clientCel" id="clientCel" value="${orgList.clientCel }"  placeholder="�ڵ�����ȣ">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">��ȭ��ȣ</label> 
											<input type="text" class="form-control" name="clientTel" id="clientTel" value="${orgList.clientTel }"  placeholder="��ȭ��ȣ">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group search-box">
													<label for="clientStatus">��������</label><br>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '0' ? 'checked="checked"' : '' }  value="0" checked />�̻��</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '1' ? 'checked="checked"' : '' }  value="1" />���</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '2' ? 'checked="checked"' : '' }  value="2" />����</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '3' ? 'checked="checked"' : '' }  value="3" />����</label> 
												</div>
											</div>
											
											<div class="col-md-6">
												<label for="userLogReason">����/��������</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" value="${orgList.userLogReason }" placeholder="����/��������">
												</div>
											</div>
											
										</div>
									</div>   

									<div class="box-footer">
										<c:if test="${orgList == null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountAdd.do');">����</button>
										</c:if>
										<c:if test="${orgList != null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountUpdateOrg.do');">����</button>
											<c:if test="${deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}"> 
												<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountDelete.do');">����</button>
											</c:if>
										</c:if>
										<button type="button" class="btn btn-primary pull-right" id="accountList">���</button>
										 
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>	
		</c:if>
		<c:if test="${orgList.clientId == null}">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-green">
					<div class="panel-heading">���������Է�</div>
					<div class="panel-body">
						<div class="row"> 
							<div class="col-md-12">
								<form role="form" action="" method="post" id="f_accountAdd" onsubmit="return submitFunction(this);">
									<input type="hidden" name="clientLevel" value="60">
									<input type="hidden" id="idCheck" > <label for="idCheck"></label>
									<input type="hidden" name="type" value="orgClient"> 
									
									<div class="col-md-12">
									<div class="row">
										<div class="col-md-6">
										<label for="orgName">���</label>
										<div class="input-group">											
											<input type="text" class="form-control" name="orgName" id="orgName"  placeholder="���">
											<input type="hidden" name="orgCode" id="orgCode" >
											<div class="input-group-btn">
												<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">�˻�</a>
											</div>
										</div>
										</div>
										
											<div class="col-md-6">
												<div class="form-group">
													<label for="orgDept">�󼼺μ���</label>  
													<input type="text" class="form-control" name="orgDept" id="orgDept" placeholder="orgDept">
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12">
						 				<div class="form-group">
											<label for="orgTitle">���μ� ��ü�̸�</label> 
											<input type="text" class="form-control" name="orgTitle" id="orgTitle" placeholder="���μ���ü�̸�">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="groupName">�׷��</label>  
													<input type="text" class="form-control" name="groupName" id="groupName" placeholder="groupName">
												</div>
											</div>
										</div>
									</div>
											
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">���̵�</label> 
											<div class="input-group">
												<input type="text" class="form-control" name="clientId" onkeydown="$('#idCheck').val('');" id="clientId" maxlength="20" placeholder="���̵�">
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">�˻�</button>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">�����</label> 
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20" placeholder="�����">
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="form-group">
											<label for="clientPwd">��й�ȣ</label>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<input type="password" class="form-control" name="clientPwd" id="clientPwd" maxlength="20" placeholder="��й�ȣ">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group" style="margin-top:-14px;"><label for="clientPwdRe"></label>
														<input type="password" class="form-control" name="clientPwdRe" id="clientPwdRe" maxlength="20" placeholder="��й�ȣȮ��">
													</div>
												</div>
											</div>
										</div>
									</div>
								
									<div class="col-md-12">
										<div class="form-group">
											<label for="uniqueNo">������ȣ</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" placeholder="������ȣ">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">�ڵ�����ȣ</label> 
											<input type="text" class="form-control" name="clientCel" id="clientCel" placeholder="�ڵ�����ȣ">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">��ȭ��ȣ</label> 
											<input type="text" class="form-control" name="clientTel" id="clientTel" placeholder="��ȭ��ȣ">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group search-box">
													<label for="clientStatus">��������</label><br>
													<label><input type="radio" name="clientStatus" value="0" checked />�̻��</label>
													<label><input type="radio" name="clientStatus" value="1" />���</label>
													<label><input type="radio" name="clientStatus" value="2" />����</label>
													<label><input type="radio" name="clientStatus" value="3" />����</label> 
												</div>
											</div>
											
											<div class="col-md-6">
												<label for="userLogReason">����/��������</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" placeholder="����/��������">
												</div>
											</div>
											
										</div>
									</div>   

									<div class="box-footer">
										<c:if test="${orgList == null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountAdd.do');">����</button>
										</c:if>
										<c:if test="${orgList != null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountUpdate.do');">����</button>
											<c:if test="${deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}"> 
												<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountDelete.do');">����</button>
											</c:if>
										</c:if>
										<button type="button" class="btn btn-primary pull-right" id="accountList">���</button>
										 
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
	</div>
</rapid:override>

<rapid:override name="javascript">
	<script type="text/javascript" src="<%=cp%>/static/js/common.js"></script>
	<script type="text/javascript" src="<%=cp%>/static/js/jquery/jquery.dataTables.js"></script>	
	<script>
		function f_submit(url) {
			if($("#exClientCel").val() != $("#clientCel").val()){
				$("#changeClientCel").val("Y");
			}
			$("#f_accountAdd").attr('action',url);
			$("#f_accountAdd").submit();
		};
		$('#checkClientId').click(function(){
			checkClientId();	
		});
		
		function checkClientId(){
			if($("#clientId").val() == null || $("#clientId").val() == "") {
				alert("���̵� �Է��ϼ���");
				return false; 
			}
			var data = 'clientId='+$("#clientId").val()+'&isAdmin=0';
			var url = '<%=cp%>/common/accountCountCheck2.do';
			callAjax(url,data,f_ckeckId);
		}
		
		var f_ckeckId = function(data){ 
			if(data.count != null &&data.count != ""){
				$("#idCheck").val("0");
				alert('�̹� ����� ���̵��Դϴ�.');
				
			}else{
				$("#idCheck").val("1");
				alert('��밡���� ���̵��Դϴ�.');
			}
		}
		
		function submitFunction(f){
			<c:if test="${ orgList.clientId == null}">
			if($("#idCheck").val() != 1){
				alert('���̵� üũ�ϼ���.');
				return false;
			}
			
	 		if( $("#clientPwd").val() != $("#clientPwdRe").val() ){
				alert("��й�ȣ�� Ȯ�����ּ���");
				return false;
			}
	 		
	 		if($("#clientPwdRe").val() == null || $("#clientPwdRe").val() == ''){
	 			alert("��й�ȣ Ȯ�ζ��� �Է����ּ���.");
	 			return false;
	 		}
	 		</c:if> 
	 		if($("#orgCode").val() == null || $("#orgCode").val() == ''){
	 			alert("�˻��� �̿��Ͽ� ����ڵ带 �������ּ���");
	 			return false;
	 		}
	 		if($("#groupName").val() == null || $("#groupName").val() == ''){
	 			alert("�׷���� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#orgDept").val() == null || $("#orgDept").val() == ''){
	 			alert("�󼼺μ����� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#orgTitle").val() == null || $("#orgTitle").val() == ''){
	 			alert("���μ� ��ü�̸��� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#clientName").val() == null || $("#clientName").val() == ''){
	 			alert("����ڸ��� �Է����ּ���.");
	 			return false;
	 		}
	 		<c:if test="${ orgList.clientId == null}">
	 		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
	 			alert("��й�ȣ�� �Է����ּ���.");
	 			return false;
	 		}
	 		</c:if>
	 		
	 		
	 		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
	 			alert("������ȣ�� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
	 			alert("�ڵ�����ȣ�� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
	 			alert("��ȭ��ȣ�� �Է����ּ���.");
	 			return false;
	 		}
	 		
	 		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
	 			alert("��ȭ��ȣ�� �Է����ּ���.");
	 			return false;
	 		}
			
			 
			
		}
		
		
		 $("#accountList").click(function(){
			document.location.href="<%=cp%>/admin/account/account.do";
		});
		 
		 
	</script>

</rapid:override>
	
	<rapid:override name="popupinc">
	<section id="popup">
		<%@ include file="/WEB-INF/view/site/orgSerch.inc.jsp"%>
	</section>
	</rapid:override>

<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>