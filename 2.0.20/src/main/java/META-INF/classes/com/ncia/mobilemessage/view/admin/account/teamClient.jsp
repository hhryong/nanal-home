<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

		<form id="f_teamClietn" method="post" onsubmit="return submitFunction(this);">
		<input type="hidden" name="type" value="teamClient"/>
		<input type="hidden" id="idCheck" /><label for="idCheck"></label>
		<input type="hidden" id="clientLevel" name="clientLevel" value="50"/><label for="clientLevel"></label>
		<input type="hidden" name="isAdmin" value="0" />
		<input type="hidden" id="masterId" name="masterId" value="${view.masterId }" /><label for="masterId"></label>
		<input type="hidden" name="smsSpeedLimit" id="smsSpeedLimit" value="30" placeholder="SMS발송속도"/><label for="smsSpeedLimit"></label>
		<input type="hidden" name="lmsSpeedLimit" id="lmsSpeedLimit" value="5" placeholder="LMS발송속도"/><label for="lmsSpeedLimit"></label>
		<input type="hidden" name="mmsSpeedLimit" id="mmsSpeedLimit" value="2" placeholder="MMS발송속도"/><label for="mmsSpeedLimit"></label>
		<input type="hidden" name="serviceStatus" id="serviceStatus" value="${view.serviceStatus }">
		<input type="hidden" name='nStatus' id='nStatus' value='${view.nStatus }'>
		<input type="hidden" name="exClientCel" id="exClientCel" value="${view.clientCel }">
		<input type="hidden" name="changeClientCel" id="changeClientCel" value="N">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-green">
						<div class="panel-heading">팀 계정정보입력</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">

									<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="upOrgId">기관계정</label>
													<div class="input-group">
														<input type="text" class="form-control" name="upOrgId" id="upOrgId" readonly="readonly" value="${view.upOrgId }" placeholder="기관계정"/>
														<div class="input-group-btn">
															<a class="btn btn-primary" id="clientSearchBtn" data-toggle="modal" data-target="#clientSearchModal">검색</a>
														</div>
													</div>
												</div>
												<div class="checkbox">
													<label for="orgNot"> <input type="checkbox" id="orgNot">	없음</label>
												</div>

											</div>
										</div>
									</div>

									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<label for="orgName">기관</label>
												<div class="input-group">

													<input type="text" class="form-control" name="orgName" id="orgName" value="${view.orgName }" placeholder="기관">
													<input type="hidden" name="orgCode" id="orgCode" value="${view.orgCode }"><label for="orgCode"></label>
													<div class="input-group-btn">
														<a class="btn btn-primary" id="orgSearchBtn" data-toggle="modal" data-target="#myModal">검색</a>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<label for="orgDept">상세부서명</label>
													<input type="text" class="form-control" name="orgDept" id="orgDept" value="${view.orgDept }" placeholder="orgDept">
												</div>
											</div>
										</div>
									</div>

								<div class="col-md-12">
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label for="orgTitle">담당부서 전체이름(서비스시스템명칭)</label>
													<input type="text" class="form-control" name="orgTitle" id="orgTitle" value="${view.orgTitle }" placeholder="orgTitle">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="groupName">그룹명</label>
													<input type="text" class="form-control" name="groupName" id="groupName" value="${view.groupName }" placeholder="groupName">
												</div>
											</div>

											
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientId">아이디</label>
											<div class="input-group">
												<input type="text" class="form-control" name="clientId" id="clientId" onkeydown="$('#idCheck').val('');" maxlength="20" value="${view.clientId }" ${view.clientId != null ? 'readonly="readonly"' : '' } placeholder="아이디">
												<div class="input-group-btn">
													<button type="button" class="btn btn-danger" id="checkClientId">검색</button>
												</div>
											</div>

										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientName">담당자</label>
											<input type="text" class="form-control" name="clientName" id="clientName" maxlength="20"  value="${view.clientName }" placeholder="담당자">
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
										<div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<label for="mngOrgCeo">대표자명</label>
													<input type="text" class="form-control" id="mngOrgCeo" name="mngOrgCeo" value="${view.mngOrgCeo }"  placeholder="대표자명">
												</div>
											</div>

											<div class="col-md-6">
												<div class="form-group">
													<label for="uniqueNo">고유번호</label>
											<input type="text" class="form-control" name="uniqueNo" id="uniqueNo" value="${view.uniqueNo }"  placeholder="고유번호">
												</div>
											</div>
										</div>
									</div>




									<div class="col-md-6">
										<div class="form-group">
											<label for="clientCel">핸드폰번호</label>
											<input type="text" class="form-control" name="clientCel" id="clientCel" value="${view.clientCel }"  placeholder="핸드폰번호">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="clientTel">전화번호</label>
											<input type="text" class="form-control" name="clientTel" id="clientTel" value="${view.clientTel }" placeholder="전화번호">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="mngFax">FAX</label>
											<input type="text" class="form-control" name="mngFax" id="mngFax" value="${view.mngFax }"  placeholder="FAX">
										</div>
									</div>
									<!-- 20160930 이메일추가 -->
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
													<label for="mngPost">주소</label>
													<input type="text" class="form-control" name="mngPost" id="mngPost" value="${view.mngPost }"  placeholder="우편번호">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group"><label for="mngAddr1"></label>
													<input type="text" class="form-control" name="mngAddr1" id="mngAddr1" value="${view.mngAddr1 }"  placeholder="주소"><label for="mngAddr1"></label>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group"><label for="mngAddr2"></label>
													<input type="text" class="form-control" name="mngAddr2" id="mngAddr2" value="${view.mngAddr2 }"  placeholder="상세주소"><label for="mngAddr2"></label>
												</div>
											</div>
										</div>
									</div>


																		<div class="col-md-12">
										<div class="form-group">
											<label for="monthSendLimite">이용건수 제한</label>
											<div class="form-group">
												<input type="text" class="form-control" name="monthSendLimite" id="monthSendLimite" value="${view.monthSendLimite }" placeholder="이용건수 제한">
											</div>
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group search-box">
											<label for="useOrg">행정기관 여부</label>
											<select class="form-control" name="useOrg" id="useOrg">
												<option value="Y" ${ view.useOrg eq 'Y' ? 'selected="selected"' : '' }>행정</option>
												<option value="N" ${ view.useOrg eq 'N' ? 'selected="selected"' : '' }>비행정</option>
											</select>

										</div>
									</div>
									
									
									
									<div class="col-md-6"> 
										<div class="form-group search-box">
											<label for="useBilling">빌링전용</label><br>
											<label><input type="radio"  name="useBilling" value="Y" ${ view.useBilling eq 'Y'  ? 'checked="checked"' : '' }/>Y</label>&nbsp;
											<label><input type="radio"  name="useBilling" value="N" ${ view.useBilling eq 'N'  ? 'checked="checked"' : '' }/>N</label>										 	
										</div>
									</div> 
									
									
									<div class="col-md-12">
									<!-- 20161220(화) SMS/MMS => MT로 변경 작업 관련 -->
									<!-- 
										<div class="form-group search-box">							
											<label for="checkbox">서비스</label><br>
											<label><input type="checkbox" name="useMt" 	value="Y" ${ view.useMt 	eq 'Y' ? 'checked="checked"' : '' } /> SMS </label>
											<label><input type="checkbox" name="useMms" 	value="Y" ${ view.useMms 	eq 'Y' ? 'checked="checked"' : '' } /> MMS </label>
											<label><input type="checkbox" name="useMo" 		value="Y" ${ view.useMo 	eq 'Y' ? 'checked="checked"' : '' } /> MO </label>
											<label><input type="checkbox" name="useWeb" 	value="Y" ${ view.useWeb 	eq 'Y' ? 'checked="checked"' : '' }/> WEB </label>
									
										</div>
									 -->	
									 
										 <div class="form-group search-box">							
											<label for="checkbox">서비스</label><br>
											<label><input type="checkbox" name="useMt"  value="Y" ${ view.useMt eq 'Y' ? 'checked="checked"' : '' } /> MT </label>
											<label><input type="checkbox" name="useMo" 	value="Y" ${ view.useMo eq 'Y' ? 'checked="checked"' : '' } /> MO </label>
											<label><input type="checkbox" name="useWeb" value="Y" ${ view.useWeb eq 'Y' ? 'checked="checked"' : '' }/> WEB </label>
											<label><input type="checkbox" name="useApp" value="Y" ${ view.useApp eq 'Y' ? 'checked="checked"' : '' }/> APP </label>
										</div>					
									
									</div>

									<div class="col-md-6" style="margin-top:20px;">
										<div class="form-group  search-box">
											<label for="checkbox">청약</label>
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
											<label for="checkbox">MO 알림 서비스</label><br>
											<label><input type="checkbox" name="moSmsNoti" 	value="Y" ${ view.moSmsNoti 	eq 'Y' ? 'checked="checked"' : '' }/></label>
											
										</div>
									</div>


										<div class="col-md-12">
										<div class="row">
											<div class="col-md-6">
											
												<div class="form-group search-box">
													<label for="clientStatus">계정상태</label><br>
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '0' ? 'checked="checked"' : ''}  value="0" />미사용</label> 												
													<label><input type="radio" name="clientStatus" ${ view.clientStatus == null || view.clientStatus eq '' || view.clientStatus eq '1' ? 'checked="checked"' : '' }  value="1" />사용</label>
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '2' ? 'checked="checked"' : '' }  value="2" />정지</label>
												 	<!-- 
													<label><input type="radio" name="clientStatus" ${ view.clientStatus eq '3' ? 'checked="checked"' : '' }  value="3" />해지</label>
													-- -->
												 
												</div>
											

											</div>
											<!-- 
											<div class="col-md-6">
												<label for="userLogReason">정지/해지사유</label>
												<div class="form-group">
													<input type="text" class="form-control" name="userLogReason" id="userLogReason" value="${view.userLogReason }" placeholder="정지/해지사유">
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
						<div class="panel-heading">서비스담당자 추가정보</div>
							<div class="panel-body">
							
							<div class="row">
<%-- 								<div class="col-md-6">
										<div class="form-group">
										<label for="serviceName1">1. 이름</label>
										<input type="text"  class="form-control" id="serviceName1" name="serviceName1" value="${view.serviceName1}" >
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceTel1">1. 휴대폰번호(연락처)</label>
										<input type="text"  class="form-control" id="serviceTel1" name="serviceTel1" value="${view.serviceTel1}">
									</div>
								</div> --%>
								
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceName2">1. 이름</label>
										<input type="text" class="form-control" id="serviceName2" name="serviceName2" value="${view.serviceName2}">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label for="serviceTel2">1. 휴대폰번호(연락처)</label>
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
						<div class="panel-heading">요금 납부 정보</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">

									<div class="col-md-4">
										<div class="form-group">
											<label>납부 방법</label>
											<select class="form-control" name="billMethod" id="billMethod">
												<option value="GIRO" ${ billing.billMethod eq 'GIRO' ? 'selected="selected"' : '' }>지로출금</option>
												<option value="AUTO" ${ billing.billMethod eq 'AUTO' ? 'selected="selected"' : '' }>계좌이체</option>
											  	<option value="CARD" ${ billing.billMethod eq 'CARD' ? 'selected="selected""' : '' }>카드결제</option>
											</select>
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankName">은행/카드사명</label>
											<input type="text" class="form-control" name="bankName" id="bankName" value="${billing.bankName }" placeholder="은행/카드사명">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankNo">계좌/카드번호</label>
											<input type="text" class="form-control" name="bankNo" id="bankNo" value="${billing.bankNo }" placeholder="계좌/카드번호">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankOwner">예금/소유주</label>
											<input type="text" class="form-control" name="bankOwner" id="bankOwner" value="${billing.bankOwner }" placeholder="예금/소유주">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="bankRegNo">고유번호</label>
											<input type="text" class="form-control"  name="bankRegNo" id="bankRegNo" value="${billing.bankRegNo }" placeholder="주민/사업자번호">
										</div>
									</div>

									<div class="col-md-4">
										<div class="form-group">
											<label for="payerNo">납부번호</label>
											<input type="text" class="form-control" name="payerNo" id="payerNo" value="${billing.payerNo }" placeholder="납부번호">
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
							요금 담당자 부서 <input type="checkbox" id="clientInfoCopy">사용자 정보와 동일
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="form-group">
											<label for="payerName">납부 담당자</label>
											<input type="text" class="form-control" name="serviceName1" id="serviceName1" value="${view.serviceName1}" placeholder="납부 담당자">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="payerOrg">납부담당 부서</label>
											<input type="text" class="form-control" name="payerOrg" id="payerOrg" value="${billing.payerOrg }" placeholder="납부담당 부서">
										</div>
									</div>

									<div class="col-md-6">
										<div class="form-group">
											<label for="payerTel">전화번호</label>
											<input type="text" class="form-control" name="payerTel" id="payerTel" value="${billing.payerTel }" placeholder="전화번호">
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
											<label for="payerPost">담당자휴대폰번호</label>
											<input type="text" class="form-control" name="serviceTel1" id="serviceTel1" value="${view.serviceTel1}"  placeholder="요금납부담당자 휴대폰번호">
										</div>
									</div>
									
									<div class="col-md-6">
										<div class="form-group">
											<label for="payerPost">담당자Email</label>
											<input type="text" class="form-control" name="payerMail" id="payerMail" value="${billing.payerMail }"  placeholder="요금납부담당자 Email">
										</div>
									</div>										
									<div class="col-md-12">
										<div class="row">
											<div class="col-md-4">
												<div class="form-group">
													<label for="payerPost">주소</label>
													<input type="text" class="form-control" name="payerPost" id="payerPost" value="${billing.payerPost }"  placeholder="우편번호">
												</div>
											</div>
											
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group"><label for="payerAddr1"></label>
													<input type="text" class="form-control" name="payerAddr1" id="payerAddr1" value="${billing.payerAddr1 }"  placeholder="주소">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group"><label for="payerAddr2"></label>
													<input type="text" class="form-control" name="payerAddr2" id="payerAddr2" value="${billing.payerAddr2 }"  placeholder="상세주소">
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
							앱메시지 채널 정보 
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="kakaoChannelId">카카오 채널 ID</label>
										<input type="text" class="form-control" name="kakaoChannelId" id="kakaoChannelId" value="${senderKeyView.kakaoChannelId }" placeholder="카카오 채널 ID">
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group">
										<label for="kakaoKey">카카오 프로필 KEY</label>
										<input type="text" class="form-control" name="kakaoKey" id="kakaoKey" value="${senderKeyView.kakaoKey }" placeholder="카카오 프로필 KEY">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-4">
									<div class="form-group">
										<label for="naverChannelId">네이버 채널 ID</label>
										<input type="text" class="form-control" name="naverChannelId" id="naverChannelId" value="${senderKeyView.naverChannelId }" placeholder="네이버 채널 ID">
									</div>
								</div>
								<div class="col-md-8">
									<div class="form-group">
										<label for="naverKey">네이버 프로필 KEY</label>
										<input type="text" class="form-control" name="naverKey" id="naverKey" value="${senderKeyView.naverKey }" placeholder="네이버 프로필 KEY">
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
								<label for="statusMemo">메모(정지/해지사유)</label> 
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
						<button  type="button" class="btn btn-primary pull-right" id="btnExcelUpLoad">엑셀 업로드</button>
					  </c:if>*/%>

					<button type="button" class="btn btn-primary pull-right" id="btnList">목록</button>

					  <c:if test="${view == null && sessionScope.w eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnSave">저장</button>
						
					  </c:if>

					  <c:if test="${view != null && deleteYN != null && deleteYN eq '1' && sessionScope.d eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnDelete">삭제</button>
					  </c:if>

					  <c:if test="${view != null && sessionScope.w eq '1'}">
						<button type="button" class="btn btn-primary pull-right" id="btnUpdate">수정</button>
						<input type="button" class="btn btn-primary pull-right" onclick="change_status('${view.clientId }','${view.clientLevel }')" value="변경이력">
						<button type="button" class="btn btn-primary pull-right" id="delStatus">해지처리</button>
					  </c:if>

					   <c:if test="${view != null }">
					   <c:if test="${view.useWeb eq 'Y'}">
						<button type="button" class="btn btn-primary pull-right" id="btnAddWeb">웹발송계정생성</button>
						
						</c:if>
						<c:if test="${view.useMo eq 'Y'}">
						<button type="button" class="btn btn-primary pull-right" id="btnAddMo">MO계정생성</button>
						</c:if>						
						<button type="button" class="btn btn-primary pull-right" id="btnAddSystem">SYSTEM계정생성</button>
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
			alert("해지처리시 메모를 입력해주세요.");
			return false;
		}
		
		if(serviceStatus == 'REPT'){
			alert("신규청약건은 해지처리할수 없습니다.");
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
			alert("아이디는 4 ~ 16 자리로 입력해주세요");
			return false;
		}
		
		if($("#clientId").val() == null || $("#clientId").val() == "") {
			alert("아이디를 입력하세요");
			return false;
		}
		var data = 'clientId='+$("#clientId").val()+'&isAdmin=0';
		var url = '<%=cp%>/common/accountCountCheck2.do';
		callAjax(url,data,f_ckeckId);
	}

	var f_ckeckId = function(data){
		if(data.count != null &&data.count == "1"){
			$("#idCheck").val("");
			alert('이미 등록한 아이디입니다.');

		}else{
			$("#idCheck").val("1");
			alert('사용가능한 아이디입니다.');
		}
	}
	
	function change_status(clientId, type){
		
		window.open("<%=cp%>/admin/account/account_change_his.do?client_id="+clientId+"&type="+type , "변경이력", "width=800, height=600, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes,resozab;e=no,left=150, top=150");
		
	}

	function submitFunction(f){
		
		if($("#orgName").val() == null || $("#orgName").val() == ''){
			alert('기관명을 입력하세요.');
			return false;
		}
		  <c:if test="${ view.clientId == null}">
		if($("#idCheck").val() != 1){
			alert('아이디 체크하세요.');
			return false;
		}

 		if($("#clientPwd").val() == null || $("#clientPwd").val() == ''){
 			alert("비밀번호를 입력해주세요.");
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
 

 		if($("#orgDept").val() == null || $("#orgDept").val() == ''){
 			alert("주기관명을 입력해주세요.");
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



 		if($("#mngOrgCeo").val() == null || $("#mngOrgCeo").val() == ''){
 			alert("대표자명을 입력해주세요.");
 			return false;
 		}

 		if($("#uniqueNo").val() == null || $("#uniqueNo").val() == ''){
 			alert("고유번호를 입력해주세요.");
 			return false;
 		}
/*
 		if($("#clientCel").val() == null || $("#clientCel").val() == ''){
 			alert("핸드폰번호를 입력해주세요.");
 			return false;
 		}
*/
 		if($("#mngAddr1").val() == null || $("#mngAddr1").val() == ''){
 			alert("주소를 입력해주세요.");
 			return false;
 		}

 		if($("#mngAddr2").val() == null || $("#mngAddr2").val() == ''){
 			alert("상세주소를 입력해주세요.");
 			return false;
 		}

 		if($("#monthSendLimite").val() == null || $("#monthSendLimite").val() == ''){
 			alert("이용건수제한 입력란을 입력해주세요.");
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
 		if($("#serviceName1").val() == null || $("#serviceName1").val() == ''){
 			alert("요금담당자부서 납부담당자명을 입력해주세요.");
 			return false;
 		}

 		if($("#payerOrg").val() == null || $("#payerOrg").val() == ''){
 			alert("요금담당자부서 납부담당부서를 입력해주세요.");
 			return false;
 		}

 		if($("#payerTel").val() == null || $("#payerTel").val() == ''){
 			alert("요금담당자부서 전화번호를 입력해주세요.");
 			return false;
 		}

 		if($("#payerFax").val() == null || $("#payerFax").val() == ''){
 			alert("요금담당자부서 FAX 번호를 입력해주세요.");
 			return false;
 		}
		/*
 		if($("#payerPost").val() == null || $("#payerPost").val() == ''){
 			alert("요금담당자부서 주소(우편번호)를 입력해주세요.");
 			return false;
 		}
		*/
 		if($("#payerAddr1").val() == null || $("#payerAddr1").val() == ''){
 			alert("요금담당자부서 주소를 입력해주세요.");
 			return false;
 		}

 		if($("#payerAddr2").val() == null || $("#payerAddr2").val() == ''){
 			alert("요금담당자부서 상세주소를 입력해주세요.");
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