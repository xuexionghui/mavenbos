package cn.itcast.bos.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.bos.domain.PageRequestBean;
import cn.itcast.bos.domain.PageResponseBean;
import cn.itcast.bos.domain.bc.Subarea;
import cn.itcast.bos.service.subareaService;

public class subareaAction  extends ActionSupport implements ModelDriven<Subarea>{
   
	private Logger log=Logger.getLogger(subareaAction.class);
	//模型驱动 封装数据
	private Subarea subarea= new Subarea();
	public Subarea getModel() {
		return subarea;
	}
	
	//属性驱动，提供set方法
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// 封装PageRequestBean
	public PageRequestBean initPageRequestBean(DetachedCriteria detachedCriteria) {
		// 将分页查询参数 ，封装 PageRequestBean 对象中
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);

		pageRequestBean.setDetachedCriteria(detachedCriteria);
		return pageRequestBean;
	}
	
	//注入service层
	@Resource(name="subareaService")
	private subareaService   subareaServiceimpl;
	public  String save() {
		subareaServiceimpl.saveOrupdate(subarea);
		return "saveOrUpdateSuccess";
	}
	    /*
		 分区条件查询步骤
		  4、使用hibernate的 QBC的查询方式，拼装条件，进行查询
		      注意：多表查询  必须创建别名，否则会出现错误
	     */
	public  String   pageQuery() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		/*
		 * 使用QBC的方式进行多条件的查询     Query By Criteria   API提供了检索对象的另一种方式
		 *    单表的查询可以直接添加属性，如果是多表的查询，必须创建别名，进行表名关联，这是QBC的强制规定
		 */
		if(subarea.getAddresskey()!=null&&subarea.getAddresskey().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("addresskey", "%"+subarea.getAddresskey()+"%"));//添加关键词的查询
		}
		
		if (subarea.getDecidedZone()!=null&&subarea.getDecidedZone().getId()!=null&&subarea.getDecidedZone().getId().trim().length()>0) {
			detachedCriteria.add(Restrictions.eq("decidedZone", subarea.getDecidedZone()));  //比较id值，其实就是比较两个对象
		}
		
		//QBC的多表查询，必须先创建别名，进行表名的关联，不然查询不到结果
		if(subarea.getRegion()!=null) {
			 //创建别名关联
			detachedCriteria.createAlias("region", "r");    
			//进行省市区的判断
			if(subarea.getRegion().getProvince()!=null&&subarea.getRegion().getProvince().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.province","%"+subarea.getRegion().getProvince()+"%"));  
			}
			if(subarea.getRegion().getCity()!=null&&subarea.getRegion().getCity().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.city", "%"+subarea.getRegion().getCity()+"%"));
			}
			if (subarea.getRegion().getDistrict()!=null&&subarea.getRegion().getDistrict().trim().length()>0) {
			detachedCriteria.add(Restrictions.like("r.district","%"+subarea.getRegion().getDistrict()+"%" ));	
			}
		}
		PageRequestBean pageRequestBean = initPageRequestBean(detachedCriteria);
		PageResponseBean pageResponseBean=subareaServiceimpl.pageQuery(pageRequestBean);
		
		//将查询结果压入值栈中
		ActionContext.getContext().put("pageResponseBean", pageResponseBean);
		//将查询结果放入到session中
		ServletActionContext.getRequest().getSession().setAttribute("pageResponseBean", pageResponseBean);
		return "pageQuerySuccess";
	}
	/*
	 * 导出在条件查询的数据
	 *    原先数据已经保存在session中，现在将它从session中读取出来，编程一个Excel文件保存到硬盘中或者在浏览器打开 
	 *    使用struts默认的stream流结果集，完成文件的下载
	 *       步骤：1、在strutsx.xml文件的result 标签的 type属性配置 为  stream
	 *           2、需要提供一个流   getInputStream(默认的输入流，在后端服务器的subareaAction中 写这个方法)
	 *             两个头信息(在struts.xml文件的result标签的子标签 <param  name="contentType">指定的类型</param>
	 *                                                  <param  name="contentDisposition">浏览器窗口打开的类型;filename=文件名.xls</param>
	 */
	public String    export() {
		return "exportSuccess";
	}
	  
	/*
	 * 为stream 流结果集提供一个 输入流
	 */
	@SuppressWarnings("resource")
	public InputStream getInputStream() throws IOException {
	  //先从session中取出  PageResponseBean
	  PageResponseBean pageResponseBean= (PageResponseBean) ServletActionContext.getRequest().getSession().getAttribute("pageResponseBean");
	  List<Subarea> subareas = pageResponseBean.getRows();  //取出里面的数据
	  
	  
	  //创建一个Excel的工作薄
	  HSSFWorkbook hssfWorkbook = new  HSSFWorkbook();
	  HSSFSheet hssfSheet = hssfWorkbook.createSheet("条件查询的分区数据");
	  
	  //完成标题行
	  HSSFRow firstRow = hssfSheet.createRow(0);      //创建创建第一行
	  
	  firstRow.createCell(0).setCellValue("分区编号");
	  firstRow.createCell(1).setCellValue("关键字");
	  firstRow.createCell(2).setCellValue("起始号");
	  firstRow.createCell(3).setCellValue("结束号");
	  firstRow.createCell(4).setCellValue("是否区分单双号");
	  firstRow.createCell(5).setCellValue("位置信息");
	  
	  //遍历，向每行的单元格写入数据
	  int rownum=1;
	  for (Subarea subarea : subareas) {
	   HSSFRow eachRow = hssfSheet.createRow(rownum);   //将subarea的的一条数据放入到excel的一行中
	   eachRow.createCell(0).setCellValue(subarea.getId());
	   eachRow.createCell(1).setCellValue(subarea.getAddresskey());
	   eachRow.createCell(2).setCellValue(subarea.getStartnum());
	   eachRow.createCell(3).setCellValue(subarea.getEndnum());
	   eachRow.createCell(4).setCellValue(subarea.getSingle());
	   eachRow.createCell(5).setCellValue(subarea.getPosition());
	   rownum++;
	}
	  
	  //内存Excel对象，转换为输入流   ，通过ByteArrayOutputStream写入到内存缓冲区，再通过输入流读取数据，返回输入流
	   
	  ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	  hssfWorkbook.write(outputStream);   //将excel里面的数据写入到输出流
	  outputStream.close();   //输出完毕
	  byte[] byteArray = outputStream.toByteArray();    //将输出流转为array数组
	  ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
       return  inputStream;    //返回携带数据的输入流
	}

}
