<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="notify/messageList" method="post">
		<!-- 分页表单参数 -->
		<%@include file="/page/inc/pageForm.jsp"%>
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>状态：
					<select name="status">
						<option value="">请选择</option>
						<option value="0"  <c:if test="${query.status == 0}"> selected="selected"</c:if>>待确认</option>
						<option value="1"  <c:if test="${query.status == 1}"> selected="selected"</c:if>>已确认</option>
					</select>
					</td>

					<td>消费队列：
						<select name="consumerQueue">status
						<option value="">请选择</option>
						<option value="MESSAGE_NOTIFY"  <c:if test="${query.consumerQueue eq 'MESSAGE_NOTIFY'}"> selected="selected"</c:if>>MESSAGE_NOTIFY</option>
					</select>
					</td>
					<td>是否死亡： <select name="areadlyDead">
						<option value="">请选择</option>
						<option value="0" <c:if test="${query.areadlyDead == 0}"> selected="selected"</c:if>>已死亡</option>
						<option value="1" <c:if test="${query.areadlyDead == 1}"> selected="selected"</c:if>>未死亡</option>
					</select>
					</td>
					<td>
						<div class="subBar">
							<ul>
								<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
		<tr>
			<th width="30">序号</th>
			<th width="130">创建时间</th>
			<th width="130">修改时间</th>
			<th width="250">消息ID</th>
			<th width="250">消费队列</th>
			<th width="50">状态</th>
			<th width="60">重发次数</th>
			<th width="60">是否死亡</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${model.recordList}" var="st">
		<tr target="sid_notify" rel="${id}">
			<td>${st.id}</td>
			<td><fmt:formatDate value="${st.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td><fmt:formatDate value="${st.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
			<td>${st.uuid}</td>
			<td>${st.consumerQueue}</td>
			<td>
				<c:if test="${st.status eq '0'}">待确认</c:if>
				<c:if test="${st.status eq '1'}">已确认</c:if>
			</td>
			<td>
			    ${st.messageSendTimes}
			</td>
			<td>
				<c:if test="${st.areadlyDead eq '0'}"><b style="color:red;">已死亡</b></c:if>
				<c:if test="${st.areadlyDead eq '1'}">未死亡</c:if>
			</td>
			<td>
				<c:if test="${st.areadlyDead eq '0'}">
					<a title="重新发送" target="ajaxTodo" href="${baseURL }/message/sendMessage?messageId=${st.uuid}" style="color: blue;" rel="dk"><b>重新发送</b></a>
				</c:if>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页条 -->
	<%@include file="/page/inc/pageBar.jsp"%>
</div>