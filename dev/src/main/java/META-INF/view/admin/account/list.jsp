<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String cp = request.getContextPath();
	request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
��������
</rapid:override>

<rapid:override name="style">
	
	<link href="<%=cp%>/static/css/jquery-ui.css" rel="stylesheet" type="text/css">
</rapid:override>

<rapid:override name="content">

	<div id="page-wrapper">
		<%@ include file="/WEB-INF/view/site/admin_breadCrumb.inc.jsp"%>
 
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-green">
					<div class="panel-heading">�˻�</div>
					<div class="panel-body">
						<form id="f_search"  action="<%=cp%>/admin/account/account.do" method="post">
							 
							<div class="col-md-12">
								<div class="form-group"> 
									<div class="row">									
										<div class="col-md-12">										 
											<label for="orderBy">����</label>  
										</div>
									</div>
									<div class="row">	
										<div class="col-md-6">  											
											<select class="form-control" name="orderBy" id="orderBy">
												<option ${search.orderBy eq 'REG_DATE' ? 'selected="selected"' : '' } value="REG_DATE">������</option> 
												<option ${search.orderBy eq 'CLIENT_ID' ? 'selected="selected"' : '' } value="CLIENT_ID">id</option>
												<option ${search.orderBy eq 'MASTER_ID' ? 'selected="selected"' : '' } value="MASTER_ID">��������</option> 
											</select> 
										</div>
								 
										<div class="col-md-6"> 
											<select class="form-control" name="orderBySc" id="orderBySc">
											<option ${search.orderBySc eq 'DESC' ? 'selected="selected"' : '' } value="DESC">��������</option>
												<option ${search.orderBySc eq 'ASC' ? 'selected="selected"' : '' } value="ASC">��������</option> 
																						 
											</select>
										</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<select class="form-control" name="searchKey" >
										<option value="clientId" <c:if test="${search.clientId 	!= null}">selected="selected"</c:if>>���̵�</option>
										<option value="clientName" <c:if test="${search.clientName != null}">selected="selected"</c:if>>�����</option>
										<option value="clientIdWithUnderId" <c:if test="${ search.searchKey eq 'clientIdWithUnderId' }">selected="selected"</c:if>>���̵�(������������)</option>
									</select> 
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group"><label for="searchVal"></label> 					 
									<input type="text" class="form-control" id="searchVal" name="searchVal" value="${search.searchVal}" placeholder="searchVal">
								</div>
							</div> 
							
							<div class="col-md-12">
								<div class="form-group">
									<label for="orgTitle">orgTitle</label> 								 
									<input type="text" class="form-control" id="orgTitle" name="orgTitle" value="${search.orgTitle}" placeholder="orgTitle">
								</div>
							</div> 
						
							<div class="col-md-12">
								<div class="form-group">
									<label for="clientLevel">����</label> 
									<select class="form-control" name="clientLevel" id="clientLevel">
										<option value="">����</option>
										<c:if test="${levelList != null }">
											<c:forEach var="level" items="${levelList}">
												<option value="${level.clientLevel}" 
												<c:if test="${search.clientLevel == level.clientLevel}">selected="selected"</c:if>>
													${level.levelName}
												</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="form-group">
									<label for="serviceStatus">û�����</label> 
									<select class="form-control" name="serviceStatus" id="serviceStatus">
										<option value="">����</option>		
										<option value="REPT" <c:if test="${search.serviceStatus	== 'REPT'}">selected="selected"</c:if>>REPT(�ű�û��)</option>
										<option value="MODI" <c:if test="${search.serviceStatus	== 'MODI'}">selected="selected"</c:if>>MODI(��������)</option>
										<option value="LGMO" <c:if test="${search.serviceStatus	== 'LGMO'}">selected="selected"</c:if>>LGMO(LG����)</option>
										<option value="LGRE" <c:if test="${search.serviceStatus	== 'LGRE'}">selected="selected"</c:if>>LGRE(û��Ϸ�)</option>
										<option value="LGCN" <c:if test="${search.serviceStatus	== 'LGCN'}">selected="selected"</c:if>>LGCN(LG����)</option>
										<option value="LGST" <c:if test="${search.serviceStatus	== 'LGST'}">selected="selected"</c:if>>LGST(LG����)</option>
										<option value="LGF1" <c:if test="${search.serviceStatus	== 'LGF1'}">selected="selected"</c:if>>LGF1(LG����)</option>
										<option value="LGF2" <c:if test="${search.serviceStatus	== 'LGF2'}">selected="selected"</c:if>>LGF2(��ݹ̳�)</option>
										<option value="STOP" <c:if test="${search.serviceStatus	== 'STOP'}">selected="selected"</c:if>>STOP(��������)</option>
										<option value="CANC" <c:if test="${search.serviceStatus	== 'CANC'}">selected="selected"</c:if>>CANC(������û)</option>
												 				 					 
									</select>
								</div>
							</div>
							
						
							<div class="col-md-12">
								<div class="form-group">
									<label for="search_date">�Ⱓ</label>
									<div class="calendar_select">
										<label for="start_date"> 
											<input class="form-control" type="text" id="startDate" name="startDate" value="${search.startDate}" title="������" />
										</label> ~ <label for="end_date"> 
											<input class="form-control" type="text" id="endDate" name="endDate" value="${search.endDate}" title="������" />
										</label>
									</div>
								</div>
							</div>
							<!-- 20161220 SMS/MMS => MT ������� -->
							<!-- 
							<div class="col-md-12">
								<div class="form-group search-box">
									<label>
									<input type="checkbox" name="useMt" id="useMt" value="Y" <c:if test="${search.useMt eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">SMS</span>
									</label>
									<label>
									<input type="checkbox" name="useMms" id="useMms" value="Y" <c:if test="${search.useMms eq 'Y'}">checked="checked"</c:if> /> 
										<span class="check_txt">MMS</span> 
									</label>
									<label>
									<input type="checkbox" name="useMo" id="useMo" value="Y" <c:if test="${search.useMo eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">MO</span> 
									</label>
									 <label>
									<input type="checkbox" name="useWeb" id="useWeb" value="Y" <c:if test="${search.useWeb eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">WEB</span> 
									</label> 
								</div>
							</div>
							 -->
							 
							 <div class="col-md-12">
								<div class="form-group search-box">
									<label>
									<input type="checkbox" name="useMt" id="useMt" value="Y" <c:if test="${search.useMt eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">MT</span>
									</label>
									<label>
									<input type="checkbox" name="useMo" id="useMo" value="Y" <c:if test="${search.useMo eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">MO</span> 
									</label>
									 <label>
									<input type="checkbox" name="useWeb" id="useWeb" value="Y" <c:if test="${search.useWeb eq 'Y'}">checked="checked"</c:if> />
										<span class="check_txt">WEB</span> 
									</label> 
								</div>
							</div>
							
							<div class="col-md-12">
								<button type="submit" class="btn btn-white" style="margin-left : 270px;">�˻�</button>
							</div> 
						</form>

					</div>

				</div>
			</div>

			<div class="col-md-8">

				<div class="panel panel-green">
                    <div class="panel-heading">����Ʈ </div>
					<div class="panel-body">

						<table class="table table-bordered">
							<tr>
								<th scope="col" style="text-align:center;">��ȣ</th>
								<th scope="col" style="text-align:center;">���̵�</th>
								<th scope="col" style="width:53px; text-align:center;">����</th>
								<th scope="col" style="width:53px; text-align:center;">����</th>
<!-- 								<th scope="col" style="width:53px; text-align:center;">�����</th> -->
								<th scope="col" style="text-align:center;">�����</th>
								<th scope="col" style="text-align:center;">���� �� �ý��۸�</th>
								<th scope="col" style="width:65px; text-align:center;">û�����</th>
								<th scope="col" style="width:65px; text-align:center;">��������</th>
								<th scope="col" style="width:91px; text-align:center;">�����<br>(�ֱټ���)</th>
							</tr> 

							<c:if test="${not empty lists}">
								<c:forEach var="item" items="${lists}">
									<tr>
										<td style="text-align:center;">${item.rowNum }</td>
										<td style="text-align:center;"><a href="<%=cp%>/admin/account/accountCreate.do?type=${
										 item.clientLevel ==10 ? "webClient" 
										: item.clientLevel==20 ? "systemClient" 
										: item.clientLevel==30 ? "moClient" 
										: item.clientLevel==50 ? "teamClient" 
										: item.clientLevel==60 ? "orgClient" 
										: item.clientLevel==70 ? "adminClient" 
										: item.clientLevel==80 ? "adminClient"
										: "adminClient"}&clientId=${item.clientId }
											&isAdmin=${item.isAdmin }">${item.clientId }</a></td>
										<td style="text-align:center;">${item.levelName }</td>	
										<!-- 
										<td>${item.useMt != null and item.useMt eq "Y" ? "SMS " : ''}
											${item.useMms != null and item.useMms eq "Y" ? "MMS " : ''}
											${item.useMo != null and item.useMo eq "Y" ? "MO " : ''}
											${item.useWeb != null and item.useWeb eq "Y" ? "WEB" : ''}
										</td>
										 -->
										 <td style="text-align:center;">${item.useMt != null and item.useMt eq "Y" ? "MT " : ''}
											${item.useMo != null and item.useMo eq "Y" ? "MO " : ''}
											${item.useWeb != null and item.useWeb eq "Y" ? "WEB" : ''}
											${item.useApp != null and item.useApp eq "Y" ? "APP" : ''}
										</td>
<%-- 										<td style="text-align:center;">${item.clientName }</td> --%>
										<td>${item.orgName }</td>
										<td>${item.orgTitle }</td>
										<td>${item.serviceStatus }</td>
										<td style="text-align:center;">${item.clientStatus eq '0' ? "�̻��" 
											: item.clientStatus eq '1' ? "���" 
											: item.clientStatus eq '2' ? "����" 
											: item.clientStatus eq '3' ? "����"  
											: "-"}</td>
										<td style="text-align:center;">${fn:substring(item.regDate,0,10) }<br>(${fn:substring(item.modifyDate,0,10) })</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty lists}">
								<tr>
									<td colspan="10">�����Ͱ� �����ϴ�.</td>
								</tr>
							</c:if>

						</table>
						<div class="box-footer clearfix text-center">
							<ul class="pagination pagination-sm no-margin">
								${javascriptPageIndexList}								
		                    </ul>
						</div>
						
						<div class="row">
							<div class="col-md-12">
								<br>
								<p class="pull-right">
									<a href="<%=cp%>/admin/account/accountCreate.do?type=adminClient" class="btn btn-white">�����ڰ������</a>
									<a href="<%=cp%>/admin/account/accountCreate.do?type=orgClient" class="btn btn-white">����������</a>
									<a href="<%=cp%>/admin/account/accountCreate.do?type=teamClient" class="btn btn-white">���������</a>  
									<a href="#" onclick="return window.open('<%=cp%>/admin/account/accountExcel.do','','');return false;"  class="btn btn-white">�������������ε�</a> 
								</p>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>
	</div>







</rapid:override>


<rapid:override name="javascript">
	<script type="text/javascript" src="<%=cp%>/static/js/common.js"></script>
	<script type="text/javascript" src="<%=cp%>/static/js/jquery/jquery-ui.js"></script>
	<script type="text/javascript">
	
		$(document).ready(
			function() {
				$("#startDate").datepicker(
					{
						dateFormat : "yymmdd",
						onClose : function(selectedDate) {
							$("#endDate").datepicker("option","minDate", selectedDate);
						}
				});
				$("#endDate").datepicker({
					dateFormat : "yymmdd",
					onClose : function(selectedDate) {
						$("#startDate").datepicker("option",
								"maxDate", selectedDate);
					}
				});
		});
		
		function listPage(pag){
			$('<input>').attr('type','hidden').attr('name','pageNum').attr('value',pag).appendTo('#f_search');		 
			$('#f_search').submit();	
		}
	</script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>
