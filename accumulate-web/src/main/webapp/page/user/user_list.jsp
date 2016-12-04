<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/inc/taglib.jsp"%>
<div class="pageHeader">
	<form id="pagerForm" onsubmit="return navTabSearch(this);" action="user/list" method="post">
	<!-- 分页表单参数 -->
    <%@include file="/page/inc/pageForm.jsp"%>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					手机号码：<input type="text" name="mobile" value="${query.mobile}" size="30" alt="精确查询"  />
				</td>
				<td>
					用户姓名：<input type="text" name="realname" value="${query.realname}" size="30" alt="模糊查询"  />
				</td>
				<td>状态：</td>
				<td>
					<select name="status" class="combox">
						<option value="">-全部-</option>
						<c:forEach items="${UserStatusEnumList}" var="userStatus">
						<option value="${userStatus.value}"
							<c:if test="${status ne null and status eq userStatus.value}">selected="selected"</c:if>>
							${userStatus.desc}
						</option>
					    </c:forEach>
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

	<div class="panelBar">
		<ul class="toolBar">
			<%--<rc:permission value="pms:user:add">--%>
				<li><a class="add" href="user/add" target="dialog" rel="input" title="添加用户"><span>添加用户</span></a></li>
			<%--</rc:permission>--%>
		</ul>
	</div>

	<table class="table" targetType="navTab" asc="asc" desc="desc" width="100%" layoutH="130">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th width="200">密码</th>
				<th>用户姓名</th>
				<th width="120">手机号码</th>
				<th width="300">操作</th><!-- 图标列不能居中 -->
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${model.recordList}" var="st">
			<tr target="sid_user" rel="${id}">
				    <td>${st.id}</td>
					<td>${st.password }</td>
					<td>${st.realname }</td>
					<td>${st.mobile }</td>

					<td>
						[<a href="user/view/${st.id}" title="查看【${st.realname }】详情" target="dialog" style="color:blue">查看</a>]
						<c:if test="${type eq UserTypeEnum.USER.value }">

						&nbsp;[<a href="user/edit/${st.id}" title="修改【${st.realname }】" target="dialog" rel="userUpdate" style="color:blue">修改</a>]

						<c:if test="${type eq UserTypeEnum.USER.value }">
						&nbsp;[<a href="pms_deleteUserStatus.action?id=${st.id}" target="ajaxTodo" title="确定要删除吗？" style="color:blue">删除</a>]
						</c:if>
						</c:if>
					</td>
				</tr>

		</c:forEach>
		</tbody>
	</table>
     <!-- 分页条 -->
    <%@include file="/page/inc/pageBar.jsp"%>
</div>