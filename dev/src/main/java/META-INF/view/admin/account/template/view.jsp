<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
팀계정
</rapid:override>

<rapid:override name="style">
</rapid:override>

<rapid:override name="content">

	<div id="page-wrapper">
		<%@ include file="/WEB-INF/view/site/admin_breadCrumb.inc.jsp"%>

		<form id="f_teamClietn" action="<c:url value='/admin/template/templateUpdate.do' />" method="post">
			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">사용자 정보</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label for="clientId">ID</label>
										<input type="text" class="form-control" id="clientId" value="${ view.clientId }" readonly>
									</div>
								</div>
								
								<div class="col-md-6">
									<div class="form-group">
										<label for="clientName">담당자명</label>
										<input type="text" class="form-control" id="clientName" value="${ view.clientName }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="orgName">기관명</label>
										<input type="text" class="form-control" id="orgName" value="${ view.orgName }" readonly>
									</div>
								</div>	
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="orgDept">부서명</label>
										<input type="text" class="form-control" id="orgDept" value="${ view.orgDept }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="kakaoChannelId">카카오 채널 ID</label>
										<input type="text" class="form-control" id="kakaoChannelId" value="${ view.kakaoChannelId }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="kakaoKey">카카오 KEY</label>
										<input type="text" class="form-control" id="kakaoKey" value="${ view.kakaoKey }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="naverChannelId">네이버 채널 ID</label>
										<input type="text" class="form-control" id="naverChannelId" value="${ view.naverChannelId }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="naverKey">네이버 KEY</label>
										<input type="text" class="form-control" id="naverKey" value="${ view.naverKey }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="appGubun">등록일</label>
										<fmt:formatDate var="formatRegiDate" value="${ view.regiDate }" pattern="yyyy-MM-dd" />
										<input type="text" class="form-control" id="formatRegiDate" value="${ formatRegiDate }" readonly>
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="appGubun">신청일</label>
										<fmt:formatDate var="formatDate" value="${ view.reportDate }" pattern="yyyy-MM-dd" />
										<input type="text" class="form-control" id="reportDate" value="${ formatDate }" readonly>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-md-6">
					<div class="panel panel-green">
						<div class="panel-heading">템플릿 정보</div>
						<div class="panel-body">
							<div class="row">
								

								<div class="col-md-8">
									<div class="form-group">
										<label for="appGubun">템플릿 코드</label>
										<input type="text" class="form-control" id="appGubun" value="${ view.templateCode }" readonly>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="status">상태</label>
										<input type="text" class="form-control" id="status"
										 value="${ view.status eq '1' ? '등록' :
										           view.status eq '2' ? '승인 신청' :
										           view.status eq '3' ? '반려' :
										           view.status eq '4' ? '승인' :
										           '-'}(${ view.isActivated eq 'Y' ? '사용' :
										                  view.isActivated eq 'N' ? '미사용' : '-' })" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="header">머릿말</label>
										<textarea class="form-control" id="header" style="height: 60px; resize: none" readonly><c:out value="${ view.header }" /></textarea>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label for="header">HTTPS 여부</label>
										<input class="form-control" type="checkbox" <c:if test="${ view.useHttps eq 'Y' }">checked</c:if> onclick="return false;">
									</div>
								</div>
								
								<div class="col-md-8">
									<div class="form-group">
										<label for="buttonName">버튼명</label>
										<input type="text" class="form-control" id="buttonName"
										 value="${ view.buttonName }" readonly>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="example1">예시1</label>
										<textarea class="form-control" id="example1" style="height: 300px; resize: none"><c:out value="${ view.example1 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example2">예시2</label>
										<textarea class="form-control" id="example2" style="height: 300px; resize: none"><c:out value="${ view.example2 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example3">예시3</label>
										<textarea class="form-control" id="example3" style="height: 300px; resize: none"><c:out value="${ view.example3 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example4">예시4</label>
										<textarea class="form-control" id="example4" style="height: 300px; resize: none"><c:out value="${ view.example4 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example5">예시5</label>
										<textarea class="form-control" id="example5" style="height: 300px; resize: none"><c:out value="${ view.example5 }" /></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-green">
						<div class="panel-heading">반려사유</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<textarea class="form-control" style="width: 100%; height: 300px; resize: none" id="rejectReason"><c:out value="${ view.rejectReason }" /></textarea>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-12">
									<p class="pull-right">
										<c:if test="${ view.isActivated eq 'Y' }">
										<button type="button" class="btn btn-primary" onclick="templateDeactivate()">사용중지</button>
										</c:if>
										<c:if test="${ view.isActivated eq 'N' }">
										<button type="button" class="btn btn-primary" onclick="templateActivate()">사용</button>
										</c:if>
										<button type="button" class="btn btn-primary" id="btnReject">반려</button>
										<button type="button" class="btn btn-primary" id="btnComplete">승인</button>
										<button type="button" class="btn btn-primary" id="btnList">목록</button>
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="seq" value="${ view.seq }">
			<c:url value="/admin/template/template.do" var="listUrl">
				<c:param name="clientId" value="${ param.clientId }" />
				<c:param name="orgName" value="${ param.orgName }" />
				<c:param name="orgDept" value="${ param.orgDept }" />
				<c:param name="groupName" value="${ param.groupName }" />
				<c:param name="templateCode" value="${ param.templateCode }" />
				<c:forEach var="status" items="${ paramValues.statusArray }">
					<c:param name="statusArray" value="${ status }" />
				</c:forEach>
			</c:url>
		</form>
	</div>
</rapid:override>

<rapid:override name="javascript">
<script type="text/javascript" src="<%=cp%>/static/js/common.js"></script>
<script>
	function templateDeactivate() {
		$.post(
			'<c:url value="/admin/template/templateDeactivate.do" />',
			{ 
				seq : $('#seq').val(),
			},
			function(data) {
				if (data.result) {
					alert('비활성화 완료');
					document.location.href='${ listUrl }';
				} else {
					alert('템플릿 코드 업데이트 실패')
				}
			}
		);
	}
	
	function templateActivate() {
		$.post(
			'<c:url value="/admin/template/templateActivate.do" />',
			{ 
				seq : $('#seq').val(),
			},
			function(data) {
				if (data.result) {
					alert('비활성화 완료');
					document.location.href='${ listUrl }';
				} else {
					alert('템플릿 코드 업데이트 실패')
				}
			}
		);
	}

	$("#btnReject").click(function(){
		let current_status = $('#status').val();
		
		if (current_status === '등록') {
			alert('템플릿이 등록상태입니다. 신청을 먼저 해주세요.');
			return;
		}
		
		const regex = /^승인 신청/;
		if (!regex.test(current_status)) {
			alert('템플릿이 신청 상태가 아닙니다.')
			return;
		}
		
		var reason = $("#rejectReason").val().trim()
		if (reason == undefined || reason.length === 0) {
			alert('반려 사유를 입력해 주세요')
			return
		}
		
		$.post(
			'<c:url value="/admin/template/templateUpdate.do" />',
			{ 
				seq : $('#seq').val(),
				status : '3',
				rejectReason : reason
			},
			function(data) {
				if (data.result) {
					alert('반려 완료');
					document.location.href='${ listUrl }';
				} else {
					alert('템플릿 코드 업데이트 실패')
				}
			}
		);
	});

	$("#btnComplete").click(function(){
		let current_status = $('#status').val();

		if (current_status === '등록') {
			alert('템플릿이 등록상태입니다. 승인 신청을 먼저 해주세요.');
			return;
		} 
		
		const regex = /^승인 신청/;
		if (!regex.test(current_status)) {
			alert('템플릿이 신청 상태가 아닙니다.')
			return;
		}
		
		$.post(
			'<c:url value="/admin/template/templateUpdate.do" />',
			{ 
				seq : $('#seq').val(),
				status : '4'
			},
			function(data) {
				if (data.result) {
					alert('승인 완료');
					document.location.href='${ listUrl }';
				} else {
					alert('템플릿 코드 업데이트 실패');
				}
			}
		);
	});

	$("#btnList").click(function(){
		document.location.href='${ listUrl }';
	});

</script>
</rapid:override>

<rapid:override name="popupinc">
<section id="popup">
	<%@ include file="/WEB-INF/view/site/orgSerch.inc.jsp"%>
	<%@ include file="/WEB-INF/view/site/upAccountSerch.inc.jsp"%>
</section>
</rapid:override>
<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>