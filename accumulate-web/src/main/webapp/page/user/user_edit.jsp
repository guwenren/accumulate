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
	<form id="form" method="post" action="user/edit" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="60">
		    <input type="hidden" name="navTabId" value="user_list">
			<input type="hidden" name="callbackType" value="closeCurrent">
			<input type="hidden" name="forwardUrl" value="">
			<input type="hidden" name="id" value="${model.id}">
			<p style="width:99%">
				<label>登录密码：</label>
				<input type="password"  name="password" accept="password" class="required" value="${model.password}" maxlength="45" size="30" />
			</p>
			<p style="width:99%">
				<label>真实姓名：</label>
				<input type="text" name="realname" accept="realname" class="required" value="${model.realname}" maxlength="30" size="30" />
			</p>
			<p style="width:99%">
				<label>手机号码：</label>
				<input type="text" name="mobile" accept="mobile" value="${model.mobile}"  maxlength="20" size="30" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="submitForm()">保存</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
	function submitForm() {
		$("#form").submit();
	}
	
</script>