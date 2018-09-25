<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 先引入 jquery的 js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 引入 easyui的js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入国际化 js -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<!-- 引入 默认样式 css -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css"/>
<!-- 引入 icon图标 css  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css"/>

<!-- 引入ztree  -->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css"/>
<script type="text/javascript">
	$(function(){ // 页面加载完毕后 执行js函数 
		// 设置树参数
		var setting = {};
		// 数据
		var zNodes = [
			{"name":"菜单一"}, // 每个{} 就是一个节点
			// 通过children 属性添加子节点
			{"name":"菜单二", "children": [
				{"name":"菜单11"},
				{"name":"菜单22"}
			]} 
		];
		// 初始化树
		$.fn.zTree.init($("#basicTree"), setting, zNodes);
		
		// 设置树参数 
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			}
		};
		
		// 数据
		var zNodes = [
		    // id 代表本节点编号，pId代表父节点编号 
			{"id":"3","pId":"1","name":"传智播客","url":"http://www.itcast.cn","icon":"${pageContext.request.contextPath}/js/ztree/img/diy/3.png"},
			{"id":"1","pId":"0","name":"菜单一"},
			{"id":"2","pId":"0","name":"菜单二"},
			{"id":"4","pId":"1","name":"菜单1-2"},
		];
		
		// 初始化树
		$.fn.zTree.init($("#simpleTree"), setting, zNodes);
	});
</script>
</head>
<body class="easyui-layout"> <!-- 使用layout布局 -->
	<!-- easyui 在 标签 data-options 配置 属性 -->
	<!-- 只有center区域 是必须的  -->
	<div data-options="region:'north',title:'北部面板'" style="height:100px;">北部</div>
	<div data-options="region:'south',title:'南部面板'" style="height:100px;">南部</div>
	<div data-options="region:'west',title:'西部面板'" style="width:200px;">
		<!-- 折叠面板  -->
		<!-- fit属性，使当前div大小占满父容器  -->
		<div class="easyui-accordion" data-options="fit:true">
			<!-- 通过iconCls 设置图标，找 icon.css中 类定义 -->
			<div data-options="title:'基本功能',iconCls:'icon-mini-add'">
				<!-- 显示树 (标准数据)-->
				<ul id="basicTree" class="ztree"></ul>
			</div> <!-- 这里每个div就是一个面板 -->
			<div data-options="title:'高级功能'">
				<!-- 显示树(简单数据 ) -->
				<ul id="simpleTree" class="ztree"></ul>
			</div>
			<div data-options="title:'管理员功能'">面板三</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 选项卡面板 -->
		<div class="easyui-tabs" data-options="fit:true">
			<div data-options="title:'选项卡一'">内容一</div> <!-- 这里每个div 就是一个选项卡 -->
			<!-- closeable 可关闭 -->
			<div data-options="title:'选项卡二',closable:true">内容二</div>
			<div data-options="title:'选项卡三'">内容三</div>
		</div>
	</div>
</body>
</html>