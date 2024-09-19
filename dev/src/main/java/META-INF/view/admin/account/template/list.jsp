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
���ø��ڵ����
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
                    <div class="panel-heading">�˻�</div>
                    <div class="panel-body">
                        <form id="f_search"  action="<%=cp%>/admin/template/template.do" method="post">
                                                    
							<div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">���̵�</label>
                                    <input class="form-control" type="text" id="clientId" name="clientId" value="${ search.clientId }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                            	<div class="form-group">
                            		<label for="kakaoChannelId">ä��ID</label>
                            		<input class="form-control" type="text" id="kakaoChannelId" name="kakaoChannelId" value="${ search.kakaoChannelId }">
                            	</div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">�����(ORG_NAME)</label>
                                    <input class="form-control" type="text" id="orgName" name="orgName" value="${ search.orgName }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">�μ���(ORG_DEPT)</label>
                                    <input class="form-control" type="text" id="orgDept" name="orgDept" value="${ search.orgDept }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">�׷��(GROUP_NAME)</label>
                                    <input class="form-control" type="text" id="groupName" name="groupName" value="${ search.groupName }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="clientId">���ø� �ڵ�</label>
                                    <input class="form-control" type="text" id="templateCode" name="templateCode" value="${ search.templateCode }">
                                </div>
                            </div>
                            
                            <div class="col-md-12">
                                <div class="form-group search-box">
                                    <label>
                                    <input type="checkbox" name="statusArray" value="1" <c:if test="${fn:contains(search.statusString, '1')}">checked="checked"</c:if> />
                                        <span class="check_txt">���</span>
                                    </label>
                                    <label>
                                    <input type="checkbox" name="statusArray" value="2" <c:if test="${fn:contains(search.statusString, '2')}">checked="checked"</c:if> />
                                        <span class="check_txt">���� ��û</span> 
                                    </label>
                                     <label>
                                    <input type="checkbox" name="statusArray" value="3" <c:if test="${fn:contains(search.statusString, '3')}">checked="checked"</c:if> />
                                        <span class="check_txt">�ݷ�</span> 
                                    </label> 
                                     <label>
                                    <input type="checkbox" name="statusArray" value="4" <c:if test="${fn:contains(search.statusString, '4')}">checked="checked"</c:if> />
                                        <span class="check_txt">����</span> 
                                    </label> 
                                    <input type="checkbox" name="statusArray" value="5" <c:if test="${fn:contains(search.statusString, '5')}">checked="checked"</c:if> />
                                        <span class="check_txt">����</span> 
                                    </label> 
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group search-box">
                                    <label>
                                    <input type="radio" name="isActivated" value="" <c:if test="${search.isActivated == null || search.isActivated eq ''}">checked="checked"</c:if> />
                                        <span class="check_txt">��ü</span>
                                    </label>
                                    <label>
                                    <input type="radio" name="isActivated" value="Y" <c:if test="${search.isActivated eq 'Y'}">checked="checked"</c:if> />
                                        <span class="check_txt">���</span> 
                                    </label>
                                     <label>
                                    <input type="radio" name="isActivated" value="N" <c:if test="${search.isActivated eq 'N'}">checked="checked"</c:if> />
                                        <span class="check_txt">�̻��</span> 
                                    </label> 
                                </div>
                            </div>
                            <div class="col-md-12"><!-- īī�� key ���� �˻� ���� -->
                                <div class="form-group search-box">
                                īī�� KEY ���翩��
                                    <label>
                                    <input type="radio" name="kakaoKey" value="" <c:if test="${search.kakaoKey == null || search.kakaoKey eq ''}">checked="checked"</c:if> />
                                        <span class="check_txt">��ü</span> 
                                    </label>
                                    <input type="radio" name="kakaoKey" value="Y" <c:if test="${search.kakaoKey eq 'Y'}">checked="checked"</c:if> />
                                        <span class="check_txt">����</span> 
                                    </label>
                                     <label>
                                    <input type="radio" name="kakaoKey" value="N" <c:if test="${search.kakaoKey eq 'N'}">checked="checked"</c:if> />
                                        <span class="check_txt">������</span> 
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
                        <div class="row">
                            <div class="col-md-12">
                                <br>
                                <p class="pull-right">
                                	<a class="btn btn-primary pull-right" id="excelUploadBtn" data-toggle="modal" data-target="#myModal">�������ε�</a>
                                    <form class="pull-right" id="bizmExcelDownload" 
                                          method="POST"
                                          action="<%=cp%>/admin/template/downloadBizmTemplate">
                                        <button class="btn btn-white">BIZM���ø��ٿ�</button>
                                    </form>
                                    <form class="pull-right" id="excelDownload" method="POST" onsubmit="return excelDownload2()">
                                        <input type="hidden" id="seqList" name="seq" >
                                        <button class="btn btn-white">���� EXCEL �ٿ�</button>
                                    </form>
                                    <button type="button" class="btn btn-white" onclick="seletedReject2()">���ùݷ�</button> 
                                    <button type="button" class="btn btn-white" onclick="seletedComplete()">���ý���</button>
                                    <button type="button" class="btn btn-white" onclick="seletedApply()">���ý�û</button>
                                    <button type="button" class="btn btn-white" onclick="seletedDelete()">���û���</button>
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
                                <th scope="col">��ȣ</th>
                                <th scope="col">�����</th>
                                <th scope="col">��û��</th>
                                <th scope="col">���ø��ڵ�</th>
                                <th scope="col">����</th>
                                <th scope="col">���̵�</th>
                                <th scope="col">�Ӹ���</th>
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
                                            �����
                                            </c:when>
                                            <c:otherwise>
                                                <fmt:formatDate var="d" value="${ item.reportDate }" pattern="yyyy-MM-dd" />${ d }
                                            </c:otherwise>
                                        </c:choose>
                                        </td>
                                        <!-- <td><a href="<%=cp%>/admin/template/templateView.do?seq=${ item.seq }">${ item.templateCode }</a></td> -->
                                        <td><a href="${ viewUrl }"><c:out value="${ item.templateCode }"/></a></td>
                                        <td>${ item.status eq '1' ? '���' :
                                               item.status eq '2' ? '���� ��û' :
                                               item.status eq '3' ? '�ݷ�' :
                                               item.status eq '4' ? '����' :
                                               item.status eq '5' ? '����' :
                                               '-' }<br>
                                               ${ item.isActivated eq 'Y' ? '(���)' :
                                                  item.isActivated eq 'N' ? '(�̻��)' : '-' }</td>
                                        <td><c:out value="${ item.clientId }"/><br>(<c:out value="${ item.clientName }" />)</td>
                                        <td><pre><c:out value="${ item.header }"/></pre></td>
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
                alert('���õ� �׸��� �����ϴ�.');
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
                alert('���õ� �׸��� �����ϴ�.');
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
        	if(!confirm('�ݷ� �Ͻðڽ��ϱ�?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('���õ� �׸��� �����ϴ�.');
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
                alert('���õ� �׸��� �����ϴ�.');
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
                        alert('���� �ݷ� �Ϸ�');
                        location.reload();
                    } else {
                        alert('���� �ݷ� ����');
                    }
                }
            );
        }
        
        function seletedComplete() {
        	if(!confirm('���� �Ͻðڽ��ϱ�?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('���õ� �׸��� �����ϴ�.');
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
                        alert('���� ���� �Ϸ�')
                        location.reload();
                    } else {
                        alert('���� ���� ����')
                    }
                }
            );
        }
        
        function seletedApply() {
        	if(!confirm('��û �Ͻðڽ��ϱ�?')){
        		return;
        	}
        	
            let arr = [];
            $('input[name=selTem]:checked').each(function(index, item) {
                arr.push($(item).val());
            });
            
            if (arr.length == 0) {
                alert('���õ� �׸��� �����ϴ�.');
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
                        alert('���� ��û �Ϸ�')
                        location.reload();
                    } else {
                        alert('���� ��û ����')
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
                alert('���õ� �׸��� �����ϴ�.');
                return;
            }
            
            if(confirm("���ø��� ���� �Ͻðڽ��ϱ�?")){
                $.post(
                    '<%=cp%>/admin/template/templateDelete.do',
                    {
                        seq : arr.join(' ')
                    },
                    function(data) {
                        if (data.result) {
                            alert('���� ���� �Ϸ�')
                            location.reload();
                        } else {
                            alert('���� ���� ����')
                        }
                    }
                );
            }
        }
        
     	// msg ó��
		var msg = $("#msg").val();
		if (msg != undefined && msg.length >0){
			console.log(msg);
			console.log(decodeURI(msg));
		   alert(decodeURI(msg));
		}
    </script>
</rapid:override>

<%@ include file="/WEB-INF/view/site/admin_main.base.jsp"%>
