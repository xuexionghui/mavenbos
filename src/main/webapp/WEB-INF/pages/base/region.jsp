<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域设置</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">	
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<!-- 导入一键上传的js， ocupload的js文件 -->
<script type="text/javascript" 
src="${pageContext.request.contextPath }/js/ocupload/jquery.ocupload-1.1.2.js"></script>

<script type="text/javascript">
	function doAdd(){
		$('#addRegionWindow').window("open");
	}
	
	function doView(){
		alert("修改...");
	}
	
	function doDelete(){
		alert("删除...");
		//1、先从表格中取出要删除项的id值
		var selections=$('#grid').datagrid('getSelections');
		//2、校验没有id数据，弹窗提示，并且返回到原来的页面
		if(selections.length==0){
			$.messager.alert('警告','您没有选择要删除的项','warning');
			return ; //返回到当前的页面
		}
		//3、检验有无id数据，有，提交表单
	        $('#deleteForm').submit();
	}
	
	//工具栏
	var toolbar = [ {
		id : 'button-edit',	
		text : '修改',
		iconCls : 'icon-edit',
		handler : doView
	}, 
	/*  添加批量导入的按钮*/
	{
		id : 'button-import',
		text : '批量导入',
		iconCls : 'icon-save'
	},
	{
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '删除',
		iconCls : 'icon-cancel',
		handler : doDelete
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'province',
		title : '省',
		width : 120,
		align : 'center'
	}, {
		field : 'city',
		title : '市',
		width : 120,
		align : 'center'
	}, {
		field : 'district',
		title : '区',
		width : 120,
		align : 'center'
	}, {
		field : 'postcode',
		title : '邮编',
		width : 120,
		align : 'center'
	}, {
		field : 'shortcode',
		title : '简码',
		width : 120,
		align : 'center'
	}, {
		field : 'citycode',
		title : '城市编码',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 收派标准数据表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/region_pageQuery.action",  /* 查询表格的数据进行分页展示 */
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加、修改区域窗口
		$('#addRegionWindow').window({
	        title: '添加修改区域',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		//对批量导入添加一键上传的效果  仿照ajax的写法
		$('#button-import').upload({
		  name:'upload',  //上传的文件名字，相当于<input type="file" name="upload"/>
		  action:'${pageContext.request.contextPath}/region_importXls.action',
		  onComplete:function(response){   //回调的函数
			  //var data=eval("("+response+")");
			  var data = eval("("+response+")");  //将一个数据转成json格式的
			  $.messager.alert('信息',data.msg,'info');
				// 使datagrid 数据刷新
				$('#grid').datagrid('reload'); 
		  }
		});
		//保存或者修改区域信息添加点击事件
	    $('#save').click(function(){
	     //进行表单验证
	     if($('#regionForm').form('validate')){
	    	//提交表单
	    	$('#regionForm').submit();
	     }else{
	    	 //弹出提示信息
	    	$.messager.alert('警告','表单存在非法的数据','warning');
	     }
	    });
	});
    
	function doDblClickRow(rowIndex,rowData){
		//alert("双击表格数据...");
		//easyui 表单的回显
		$('#id').val(rowData.id);
		$('#province').val(rowData.province);
		$('#city').val(rowData.city);
		$('#district').val(rowData.district);
		$('#postcode').val(rowData.postcode);
		$('#shortcode').val(rowData.shortcode);
		$('#citycode').val(rowData.citycode);
		//打开窗口
		$('#addRegionWindow').window("open");
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<form id="deleteForm" action="${pageContext.request.contextPath }/region_deleteBatch.action" method="post">
		<div region="center" border="false">
	    	<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="区域添加修改" id="addRegionWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="regionForm" action="${pageContext.request.contextPath }/region_saveOrUpdate.action"  method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">区域信息</td>
						<input  id="id" name="id" type="hidden"/>
					</tr>
					<tr>
						<td>省</td>
						<td><input  id="province" type="text" name="province" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>市</td>
						<td><input id="city" type="text" name="city" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>区</td>
						<td><input  id="district" type="text" name="district" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>邮编</td>
						<td><input  id="postcode" type="text" name="postcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>简码</td>
						<td><input id="shortcode" type="text" name="shortcode" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>城市编码</td>
						<td><input id="citycode"  type="text" name="citycode" class="easyui-validatebox" required="true"/></td>
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>