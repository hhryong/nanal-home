<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
    String cp = request.getContextPath();
    request.setCharacterEncoding("euc-kr");
%>

<rapid:override name="title">
템플릿코드관리
</rapid:override>

<rapid:override name="style">
    <link href="<%=cp%>/static/css/jquery-ui.css" rel="stylesheet" type="text/css">
</rapid:override>

<rapid:override name="content">

    <div id="page-wrapper">
        <%@ include file="/WEB-INF/view/site/admin_breadCrumb.inc.jsp"%>
		<input type="hidden" id="msg" value="${msg != null ? msg : param.msg}" >
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-green">
                    <div class="panel-heading">검색</div>
                    <div class="panel-body">
                        <form id="f_search"  action="<%=cp%>/admin/template/template.do" method="post">
                                                    
							<div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">아이디</label>
                                    <input class="form-control" type="text" id="clientId" name="clientId" value="${ search.clientId }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                            	<div class="form-group">
                            		<label for="kakaoChannelId">채널ID</label>
                            		<input class="form-control" type="text" id="kakaoChannelId" name="kakaoChannelId" value="${ search.kakaoChannelId }">
                            	</div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">기관명(ORG_NAME)</label>
                                    <input class="form-control" type="text" id="orgName" name="orgName" value="${ search.orgName }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">부서명(ORG_DEPT)</label>
                                    <input class="form-control" type="text" id="orgDept" name="orgDept" value="${ search.orgDept }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">그룹명(GROUP_NAME)</label>
                                    <input class="form-control" type="text" id="groupName" name="groupName" value="${ search.groupName }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">템플릿 코드</label>
                                    <input class="form-control" type="text" id="templateCode" name="templateCode" value="${ search.templateCode }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group search-box">
                                    <label>
                                    <input type="checkbox" name="statusArray" value="1" <c:if test="${fn:contains(search.statusString, '1')}">checked="checked"</c:if> />
                                        <span class="check_txt">등록</span>
                                    </label>
                                    <label>
                                    <input type="checkbox" name="statusArray" value="2" <c:if test="${fn:contains(search.statusString, '2')}">checked="checked"</c:if> />
                                        <span class="check_txt">승인 신청</span> 
                                    </label>
                                     <label>
                                    <input type="checkbox" name="statusArray" value="3" <c:if test="${fn:contains(search.statusString, '3')}">checked="checked"</c:if> />
                                        <span class="check_txt">반려</span> 
                                    </label> 
                                     <label>
                                    <input type="checkbox" name="statusArray" value="4" <c:if test="${fn:contains(search.statusString, '4')}">checked="checked"</c:if> />
                                        <span class="check_txt">승인</span> 
                                    </label> 
                                    <input type="checkbox" name="statusArray" value="5" <c:if test="${fn:contains(search.statusString, '5')}">checked="checked"</c:if> />
                                        <span class="check_txt">삭제</span> 
                                    </label> 
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group search-box">
                                    <label>
                                    <input type="radio" name="isActivated" value="" <c:if test="${search.isActivated == null || search.isActivated eq ''}">checked="checked"</c:if> />
                                        <span class="check_txt">전체</span>
                                    </label>
                                    <label>
                                    <input type="radio" name="isActivated" value="Y" <c:if test="${search.isActivated eq 'Y'}">checked="checked"</c:if> />
                                        <span class="check_txt">사용</span> 
                                    </label>
                                     <label>
                                    <input type="radio" name="isActivated" value="N" <c:if test="${search.isActivated eq 'N'}">checked="checked"</c:if> />
                                        <span class="check_txt">미사용</span> 
                                    </label> 
                                </div>
                            </div>
                            <div class="col-md-12"><!-- 카카오 key 여부 검색 조건 -->
                                <div class="form-group search-box">
                                카카오 KEY 존재여부
                                    <label>
                                    <input type="radio" name="kakaoKey" value="" <c:if test="${search.kakaoKey == null || search.kakaoKey eq ''}">checked="checked"</c:if> />
                                        <span class="check_txt">전체</span> 
                                    </label>
                                    <input type="radio" name="kakaoKey" value="Y" <c:if test="${search.kakaoKey eq 'Y'}">checked="checked"</c:if> />
                                        <span class="check_txt">존재</span> 
                                    </label>
                                     <label>
                                    <input type="radio" name="kakaoKey" value="N" <c:if test="${search.kakaoKey eq 'N'}">checked="checked"</c:if> />
                                        <span class="check_txt">미존재</span> 
                                    </label> 
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-white" style="margin-left : 270px;">검색</button>
                            </div> 
                        </form>

                    </div>

                </div>
            </div>

            <div class="col-md-8">

                <div class="panel panel-green">
                    <div class="panel-heading">리스트 </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <br>
                                <p class="pull-right">
                                	<a class="btn btn-primary pull-right" id="excelUploadBtn" data-toggle="modal" data-target="#myModal">엑셀업로드</a>
                                    <form class="pull-right" id="bizmExcelDownload" 
                                          method="POST"
                                          action="<%=cp%>/admin/template/downloadBizmTemplate">
                                        <button class="btn btn-white">BIZM템플릿다운</button>
                                    </form>
                                    <form class="pull-right" id="excelDownload" method="POST" onsubmit="return excelDownload2()">
                                        <input type="hidden" id="seqList" name="seq" >
                                        <button class="btn btn-white">선택 EXCEL 다운</button>
                                    </form>
                                    <button type="button" class="btn btn-white" onclick="seletedReject2()">선택반려</button> 
                                    <button type="button" class="btn btn-white" onclick="seletedComplete()">선택승인</button>
                                    <button type="button" class="btn btn-white" onclick="seletedApply()">선택신청</button>
                                    <button type="button" class="btn btn-white" onclick="seletedDelete()">선택삭제</button>
                                </p>
                            </div>
                        </div>

                        <table class="table table-bordered">
                            <colgroup>
                                <col style="width: 3%;">
                                <col style="width: 6%;">
                                <col style="width: 11%;">
                                <col style="">
                                <col style="">
                                <col style="">
                                <col style="">
                                <col style="">
                                <col style="">
                            </colgroup>
                            <tr>
                                <th scope="col"><input type="checkbox" id="allCheck" value="1" /></th>
                                <th scope="col">번호</th>
                                <th scope="col">등록일</th>
                                <th scope="col">신청일</th>
                                <th scope="col">템플릿코드</th>
                                <th scope="col">상태</th>
                                <th scope="col">아이디</th>
                                <th scope="col">머릿말</th>
                            </tr> 

                            <c:if test="${not empty lists}">
                                <c:forEach var="item" items="${lists}">
                                	<c:url value="/admin/template/templateView.do" var="viewUrl">
                                		<c:param name="seq" value="${item.seq }" />
                                		<c:param name="clientId" value="${ search.clientId }" />
                                		<c:param name="orgName" value="${ search.orgName }" />
                                		<c:param name="orgDept" value="${ search.orgDept }" />
                                		<c:param name="groupName" value="${ search.groupName }" />
                                		<c:param name="templateCode" value="${ search.templateCode }" />
                                		<c:forEach var="status" items="${ search.statusArray }">
                                			<c:param name="statusArray" value="${ status }" />
                                		</c:forEach>
                                	</c:url>
                                    <tr>
                                        <td><input type="checkbox" name="selTem" class="selectCheck" value="${ item.seq }" /></td>
                                        <td style="text-align: center;">${ item.rowNum }</td>
                                        <td style="text-align: center;"><fmt:formatDate var="d" value="${ item.regiDate }" pattern="yyyy-MM-dd" />${ d }</td>
                                        <td style="text-align: center;">
                                        <c:choose>
                                            <c:when test="${empty item.reportDate || item.reportDate eq 'null'}">
                                            대기중
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate var="d" value="${ item.reportDate }" pattern="yyyy-MM-dd" />${ d }
                                            </c:otherwise>
                                        </c:choose>
                                        </td>
                                        <!-- <td><a href="<%=cp%>/admin/template/templateView.do?seq=${ item.seq }">${ item.templateCode }</a></td> -->
                                        <td><a href="${ viewUrl }"><c:out value="${ item.templateCode }"/></a></td>
                                        <td>${ item.status eq '1' ? '등록' :
                                               item.status eq '2' ? '승인 신청' :
                                               item.status eq '3' ? '반려' :
                                               item.status eq '4' ? '승인' :
                                               item.status eq '5' ? '삭제' :
                                               '-' }<br>
                                               ${ item.isActivated eq 'Y' ? '(사용)' :
                                                  item.isActivated eq 'N' ? '(미사용)' : '-' }</td>
                                        <td><c:out value="${ item.clientId }"/><br>(<c:out value="${ item.clientName }" />)</td>
                                        <td><pre><c:out value="${ item.header }"/></pre></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty lists}">
                                <tr>
                                    <td colspan="10">데이터가 없습니다.</td>
                                </tr>
                            </c:if>
                        </table>
                        <div class="box-footer clearfix text-center">
                            <ul class="pagination pagination-sm no-margin">
                                ${javascriptPageIndexList}                              
                            </ul>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>

<rapid:override name="popupinc">
	<section id="popup"> 
		<%@ include file="/WEB-INF/view/site/template_excel_upload.inc.jsp"%>
	</section>
</rapid:override>


<rapid:override name="javascript">
    <script type="text/javascript" src="<%=cp%>/static/js/common.js"></script>
    <script type="text/javascript" src="<%=cp%>/static/js/jquery/jquery-ui.js"></script>
    <script type="text/javascript">
        $('#allCheck').click(
            function(box) {
				$(".selectCheck").prop("checked", $(this).is(":checked"));
            }   
        );
        
        function excelDownload2() {
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return false;
            } 
            
            $('#seqList').val(arr.join(' '));
            console.log(arr.join(' '));
            $('#excelDownload').attr('action', "<%=cp%>/admin/template/templateDownload.do");
            return true;
        }
        
        
        function excelDownload() {
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            $('#seqList').val(arr.join(' '));
            $('#excelDownload').attr('action', "<%=cp%>/admin/template/templateDownload.do");
            $('#excelDownload').submit();
        }
        
        function listPage(pag){
            $('<input>').attr('type','hidden').attr('name','pageNum').attr('value',pag).appendTo('#f_search');       
            $('#f_search').submit();    
        }
        
        function seletedReject2() {
        	if(!confirm('반려 하시겠습니까?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            console.log(arr.join(' '));
            let win = window.open("", "rejectReasonInput", "width=800, height=600, toolbar=no, location=no, status=no, menubar=no, scrollbars=yes,resozab;e=no,left=150, top=150");
            
            var frmObj = $('<form>', {'id': 'fm_reject', 'action': '<%=cp%>/admin/template/templateRejectReasonList.do', 'target': 'rejectReasonInput', 'method': 'POST'});
            var inpObj = $('<input>', {'name': 'seq', 'value': arr.join(' ')});
            frmObj.append(inpObj);
            $(document.body).append(frmObj);
            $('#fm_reject').submit();
            $('#fm_reject').remove();
            
            let timer = setInterval(function() {
                console.log(win.closed);
                if (win.closed) {
                    clearInterval(timer);
                    location.reload();
                }
            }, 500);
        }
        
        function seletedReject() {
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            $.post(
                '<%=cp%>/admin/template/templateUpdateAll.do',
                {
                    seq : arr.join(' '),
                    status : '3'
                },
                function(data) {
                    if (data.result) {
                        alert('선택 반려 완료');
                        location.reload();
                    } else {
                        alert('선택 반려 실패');
                    }
                }
            );
        }
        
        function seletedComplete() {
        	if(!confirm('승인 하시겠습니까?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            $.post(
                '<%=cp%>/admin/template/templateUpdateAll.do',
                {
                    seq : arr.join(' '),
                    status : '4'
                },
                function(data) {
                    if (data.result) {
                        alert('선택 승인 완료')
                        location.reload();
                    } else {
                        alert('선택 승인 실패')
                    }
                }
            );
        }
        
        function seletedApply() {
        	if(!confirm('신청 하시겠습니까?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            $.post(
                '<%=cp%>/admin/template/templateUpdateAll.do',
                {
                    seq : arr.join(' '),
                    status : '3'
                },
                function(data) {
                    if (data.result) {
                        alert('선택 신청 완료')
                        location.reload();
                    } else {
                        alert('선택 신청 실패')
                    }
                }
            );
        }
        
        function seletedDelete() {
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('선택된 항목이 없습니다.');
                return;
            }
            
            if(confirm("템플릿을 삭제 하시겠습니까?")){
                $.post(
                    '<%=cp%>/admin/template/templateDelete.do',
                    {
                        seq : arr.join(' ')
                    },
                    function(data) {
                        if (data.result) {
                            alert('선택 삭제 완료')
                            location.reload();
                        } else {
                            alert('선택 삭제 실패')
                        }
                    }
                );
            }
        }
        
     	// msg 처리
		var msg = $("#msg").val();
		if (msg != undefined && msg.length >0){
			console.log(msg);
			console.log(decodeURI(msg));
		   alert(decodeURI(msg));
		}
    </script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>
