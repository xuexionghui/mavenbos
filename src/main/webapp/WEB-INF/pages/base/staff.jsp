<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<script type="text/javascript">
	function doAdd(){
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}
	
	function doView(){
		alert("查看...");
	}
	
	function doDelete(){
		var arrays=$('#grid').datagrid('getSelections');
		if(arrays.length==0){
			$.messager.alert('警告','没有选中数据','warning');
		}else{
			$('#deleteForm').submit();
		}
	}
	
	function doRestore(){
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',	
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},{
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	}];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	},{
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			if(data=="0"){
				return "正常使用"
			}else{
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center',
		formatter : function(data,row, index){
			return data.name;
		}
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];
	
	$(function(){   //在页面jsp页面加载的时候就加载这个函数
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({visibility:"visible"});
		
		// 取派员信息表格
		$('#grid').datagrid( {
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList: [30,50,100],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staff_pageQuery.action",  /*数据的请求链接  */
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});
		
		// 添加取派员窗口
		$('#addStaffWindow').window({
	        title: '添加取派员',
	        width: 400,
	        modal: true,
	        shadow: true,
	        closed: true,
	        height: 400,
	        resizable:false
	    });
		
		//发送请求，获取取件标准的下拉列表
		/* $.post("${pageContext.request.contextPath}/standard_ajaxlist",
				function(data){  //数据的回调
			//jquery的方式遍历数据
			$(data).each(function(){
				var option=$("<option value='"+this.id+"'>"+this.name+"</option>");  
				$("#standardList").append(option);   //下拉选增加
			});
			
		   $("#standardList").combobox({});
			
		}); */
		//为保存或者修改取派员信息添加点击事件
		$('#save').click(function(){
			//进行表单的验证
			if($('#staffForm').form('validate')){   //就一个字的差别，千差万别 form和from的区别
				//检验通过，那么提交表单
				$('#staffForm').submit();
			}else{
				$.messager.alert('警告','表单存在非法数据',
						'warning');
			}
		});
		
	});
	
	

	function doDblClickRow(rowIndex, rowData){
		alert("双击表格数据...");
		//form表单的数据回显
		$('#id').val(rowData.id);
		$('#id').attr('reanonly','reanonly');  //只读
		$('#name').val(rowData.name);
		$('#telephone').val(rowData.telephone);
		$('#station').val(rowData.station);
		//勾选框的回显，用if进行判断
		if(rowData.haspda=="1"){
			$('#haspda').attr("checked","checked");  //如果值为1，那么就选中
		}else{
			$('#haspda').removeAttr("checked");
		}
		 //下拉选的easyui的回显
	    $('#standardId').combobox('setValue', rowData.standard.id); 
		$('#addStaffWindow').window('open');  //弹出窗口
	}
</script>	
</head>
<body class="easyui-layout" style="visibility:hidden;">
    <form  id="deleteForm"  action="${pageContext.request.contextPath }/staff_delBatch.action" method="post">
		<div region="center" border="false">
	    	<table id="grid"></table>
		</div>
	</form>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false" >
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true" >保存</a>
			</div>
		</div>
		
		<div region="center" style="overflow:auto;padding:5px;" border="false">
		     <!-- 提交表单，保存活着更新取派员信息 -->
			<form id="staffForm" action="${pageContext.request.contextPath }/staff_saveStaff.action" method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<tr>
						<td>取派员编号</td>   <!-- id:一般是作为css 、div的调用时使用的，而name:是与服务器端接收数据的时候使用的 -->
						<td><input type="text" id="id" name="id" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>姓名</td>
						<td><input type="text" id="name" name="name" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text"   id="telephone" name="telephone" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text"  id="station" name="station" class="easyui-validatebox" required="true"/></td>
					</tr>
					<tr>
						<td colspan="2">
						<input type="checkbox"  id="haspda" name="haspda" value="1" />
						是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td>
						    <!--第一种方式： 使用s:select的方式 ，列举收派标准-->
						   <!--  <select id="standardList">    使用<select></select>标签作为下拉选的标签
						    </select>  --> 
							
						   <!--第二种方式：使用easy-ui 的方式列举收派标准 -->
						   <!--  standard.id的属性意义：封装model中standard属性的id值
						         model提供setSatndard方法
						         standard类提供setId方法
						   -->                                                 <!-- 必须提供这个属性 -->
						<input class="easyui-combobox"  id="standardId" name="standard.id"             
						data-options="url:'${pageContext.request.contextPath}/standard_ajaxlist.action',valueField:'id',textField:'name',
						required:true" />  <!-- 取派员标准的属性 -->                                          <!--存的是id -->   <!--显示的是名字 -->
						</td> 
					</tr>
					</table>
			</form>
		</div>
	</div>
</body>
</html>	