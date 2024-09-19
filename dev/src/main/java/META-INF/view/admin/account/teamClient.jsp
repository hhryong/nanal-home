<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

		<form id="f_teamClietn" method="post" onsubmit="return submitFunction(this);">
		<input type="hidden" name="type" value="teamClient"/>
		<input type="hidden" id="idCheck" /><label for="idCheck"></label>
		<input type="hidden" id="clientLevel" name="clientLevel" value="50"/><label for="clientLevel"></label>
		<input type="hidden" name="isAdmin" value="0" />
		<input type="hidden" id="masterId" name="masterId" value="${view.masterId }" /><label for="masterId"></label>
		<input type="hidden" name="smsSpeedLimit" id="smsSpeedLimit" value="30" placeholder="SMS�߼ۼӵ�"/><label for="smsSpeedLimit"></label>
		<input type="hidden" name="lmsSpeedLimit" id="lmsSpeedLimit" value="5" placeholder="LMS�߼ۼӵ�"/><label for="lmsSpeedLimit"></label>
		<input type="hidden" name="mmsSpeedLimit" id="mmsSpeedLimit" value="2" placeholder="MMS�߼ۼӵ�"/><label for="mmsSpeedLimit"></label>
		<input type="hidden" name="serviceStatus" id="serviceStatus" value="${view.serviceStatus }">
		<input type="hidden" name='nStatus' id='nStatus' value='${view.nStatus }'>
		<input type="hidden" name="exClientCel" id="exClientCel" value="${view.clientCel }">
		<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-green">
						<div class="panel-heading">�� ���������Է�</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">

									<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="upOrgId">�������</label>
													<div class="input-group">
														<input type="text" class="form-control" name="upOrgId" id="upOrgId" readonly="readonly" value="${view.upOrgId }" placeholder="�������"/>
														<div class="input-group-btn">
															<a class="btn btn-primary" id="clientSearchBtn" data-toggle="modal" data-target="#clientSearchModal">�˻�</a>
														</div>
													</div>
												</div>
												<div class="checkbox">
													<label for="orgNot"> <input type="checkbox" id="orgNot">	����</label>
												</div>

											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<label for="orgName">���</label>
												<div class="input-group">

													<input type="text" class="form-control" name="orgName" id="orgName" value="${view.orgName }" placeholder="���">
													<input type="hidden" name="orgCode" id="orgCode" value="${view.orgCode }"><label for="orgCode"></label>
													<div class="input-group-btn">
														<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">�˻�</a>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="orgDept">�󼼺μ���</label>
													<input type="text" class="form-control" name="orgDept" id="orgDept" value="${view.orgDept }" placeholder="orgDept">
												</div>
											</div>
										</div>
									</div>

								<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="orgTitle">���μ� ��ü�̸�(���񽺽ý��۸�Ī)</label>
													<input type="text" class="form-control" name="orgTitle" id="orgTitle" value="${view.orgTitle }" placeholder="orgTitle">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="groupName">�׷��</label>
													<input type="text" class="form-control" name="groupName" id="groupName" value="${view.groupName }" placeholder="groupName">
												</div>
											</div>

											
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">���̵�</label>
											<div class="input-group">
												<input type="text" class="form-control" name="clientId" id="clientId" onkeydown="$('#idCheck').val('');" maxlength="20" value="${view.clientId }" ${view.clientId != null ? 'readonly="readonly"' : '' } placeholder="���̵�">
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">�˻�</button>
												</div>
											</div>

										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">�����</label>
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20"  value="${view.clientName }" placeholder="�����">
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
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="mngOrgCeo">��ǥ�ڸ�</label>
													<input type="text" class="form-control" id="mngOrgCeo" name="mngOrgCeo" value="${view.mngOrgCeo }"  placeholder="��ǥ�ڸ�">
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label for="uniqueNo">������ȣ</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" value="${view.uniqueNo }"  placeholder="������ȣ">
												</div>
											</div>
										</div>
									</div>




									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">�ڵ�����ȣ</label>
											<input type="text" class="form-control" name="clientCel" id="clientCel" value="${view.clientCel }"  placeholder="�ڵ�����ȣ">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">��ȭ��ȣ</label>
											<input type="text" class="form-control" name="clientTel" id="clientTel" value="${view.clientTel }" placeholder="��ȭ��ȣ">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="mngFax">FAX</label>
											<input type="text" class="form-control" name="mngFax" id="mngFax" value="${view.mngFax }"  placeholder="FAX">
										</div>
									</div>
									<!-- 20160930 �̸����߰� -->
									<div class="col-md-6">
										<div class="form-group">
											<label for="clientMail">E-MAIL</label>
											<input type="text" class="form-control" name="clientMail" id="clientMail" value="${view.clientMail}"  placeholder="E-MAIL">
										</div>
									</div>



									<div class="col-md-12">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="mngPost">�ּ�</label>
													<input type="text" class="form-control" name="mngPost" id="mngPost" value="${view.mngPost }"  placeholder="�����ȣ">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group"><label for="mngAddr1"></label>
													<input type="text" class="form-control" name="mngAddr1" id="mngAddr1" value="${view.mngAddr1 }"  placeholder="�ּ�"><label for="mngAddr1"></label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group"><label for="mngAddr2"></label>
													<input type="text" class="form-control" name="mngAddr2" id="mngAddr2" value="${view.mngAddr2 }"  placeholder="���ּ�"><label for="mngAddr2"></label>
												</div>
											</div>
										</div>
									</div>


																		<div class="col-md-12">
										<div class="form-group">
											<label for="monthSendLimite">�̿�Ǽ� ����</label>
											<div class="form-group">
												<input type="text" class="form-control" name="monthSendLimite" id="monthSendLimite" value="${view.monthSendLimite }" placeholder="�̿�Ǽ� ����">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group search-box">
											<label for="useOrg">������� ����</label>
											<select class="form-control" name="useOrg" id="useOrg">
												<option value="Y" ${ view.useOrg eq 'Y' ? 'selected="selected"' : '' }>����</option>
												<option value="N" ${ view.useOrg eq 'N' ? 'selected="selected"' : '' }>������</option>
											</select>

										</div>
									</div>
									
									
									
									<div class="col-md-6"> 
										<div class="form-group search-box">
											<label for="useBilling">��������</label><br>
											<label><input type="radio"  name="useBilling" value="Y" ${ view.useBilling eq 'Y'  ? 'checked="checked"' : '' }/>Y</label>&nbsp;
											<label><input type="radio"  name="useBilling" value="N" ${ view.useBilling eq 'N'  ? 'checked="checked"' : '' }/>N</label>										 	
										</div>
									</div> 
									
									
									<div class="col-md-12">
									<!-- 20161220(ȭ) SMS/MMS => MT�� ���� �۾� ���� -->
									<!-- 
										<div class="form-group search-box">							
											<label for="checkbox">����</label><br>
											<label><input type="checkbox" name="useMt" 	value="Y" ${ view.useMt 	eq 'Y' ? 'checked="checked"' : '' } /> SMS </label>
											<label><input type="checkbox" name="useMms" 	value="Y" ${ view.useMms 	eq 'Y' ? 'checked="checked"' : '' } /> MMS </label>
											<label><input type="checkbox" name="useMo" 		value="Y" ${ view.useMo 	eq 'Y' ? 'checked="checked"' : '' } /> MO </label>
											<label><input type="checkbox" name="useWeb" 	value="Y" ${ view.useWeb 	eq 'Y' ? 'checked="checked"' : '' }/> WEB </label>
									
										</div>
									 -->	
									 
										 <div class="form-group search-box">							
											<label for="checkbox">����</label><br>
											<label><input type="checkbox" name="useMt"  value="Y" ${ view.useMt eq 'Y' ? 'checked="checked"' : '' } /> MT </label>
											<label><input type="checkbox" name="useMo" 	value="Y" ${ view.useMo eq 'Y' ? 'checked="checked"' : '' } /> MO </label>
											<label><input type="checkbox" name="useWeb" value="Y" ${ view.useWeb eq 'Y' ? 'checked="checked"' : '' }/> WEB </label>
											<label><input type="checkbox" name="useApp" value="Y" ${ view.useApp eq 'Y' ? 'checked="checked"' : '' }/> APP </label>
										</div>					
									
									</div>

									<div class="col-md-6" style="margin-top:20px;">
										<div class="form-group  search-box">
											<label for="checkbox">û��</label>
											<c:set var ="change_status_sub" value="${view.changeStatus}" />
											<c:choose>
											<c:when test="${change_status_sub eq 'Y'}">
											<label><input type="hidden" name="changeStatus" value="Y"  ${ view.changeStatus eq 'Y' ? 'checked="checked"' : '' }  />(${view.serviceStatus })</label>
											</c:when>
											<c:when test="${change_status_sub eq 'N'}">
											<label><input type="checkbox" name="changeStatus" value="N"  ${ view.changeStatus eq 'N' ? 'checked="checked"' : '' }  />(${view.serviceStatus })</label>
											</c:when>
											<c:otherwise>
											<label><input type="checkbox" name="changeStatus" value="N"  ${ view.changeStatus eq 'N' ? 'checked="checked"' : '' }  />(${view.serviceStatus })</label>
											</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group search-box">
											<label for="checkbox">MO �˸� ����</label><br>
											<label><input type="checkbox" name="moSmsNoti" 	value="Y" ${ view.moSmsNoti 	eq 'Y' ? 'checked="checked"' : '' }/></label>
											
										</div>
									</div>


										<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
											
												<div class="form-group search-box">
													<label for="clientStatus">��������</label><br>
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '0' ? 'checked="checked"' : ''}  value="0" />�̻��</label> 												
													<label><input type="radio" name="clientStatus" ${ view.clientStatus == null || view.clientStatus eq '' || view.clientStatus eq '1' ? 'checked="checked"' : '' }  value="1" />���</label>
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '2' ? 'checked="checked"' : '' }  value="2" />����</label>
												 	<!-- 
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '3' ? 'checked="checked"' : '' }  value="3" />����</label>
													-- -->
												 
												</div>
											

											</div>
											<!-- 
											<div class="col-md-6">
												<label for="userLogReason">����/��������</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" value="${view.userLogReason }" placeholder="����/��������">
												</div>
											</div>
											 -->
 

										</div>
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
						<div class="panel-heading">���񽺴���� �߰�����</div>
							<div class="panel-body">
							
							<div class="row">
<%-- 								<div class="col-md-6">
										<div class="form-group">
										<label for="serviceName1">1. �̸�</label>
										<input type="text"  class="form-control" id="serviceName1" name="serviceName1" value="${view.serviceName1}" >
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceTel1">1. �޴�����ȣ(����ó)</label>
										<input type="text"  class="form-control" id="serviceTel1" name="serviceTel1" value="${view.serviceTel1}">
									</div>
								</div> --%>
								
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceName2">1. �̸�</label>
										<input type="text" class="form-control" id="serviceName2" name="serviceName2" value="${view.serviceName2}">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceTel2">1. �޴�����ȣ(����ó)</label>
										<input type="text" class="form-control" id="serviceTel2" name="serviceTel2" value="${view.serviceTel2}">
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
						<div class="panel-heading">��� ���� ����</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">

									<div class="col-md-4">
										<div class="form-group">
											<label>���� ���</label>
											<select class="form-control" name="billMethod" id="billMethod">
												<option value="GIRO" ${ billing.billMethod eq 'GIRO' ? 'selected="selected"' : '' }>�������</option>
												<option value="AUTO" ${ billing.billMethod eq 'AUTO' ? 'selected="selected"' : '' }>������ü</option>
											  	<option value="CARD" ${ billing.billMethod eq 'CARD' ? 'selected="selected""' : '' }>ī�����</option>
											</select>
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankName">����/ī����</label>
											<input type="text" class="form-control" name="bankName" id="bankName" value="${billing.bankName }" placeholder="����/ī����">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankNo">����/ī���ȣ</label>
											<input type="text" class="form-control" name="bankNo" id="bankNo" value="${billing.bankNo }" placeholder="����/ī���ȣ">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankOwner">����/������</label>
											<input type="text" class="form-control" name="bankOwner" id="bankOwner" value="${billing.bankOwner }" placeholder="����/������">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankRegNo">������ȣ</label>
											<input type="text" class="form-control"  name="bankRegNo" id="bankRegNo" value="${billing.bankRegNo }" placeholder="�ֹ�/����ڹ�ȣ">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="payerNo">���ι�ȣ</label>
											<input type="text" class="form-control" name="payerNo" id="payerNo" value="${billing.payerNo }" placeholder="���ι�ȣ">
										</div>
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
						<div class="panel-heading"><label for="clientInfoCopy"></label>
							��� ����� �μ� <input type="checkbox" id="clientInfoCopy">����� ������ ����
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="form-group">
											<label for="payerName">���� �����</label>
											<input type="text" class="form-control" name="serviceName1" id="serviceName1" value="${view.serviceName1}" placeholder="���� �����">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="payerOrg">���δ�� �μ�</label>
											<input type="text" class="form-control" name="payerOrg" id="payerOrg" value="${billing.payerOrg }" placeholder="���δ�� �μ�">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="payerTel">��ȭ��ȣ</label>
											<input type="text" class="form-control" name="payerTel" id="payerTel" value="${billing.payerTel }" placeholder="��ȭ��ȣ">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="payerFax">FAX</label>
											<input type="text" class="form-control" name="payerFax" id="payerFax" value="${billing.payerFax }" placeholder="FAX">
										</div>
									</div>
									 
									<div class="col-md-6">
										<div class="form-group">
											<label for="payerPost">������޴�����ȣ</label>
											<input type="text" class="form-control" name="serviceTel1" id="serviceTel1" value="${view.serviceTel1}"  placeholder="��ݳ��δ���� �޴�����ȣ">
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label for="payerPost">�����Email</label>
											<input type="text" class="form-control" name="payerMail" id="payerMail" value="${billing.payerMail }"  placeholder="��ݳ��δ���� Email">
										</div>
									</div>										
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="payerPost">�ּ�</label>
													<input type="text" class="form-control" name="payerPost" id="payerPost" value="${billing.payerPost }"  placeholder="�����ȣ">
												</div>
											</div>
											
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group"><label for="payerAddr1"></label>
													<input type="text" class="form-control" name="payerAddr1" id="payerAddr1" value="${billing.payerAddr1 }"  placeholder="�ּ�">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group"><label for="payerAddr2"></label>
													<input type="text" class="form-control" name="payerAddr2" id="payerAddr2" value="${billing.payerAddr2 }"  placeholder="���ּ�">
												</div>
											</div>
										</div>
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
						<div class="panel-heading">
							�۸޽��� ä�� ���� 
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="kakaoChannelId">īī�� ä�� ID</label>
										<input type="text" class="form-control" name="kakaoChannelId" id="kakaoChannelId" value="${senderKeyView.kakaoChannelId }" placeholder="īī�� ä�� ID">
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group">
										<label for="kakaoKey">īī�� ������ KEY</label>
										<input type="text" class="form-control" name="kakaoKey" id="kakaoKey" value="${senderKeyView.kakaoKey }" placeholder="īī�� ������ KEY">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="naverChannelId">���̹� ä�� ID</label>
										<input type="text" class="form-control" name="naverChannelId" id="naverChannelId" value="${senderKeyView.naverChannelId }" placeholder="���̹� ä�� ID">
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group">
										<label for="naverKey">���̹� ������ KEY</label>
										<input type="text" class="form-control" name="naverKey" id="naverKey" value="${senderKeyView.naverKey }" placeholder="���̹� ������ KEY">
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
							<div class="panel-heading">
								<label for="statusMemo">�޸�(����/��������)</label> 
							</div>
							<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-12">
										<div class="form-group"><label for="clientMemo"></label>
											<textarea name="clientMemo" id="clientMemo" style="width:100%; height:100px;">${view.clientMemo }</textarea>
										</div>
									</div>
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<%/*  <c:if test="${view == null && sessionScope.w eq '1'}">
						<button  type="button" class="btn btn-primary pull-right" id="btnExcelUpLoad">���� ���ε�</button>
					  </c:if>*/%>

					<button type="button" class="btn btn-primary pull-right" id="btnList">���</button>

					  <c:if test="${view == null && sessionScope.w eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnSave">����</button>
						
					  </c:if>

					  <c:if test="${view != null && deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnDelete">����</button>
					  </c:if>

					  <c:if test="${view != null && sessionScope.w eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnUpdate">����</button>
						<input type="button" class="btn btn-primary pull-right" onclick="change_status('${view.clientId }','${view.clientLevel }')" value="�����̷�">
						<button type="button" class="btn btn-primary pull-right" id="delStatus">����ó��</button>
					  </c:if>

					   <c:if test="${view != null }">
					   <c:if test="${view.useWeb eq 'Y'}">
						<button type="button" class="btn btn-primary pull-right" id="btnAddWeb">���߼۰�������</button>
						
						</c:if>
						<c:if test="${view.useMo eq 'Y'}">
						<button type="button" class="btn btn-primary pull-right" id="btnAddMo">MO��������</button>
						</c:if>						
						<button type="button" class="btn btn-primary pull-right" id="btnAddSystem">SYSTEM��������</button>
					    </c:if>

				</div>
			</div>
		</form>
	</div>
</rapid:override>

<rapid:override name="javascript">
	<script type="text/javascript" src="<%=cp%>/static/js/common.js"></script>
	<script>

	$("#btnAddWeb").click(function(){
		document.location.href="<%=cp%>/admin/account/accountCreate.do?type=webClient&masterId=${view.clientId}";
	});
	$("#btnAddMo").click(function(){
		document.location.href="<%=cp%>/admin/account/accountCreate.do?type=moClient&masterId=${view.clientId}";
	});
	$("#btnAddSystem").click(function(){
		document.location.href="<%=cp%>/admin/account/accountCreate.do?type=systemClient&masterId=${view.clientId}";
	});

	$("#orgNot").click( function(){
		if($(this).is(':checked') ){
			$("#upOrgId").val('');
		}
	});

	$("#clientInfoCopy").click( function(){
		if($(this).is(':checked') ){
			$("#serviceName1").val($("#clientName").val());
            $("#serviceTel1").val($("#clientCel").val());
            $("#payerOrg").val($("#orgDept").val());
            $("#payerTel").val($("#clientTel").val());
            $("#payerFax").val($("#mngFax").val());
            $("#payerPost").val($("#mngPost").val());
            $("#payerAddr1").val($("#mngAddr1").val());
            $("#payerAddr2").val($("#mngAddr2").val());
            $("#payerCel").val($("#clientCel").val());
            $("#payerMail").val($("#clientMail").val());
		}
	});

	$("#btnExcelUpLoad").click(function(){
		document.location.href="<%=cp%>/admin/account/accountCreate.do?type=excelClient";
	});
	$("#btnSave").click(function(){
		$("#f_teamClietn").attr('action','<%=cp%>/admin/account/accountAdd.do');
		$("#masterId").val($("#clientId").val());
		$("#f_teamClietn").submit();
	});

	$("#btnDelete").click(function(){
		$("#f_teamClietn").attr('action','<%=cp%>/admin/account/accountDelete.do');
		$("#f_teamClietn").submit();
	});

	$("#btnUpdate").click(function(){
		if($("#exClientCel").val() != $("#clientCel").val()){
			$("#changeClientCel").val("Y");
		}
		$("#f_teamClietn").attr('action','<%=cp%>/admin/account/accountUpdate.do');
		$("#masterId").val($("#clientId").val());
		$("#f_teamClietn").submit();
	});
	
	$("#delStatus").click(function(){
		
		var clientMemo = document.getElementById('clientMemo').value;
		var serviceStatus = document.getElementById('serviceStatus').value;
		
		if(clientMemo == null || clientMemo == ''){
			alert("����ó���� �޸� �Է����ּ���.");
			return false;
		}
		
		if(serviceStatus == 'REPT'){
			alert("�ű�û����� ����ó���Ҽ� �����ϴ�.");
			return false;
		}
		
		$("#f_teamClietn").attr('action','<%=cp%>/admin/account/accountDeleteStatus.do');
		$("#masterId").val($("#clientId").val());
		$("#f_teamClietn").submit();
	});
	


	$("#btnList").click(function(){
		document.location.href="<%=cp%>/admin/account/account.do";
	});


	$('#checkClientId').click(function(){
		checkClientId();
	});
	function checkClientId(){
		
		if(document.getElementById("clientId").value.length < 4 || document.getElementById("clientId").value.length > 16 ){
			alert("���̵�� 4 ~ 16 �ڸ��� �Է����ּ���");
			return false;
		}
		
		if($("#clientId").val() == null || $("#clientId").val() == "") {
			alert("���̵� �Է��ϼ���");
			return false;
		}
		var data = 'clientId='+$("#clientId").val()+'&isAdmin=0';
		var url = '<%=cp%>/common/accountCountCheck2.do';
		callAjax(url,data,f_ckeckId);
	}

	var f_ckeckId = function(data){
		if(data.count != null &&data.count == "1"){
			$("#idCheck").val("");
			alert('�̹� ����� ���̵��Դϴ�.');

		}else{
			$("#idCheck").val("1");
			alert('��밡���� ���̵��Դϴ�.');
		}
	}
	
	function change_status(clientId, type){
		
		window.open("<%=cp%>/admin/account/account_change_his.do?client_id="+clientId+"&type="+type , "�����̷�", "width=800, height=600, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes,resozab;e=no,left=150, top=150");
		
	}

	function submitFunction(f){
		
		if($("#orgName").val() == null || $("#orgName").val() == ''){
			alert('������� �Է��ϼ���.');
			return false;
		}
		  <c:if test="${ view.clientId == null}">
		if($("#idCheck").val() != 1){
			alert('���̵� üũ�ϼ���.');
			return false;
		}

 		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
 			alert("��й�ȣ�� �Է����ּ���.");
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
 

 		if($("#orgDept").val() == null || $("#orgDept").val() == ''){
 			alert("�ֱ������ �Է����ּ���.");
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



 		if($("#mngOrgCeo").val() == null || $("#mngOrgCeo").val() == ''){
 			alert("��ǥ�ڸ��� �Է����ּ���.");
 			return false;
 		}

 		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
 			alert("������ȣ�� �Է����ּ���.");
 			return false;
 		}
/*
 		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
 			alert("�ڵ�����ȣ�� �Է����ּ���.");
 			return false;
 		}
*/
 		if($("#mngAddr1").val() == null || $("#mngAddr1").val() == ''){
 			alert("�ּҸ� �Է����ּ���.");
 			return false;
 		}

 		if($("#mngAddr2").val() == null || $("#mngAddr2").val() == ''){
 			alert("���ּҸ� �Է����ּ���.");
 			return false;
 		}

 		if($("#monthSendLimite").val() == null || $("#monthSendLimite").val() == ''){
 			alert("�̿�Ǽ����� �Է¶��� �Է����ּ���.");
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
 		if($("#serviceName1").val() == null || $("#serviceName1").val() == ''){
 			alert("��ݴ���ںμ� ���δ���ڸ��� �Է����ּ���.");
 			return false;
 		}

 		if($("#payerOrg").val() == null || $("#payerOrg").val() == ''){
 			alert("��ݴ���ںμ� ���δ��μ��� �Է����ּ���.");
 			return false;
 		}

 		if($("#payerTel").val() == null || $("#payerTel").val() == ''){
 			alert("��ݴ���ںμ� ��ȭ��ȣ�� �Է����ּ���.");
 			return false;
 		}

 		if($("#payerFax").val() == null || $("#payerFax").val() == ''){
 			alert("��ݴ���ںμ� FAX ��ȣ�� �Է����ּ���.");
 			return false;
 		}
		/*
 		if($("#payerPost").val() == null || $("#payerPost").val() == ''){
 			alert("��ݴ���ںμ� �ּ�(�����ȣ)�� �Է����ּ���.");
 			return false;
 		}
		*/
 		if($("#payerAddr1").val() == null || $("#payerAddr1").val() == ''){
 			alert("��ݴ���ںμ� �ּҸ� �Է����ּ���.");
 			return false;
 		}

 		if($("#payerAddr2").val() == null || $("#payerAddr2").val() == ''){
 			alert("��ݴ���ںμ� ���ּҸ� �Է����ּ���.");
 			return false;
 		}
 
		var clientId = "";
		clientId = $("#clientId").val().trim();
		$("#clientId").val(clientId);

	}
	</script>
</rapid:override>

<rapid:override name="popupinc">
<section id="popup">
	<%@ include file="/WEB-INF/view/site/orgSerch.inc.jsp"%>
	<%@ include file="/WEB-INF/view/site/upAccountSerch.inc.jsp"%>
</section>
</rapid:override>


<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>