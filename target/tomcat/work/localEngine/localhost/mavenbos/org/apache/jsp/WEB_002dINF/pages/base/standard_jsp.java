package org.apache.jsp.WEB_002dINF.pages.base;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class standard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>收排标准</title>\r\n");
      out.write("<!-- 导入jquery核心类库 -->\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.8.3.js\"></script>\r\n");
      out.write("<!-- 导入easyui类库 -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/default/easyui.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/themes/icon.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/portal.css\">\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\"\r\n");
      out.write("\thref=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/css/default.css\">\t\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.portal.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\"\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/ext/jquery.cookie.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("\tsrc=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/js/easyui/locale/easyui-lang-zh_CN.js\"\r\n");
      out.write("\ttype=\"text/javascript\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\tfunction doAdd(){\r\n");
      out.write("\t\t// form回显\r\n");
      out.write("\t\t$('#name').val('');\r\n");
      out.write("\t\t$('#minweight').numberbox('setValue',null); \t\r\n");
      out.write("\t\t$('#maxweight').numberbox('setValue', null); \t\r\n");
      out.write("\t\t$('#id').val('') ;\r\n");
      out.write("\t\t$('#addStandardWindow').window(\"open\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doView(){\r\n");
      out.write("\t\talert(\"查看...\");\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doDelete(){\r\n");
      out.write("\t\t//alert(\"删除...\");\r\n");
      out.write("\t\t//判断有无选中 数据  deleteForm\r\n");
      out.write("\t\tvar  selections=$('#grid').datagrid('getSelections');   //会返回数组\r\n");
      out.write("\t\tif(selections.length==0){\r\n");
      out.write("\t\t\t$.messager.alert('警告','您没有选中数据','warning');\r\n");
      out.write("\t\t\treturn ;   //返回到原来的页面\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t$('#deleteForm').submit();  //提交\r\n");
      out.write("\t}\r\n");
      out.write("\t//工具栏\r\n");
      out.write("\tvar toolbar = [ {\r\n");
      out.write("\t\tid : 'button-view',\t\r\n");
      out.write("\t\ttext : '查询',\r\n");
      out.write("\t\ticonCls : 'icon-search',\r\n");
      out.write("\t\thandler : doView\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'button-add',\r\n");
      out.write("\t\ttext : '增加',\r\n");
      out.write("\t\ticonCls : 'icon-add',\r\n");
      out.write("\t\thandler : doAdd\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tid : 'button-delete',\r\n");
      out.write("\t\ttext : '删除',\r\n");
      out.write("\t\ticonCls : 'icon-cancel',\r\n");
      out.write("\t\thandler : doDelete\r\n");
      out.write("\t}];\r\n");
      out.write("\t// 定义列\r\n");
      out.write("\tvar columns = [ [ {\r\n");
      out.write("\t\tfield : 'id',\r\n");
      out.write("\t\tcheckbox : true,\r\n");
      out.write("\t},{\r\n");
      out.write("\t\tfield : 'name',\r\n");
      out.write("\t\ttitle : '标准名称',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center'\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'minweight',\r\n");
      out.write("\t\ttitle : '最小重量',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center'\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'maxweight',\r\n");
      out.write("\t\ttitle : '最大重量',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center'\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'user.username',\r\n");
      out.write("\t\ttitle : '操作人',\r\n");
      out.write("\t\twidth : 120,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tformatter : function(value,rowData, rowIndex){ \r\n");
      out.write("\t\t\t// value 表示匹配了当前属性的值\r\n");
      out.write("\t\t\t// rowData 代表整行数据 \r\n");
      out.write("\t\t\t// rowIndex 代表行号 \r\n");
      out.write("\t\t\tif(rowData.user!=null){\r\n");
      out.write("\t\t\t\treturn rowData.user.username;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'updatetime',\r\n");
      out.write("\t\ttitle : '操作时间',\r\n");
      out.write("\t\twidth : 160,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tformatter : function(value ,rowData, rowIndex){\r\n");
      out.write("\t\t\treturn value.replace(\"T\",\" \");\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}, {\r\n");
      out.write("\t\tfield : 'user.station',\r\n");
      out.write("\t\ttitle : '操作单位',\r\n");
      out.write("\t\twidth : 200,\r\n");
      out.write("\t\talign : 'center',\r\n");
      out.write("\t\tformatter : function(value,rowData, rowIndex){ \r\n");
      out.write("\t\t\t// value 表示匹配了当前属性的值\r\n");
      out.write("\t\t\t// rowData 代表整行数据 \r\n");
      out.write("\t\t\t// rowIndex 代表行号 \r\n");
      out.write("\t\t\tif(rowData.user!=null){\r\n");
      out.write("\t\t\t\treturn rowData.user.station;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t} ] ];\r\n");
      out.write("\t\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t// 先将body隐藏，再显示，不会出现页面刷新效果\r\n");
      out.write("\t\t$(\"body\").css({visibility:\"visible\"});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 收派标准数据表格\r\n");
      out.write("\t\t$('#grid').datagrid( {\r\n");
      out.write("\t\t\ticonCls : 'icon-forward',\r\n");
      out.write("\t\t\tfit : true,\r\n");
      out.write("\t\t\tborder : false,\r\n");
      out.write("\t\t\trownumbers : true,\r\n");
      out.write("\t\t\tstriped : true,\r\n");
      out.write("\t\t\tpageList: [10,3,5],\r\n");
      out.write("\t\t\tpagination : true,\r\n");
      out.write("\t\t\ttoolbar : toolbar,\r\n");
      out.write("\t\t\turl : \"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/standard_pageQuery.action\",  //这个地方获得数据\r\n");
      out.write("\t\t\tidField : 'id',\r\n");
      out.write("\t\t\tcolumns : columns,\r\n");
      out.write("\t\t\tonDblClickRow : doDblClickRow      //双击进入修改\r\n");
      out.write("\t\t});\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t// 添加收派标准窗口\r\n");
      out.write("\t\t$('#addStandardWindow').window({\r\n");
      out.write("            title: '添加收派标准',\r\n");
      out.write("            width: 400,\r\n");
      out.write("            modal: true,\r\n");
      out.write("            shadow: true,\r\n");
      out.write("            closed: true,\r\n");
      out.write("            height: 400,\r\n");
      out.write("            resizable:false\r\n");
      out.write("        });\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\tfunction doDblClickRow(rowIndex,rowData){   //利用dataGrid进行数据的回显\r\n");
      out.write("\t\t// form回显\r\n");
      out.write("\t\t$('#name').val(rowData.name);\r\n");
      out.write("\t\t$('#minweight').numberbox('setValue', rowData.minweight); \t\r\n");
      out.write("\t\t$('#maxweight').numberbox('setValue', rowData.maxweight); \t\r\n");
      out.write("\t\t$('#id').val(rowData.id) ;\r\n");
      out.write("\t    \r\n");
      out.write("\t\t// 弹出修改窗口\r\n");
      out.write("\t\t$('#addStandardWindow').window('open');\r\n");
      out.write("\t    \r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction commitstandardForm(){\r\n");
      out.write("\t\t//判断是否可以检验通过\r\n");
      out.write("\t\tif($('#standardForm').form('validate')){\r\n");
      out.write("\t\t\t//校验通过，允许调教\r\n");
      out.write("\t\t\t$('#standardForm').submit();   //提交\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\t$.messager.alert('警告','表单存在非法数据','warning');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\t\r\n");
      out.write("</script>\t\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"easyui-layout\" style=\"visibility:hidden;\">\r\n");
      out.write("    <form  id=\"deleteForm\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/standard_deleteBatch.action\" method=\"post\">\r\n");
      out.write("\t    <div region=\"center\" border=\"false\">\r\n");
      out.write("\t    \t<table id=\"grid\"></table>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div class=\"easyui-window\" title=\"添加收派标准\" id=\"addStandardWindow\" collapsible=\"false\" minimizable=\"false\" maximizable=\"false\" style=\"top:100px;left:200px\">\r\n");
      out.write("\t\t<div region=\"north\" style=\"height:31px;overflow:hidden;\" split=\"false\" border=\"false\" >\r\n");
      out.write("\t\t\t<div class=\"datagrid-toolbar\">\r\n");
      out.write("\t\t\t\t<a id=\"save\" icon=\"icon-save\" href=\"javascript:commitstandardForm();\" class=\"easyui-linkbutton\" plain=\"true\" >保存</a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div region=\"center\" style=\"overflow:auto;padding:5px;\" border=\"false\">\r\n");
      out.write("\t\t\t<form  id=\"standardForm\"  method=\"post\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("/standard_save.action\">\r\n");
      out.write("\t\t\t\t<table class=\"table-edit\" width=\"80%\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t<tr class=\"title\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\">收派标准信息\r\n");
      out.write("\t\t\t\t\t\t <input id=\"id\" name=\"id\" type=\"hidden\"/>   <!--  如果没有这个隐藏的id属性，那么所有的修改都会变成新增-->\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>标准名称</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"name\"       name=\"name\"       type=\"text\" class=\"easyui-validatebox\" required=\"true\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>最小重量</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"minweight\"  name=\"minweight\"  type=\"text\" class=\"easyui-numberbox\"  /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>最大重量</td>\r\n");
      out.write("\t\t\t\t\t\t<td><input id=\"maxweight\"  name=\"maxweight\"   type=\"text\" class=\"easyui-numberbox\" /></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
