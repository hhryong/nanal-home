<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");	
%>

<rapid:override name="title">
기관계정등록
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
					<div class="panel-heading">계정정보입력</div>
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
										<label for="orgName">기관</label>
										<div class="input-group">
											<input type="text" class="form-control" name="orgName" id="orgName" value="${orgList.orgName }" placeholder="기관">
											<input type="hidden" name="orgCode" id="orgCode" value="${orgList.orgCode }">
											<div class="input-group-btn">
												<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">검색</a>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
										<label for="orgDept">상세부서명</label>  
										<input type="text" class="form-control" name="orgDept" id="orgDept" value="${orgList.orgDept }"  placeholder="orgDept">
										</div>
									</div>
										</div>
									</div>																	
									
									<div class="col-md-12">
						 				<div class="form-group">
											<label for="orgTitle">담당부서 전체이름</label> 
											<input type="text" class="form-control" name="orgTitle" id="orgTitle" value="${orgList.orgTitle }" placeholder="담당부서전체이름">
										</div>
									</div> 
																																				
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="groupName">그룹명</label>  
													<input type="text" class="form-control" name="groupName" id="groupName" value="${orgList.groupName }" placeholder="groupName">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">아이디</label> 
											<!-- <div class="input-group"> -->
												<input type="text" class="form-control" name="clientId" onkeydown="$('#idCheck').val('');" id="clientId" maxlength="20" value="${orgList.clientId }" ${orgList.clientId != null ? 'readonly="readonly"' : '' } placeholder="아이디">
												<!-- 
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">검색</button>
												</div>
												 -->
											<!-- </div> -->
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">담당자</label> 
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20"  value="${orgList.clientName }" placeholder="담당자">
										</div>
									</div>
									
									 
									<div class="col-md-12">
										<div class="form-group">
											<label for="clientPwd">비밀번호</label>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<input type="password" class="form-control" name="clientPwd" id="clientPwd" maxlength="20"  value="${write1}"  placeholder="비밀번호">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group" style="margin-top:-14px;"><label for="clientPwdRe"></label>
														<input type="password" class="form-control" name="clientPwdRe" id="clientPwdRe" maxlength="20"  value="${write1}"  placeholder="비밀번호확인">
													</div>
												</div>
											</div>
										</div>
									</div>
									
								
								
									<div class="col-md-12">
										<div class="form-group">
											<label for="uniqueNo">고유번호</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" value="${orgList.uniqueNo }"  placeholder="고유번호">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">핸드폰번호</label> 
											<input type="text" class="form-control" name="clientCel" id="clientCel" value="${orgList.clientCel }"  placeholder="핸드폰번호">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">전화번호</label> 
											<input type="text" class="form-control" name="clientTel" id="clientTel" value="${orgList.clientTel }"  placeholder="전화번호">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group search-box">
													<label for="clientStatus">계정상태</label><br>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '0' ? 'checked="checked"' : '' }  value="0" checked />미사용</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '1' ? 'checked="checked"' : '' }  value="1" />사용</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '2' ? 'checked="checked"' : '' }  value="2" />정지</label>
													<label><input type="radio" name="clientStatus" ${ orgList.clientStatus eq '3' ? 'checked="checked"' : '' }  value="3" />해지</label> 
												</div>
											</div>
											
											<div class="col-md-6">
												<label for="userLogReason">정지/해지사유</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" value="${orgList.userLogReason }" placeholder="정지/해지사유">
												</div>
											</div>
											
										</div>
									</div>   

									<div class="box-footer">
										<c:if test="${orgList == null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountAdd.do');">저장</button>
										</c:if>
										<c:if test="${orgList != null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountUpdateOrg.do');">수정</button>
											<c:if test="${deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}"> 
												<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountDelete.do');">삭제</button>
											</c:if>
										</c:if>
										<button type="button" class="btn btn-primary pull-right" id="accountList">목록</button>
										 
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
					<div class="panel-heading">계정정보입력</div>
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
										<label for="orgName">기관</label>
										<div class="input-group">											
											<input type="text" class="form-control" name="orgName" id="orgName"  placeholder="기관">
											<input type="hidden" name="orgCode" id="orgCode" >
											<div class="input-group-btn">
												<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">검색</a>
											</div>
										</div>
										</div>
										
											<div class="col-md-6">
												<div class="form-group">
													<label for="orgDept">상세부서명</label>  
													<input type="text" class="form-control" name="orgDept" id="orgDept" placeholder="orgDept">
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12">
						 				<div class="form-group">
											<label for="orgTitle">담당부서 전체이름</label> 
											<input type="text" class="form-control" name="orgTitle" id="orgTitle" placeholder="담당부서전체이름">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="groupName">그룹명</label>  
													<input type="text" class="form-control" name="groupName" id="groupName" placeholder="groupName">
												</div>
											</div>
										</div>
									</div>
											
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">아이디</label> 
											<div class="input-group">
												<input type="text" class="form-control" name="clientId" onkeydown="$('#idCheck').val('');" id="clientId" maxlength="20" placeholder="아이디">
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">검색</button>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">담당자</label> 
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20" placeholder="담당자">
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="form-group">
											<label for="clientPwd">비밀번호</label>
											<div class="row">
												<div class="col-md-6">
													<div class="form-group">
														<input type="password" class="form-control" name="clientPwd" id="clientPwd" maxlength="20" placeholder="비밀번호">
													</div>
												</div>
												<div class="col-md-6">
													<div class="form-group" style="margin-top:-14px;"><label for="clientPwdRe"></label>
														<input type="password" class="form-control" name="clientPwdRe" id="clientPwdRe" maxlength="20" placeholder="비밀번호확인">
													</div>
												</div>
											</div>
										</div>
									</div>
								
									<div class="col-md-12">
										<div class="form-group">
											<label for="uniqueNo">고유번호</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" placeholder="고유번호">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">핸드폰번호</label> 
											<input type="text" class="form-control" name="clientCel" id="clientCel" placeholder="핸드폰번호">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">전화번호</label> 
											<input type="text" class="form-control" name="clientTel" id="clientTel" placeholder="전화번호">
										</div>
									</div> 
									
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group search-box">
													<label for="clientStatus">계정상태</label><br>
													<label><input type="radio" name="clientStatus" value="0" checked />미사용</label>
													<label><input type="radio" name="clientStatus" value="1" />사용</label>
													<label><input type="radio" name="clientStatus" value="2" />정지</label>
													<label><input type="radio" name="clientStatus" value="3" />해지</label> 
												</div>
											</div>
											
											<div class="col-md-6">
												<label for="userLogReason">정지/해지사유</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" placeholder="정지/해지사유">
												</div>
											</div>
											
										</div>
									</div>   

									<div class="box-footer">
										<c:if test="${orgList == null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountAdd.do');">저장</button>
										</c:if>
										<c:if test="${orgList != null && sessionScope.w eq '1'}">
											<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountUpdate.do');">수정</button>
											<c:if test="${deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}"> 
												<button type="button" class="btn btn-primary pull-right" onclick="f_submit('<%=cp%>/admin/account/accountDelete.do');">삭제</button>
											</c:if>
										</c:if>
										<button type="button" class="btn btn-primary pull-right" id="accountList">목록</button>
										 
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
				alert("아이디를 입력하세요");
				return false; 
			}
			var data = 'clientId='+$("#clientId").val()+'&isAdmin=0';
			var url = '<%=cp%>/common/accountCountCheck2.do';
			callAjax(url,data,f_ckeckId);
		}
		
		var f_ckeckId = function(data){ 
			if(data.count != null &&data.count != ""){
				$("#idCheck").val("0");
				alert('이미 등록한 아이디입니다.');
				
			}else{
				$("#idCheck").val("1");
				alert('사용가능한 아이디입니다.');
			}
		}
		
		function submitFunction(f){
			<c:if test="${ orgList.clientId == null}">
			if($("#idCheck").val() != 1){
				alert('아이디 체크하세요.');
				return false;
			}
			
	 		if( $("#clientPwd").val() != $("#clientPwdRe").val() ){
				alert("비밀번호를 확인해주세요");
				return false;
			}
	 		
	 		if($("#clientPwdRe").val() == null || $("#clientPwdRe").val() == ''){
	 			alert("비밀번호 확인란을 입력해주세요.");
	 			return false;
	 		}
	 		</c:if> 
	 		if($("#orgCode").val() == null || $("#orgCode").val() == ''){
	 			alert("검색을 이용하여 기관코드를 설정해주세요");
	 			return false;
	 		}
	 		if($("#groupName").val() == null || $("#groupName").val() == ''){
	 			alert("그룹명을 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#orgDept").val() == null || $("#orgDept").val() == ''){
	 			alert("상세부서명을 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#orgTitle").val() == null || $("#orgTitle").val() == ''){
	 			alert("담당부서 전체이름을 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#clientName").val() == null || $("#clientName").val() == ''){
	 			alert("담당자명을 입력해주세요.");
	 			return false;
	 		}
	 		<c:if test="${ orgList.clientId == null}">
	 		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
	 			alert("비밀번호를 입력해주세요.");
	 			return false;
	 		}
	 		</c:if>
	 		
	 		
	 		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
	 			alert("고유번호를 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
	 			alert("핸드폰번호를 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
	 			alert("전화번호를 입력해주세요.");
	 			return false;
	 		}
	 		
	 		if($("#clientTel").val() == null || $("#clientTel").val() == ''){
	 			alert("전화번호를 입력해주세요.");
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