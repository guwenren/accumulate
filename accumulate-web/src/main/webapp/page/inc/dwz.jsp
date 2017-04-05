<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/static/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/static/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="/static/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="/static/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
<!--[if IE]>
<link href="/static/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->
<!--[if lte IE 9]>
<script src="/static/dwz/js/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="/static/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="/static/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="/static/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="/static/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="/static/dwz/xheditor/xheditor-1.2.1.min.js" type="text/javascript"></script>
<script src="/static/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="/static/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<!-- svg图表  supports Firefox 3.0+, Safari 3.0+, Chrome 5.0+, Opera 9.5+ and Internet Explorer 6.0+ -->
<script type="text/javascript" src="/static/dwz/chart/raphael.js"></script>
<script type="text/javascript" src="/static/dwz/chart/g.raphael.js"></script>
<script type="text/javascript" src="/static/dwz/chart/g.bar.js"></script>
<script type="text/javascript" src="/static/dwz/chart/g.line.js"></script>
<script type="text/javascript" src="/static/dwz/chart/g.pie.js"></script>
<script type="text/javascript" src="/static/dwz/chart/g.dot.js"></script>

<script src="/static/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="/static/dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="/static/dwz/bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="/static/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	DWZ.init("/static/dwz/dwz.frag.xml", {
		loginUrl:"login_loginPage.action", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"/static/dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>