<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/page/inc/taglib.jsp"%>
<style>
<!--
.pageFormContent fieldset label{
	width: 200px;
}
-->
</style>
<div class="pageContent">
	<form>
		<div class="pageFormContent" layoutH="60">
			
			<p style="width:99%">
				<label>主键：</label>
				<%--<s:textfield name="userName" readonly="true" size="30" />--%>
				<input name="id" readonly="readonly" size="30"  value="${m.id}">
			</p>
			<p style="width:99%">
				<label>登录密码：</label>
				<%--<s:textfield name="userNo" readonly="true" size="30" />--%>
				<input name="password" readonly="readonly" size="30"  value="${m.password}">
			</p>
			<p style="width:99%">
				<label>真实姓名：</label>
				<%--<fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
				<input name="realname" readonly="readonly" size="30"  value="${m.realname}">
			</p>
			<p style="width:99%">
				<label>手机号码：</label>
				<input name="mobile" readonly="readonly" size="30"  value="${m.mobile}">
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
</div>