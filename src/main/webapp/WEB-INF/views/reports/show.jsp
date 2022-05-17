<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actFav" value="${ForwardConst.ACT_FAV.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commDest" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>日報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td><%--改行 --%>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>出勤時間</th>
                    <fmt:parseDate value="${report.startTime}" pattern="HH:mm" var="start_time" type="time" />
                    <td><fmt:formatDate value="${start_time}" pattern="HH:mm" /></td>
                </tr>
                <tr>
                    <th>いいね数</th>

                    <td><c:out value="${fn:length(favorites)}" /></td>
                </tr>
                <tr>
                    <th>退勤時間</th>
                    <fmt:parseDate value="${report.endTime}" pattern="HH:mm" var="end_time" type="time" />
                    <td><fmt:formatDate value="${end_time}" pattern="HH:mm" /></td>
                </tr>
            </tbody>
        </table>


    <p></p>
        <h3>${report.title}へのいいね一覧</h3>

            <table>
                <tbody>
                    <tr>
                        <th>氏名</th>
                        <th>登録日時</th>
                    </tr>
                    <c:forEach var="favorite" items="${favorites}" varStatus="status">
                        <fmt:parseDate value="${favorite.createdAt}" pattern="yyyy-MM-dd" var="createdAt" type="date" />

                    <tr class="row${status.count % 2}">
                        <td class="report_name"><c:out value="${favorite.employee.name}" /></td>
                        <td class="created_at"><fmt:formatDate value='${createdAt}' pattern='yyyy-MM-dd' /></td>
                    </tr>
                     </c:forEach>
                </tbody>
            </table>

			<c:choose>
				<c:when test="${favorite_flag == false}">
		            <form method="POST" action="<c:url value='?action=${actFav}&command=${commCrt}' />">
			            <p>
			                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
			                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
			                <button type="submit" >いいね！</button>
			            </p>
		            </form>
	            </c:when>
	            <c:otherwise>
		            <form method="POST" action="<c:url value='?action=${actFav}&command=${commDest}' />">
			            <p>
			                <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
			                <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
			                <button type="submit" >いいね解除</button>
			            </p>
		            </form>

	            </c:otherwise>
			</c:choose>


        <c:if test="${sessionScope.login_employee.id == report.employee.id}">　<%--作成者のみ編集リンクを表示 --%>
            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>



        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>