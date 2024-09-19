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
������
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
						<div class="panel-heading">����� ����</div>
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
										<label for="clientName">����ڸ�</label>
										<input type="text" class="form-control" id="clientName" value="${ view.clientName }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="orgName">�����</label>
										<input type="text" class="form-control" id="orgName" value="${ view.orgName }" readonly>
									</div>
								</div>	
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="orgDept">�μ���</label>
										<input type="text" class="form-control" id="orgDept" value="${ view.orgDept }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="kakaoChannelId">īī�� ä�� ID</label>
										<input type="text" class="form-control" id="kakaoChannelId" value="${ view.kakaoChannelId }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="kakaoKey">īī�� KEY</label>
										<input type="text" class="form-control" id="kakaoKey" value="${ view.kakaoKey }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="naverChannelId">���̹� ä�� ID</label>
										<input type="text" class="form-control" id="naverChannelId" value="${ view.naverChannelId }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="naverKey">���̹� KEY</label>
										<input type="text" class="form-control" id="naverKey" value="${ view.naverKey }" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="appGubun">�����</label>
										<fmt:formatDate var="formatRegiDate" value="${ view.regiDate }" pattern="yyyy-MM-dd" />
										<input type="text" class="form-control" id="formatRegiDate" value="${ formatRegiDate }" readonly>
									</div>
								</div>

								<div class="col-md-12">
									<div class="form-group">
										<label for="appGubun">��û��</label>
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
						<div class="panel-heading">���ø� ����</div>
						<div class="panel-body">
							<div class="row">
								

								<div class="col-md-8">
									<div class="form-group">
										<label for="appGubun">���ø� �ڵ�</label>
										<input type="text" class="form-control" id="appGubun" value="${ view.templateCode }" readonly>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="status">����</label>
										<input type="text" class="form-control" id="status"
										 value="${ view.status eq '1' ? '���' :
										           view.status eq '2' ? '���� ��û' :
										           view.status eq '3' ? '�ݷ�' :
										           view.status eq '4' ? '����' :
										           '-'}(${ view.isActivated eq 'Y' ? '���' :
										                  view.isActivated eq 'N' ? '�̻��' : '-' })" readonly>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label for="header">�Ӹ���</label>
										<textarea class="form-control" id="header" style="height: 60px; resize: none" readonly><c:out value="${ view.header }" /></textarea>
									</div>
								</div>

								<div class="col-md-4">
									<div class="form-group">
										<label for="header">HTTPS ����</label>
										<input class="form-control" type="checkbox" <c:if test="${ view.useHttps eq 'Y' }">checked</c:if> onclick="return false;">
									</div>
								</div>
								
								<div class="col-md-8">
									<div class="form-group">
										<label for="buttonName">��ư��</label>
										<input type="text" class="form-control" id="buttonName"
										 value="${ view.buttonName }" readonly>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="example1">����1</label>
										<textarea class="form-control" id="example1" style="height: 300px; resize: none"><c:out value="${ view.example1 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example2">����2</label>
										<textarea class="form-control" id="example2" style="height: 300px; resize: none"><c:out value="${ view.example2 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example3">����3</label>
										<textarea class="form-control" id="example3" style="height: 300px; resize: none"><c:out value="${ view.example3 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example4">����4</label>
										<textarea class="form-control" id="example4" style="height: 300px; resize: none"><c:out value="${ view.example4 }" /></textarea>
									</div>
								</div>
								
								<div class="col-md-4">
									<div class="form-group">
										<label for="example5">����5</label>
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
						<div class="panel-heading">�ݷ�����</div>
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
										<button type="button" class="btn btn-primary" onclick="templateDeactivate()">�������</button>
										</c:if>
										<c:if test="${ view.isActivated eq 'N' }">
										<button type="button" class="btn btn-primary" onclick="templateActivate()">���</button>
										</c:if>
										<button type="button" class="btn btn-primary" id="btnReject">�ݷ�</button>
										<button type="button" class="btn btn-primary" id="btnComplete">����</button>
										<button type="button" class="btn btn-primary" id="btnList">���</button>
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
					alert('��Ȱ��ȭ �Ϸ�');
					document.location.href='${ listUrl }';
				} else {
					alert('���ø� �ڵ� ������Ʈ ����')
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
					alert('��Ȱ��ȭ �Ϸ�');
					document.location.href='${ listUrl }';
				} else {
					alert('���ø� �ڵ� ������Ʈ ����')
				}
			}
		);
	}

	$("#btnReject").click(function(){
		let current_status = $('#status').val();
		
		if (current_status === '���') {
			alert('���ø��� ��ϻ����Դϴ�. ��û�� ���� ���ּ���.');
			return;
		}
		
		const regex = /^���� ��û/;
		if (!regex.test(current_status)) {
			alert('���ø��� ��û ���°� �ƴմϴ�.')
			return;
		}
		
		var reason = $("#rejectReason").val().trim()
		if (reason == undefined || reason.length === 0) {
			alert('�ݷ� ������ �Է��� �ּ���')
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
					alert('�ݷ� �Ϸ�');
					document.location.href='${ listUrl }';
				} else {
					alert('���ø� �ڵ� ������Ʈ ����')
				}
			}
		);
	});

	$("#btnComplete").click(function(){
		let current_status = $('#status').val();

		if (current_status === '���') {
			alert('���ø��� ��ϻ����Դϴ�. ���� ��û�� ���� ���ּ���.');
			return;
		} 
		
		const regex = /^���� ��û/;
		if (!regex.test(current_status)) {
			alert('���ø��� ��û ���°� �ƴմϴ�.')
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
					alert('���� �Ϸ�');
					document.location.href='${ listUrl }';
				} else {
					alert('���ø� �ڵ� ������Ʈ ����');
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